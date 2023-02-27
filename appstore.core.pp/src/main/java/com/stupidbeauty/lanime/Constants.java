package com.stupidbeauty.lanime;



/**
 * 各个常量。
 * @author root
 *
 */
public class Constants 
{
	public static final int MAX_AUTO_CALL_TICKET_AMOUNT = 2; //!<自动呼叫的票的个数。

	/**
	 * 文件路径。
	 * @author root 蔡火胜。
	 *
	 */
	public static class FilePath
	{

		public static final String OsiLogPath = "/skyroam/cfg/gmates/osi_log.txt"; //!<OSI日志文件路径。
		public static final String UnknownDeviceMacAddr = "UnkownMacAddr"; //!<未知的网卡物理地址。
		
	} //public static class FilePath



	
	
	/**
	 * GMate的各个信息。
	 * @author root
	 *
	 */
	public final class GMate 
	{
		/** 支持的最大SIM卡数 */
		public static final short MAX_SIM = 2; //!<SIM卡的数量。
		public static final short SIM1 = 0; //!<第一张SIM卡。
		public static final short SIM2 = 1; //!<第二张SIM卡。

		/** 信号 */
		public static final short SINGAL_0 = 0; //!<信号强度0.
		public static final short SINGAL_1 = 25; //!<信号强度25.
		public static final short SINGAL_2 = 50; //!<信号强度50.
		public static final short SINGAL_3 = 75; //!<信号强度75.
		public static final short SINGAL_4 = 100;  //!<信号强度100.
		public static final short BREAKUP_5 = 5; //!<信号强度5.

		/** 电量 */
		public static final short BATTERY_0 = 0; //!<电量0.
		public static final short BATTERY_1 = 20; //!<电量20.
		public static final short BATTERY_2 = 100; //!<电量100.

		/** SIM卡类型 */
		public static final short SIM_TYPE_PHYSICAL = 0; //!<物理卡。
		public static final short SIM_TYPE_VIRTUAL = 1; //!<虚拟卡。
		public static final short SIM_TYPE_NONE = 2; //!<未知卡类型。

		/** SIM卡工作模式 */
		public static final short SIM_MODE_ONLINE = 0; //!<卡是在线模式。
		public static final short SIM_MODE_OFFLINE = 1; //!<卡是离线模式。

		/** SIM卡出入拔出事件 */
		public static final short SIM_UNPLUG = 0; //!<卡被拔出。
		public static final short SIM_PLUG = 1; //!<卡被插入。
	} //public final class GMate 

	/**
	 * 原始消息。
	 * @author root
	 *
	 */
	public final class NativeMessage 
	{
		public static final String NOTIFY_CALLBACK_IP = "notifyCallbackIp"; //!<报告回调IP。

		/** APN */
		public static final String NOTIFY_APN_RESPONSE_STATUS = "notifyApnResponseStatus"; //!<设置APN的结果。
		/** Wifi */
		public static final String NOTIFY_WIFI_RESPONSE_STATUS = "notifyWifiResponseStatus";
		
		/** GmateLog */
		public static final String NOTIFY_READ_GMATE_LOG = "notifyReadGmateLog"; //!<要求读取Gmate日志。
		public static final String NOTIFY_READ_GMATE_LOG_SN = "notifyReadGmateLogSn";
		
		/** 通知充电状态 */
		public static final String NOTIFY_CHARGING = "notifyCharging";
		public static final String NOTIFY_CHARGING_BOOLEAN_STATUS = "isCharging";

		/** 通知电量 */
		public static final String NOTIFY_BATTERY = "notifyBattery"; //!<电量发生变化。
		public static final String NOTIFY_BATTERY_SHORT_BATTERY = "battery";
		
		/** 通知wifi 信号 */
		public static final String NOTIFY_WIFI_SIGNAL = "notifyWifiSignal";
		public static final String NOTIFY_WIFI_CONN_NUM = "notifyWifiConnNum";
		/** 通知 token */
		public static final String NOTIFY_GMATE_TOKEN = "notifyGmateToken";
		/** 通知virtual sim type */
		public static final String NOTIFY_VIRTUAL_SIM_TYPE = "notifyVirtualSimType";

		/** 报告电池异常 */
		public static final String NOTIFY_BATTERY_EXCEPTION = "notifyBatteryException";
		public static final String NOTIFY_BATTERY_EXCEPTION_SHORT_STATE = "state";

