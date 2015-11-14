package com.pictem.util;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import com.google.protobuf.DescriptorProtos.FileDescriptorSet;
import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.DynamicMessage.Builder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: fengguojing01
 * Date: 14-4-3
 * Time: 上午2:03
 */
public class JsonToProtobuf {
	private static Logger log = Logger.getLogger(JsonToProtobuf.class);
    public static JSONArray readJSON(String filename) throws FileNotFoundException,IOException {
        //FileInputStream fileInputStream = new FileInputStream(filename);
        File file = new File(filename);
        Long fileLong = file.length();
        byte[] fileContent = new byte[fileLong.intValue()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(fileContent);

        JSONArray jsonArray = JSONArray.fromObject(new String(fileContent));
        //System.out.println(jsonObject.toString(4));
        return jsonArray;
    }

    /**
     * Json 转 PB对象的入口方法。
     * @param jsonObject
     *          传入的json对象，不是jsonArray
     * @param fileDescriptor
     *          PB对象的描述
     * @param messageName
     *          PB对象的名称。Json对象中的key需要跟PB对象的名称保持一致
     * */
    public static Builder handleJsonToPB(Object jsonObject, FileDescriptor fileDescriptor, String messageName) throws Exception {
        Builder builder = null;
        log.info(jsonObject.toString());
        if (jsonObject == null){
        	log.error("jsonObject is null");
            throw new Exception("JSONObject is null!");
        }
        log.info("messageName:" + messageName);
        Descriptor descriptor = fileDescriptor.findMessageTypeByName(messageName);
        builder = jsonToPBDance(jsonObject,descriptor);
        log.info("jsonToPBDance end");
        return builder;
    }

    private static Object getFieldFromJson(String fieldName, Object o){
        if (o instanceof JSONObject){
            JSONObject jo = (JSONObject) o;
            if (jo.containsKey(fieldName)){
                return jo.get(fieldName);
            } else {
                return null;
            }
        }

        return null;
    }

    public static Builder jsonToPBDance(Object o, Descriptor descriptor) {
        Builder builder = DynamicMessage.newBuilder(descriptor);

        for (FieldDescriptor fieldDescriptor: descriptor.getFields()){
            Object thisObject = getFieldFromJson(fieldDescriptor.getName(), o);
            copyField(thisObject, fieldDescriptor, builder);
        }

        return builder;
    }

    /*
    * 拷贝Json对象中的属性到PB对象DynamicMessage的相应属性中。<br>
    * 只处理非嵌套Message的情况。
    * */
    private static void copyField(Object thisObject, FieldDescriptor fieldDescriptor, Builder parentBuilder) {
        if (thisObject == null){
            return;
        }
        Builder sonBuilder = null;
        //如果是子message对象，进入递归处理
        if (fieldDescriptor.getType().equals(Type.MESSAGE)){
            try {
                sonBuilder = jsonToPB(thisObject, fieldDescriptor, parentBuilder, fieldDescriptor.toProto().getLabel());
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (sonBuilder != null){
                if (!fieldDescriptor.isRepeated()){
                    parentBuilder.setField(fieldDescriptor, sonBuilder.build());
                }
            }
        } else if (fieldDescriptor.isRepeated()){
            //如果是普通类型的List，需要循环拷贝
            JSONArray joList = (JSONArray) thisObject;
            for (int i = 0; i < joList.size(); i++){
                //builder.addRepeatedField(fieldDescriptor, joList.get(i));
                addRepeatedFieldMapJavaType(parentBuilder, fieldDescriptor, joList.get(i));
            }
        } else {
            //直接拷贝属性值
            //builder.setField(fieldDescriptor, thisObject);
            setFieldMapJavaType(parentBuilder, fieldDescriptor, thisObject);
        }
    }

    /*
    * 设置Message中属性的值。内部含有Message的Type到javaType的转换
    * 如 int64 <-> Long 等
    * */
    private static void setFieldMapJavaType(Builder builder, FieldDescriptor fieldDescriptor, Object v){
        if (v == null) {
            return;
        }

        Type fieldType = fieldDescriptor.getType();
        if (fieldType.equals(Type.INT64)) {
            // int64类型的映射到java的long类型
            builder.setField(fieldDescriptor, Long.valueOf(String.valueOf(v)));
        } else if (fieldType.equals(Type.DOUBLE)) {
            //Double类型的映射到java的double类型
            builder.setField(fieldDescriptor, Double.valueOf(String.valueOf(v)));
        } else if (fieldType.equals(Type.BOOL)){
        	//Bool类型的映射到java的Boolean类型
        	builder.setField(fieldDescriptor, Boolean.parseBoolean(String.valueOf(v)));
        } else {
            builder.setField(fieldDescriptor, v);
        }
    }

    /*
    * 设置列表形式的Message中属性的值。内部含有Message的Type到javaType的转换
    * 如 int64 <-> Long 等
    * */
    private static void addRepeatedFieldMapJavaType(Builder builder, FieldDescriptor fieldDescriptor, Object v) {
        if (v == null) {
            return;
        }
    
        Type fieldType = fieldDescriptor.getType();
        if (fieldType.equals(Type.INT64)) {
            //int64类型的映射到java的long类型
            builder.addRepeatedField(fieldDescriptor, Long.valueOf(String.valueOf(v)));
        } else if (fieldType.equals(Type.DOUBLE)) {
            //Double类型的映射到java的double类型
            builder.addRepeatedField(fieldDescriptor, Double.valueOf(String.valueOf(v)));
        } else if (fieldType.equals(Type.BOOL)) {
        	//Bool类型的映射到java的Boolean类型
        	builder.addRepeatedField(fieldDescriptor, Boolean.parseBoolean(String.valueOf(v)));
        }else {
            builder.addRepeatedField(fieldDescriptor, v);
        }
    }
    /*
    * 递归方法，专门处理Message嵌套Message对象的方法。<br>
    * 非Message嵌套的情况，copyField方法实现
    * */
    private static Builder jsonToPB(Object thisObject, FieldDescriptor fieldDescriptor, Builder builder, Label label) throws Exception {
        if (!fieldDescriptor.getType().equals(Type.MESSAGE)) {
            throw new Exception(fieldDescriptor.getName() + "is not a Message");
        }
        if (thisObject == null) {
            return null;
        }
        Descriptor descriptor = fieldDescriptor.getMessageType();
        Builder retBuilder = DynamicMessage.newBuilder(descriptor);

        //List类型的Message
        if (label.equals(Label.LABEL_REPEATED)){
            if (thisObject instanceof JSONArray){
                JSONArray joList = (JSONArray) thisObject;
                for (int i = 0;i < joList.size();i++){
                    Builder tmp = jsonToPB(joList.get(i), fieldDescriptor, retBuilder, Label.LABEL_OPTIONAL);
                    builder.addRepeatedField(fieldDescriptor, tmp.build());
                }
            } else {
                throw new Exception("This JSON Is Not A Array!" + thisObject.toString());
            }
        } else {
            //非List类型的Message
            for (FieldDescriptor fd: descriptor.getFields()){
                Object son = getFieldFromJson(fd.getName(), thisObject);
                copyField(son, fd, retBuilder);
            }
        }

        return retBuilder;
    }

}
