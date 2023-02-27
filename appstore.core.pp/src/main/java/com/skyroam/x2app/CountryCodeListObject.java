package com.skyroam.x2app;

import java.util.ArrayList;


/**
 * BOOS登录结果类。
 * @author root 蔡火胜。
 *
 */
public class CountryCodeListObject 
{

	private ArrayList<CountryCodeObject> countryCodeList; //!<国家代码列表。
	public ArrayList<CountryCodeObject> getCountryCodeList() {
		return countryCodeList;
	}


	public void setCountryCodeList(ArrayList<CountryCodeObject> countryCodeList) {
		this.countryCodeList = countryCodeList;
	}

	private String email; //!<邮件地址。
	private String skyroamId; //!<Skyroam ID.
	private String phoneNumber; //!<电话号码。
	private int errorCode; //!<错误代码。
	private String msg; //!<错误消息内容。
	
	public String getMsg()
	{
		return msg;
	} //public String getMsg()
	
	
	public int getErrorCode()
	{
		return errorCode;
	} //public int getErrorCode()
	
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	} //public String getPhoneNumber()
	
	
	public String getSkyroamId()
	{
		return skyroamId;
	} //public String getSkyroamId()
	
	
	public void setEmail(String eml2Set)
	{
		email=eml2Set; //记录。
	} //public void setEmail(String eml2Set)
	
	public void setSkyroamId(String skrId2St)
	{
		skyroamId=skrId2St; //记录。
	} //public void setSkyroamId(String skrId2St)
	
	public void setPhoneNumber(String phnNmr2St)
	{
		phoneNumber=phnNmr2St; //记录。
	} //public void setPhoneNumber(String phnNmr2St)
	
	
	public void setErrorCode(int errCd2St)
	{
		errorCode=errCd2St; //记录。
	} //public void setErrorCode(int errCd2St)
	
	public void setMsg(String msg2St)
	{
		msg=msg2St; //记录。
	} //public void setMsg(String msg2St)
	
	public String getEmail()
	{
		return email;
	} //public String getEmail()
	
	
	
	

} //public class BossLoginResult