		/** 蓝牙名称 */
		public static final String NOTIFY_BLUETOOTH_NAME = "notifyBluetoothName";
		public static final String NOTIFY_BLUETOOTH_NAME_STRING_NAME = "name";

		/** 蓝牙配对码 */
		public static final String NOTIFY_BLUETOOTH_PIN = "notifyBluetoothPIN";
		public static final String NOTIFY_BLUETOOTH_PIN_STRING_PIN = "pin";

		/** 初始化完毕 */
		public static final String NOTIFY_GMATE_INIT_END = "notifyGmateInitEnd";

		/** 报告Gmate是否支持震动 */
		public static final String NOTIFY_VIBRATOR_MODULE = "notifyVibratorModule";

		/** 报告Gmate震动状态 */
		public static final String NOTIFY_VIBRATOR_SETTING = "notifyVibratorSetting";

		/** 接收解除绑定结果 */
		public static final String NOTIFY_DISSOLVE_STATE = "notifyDissolveState";
		public static final String NOTIFY_DISSOLVE_STATE_SHORT_STATE = "state";
		public static final String NOTIFY_DISSOLVE_STATE_LONG_TIME = "time";

		/** 接收绑定查询结果 */
		public static final String NOTIFY_BUND_STATE = "notifyBindState";

		/** 报告激活状态 */
		public static final String NOTIFY_ACTIVATE_STATE = "notifyActivateState";
		public static final String NOTIFY_ACTIVATE_STATE_SHORT_STATE = "state";

		/** 接收到xml配置文件更新消息 */
		public static final String NOTIFY_XML_CONFIG_CHANGE = "notifyRecvModuleConfigXml";
		
		/** Admin账户请求校验码 */
		public static final String NOTIFY_ADMIN_ACCOUNT_VALIDITY_CODE = "notify_admin_account_validity_code"; //!<账户匹配状态代码。
		
		/** 报告登录 被挤兑*/
		public static final String NOTIFY_LOG_OUT = "notify_log_out"; //!<被挤掉，要求退出。
		
		public static final String NOTIFY_ADMIN_LOGIN_FAIL = "notify_admin_login_fail"; //!<We failed to login by admin.

		
		/*********************************************** Gmate SIM *************************************************/

		public static final String RESOURCE_ID = "RESOURCE_ID";

		/** SIM卡名字和号码 */
		public static final String NOTIFY_SIM_NAME_AND_NUMBER = "notifySimNameAndNumber";
		public static final String NOTIFY_SIM_NAME_AND_NUMBER_STRING_NAME = "name";
		public static final String NOTIFY_SIM_NAME_AND_NUMBER_STRING_NUMBER = "number";

		/** 信号强度 */
		public static final String NOTIFY_SIGNAL = "notifySignal";
		public static final String NOTIFY_SIGNAL_SHORT_SIGNAL = "signal";

		public static final String NOTIFY_CONNECTION_STATUS = "notifyConnectionStatusChange"; //!<连接状态发生变化。
		
		
		/** 充电状态 */
		public static final String NOTIFY_CHARGGING_STATE = "NOTIFY_CHARGGING_STEP"; //!<电池充电状态发生变化。
		
		/** SIM卡登录的运营商 */
		public static final String NOTIFY_SIM_OPERATOR = "notifySimOperator";
		public static final String NOTIFY_SIM_OPERATOR_STRING_OPERATOR = "operator";

		/** SIM卡热插拔 */
		public static final String NOTIFY_SIM_HOT_PLUGED = "notifySimHotPluged";
		public static final String NOTIFY_SIM_HOT_PLUGED_SHORT_STATE = "state";

		/** 报告SIM卡里联系人数量 */
		public static final String NOTIFY_SIM_CONTACTS_NUMBER = "notifySimContactsNumber";
		public static final String NOTIFY_SIM_CONTACTS_NUMBER_INT_NUMBER = "number";

		/** 报告一个SIM中联系人的信息 */
		public static final String NOTIFY_SIM_CONTACT = "notifySimContact";
		public static final String NOTIFY_SIM_CONTACT_STRING_NAME = "name";
		public static final String NOTIFY_SIM_CONTACT_STRING_NUMBER = "number";

