package com.pictem.test.mongo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pictem.dao.mongo.UserMg;
import com.pictem.model.User;

public class UserTest {
	
	private UserMg um;

	private MongoTemplate mongoTemplate;
    @Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
        		,"classpath:conf/spring-mybatis.xml"});
        mongoTemplate=(MongoTemplate) context.getBean("mongoTemplate");
    }
     
    @Test
    public void addUser(){
        User user = new User();
        user.setName("你好2");
        System.out.println(mongoTemplate);
        //System.out.println(um.mongoTemplate);
        //um.saveUser(user);
       
    }
	
	
}
