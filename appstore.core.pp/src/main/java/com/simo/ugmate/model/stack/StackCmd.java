package com.simo.ugmate.model.stack;

/**
 * 命令字定义。
 * @author root 蔡火胜。
 *
 */
public class StackCmd 
{
	public static final int REPORT_VIRTUAL_SIM_PROGRESS = 0x4; //!<报告虚拟卡启动进度。
	public static final int REPORT_SN = 0x9; //!<报告SN。
	public static final int REPORT_GOOD_INFORMATION = 0xb; //!<报告套餐信息。
	public static final int REPORT_NEED_CONFIRM_DAYPASS = 0x5; //!<报告需确认日套餐。
	public static final int REPORT_NEWS = 0x6; //!<报告最新状态信息。
	public static final int REPORT_CONFIRM_DAYPASS_RESULT = 0x8; //!<报告确认日套餐的结果。
	public static final int HEARTBIT = 0xa; //!<心跳数据包。
	public static final int REPORT_STACK_VERSION = 0x134; //!<报告协议栈版本号。
	public static final int SEND_ACCOUNT_INFO = 0x135; //!<Send the account information.
	public static final int REPORT_ACCOUNT_VALIDITY = 0x136; //!<报告账户有效性。
	public static final int REPORT_LOGOUT_CLIENT = 0x137; //!<3g mate 要求我们主动退出。
    
	public static final int QUERY_CHARGE_STATUS = 0x201;
	public static final int REPORT_CHARGE_STATUS = 0x202;
	public static final int QUERY_STRENGTH = 0x203;
	public static final int BATTERY_PERCENT = 0x204; //!<电池电量报告。百分比。
	public static final int REPORT_BATTERY_CHARGE_FINISHED = 0x227; //!<充电完成。
    
	public static final int CHARGE_REQUEST = 0x205; //!<请求充电。
	public static final int QUERY_SIGNAL = 0x208;
	public static final int REPORT_SIGNAL = 0x209; //!<信号强度。
	public static final int QUERY_SIM_COUNT  = 0x20D;
	public static final int REPORT_SIM_COUNT = 0x20E; //!<SIM卡总数及类型。
	public static final int QUERY_SIM_NETWORKID = 0x211;
	public static final int REPORT_SIM_NETWORKID = 0x212; //!<报告SIM卡与对应基站的编号。
	public static final int QUERY_SIM_OPERATOR = 0x213;
	public static final int REPORT_SIM_CARRIER = 0x214; //!<报告SIM卡的运营商名字。
	public static final int QUERY_SIM_NAME_NUMBER = 0x217; //!<查询SIM卡的名称及编号。
	public static final int REPORT_SIM_NAME_NUMBER = 0x218; //!<报告SIM卡的名称及编号。






	public static final int REGISTER_PROCESS_REPORT = 0x22C;
	public static final int SET_SIM_NAME_NUMBER = 0x233;



	public static final int QUERY_SN = 0x0243; //!<查询序列号。
	public static final int REQUERY_SN = 0x2043;//2043、2044与0243、0244效果一样
	
	public static final int REREPORT_SN = 0x2044;
    
	public static final int CALL_NOTIFY_RINGING = 0x301;
	public static final int CALL_DIAL = 0x302;
	public static final int CALL_ANSWER = 0x303;
	public static final int CALL_HOLD = 0x304;
	public static final int CALL_RESUME = 0x305;
	public static final int CALL_SWAP = 0x306;
	public static final int CALL_HANDUP = 0x307;
	public static final int CALL_NOTIFY_AUDIO_START = 0x308;
	public static final int CALL_NOTIFY_AUDIO_END = 0x309;
	public static final int CALL_NOTIFY_STATUS_UPDATE = 0x30A;
	public static final int CALL_SEND_DTMF = 0x30B;
	public static final int CALL_HANDUP_ALL = 0x031B;
	public static final int CALL_SELF_HOLD = 0x0324;
	public static final int CALL_SELF_RESUME = 0x0325;
	public static final int SEND_USSD = 0x0326;
	public static final int RECEOVE_USSD = 0x0327;
	public static final int GMATE_USSD_VERSION = 0x0329;
	public static final int REPORT_USSD_VERSION = 0x032a;
    
