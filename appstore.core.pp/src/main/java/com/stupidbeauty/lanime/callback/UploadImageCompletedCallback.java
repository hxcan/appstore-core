package com.stupidbeauty.lanime.callback;

import org.json.JSONException;
import org.json.JSONObject;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;

public class UploadImageCompletedCallback implements CompletedCallback {

	private AsyncHttpServerResponse response; //!<回复对象。

	/**
	 * 构造函数。
	 * @param arg1 回复对象。
	 */
	public UploadImageCompletedCallback(AsyncHttpServerResponse arg1) 
	{
		response=arg1; //记录。
	} //public UploadImageCompletedCallback(AsyncHttpServerResponse arg1)

	@Override
	public void onCompleted(Exception arg0) {
		//回复：
		JSONObject rplyObj=new JSONObject(); //创建回复对象。
		try //填充JSON内容，并且捕获可能的异常。 
		{
			rplyObj.put("success", true); //请求成功。
		} //try //填充JSON内容，并且捕获可能的异常。
		catch (JSONException e)  //捕获异常。
		{
			e.printStackTrace(); //报告错误信息。
		} //请求成功。
		
		response.send(rplyObj); //发送回复。
	}

}