		/** 报告SIM卡PIN码验证的结果 */
		public static final String NOTIFY_PIN_VERIFICATE = "notifyPINVerificate";
		public static final String NOTIFY_PIN_VERIFICATE_SHORT_TIMES = "times";

		/** 报告SIM卡PUK码验证的结果 */
		public static final String NOTIFY_PUK_VERIFICATE = "notifyPUKVerificate";
		public static final String NOTIFY_PUK_VERIFICATE_SHORT_TIMES = "times";

		/*********************************************** update *************************************************/

		public static final String NOTIFY_FIRMWARE_REPORT_VERSION = "notifyFirmwareReportVersion";

		public static final String NOTIFY_FIRMWARE_START_UPDATE_ACK = "notifyFirmwareStartUpdateAck";

		public static final String NOTIFY_FIRMWARE_UPDATE_DATA_ACK = "notifyFirmwareUpdateDataAck";
		public static final String NOTIFY_FIRMWARE_UPDATE_DATA_ACK_LONG_SEQUENCE = "sequence";

		public static final String NOTIFY_FIRMWARE_COMPLETE_UPDATE = "notifyFirmwareCompleteUpdate";
		public static final String NOTIFY_FIRMWARE_COMPLETE_UPDATE_SHORT_STATE = "state";

		public static final String NOTIFY_FIRMWARE_UPDATE_EXCEPTION = "notifyFirmwareUpdateException";

		public static final String NOTIFY_FIRMWARE_REBOOT = "notifyFirmwareReboot";

		/*********************************************** Network *************************************************/

		/*********************************************** Call *************************************************/

		/** 正在拨号 */
		public static final String NOTIFY_CALL_DAILLING = "notifyCallDailling";
		public static final String NOTIFY_CALL_DAILLING_SHORT_CALLID = "callId";

		/** 电话拨打失败 */
		public static final String NOTIFY_CALL_DIALING_ERROR = "notifyCallDialingError";
		public static final String NOTIFY_CALL_DIALING_ERROR_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_DIALING_ERROR_SHORT_ERRCODE = "errCode";

		/** 来电 */
		public static final String NOTIFY_CALL_RING = "notifyCallRinging";
		public static final String NOTIFY_CALL_RING_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_RING_STRING_NUMBER = "number";

		/** 电话已连接 */
		public static final String NOTIFY_CALL_CONNECTED = "notifyCallConnected";
		public static final String NOTIFY_CALL_CONNECTED_SHORT_CALLID = "callId";

		/** 电话已保持 */
		public static final String NOTIFY_CALL_HOLD = "notifyCallHold";
		public static final String NOTIFY_CALL_HOLD_SHORT_CALLID = "callId";

		/** 电话已恢复 */
		public static final String NOTIFY_CALL_RESUME = "notifyCallResume";
		public static final String NOTIFY_CALL_RESUME_SHORT_CALLID = "callId";

		/** 语音开始 */
		public static final String NOTIFY_CALL_AUDIO_START = "notifyCallAudioStart";

		public static final String NOTIFY_CALL_TIMER = "notifyCallTimer";
		public static final String NOTIFY_CALL_TIMER_SHORT_CALLID = "callId";
		public static final String NOTIFY_CALL_TIMER_LONG_DURATION = "duration";
		public static final String NOTIFY_CALL_TIMER_STRING_DURATION = "durationString";
		/** 电话已挂断 */
		public static final String NOTIFY_CALL_HANDUP = "notifyCallHandup";
		public static final String NOTIFY_CALL_HANDUP_SHORT_CALLID = "callId";

		/** 语音结束 */
		public static final String NOTIFY_CALL_AUDIO_END = "notifyCallAudioEnd";

		/** 接收USSD */
		public static final String NOTIFY_RECEIVE_USSD = "notifyReceiveUSSD";

		/*********************************************** Call log *************************************************/

		public static final String NOTIFY_CALL_LOG = "notifyCallLog";
		public static final String NOTIFY_CONTACT_LIST_CHANGED = "notifyContactSyncFinished";
		/** 呼出通话记录 */
		public static final String NOTIFY_OUTGOING_CALLLOG = "notifyOutgoingCallLog";
		public static final String NOTIFY_OUTGOING_CALLLOG_SHORT_CALLID = "callId";
		public static final String NOTIFY_OUTGOING_CALLLOG_STRING_NUMBER = "number";
		public static final String NOTIFY_OUTGOING_CALLLOG_LONG_DATE = "date";
		public static final String NOTIFY_OUTGOING_CALLLOG_INT_DURATION = "duration";

