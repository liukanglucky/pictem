package com.pictem.dao.mongo;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.pictem.model.User;

public class UserMg {
	private static String USER_COLLECTION = "user";  
	
    @Autowired  
    public MongoTemplate mongoTemplate;  
    
    public UserMg() {
		// TODO Auto-generated constructor stub
    	@SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
        		,"classpath:conf/spring-mybatis.xml"});
        this.mongoTemplate=(MongoTemplate) context.getBean("mongoTemplate");
	}
    /** 
     *  
     * @param user 
     */  
    public void saveUser(User user){  
           
        this.mongoTemplate.save(user, USER_COLLECTION);  
           
    }  
       
    /** 
     *  
     * @param name 
     * @return  
     */  
    public User findUserByName(String name){  
           
        return this.mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), User.class, USER_COLLECTION);  
           
    }  
    
    public void test() throws UnknownHostException{
    	Mongo mongo = new Mongo();
    	DB db = mongo.getDB("pictem");
    	DBCollection users = db.getCollection("users");
    	DBCursor cur = users.find();

    	while (cur.hasNext()) {
    	
    	System.out.println(cur.next());

    	}
    }
    
    public static void main(String[] args) throws UnknownHostException {
		UserMg um = new UserMg();
		User u = new User();
		System.out.println(um.mongoTemplate);
		u.setName("liukang");
		um.saveUser(u);
		//um.test();
	}
}
