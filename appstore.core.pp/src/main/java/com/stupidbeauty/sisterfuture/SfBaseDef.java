package com.stupidbeauty.sisterfuture;

import java.io.File;
import android.os.Environment;

/**
 * 一些基本信息的定义。
 * @author root
 *
 */
public class SfBaseDef
{
	public static final String LOG_BASE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"com.stupidbeauty.sisterfuture"+File.separator+"Log"; //!<日志目录的路径。
	public static final String LOG_DIR_FILE = "StupidBeauty"+File.separator+"Log";
	public static final String LOG_ANDROID_DIR = "ANDROID";
	
} //public class SfBaseDef