		/** 呼入通话记录 */
		public static final String NOTIFY_INCOMING_CALLLOG = "notifyIncomingCallLog";
		public static final String NOTIFY_INCOMING_CALLLOG_SHORT_CALLID = "callId";
		public static final String NOTIFY_INCOMING_CALLLOG_STRING_NUMBER = "number";
		public static final String NOTIFY_INCOMING_CALLLOG_LONG_DATE = "date";
		public static final String NOTIFY_INCOMING_CALLLOG_INT_DURATION = "duration";

		/** 更新通话记录 */
		public static final String NOTIFY_UPDATE_CALLLOG = "notifyUpdateCallLog";
		public static final String NOTIFY_UPDATE_CALLLOG_INT_ID = "id";
		public static final String NOTIFY_UPDATE_CALLLOG_SHORT_STATE = "state";
		public static final String NOTIFY_UPDATE_CALLLOG_STRING_BUMBER = "number";
		public static final String NOTIFY_UPDATE_CALLLOG_INT_DURATION = "duration";
		public static final String NOTIFY_UPDATE_CALLLOG_BOOLEAN_INCOMING = "incoming";

		/*********************************************** SMS *************************************************/

		/** 添加新短信 */
		public static final String NOTIFY_ADD_INBOX_SMS = "notifyAddInboxSms";
		public static final String NOTIFY_ADD_INBOX_SMS_LONG_DATE = "date";
		public static final String NOTIFY_ADD_INBOX_SMS_STRING_BUMBER = "number";
		public static final String NOTIFY_ADD_INBOX_SMS_STRING_MESSAGE = "message";

		/** 短信发送失败 */
		public static final String NOTIFY_SEND_FAILED_SMS = "notifySendFailedSms";
		public static final String NOTIFY_SEND_FAILED_SMS_STRING_BUMBER = "number";
		public static final String NOTIFY_SEND_FAILED_SMS_SHORT_ERROR = "error";

		public static final String NOTIFY_CALL_MUTE = "notifyCallMute";
		public static final String NOTIFY_CALL_MUTE_BOOLEAN_MUTE = "mute";

		public static final String NOTIFY_CALL_SPEAKER = "notifyCallSpeaker";
		public static final String NOTIFY_CALL_SPEAKER_BOOLEAN_SPEAKER = "speaker";
		public static final String	NOTIFY_SN	= "notifySn"; //!<We have got sn.
		public static final String	CHAT_MESSAGE_PUSH	= "chatMessagePush"; //!<Got a push message.
	}

	/**
	 * 常用常量。
	 * @author root 蔡火胜。
	 *
	 */
	public final class Common 
	{
		/** 控制业务tabs 的显示 */
		public static final String NATIVE_ACTION_SHOW_BUSINESS_TABS = "native_action.SHOW_TABS";
		public static final String NATIVE_ACTION_GONE_BUSINESS_TABS = "native_action.GONE_TABS";

		/** 服务已启动 */
		public static final String NATIVE_ACTION_SERVICE_FINISH = "native_action.SERVICE_FINISH"; //!<后台服务已启动。

		/** 显示下一个界面 */
		public static final String NATIVE_ACTION_ON_SHOW_NEXT_VIEW = "native_action.ON_SHOW_NEXT_VIEW"; //!<显示下一个界面。
		public static final String NATIVE_ACTION_ON_SHOW_NEXT_VIEW_STRING_ACTIVITY = "activity";

		/** 显示前一个界面 */
		public static final String NATIVE_ACTION_ON_SHOW_PREVIOUS_VIEW = "native_action.ON_SHOW_PREVIOUS_VIEW";
		public static final String NATIVE_ACTION_ON_SHOW_PREVIOUS_VIEW_STRING_KEY = "key";

		/** 显示PIN输入框 */
		public static final String NATIVE_ACTION_ON_SHOW_PIN_OR_PUK_VIEW = "native_action.ON_SHOW_PIN_OR_PUK_VIEW";
		public static final String NATIVE_ACTION_ON_SHOW_PIN_OR_PUK_VIEW_STRING_JSON = "json";

