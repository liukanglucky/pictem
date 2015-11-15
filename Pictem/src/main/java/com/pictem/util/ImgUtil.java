package com.pictem.util;

import java.io.*;  
import java.util.Date;  
import java.awt.*;  
import java.awt.image.*;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.*;
/**
 * 
 * @author liukang
 *
 * 功能：image工具类
 */
public class ImgUtil {
	private Image img;  
    private int width;  
    private int height;  
    private String sourceFile;
    private String sourceFileName;
    private String targetFile;
    @SuppressWarnings("deprecation")  
    public static void main(String[] args) throws Exception {  
        System.out.println("开始：" + new Date().toLocaleString());  
        ImgUtil imgCom = new ImgUtil("IMG_3023.jpg","/Users/songshaoying/Desktop/","/Users/songshaoying/Desktop/");  
        imgCom.resizeFix(400, 400,imgCom.sourceFileName);  
        System.out.println("结束：" + new Date().toLocaleString());  
    }  
    /** 
     * 构造函数 
     */  
    public ImgUtil(String fileName,String s,String t) throws IOException {
    	sourceFileName = fileName;
        File file = new File(s+fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
        targetFile = s;
    }  
    
    /** 
     * 按照宽度或高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h,String filename) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w, filename);  
        } else {  
            resizeByHeight(h, filename);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public String resizeByWidth(int w,String filename) throws IOException {  
        int h = (int) (height * w / width);  
        return resize(w, h,filename);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public String resizeByHeight(int h,String filename) throws IOException {  
        int w = (int) (width * h / height);  
        return resize(w, h,filename);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public String resize(int w, int h,String filename) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        String newFilename = targetFile+"c_"+filename;
        File destFile = new File(newFilename);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close(); 
        
        return newFilename;
    }  
}
