package com.simo.ugmate.widgets;


import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;


public interface UIAlertViewInterface extends OnClickListener, OnCancelListener {

	public static final int BUTTON_CANCEL = -6;
	
	
	

	




	
	public void setTitle(String title) ; //!<设置标题。
	
	public void setMessage(String message); //!<设置消息内容。


	
	public void setButton(String btn0); //!<设置按钮。
	

	

	

	

	

	

	
	public void show(); //!<显示。
	



	

	
	@Override
	public void onClick(DialogInterface dialog, int which); //!<按钮被点击。

	@Override
	public void onCancel(DialogInterface dialog); //!<对话框被取消。
	
	public interface IAlertViewCallback {
		public void onClickAlertView(DialogInterface dialog, int which);
	}
}
