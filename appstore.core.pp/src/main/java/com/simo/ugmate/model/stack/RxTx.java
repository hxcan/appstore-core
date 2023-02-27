package com.simo.ugmate.model.stack;

/**
 * 处理StackRxTx协议栈数据
 * @author justin
 *
 */
public interface RxTx 
{
	
	/** 组装加密帧 */
	public void sendEncryptionFrame(short port, byte[] desID, byte[] data);
	
	/** 组装可靠帧，即需要确认 */
	public void sendReliableFrame(short port, int cmd, byte[] data, boolean flag);
	
	/** 发送不可靠帧，即不需要确认 */
	public void sendUnreliableFrame(short port, int cmd, byte[] data);
	
	
	/** 发送帧 */
	public void write(byte[] data);
	
	/** 处理协议帧 */
	public void processFrame(short port, int cmd, byte[] data);
	
	
	public boolean canSend();
} //public interface RxTx
