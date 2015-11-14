package com.pictem.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 创建人：liukang <br>
 * 功能描述：cookie工具类 <br>
 */
public abstract class CookieUtil {

	/**
	 * 获取cookies的对应值
	 * 
	 * @param request
	 * @param name
	 * @return String
	 */
	public static String getCookiesValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		String value = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
}