package com.simo.ugmate.network;

/**
 * @author JinPing.Lin
 * @since Mar 13, 2014
 * Login response info
 */
public class LoginResponseData extends SimoResponseData {
	public boolean success;
	public String message;
	
	//=====
	/*0成功  10000  密码错误  10001  用户已经注销  10002无权登录   10003 无效账户*/
	public int errorCode;
	public String skyroamId;
	public String email;
	public String phoneNumber;
}
