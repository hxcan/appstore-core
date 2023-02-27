package com.simo.ugmate.widgets;


import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;



public interface UIProgressDialogInterface extends OnClickListener, OnCancelListener {

	public static final int BUTTON_CANCEL = -6;









	public void setTileResId(int id); //!<设置显示的内容编号。





	public void show(); //!<显示。





	public void cancel(); //!<取消。

	public void setButton(String btn0); //!<设置按钮文字。

















	@Override
	public void onClick(DialogInterface dialog, int which); //!<按钮被点击。

	@Override
	public void onCancel(DialogInterface dialog); //!<对话框被取消。

	public interface IProgressDialogCallback {
		public void onClickProgressDialog(DialogInterface dialog, int which);
	}
}
