package com.pictem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pictem.model.User;
import com.pictem.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	private UserServiceImpl usi;
	
	@RequestMapping("index")
    public ModelAndView index(){
		System.out.println("++++++++++++======================================");
        return new ModelAndView("index.html");
    }
	
	@RequestMapping("test")
	public String test(ModelMap modelMap){
		List<User> list = usi.findAllUser(); 
		modelMap.addAttribute("userDo",list);
		return "/test";
	}
}
