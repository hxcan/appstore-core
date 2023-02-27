/**
 * 
 */
package com.stupidbeauty.lanime.callback;

import java.io.File;
import android.os.Environment;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.stupidbeauty.lanime.Constants;

/**
 * @author root 蔡火胜。
 * 收到对于通知的回复内容之后，对之进行处理的回调对象。
 */
public class PhoneAvatarCallback implements HttpServerRequestCallback 
{
	@Override
	/**
	 * 收到请求。
	 */
	public void onRequest(AsyncHttpServerRequest arg0,AsyncHttpServerResponse arg1) 
	{
		
		
		File rplyObj=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/"+ Constants.Path.SdCardDirectoryName+ "/" + Constants.Path.PhoneAvatarFileName); //要发送的文件。
		arg1.sendFile(rplyObj); //发送回复。
		
		return;
	} //public void onRequest(AsyncHttpServerRequest arg0,AsyncHttpServerResponse arg1)
} //public class NoticeCallback
