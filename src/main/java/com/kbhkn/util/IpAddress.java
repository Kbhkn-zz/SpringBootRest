package com.kbhkn.util;

public class IpAddress {
	private static String IPAddress = null;
	private static final String[] IPHeaderList = 
		{ 
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR"
		};

	public static String getClientRealIpAdress(javax.servlet.http.HttpServletRequest request) {
		for (String header : IPHeaderList) {
			IPAddress = request.getHeader(header);
			if (IPAddress != null && IPAddress.length() != 0 && !"unknown".equalsIgnoreCase(IPAddress)) {
				return IPAddress;
			}
		}
		return request.getRemoteAddr();
	}
}
