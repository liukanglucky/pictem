package com.pictem.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pictem.model.Tag;
import com.pictem.model.User;
import com.pictem.service.TagService;
import com.pictem.service.UserService;

public class UserTest {
	
	private UserService userService;
	private TagService tagService;
    
    @Before
    public void before(){                                                                    
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userServiceImpl");
        tagService = (TagService) context.getBean("tagServiceImpl");
    }
     
    @Test
    public void addUser(){
//        User user = new User();
//        user.setName("你好2");
//        System.out.println(userService.insertUser(user));
//        System.out.println(user.getId());
//        user.setName("admin");
//        System.out.println(userService.updateUser(user));
//        
//        userService.delUser(2);
    	
    	Tag tag = new Tag();
    	tag.setName("test");
    	System.out.println(tagService.insertTag(tag));
    }
	
	
}