		/** 隐藏PIN输入框 */
		public static final String NATIVE_ACTION_ON_HIDE_PIN_OR_PUK_VIEW = "native_action.ON_HIDE_PIN_OR_PUK_VIEW";
		/** 切换tab */
		public static final String NATIVE_ACTION_ON_SWITCH_TAB_VIEW = "native_action.ON_SWITCH_TAB_VIEW";
		public static final String NATIVE_ACTION_ON_SWITCH_TAB_VIEW_INT_INDEX = "index";

		/** 检查更新 */
		public static final String NATIVE_ACTION_ON_CHECK_UPDATE = "native_action.ON_CHECK_UPDATE";
		public static final String NATIVE_ACTION_ON_CHECK_UPDATE_BOOL_AUTO = "auto";
		
		/** 播放铃音 */
		public static final String NATIVE_ACTION_RING = "native_action.RING"; //!<播放铃声。
		public static final String NATIVE_ACTION_RING_STRING_JSON = "json";

		/** 更新logo状态 */
		public static final String GMATE_UPDATE_LOGO_STATE = "gmate.UPDATE_LOGO_STATE";

		/** 字符串常量，存放在Intent中的设备对象 */
		public static final String DEVICE = "DEVICE";
		/** Action：设备列表 */
		public static final String ACTION_FOUND_DEVICE = "ACTION_FOUND_DEVICE";
		/** Action：关闭后台Service */
		public static final String ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE";
		/** code of wifi */
		public static final int WIFI_DEVICE_CODE = 1;
		/** code of bluetooth */
		public static final int BLUETOOTH_DEVICE_CODE = 0;

		public static final String NATIVE_SETTING_PREFERENCES_NAME = "native_setting_preferences_name";
		public static final String IS_FAQ_KEYWORK_SEARCHING = "is_faq_keywork_searching";
		public static final String IS_FAQ_INIT_LOCAL_DATA = "is_faq_init_local_data";
		public static final String IS_SERVICE_HAS_BEAN_START = "is_service_has_bean_start"; //!<后台服务是否已经启动。

		public static final String PREF_TEMP_SN = "PREF_TEMP_SN";
		public static final String PREF_TEMP_VERSION = "PREF_TEMP_VERSION";
		public static final String HAS_BEAN_LOGIN_SUCCESS = "has_bean_login_success"; //!<是否曾经成功登录过。
		public static final String HAS_BEAN_CLICK_LOGING_BTN = "has_bean_click_loging_btn";

		public static final String PREF_LOGIN_USER_NAME = "PREF_LOGIN_USER_NAME";
		public static final String PREF_LOGIN_USER_PWD = "PREF_LOGIN_USER_PWD";
		public static final String MATCH_ACCOUNT_WITH_GMATE_KEY = "match_account_with_gmate"; //!<当前登录的skyroam id是否与设备绑定的ID匹配。
		
		
		/**
		 * 静态网页类型。
		 */
		public static final String STATIC_WEBPAGE_COVERAGE = "staticWebPageTerritory"; //!<覆盖地区。
		public static final String STATIC_WEBPAGE_TYPE = "staticWebPageType"; //!<参数，静态网页类型。
		public static final String STATIC_WEBPAGE_PRODUCTS = "staticWebPageProducts"; //!<产品介绍。
		public static final String STATIC_WEBPAGE_INSTRUCTIONS = "staticWebPageInstructions"; //!<使用说明。
		
		public static final String STATIC_WEBPAGE_SN_PARAM_KEY = "sn"; //!<sn参数的键。	
		public static final String STATIC_WEBPAGE_MODULE_PARAM_KEY = "module"; //!<module参数键。
		
		public static final String STATIC_WEBPAGE_MODULE_COVERAGE = "coverage"; //!<module参数值，覆盖地区。
		public static final String STATIC_WEBPAGE_MODULE_PRODUCTS = "products"; //!<module参数值，产品介绍。
		public static final String STATIC_WEBPAGE_MODULE_INSTRUCTIONS = "userguide"; //!<module参数值，使用说明。
	