	public static final int QUERY_SUPPORTED_VOICE_CODEC = 0x30F;
	public static final int REPORT_SUPPORTED_VOICE_CODEC = 0x310;
	public static final int CONFIRM_VOICE_CODEC = 0x311;
	public static final int CALL_HANDUP_DIALING = 0x315;
	public static final int REPORT_CALL_DIALING_ERROR = 0x318;
	public static final int REPORT_CALL_LOG = 0x319;
	public static final int DELETE_CALL_LOG = 0x31A;
    
	public static final int REPORT_NEW_SMS_ID = 0x401;
	public static final int READ_SMS = 0x402;
	public static final int REPORT_SMS = 0x403;
	public static final int SEND_SMS = 0x408;
	public static final int DELETE_SMS = 0x40C;
	public static final int REPORT_SMS_SENT_CONDITION = 0x0409;
    
	public static final int SET_TIME = 0x801;
	public static final int SET_TIME_RESULT = 0x802; //!<设置时间的结果。


	public static final int SET_BLUETOOTH_NAME = 0x807;
	public static final int SET_BLUETOOTH_PIN = 0x809;
    
	public static final int FIREWARE_QUERY_VERSION = 0xE01;
	public static final int FIREWARE_REPORT_VERSION = 0xE02;
	public static final int FIREWARE_START_UPDATE = 0xE03;
	public static final int FIREWARE_START_UPDATE_ACK = 0xE04;
	public static final int FIREWARE_UPDATING_DATA = 0xE05;
	public static final int FIREWARE_UPDATING_DATA_ACK = 0xE06;
	public static final int FIREWARE_COMPLETE_UPDATE = 0xE07;
	public static final int FIREWARE_START_STORE = 0xE08;
	public static final int FIREWARE_COMPLETE_STORE = 0xE09;
	public static final int FIREWARE_REBOOT = 0xE0A;
	public static final int FIREWARE_REBOOT_ACK = 0xE0B;
	public static final int FIREWARE_UPDATE_EXCEPTION = 0xE0C;
 
	public static final int RSAKEY_RECEIVE = 0x0111;
	public static final int RSAKEY_RECEIVE_CONFIRM = 0x0112;
	public static final int DESKEY_SYCHRO_REQUEST = 0x0113;
	public static final int DESKEY = 0x0114;
	public static final int DESKEY_RECEIVE_CONFIRM = 0x0115;
	public static final int DESKEY_SYCHRO_OVER = 0x0116;
	public static final int PING_SEND = 0x11B;  
	public static final int PING_RECEIVE = 0x11C;
    
	//by xuxin
	public static final int QUERY_SIM_CONTACTS_NUM=0x0320;
	public static final int GET_SIM_CONTACTS_NUM=0x0321;
	public static final int QUERY_SIM_CONTACT_BY_INDEX=0x0322;
	public static final int GET_SIM_CONTACT_BY_INDEX=0x0323;
    
	public static final int GET_IMATE_BATTERY_EXCEPTION=0x023B;
	public static final int GET_INPUT_SIM_PUK=0x023D;
	public static final int SEND_SIM_PIN_OR_PUK=0x023E;
      
	public static final int REQUEST_APN_PARAMETERS=0x060E;
	public static final int ANSWER_REQUEST_APN_PARAMETERS=0x060F; //!<报告APN参数。
	public static final int SET_APN_PARAMETERS=0x0611; //!<设置APN参数。
	public static final int READ_APN_PARAMETERS_FAIL=0x0610; //!<APN参数获取失败。
	public static final int ACK_SET_VPN_PARAMETERS=0x0612; //!<回复，APN参数设置结果。
    
	public static final int IP_TUNNEL_READY=0x0613;
	public static final int IP_TUNNEL_LOST=0x0614;
    
	public static final int IP_TUNNEL_LOAD=0x060d;
	public static final int IP_TUNNEL_NO_USE=0x060c;
	public static final int GPRS_DATA_STATISTIC=0x0616;
    
	public static final int REQUEST_CHANNEL_CREATE=0x0601;
	public static final int CHANNEL_CREATE_SUCCESS=0x0602;
	public static final int CHANNEL_DISCONNECT=0x0603;
	public static final int CHANNEL_TRANSMISSION_LOAD=0x0604; 
	public static final int QUERY_NEIGHBORINGCELLINFO = 0x22E;
	public static final int DEVICE_ACTIVATE_RESULT = 0x0240;//激活结果报告
	public static final int DEVICE_ACTIVATE_QUERY = 0x0241;//查询激活状态 （是否已经激活）
	public static final int DEVICE_ACTIVATE = 0x0242;//发起激活
	public static final int DEVICE_ACTIVATE_STATE = 0x0246;//报告激活状态
    
