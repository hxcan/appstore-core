package com.simo.ugmate.state;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Reachbility {

	private Context _context;

	public Reachbility(Context context) {
		this._context = context;
	}

	/**
	 * 检查是否已经连接到互联网。
	 * @return 是否已经连接到互联网。
	 */
	public boolean isConnectingToInternet() 
	{
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE); //获取连接性管理器。
		
		if (connectivity != null) //成功获取到连接性管理器。 
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo(); //获取全部网络连接信息。
			
			if (info != null) //信息存在。
			{
				for (int i = 0; i < info.length; i++) //一个个地检查。
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED) //处于已连上状态。 
					{
						return true; //已经连接到互联网。
					} //if (info[i].getState() == NetworkInfo.State.CONNECTED) //处于已连上状态。

				} //for (int i = 0; i < info.length; i++) //一个个地检查。
			} //if (info != null) //信息存在。
		} //if (connectivity != null) //成功获取到连接性管理器。
		
		return false; //未连接。
	} //public boolean isConnectingToInternet()
}