		public static final String STATIC_WEBPAGE_CSYSTEM_PARAM_KEY = "csystem"; //!<csystem 参数的键。
		public static final String STATIC_WEBPAGE_CSYSTEM_ANDROID = "2"; //!<csystem参数的值，android.
		public static final String STATIC_WEBPAGE_LANG_PARAM_KEY = "lang"; //!<lang参数的值。
		public static final String STATIC_WEBPAGE_SKYROAMID_PARAM_KEY = "skyroam_id"; //!<skyroam id 参数的键。
		public static final String MATCH_ACCOUNT_NAMES_JSON = "match_account_names_json"; //!<以JSON字符串表示的，匹配的账户名集合。
		public static final String	NOTIFY_SN	= "notifySn"; //!<We have got sn.
		public static final String	WHETHER_IN_CHAT_ACTIVITY	= "whetherInChatActivity"; //!<Whether we are in the chat activity.
		public static final String	CHAT_MESSAGE_PUSH	= "chatMessagePushContent"; //!<The content of a push chat message.
		public static final String	CHAT_MESSAGE_PUSH_FROM_NAME	= "chatMessagePushFromName"; //!<The sender name.
		public static final String	CHAT_MESSAGE_PUSH_ADD_TIME	= "chatMessagePushAddTime"; //!<The add time.
		public static final String	COOKIE_URI_SET	= "cookieUriSet"; //!<The cookie uri set.
		public static final String	URI_COOKIE_MAP	= "uriCookieMap"; //!<The uri cookie map.
	} //public final class Common 

	public final class Call 
	{
		/** 通话记录 */
		public static final String CALL_ACTION_CALL_LOG = "call_action.CALL_LOG";

		/** 发送USSD */
		public static final String CALL_ACTION_USSD = "call_action.USSD";

		/** 扬声器改变 */
		public static final String CALL_ACTION_SPEAKER_CHANGED = "call_action.SPEAKER_CHANGED";

		/** 支持的最大通话数 */
		public static final short MAX_CALL = 2;
		/** 通话1 */
		public static final short CALL1 = 0;
		/** 通话2 */
		public static final short CALL2 = 1;

		/** 空闲 */
		public static final short CALL_STATUS_IDLE = 0;
		/** 拨号 */
		public static final short CALL_STATUS_DIALING = 1;
		/** 来电 */
		public static final short CALL_STATUS_RINGING = 2;
		/** 接通 */
		public static final short CALL_STATUS_CONNECTED = 3;
		/** 保持 */
		public static final short CALL_STATUS_HOLD = 4;

		/** DTMF */
		public static final short DTMF_0 = 0;
		public static final short DTMF_1 = 1;
		public static final short DTMF_2 = 2;
		public static final short DTMF_3 = 3;
		public static final short DTMF_4 = 4;
		public static final short DTMF_5 = 5;
		public static final short DTMF_6 = 6;
		public static final short DTMF_7 = 7;
		public static final short DTMF_8 = 8;
		public static final short DTMF_9 = 9;
		public static final short DTMF_ASTERISK = 10;
		public static final short DTMF_SHARP = 11;
		public static final short DTMF_A = 12;
		public static final short DTMF_B = 13;
		public static final short DTMF_C = 14;
		public static final short DTMF_D = 15;
		public static final int CALL_INVALID_ID = 0x0000FFFF;

		/** 通话状态 */
		public static final String CALL_STATUS = "call.STATUS";
		/** 通话ID */
		public static final String CALL_CALL_ID = "call.CALL_ID";
		/** SIM ID */
		public static final String CALL_SIM_ID = "call.SIM_ID";
		/** 起始时间 */
		public static final String CALL_START_TIME = "call.START_TIME";
		/** 持续时间 */
		public static final String CALL_DURATION_TIME = "call.DURATION_TIME";
		/** 数据库对应的数据ID */
		public static final String CALL_THREAD_ID = "call.THREAD_ID";
		/** 号码 */
		public static final String CALL_NUMBER = "call.NUMBER";
		/** 是否是来电 */
		public static final String CALL_IS_INCOMING = "call.IS_INCOMING";
		/** 联系人头像 */
		public static final String CALL_PHOTO = "call.PHOTO";
		/** 联系人信息 */
		public static final String CALL_CONTACT_INFO = "call.CONTACT_INFO";
	}

