package com.skyroam.x2app;

/**
 * BOOS登录结果类。
 * @author root 蔡火胜。
 *
 */
public class CountryCodeObject 
{

	private static final String TAG = "BossLoginResult"; //!<输出调试信息时使用的标记。
	private boolean success; //!<是否登录成功。
	private String countryCode; //!<国家代码，例如“CN”。
	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	private String skyroamId; //!<Skyroam ID.
	private String cc; //!<电话号码。
	private int errorCode; //!<错误代码。
	private String name; //!<国家名字，中文。
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMsg()
	{
		return name;
	} //public String getMsg()
	
	
	public int getErrorCode()
	{
		return errorCode;
	} //public int getErrorCode()
	
	
	public String getCc()
	{
		return cc;
	} //public String getPhoneNumber()
	
	
	public String getSkyroamId()
	{
		return skyroamId;
	} //public String getSkyroamId()
	
	
	public void setSuccess(boolean suc2Set)
	{
		success=suc2Set; //记录。
	} //public void setSuccess()
	

	public boolean getSuccess() 
	{
		

		return success;
	} //public boolean isSuccess()
	
	
	public void setEmail(String eml2Set)
	{
		countryCode=eml2Set; //记录。
	} //public void setEmail(String eml2Set)
	
	public void setSkyroamId(String skrId2St)
	{
		skyroamId=skrId2St; //记录。
	} //public void setSkyroamId(String skrId2St)
	
	public void setCc(String phnNmr2St)
	{
		cc=phnNmr2St; //记录。
	} //public void setPhoneNumber(String phnNmr2St)
	
	
	public void setErrorCode(int errCd2St)
	{
		errorCode=errCd2St; //记录。
	} //public void setErrorCode(int errCd2St)
	
	public void setMsg(String msg2St)
	{
		name=msg2St; //记录。
	} //public void setMsg(String msg2St)
	
	public String getEmail()
	{
		return countryCode;
	} //public String getEmail()
	
	
	
	

} //public class BossLoginResult
