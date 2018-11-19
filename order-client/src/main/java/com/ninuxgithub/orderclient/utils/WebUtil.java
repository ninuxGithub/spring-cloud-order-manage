package com.ninuxgithub.orderclient.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	public static String getRemoteAddress(HttpServletRequest request) {
		String addr = request.getHeader("x-forwarded-for");

		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getHeader("Proxy-Client-IP");
		}

		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
			addr = request.getRemoteAddr();
		}

		return addr;
	}

}