	/**
	 * 连接状态定义。
	 * @author root
	 *
	 */
	public final class Connection 
	{
		public static final int CONNECT_STATE_DISCONNECTED = 0;
		public static final int CONNECT_STATE_CONNECTTING = 1; //!<正在连接。
		public static final int CONNECT_STATE_FAIL = 2;
		public static final int CONNECT_STATE_CONNECTED = 3; //!<已经建立连接。
		public static final int CONNECT_STATUS_DISCONNECT = 4; //!<已断开连接。
		public static final int CONNECT_STATUS_IS_MASTER = 5; //!<我们是管理员。
		public final static int CONNECTION_STATE_IS_LOGINED = 6;
	} //public final class Connection

	public final class GmateVirtualSimType {
		public static final int GMATE_VIRTUAL_SIM_NULL = 0;
		public static final int GMATE_VIRTUAL_SIM_APPLYING = 1;
		public static final int GMATE_VIRTUAL_SIM_DATA_ONLY = 2;
		public static final int GMATE_VIRTUAL_SIM_VOICE_ONLY = 3;
		public static final int GMATE_VIRTUAL_SIM_SMS_ONLY = 4;
		public static final int GMATE_VIRTUAL_SIM_DATA_AND_VOICE = 5;
		public static final int GMATE_VIRTUAL_SIM_DATA_AND_SMS = 6;
		public static final int GMATE_VIRTUAL_SIM_VOICE_AND_SMS = 7;
		public static final int GMATE_VIRTUAL_SIM_DATA_VOICE_SMS = 8;
		public static final int GMATE_VIRTUAL_SIM_MAX = 9;

	}

	public final class BaseBandAction {
		public static final int BB_MODE_ACTION_TO_NORMAL = 0;
		public static final int BB_MODE_ACTION_TO_FLIGHT = 1;
	}

	public final class AudioStreamVersion {
		public static final int AUDIO_STREAM_VERSION_WITH_HEADER = 0;// 330
		public static final int AUDIO_STREAM_VERSION_MUTIFRAME = 1; // 990
		public static final int AUDIO_STREAM_VERSION_MAX = 2;
	}

	public final class FAQTag {
		public static final String TAG_STRING = "string";
		public static final String TAG_QUESTION = "question";
		public static final String TAG_ANSWER = "answer";
		public static final String TAG_CAT = "cat";
		public static final String TAG_KEYWORDS = "keywords";

		public static final String TAG_FAQ_VERSION = "version";
		public static final String TAG_FAQ_URL = "url";
	}

	/**
	 * Operations。
	 * @author root 蔡火胜。
	 *
	 */
	public final class Operation 
	{
		public static final String CommitText = "com.stupidbeauty.lanime.commitText"; //!<提交文本内容。
		public static final String CommitControlCharacter = "com.stupidbeauty.lanime.commitControlCharacter"; //!<提交控制字符。
		public static final String HideKeyboard = "com.stupidbeauty.lanime.hideKeyboard"; //!<隐藏软键盘。
	} //public final class FAQLangKey


	public static final int ACCOUNT_VALIDITY_ID_FAILED = 5; //!<账户不匹配。
	public static final int ACCOUNT_VALIDITY_UNDEFINED_TYPE = 6; //!<未定义。
	
	/**
	 * 跟配置信息相关的常量。
	 * @author root 蔡火胜。
	 *
	 */
	public static final class Config
	{

		public static final String LOCAL_CONFIG_FILE_NAME = "localConfigFile.json"; //!<下载到本地的配置文件名。
		
	} //public static final class Config

	/**
	 * 被废弃掉的常量值。在某些清除动作中还需要用到它们。
	 * @author root 蔡火胜。
	 *
	 */
	public static final class Deprecated 
	{
		public static final String ADMIN_LOGING_STATUS_KEY = "admin_loging_status_key"; //!<管理员登录状态。
		public static final String MATCH_ACCOUNT_NAMES = "match_account_names"; //!<匹配的账户名集合。
	} //public static final String Deprecated

	/**
	 * 路径相关的常量。
	 * @author root 蔡火胜。
	 *
	 */
	public static final class Path
	{

		public static final String SdCardDirectoryName = "com.stupidbeauty.lanime"; //!<SD卡上的目录名字。
		public static final String PhoneAvatarFileName = "phoneAvatar"; //!<手机头像图片文件名字。
		
	} //public static final Class Path
	
}