	public static final int BIND_STATE_QUERY = 0x024A;//查询品牌绑定状态
	public static final int BIND_STATE_REPORT = 0x024B;
	public static final int BIND_DISSOLVE = 0x024E;
	public static final int QUERY_PHONE_BRAND = 0x024C;
	public static final int SEND_PHONE_BRAND = 0x024D;
	public static final int REC_BIND_REQUEST =0x024F;
    
	public static final int SIMO_VIBRATOR_SETTING = 0x0252;
	public static final int SIMO_VIBRATOR_EXPERIENCE = 0x0253;
	public static final int SIMO_VIBRATOR_STATUS_REPORT = 0x0255;
	public static final int SIMO_VIBRATOR_MODULE_QUERY = 0x0256;
	public static final int REPORT_SIM_INFORMATION = 0x248; //!<Gmate回报SIM卡信息。
	public static final int REPORT_NETWORK_ROAMING = 0x249;

	//wifi info query request 
	public static final int QUERY_WIFI_INFO = 0x0258;
	public static final int QUERY_WIFI_INFO_BACK = 0x0259;
	public static final int REQUEST_WIFI_INFO = 0x025A; //!<请求修改无线网信息。
	public static final int REQUEST_WIFI_INFO_BACK = 0x025B;

	public static final int REQUEST_NETWORK_TYPES = 0x025C;
	public static final int REPORT_NETWORK_TYPES = 0x025D; //!<Network type.

	public static final int REQUEST_WIFI_NUM = 0x0260; //!<Request for wlan client amount.
	public static final int REPORT_WIFI_NUM = 0x0261; //!<Report for wlan client amount.
    
	// for MMI sleep
	public static final int REPORT_CLIENT_MMI_SLEEP = 0x0262;
	public static final int REPLY_CLIENT_MMI_SLEEP  = 0x0263;
    
	// for query sn / versions
	public static final int REQUEST_SN_AND_VERSIONS = 0x0264; //!<Request for sn and versions.
	public static final int REPORT_SN_AND_VERSIONS = 0x0265;
    
	// report reset gmate's master
	public static final int REQUEST_RESET_GMATE_MASTER = 0x0266; //!<重置管理员。
    
	// about flight mode
	public static final int QUERY_FLIGHT_MODE   = 0x0267;
	public static final int REQUEST_FLIGHT_MODE = 0x0268;
	public static final int REPORT_FLIGHT_MODE  = 0x0269;
    
	// about let gmate sleep
	public static final int REQUEST_GMATE_SLEEP = 0x0270;
	// about token info
	public static final int QUERY_GMATE_TOKEN_INFO  = 0x0272;
	public static final int REPORT_GMATE_TOKEN_INFO = 0x0273;
    
	// about virtual sim card type
	public static final int QUERY_VIRTUAL_SIM_TYPE  = 0x0274;
	// add heart beat
	public static final int HEART_BEAT = 0x02FF;
    
	public static final int QUERY_VSIM_NUMBER = 0xE10;
	public static final int QUERY_ACCOUNT_INFO = 0xE11;
	public static final int GET_CALL_ABILITY_INFO_REQUEST	= 0x0E12;
	public static final int GET_CALL_ABILITY_INFO_RESPONSE	= 0x0E13;
	public static final int REPORT_VSIM_NUMBER = 0xE0E;
	public static final int REPORT_ACCOUT_INFO = 0xE0F; //!<Report the account information.

	public static final int REQUEST_FILE_MOVE_BEGIN	= 0x0F01; //!<请求传送文件。
	public static final int REPORT_FILE_MOVE_REPLY	= 0x0F02; //!<对于文件传送请求的回应。
	public static final int REPORT_FILE_MOVE_END	= 0x0F03; //!<报告OTA文件传送完毕。
	
	//for log
	public static final int QUERY_LOG_STATE = 0x0F10; //!<查询日志状态。
	public static final int REPORT_LOG_STATE = 0x0F11; //!<报告日志状态。
} //public class StackCmd 

