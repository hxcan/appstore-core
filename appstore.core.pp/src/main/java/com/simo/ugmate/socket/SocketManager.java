package com.simo.ugmate.socket;

public interface SocketManager {
	
    public static final int READ_ERROR = 0;
    public static final int WRITE_ERROR = 1;
    public static final int INIT_ERROR = 2;
    public static final int INIT_SUCCESS = 3;
	
	public void read(byte[] msg);
	public void message(int message);
	
}
