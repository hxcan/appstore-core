package com.stupidbeauty.hxlauncher.rpc;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;

import java.util.Iterator;

/**
 * 用于对SDK进行全局初始化。
 */
public class SpeechUtility
{

    private Context g = null;

    private static SpeechUtility d = null;

    /**
     * 进行全局初始化。
     * @param context 用于获取安卓系统资源的上下文对象。
     * @param parameters 额外参数。目前可为空白。
     * @return 初始化得到的全局工具实例。
     */
    public static synchronized SpeechUtility createUtility(Context context, String parameters)
    {

        return d;
    }

    private SpeechUtility(Context var1, String var2) throws SpeechError {

        this.g = var1.getApplicationContext();






    }


}
