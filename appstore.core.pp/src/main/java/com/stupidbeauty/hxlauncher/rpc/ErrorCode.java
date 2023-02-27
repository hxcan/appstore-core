package com.stupidbeauty.hxlauncher.rpc;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 错误码定义。
 * @author root 蔡火胜。
 *

 */
@SuppressWarnings("WeakerAccess")
public class ErrorCode
{
    /**
     * 录音失败。可能的原因：麦克风被占用。
     */
    public static final int ERROR_AUDIO_RECORD = 103;

    /**
     * 当前应用程序不允许明文HTTP传输。如果服务端提供的是明文HTTP接口，则需要由应用程序的开发者明确启用明文HTTP传输。参考：https://developer.android.com/training/articles/security-config#CleartextTrafficPermitted
     */
    public static final int ERROR_CLEARTEXT_HTTP_NOT_PERMITTED = 106;

    /**
     * 网络连接失败。服务端明确拒绝连接。
     */
    public static final int ERROR_NET_CONNECTSOCK = 105;



    /**
     * 网络超时。尝试连接到服务端时发生了网络连接超时，这一般是本地网络原因引起的。
     */
    public static final int ERROR_NETWORK_TIMEOUT = 104;

    /**
     * 网络不通。可能本设备当前未连接到网络。
     */
    public static final int ERROR_NETWORK_UNREACHABLE = 108;

    /**
     * 间隔时间过短。在上一次语音识别会话刚结束时立即调用下一次会话，此时间间隔受安卓系统录音资源的释放速度限制，或在上一次语音识别会话尚未结束时就调用下一次会话。
     */
    public static final int ERROR_OVERFLOW = 107;



    /**
     * 未能获得录音权限。
     */
    public static final int ERROR_PERMISSION_DENIED = 100;

    /**
     * 未知错误。
     */
    public static final int ERROR_UNKNOWN = 301;

    /**
     * 不支持OkHttp。
     */
    public static final int ERROR_NOT_SUPPORT_OK_HTTP = 109;
    
    public static final int ERROR_DOWNLOAD_FAILURE = 824; //!< 下载失败。

    /**
     * 正常完成操作。未发生错误。
     */
    public static final int SUCCESS = 0;

    private String fileName; //!<文件名。
    private static final String TAG="VFile"; //!<输出调试信息时使用的标记。

    private int victoriaFreshDataFileId=0; //!<VictoriaFreSh数据文件编号。

    private int victoriaFreshIndexFileId=0; //!<VictoriaFreSh索引文件编号。

} //public class VFile
