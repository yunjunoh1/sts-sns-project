package com.cos.photogramstart.util;

public class Script {
	
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');"); // 경고창을 띄우고
		sb.append("history.back()"); // 뒤로 돌아감
		sb.append("</script>");
		return sb.toString();
	}

}
