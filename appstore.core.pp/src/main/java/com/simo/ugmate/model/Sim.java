package com.simo.ugmate.model;

/**
 * SIM卡。
 * @author root
 *
 */
public class Sim 
{
	
	/** SIM卡类型 */
	public static final short SIM_TYPE_PHYSICAL = 0;
	public static final short SIM_TYPE_VIRTUAL  = 1;
	public static final short SIM_TYPE_NONE     = 2;

	
	/** SIM卡出入拔出事件 */
	public static final short SIM_UNPLUG = 0; //!<拔出。
	public static final short SIM_PLUG = 1; //!<插入。
	
	private short mType;  //!<类型

	private short mNetworkId;  // 网络ID
	private short mSignal;     //!<信号
	private String mName;  // 名称
	private String mNumber;  // 号码
	private String mOperator; // 运营商
	private short simId; //!<SIM卡编号。
	private String mGmateMCC;
	private String mGmateMNC;
	private String mGmateIMSI;
	private boolean isRoaming;

	public Sim() {
		reset();
	}
	
	public void reset() {
		mType = SIM_TYPE_NONE;

		mNetworkId = 0;
		mSignal = 0;
		mName = "";
		mNumber = "";
		mOperator = "";
		mGmateMCC = "";
		mGmateMNC = "";
		mGmateIMSI = "";
		isRoaming = false;
		simId = 0 ;
	}
	
	/**
	 * 设置SIM卡类型
	 */
	public void setType(short type) {
		mType = type;
	}
	
	/**
	 * 查询SIM卡类型
	 */
	public short getType() 
	{
		return mType;
	} //public short getType()
	

	

	
	/**
	 * 设置SIM卡对应的网络号
	 */
	public void setNetworkId(short networkId) {
		mNetworkId = networkId;
	}
	
	/**
	 * 查询SIM卡对应的网络号
	 */
	public short getNetworkId() {
		return mNetworkId;
	}
	
	/**
	 * 设置信号
	 */
	public void setSignal(short signal) {
		mSignal = signal;
	}
	
	/**
	 * 查询信号
	 */
	public short getSignal() 
	{
		return mSignal;
	} //public short getSignal()
	
	/**
	 * 设置名称
	 */
	public void setName(String name) {
		mName = name;
	}
	
	/**
	 * 查询名称
	 * @return
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * 设置号码
	 */
	public void setNumber(String number) {
		mNumber = number;
	}
	
	
	public String getTotleUsage(){
		return "100";
	}
	
	public String getTotleCallTime(){
		return "100";
	}
	
	public String getTotleSmsCount(){
		return "100";
	}
	
	/**
	 * 查询号码
	 */
	public String getNumber() {
		return mNumber;
	}
	
	/**
	 * 设置运营商名称
	 */
	public void setOperator(String operator) {
		mOperator = operator;
	}
	    
	public boolean isRoaming() {
		return isRoaming;
	}

	public void setRoaming(boolean isRoaming) {
		this.isRoaming = isRoaming;
	}

	/**
	 * 查询运营商名称
	 */
	public String getOperator() {
		return mOperator;
	}
	
	public short getSimId() {
		return simId;
	}

	public void setSimId(short simId) {
		this.simId = simId;
	}

	public String getmGmateMCC() {
		return mGmateMCC;
	}

	public void setmGmateMCC(String mGmateMCC) {
		this.mGmateMCC = mGmateMCC;
	}

	public String getmGmateMNC() {
		return mGmateMNC;
	}

	public void setmGmateMNC(String mGmateMNC) {
		this.mGmateMNC = mGmateMNC;
	}

	public String getmGmateIMSI() {
		return mGmateIMSI;
	}

	public void setmGmateIMSI(String mGmateIMSI) {
		this.mGmateIMSI = mGmateIMSI;
	}
}
