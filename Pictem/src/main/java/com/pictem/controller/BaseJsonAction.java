package com.pictem.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;


public class BaseJsonAction  {
	
	protected Object data;
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }
	
//	public BaseJsonAction(HttpServletRequest request, HttpServletResponse response) {
//		this.request = request;
//		this.response= response;
//	}
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.request=request;
		this.response=response;
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	doGet(request, response);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	protected void outPut(){
		String json = JSON.toJSONString(data);
		response.setContentType("text/javascript");
		response.setCharacterEncoding("utf-8");
		OutputStreamWriter pw = null;
		try{
			OutputStream os = response.getOutputStream();
			pw = new OutputStreamWriter(os);
			pw.write(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != pw){
				try {
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}


}
