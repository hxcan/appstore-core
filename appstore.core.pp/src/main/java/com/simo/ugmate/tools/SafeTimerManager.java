package com.simo.ugmate.tools;

public class SafeTimerManager {
	private static SafeTimerManager mSafeTimerManager;
	
	
	public static SafeTimerManager shareInstance(){
		if(mSafeTimerManager == null){
			mSafeTimerManager = new SafeTimerManager();
		}
		return mSafeTimerManager;
	}
	
	
	private int doAddRepeatTask(IfSafeTimerManager task, int p){
		return p;
		
	}
	
	private void doRemoveTimer(int id) {
		 
		
	}
	
	public static int addRepeatTask(IfSafeTimerManager task, int p){
		return SafeTimerManager.shareInstance().doAddRepeatTask(task, p);
	}
	
	public static void removeTimer(int id){
		SafeTimerManager.shareInstance().doRemoveTimer(id);
	}

	public interface IfSafeTimerManager{
		public void onTimerEvent(int timerid);
	}

}
