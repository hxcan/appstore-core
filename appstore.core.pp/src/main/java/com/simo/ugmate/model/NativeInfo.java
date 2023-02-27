package com.simo.ugmate.model;

public class NativeInfo {

	private static NativeInfo mNativeInfo = new NativeInfo();
	public static NativeInfo getInstance(){
		return mNativeInfo;
	}
	
	private NativeInfo() {
	}
	

	private String mCurrentConnectedMacAddr;
	private String mPhoneDeviceModel;
	private String  mPhoneDeviceUuid;
	
	public String getCurrentConnectedMacAddr() {
		return mCurrentConnectedMacAddr;
	}
	public void setCurrentConnectedMacAddr(String mCurrentConnectedMacAddr) {
		this.mCurrentConnectedMacAddr = mCurrentConnectedMacAddr;
	}
	public String getPhoneDeviceModel() {
		return mPhoneDeviceModel;
	}
	public void setPhoneDeviceModel(String mPhoneDeviceModel) {
		this.mPhoneDeviceModel = mPhoneDeviceModel;
	}
	public String getPhoneDeviceUuid() {
		return mPhoneDeviceUuid;
	}
	public void setPhoneDeviceUuid(String mPhoneDeviceUuid) {
		this.mPhoneDeviceUuid = mPhoneDeviceUuid;
	}
	
	
}
