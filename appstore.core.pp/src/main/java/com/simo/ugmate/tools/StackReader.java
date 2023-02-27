package com.simo.ugmate.tools;


/**
 * 协议栈数据读类
 * @author justin
 *
 */
public class StackReader 
{
	/**
	 * 读取32位无符号数据
	 * @param msg 待读取的数组
	 * @param off 偏移量
	 * @param retOff 读取后的偏移量
	 * @return 读取的数据
	 */
	public static long ReadUint32(byte[] msg, int off, StackInteger retOff) {
		long data = 0;
		data = (((msg[off] << 24) & 0xFF000000)
		              | ((msg[off+1] << 16) & 0x00FF0000) 
		              | ((msg[off+2] << 8) & 0x0000FF00)
		              | (msg[off+3] & 0x000000FF));
		data &= 0x00000000FFFFFFFF;
		
		retOff.setValue(off + 4);
		return data;
	}
	
	/**
	 * 从协议栈中读取16位无符号数据
	 * @param msg 待读取的数组
	 * @param off 偏移量
	 * @param retOff 读取后的偏移量
	 * @return 读取的数据
	 */
	public static int ReadUint16(byte[] msg, int off, StackInteger retOff) {
		int data = 0;
		data = ((msg[off] << 8) & 0x0000FF00) | (msg[off+1] & 0x000000FF);
		
		retOff.setValue(off + 2);
		return data;
	}
	
	/**
	 * 从协议栈中读取8位无符号数据
	 * @param msg 待读取的数组
	 * @param off 偏移量
	 * @param retOff 读取后的偏移量
	 * @return 读取的数据
	 */
	public static short ReadUint8(byte[] msg, int off, StackInteger retOff) {
		short data = 0;
		data = (msg[off]);
		data &= 0x00FF;
		
		retOff.setValue(off + 1);
    	return data;
	}
	
	/**
	 * 读取字节数组
	 * @param msg 待读取的数组
	 * @param off 偏移量
	 * @param length 读取长度
	 * @param retOff 读取后的偏移量
	 * @return 字节数组
	 */
	public static byte[] ReadByteArray(byte[] msg, int off, int length, StackInteger retOff) {
		byte[] data = new byte[length];
		System.arraycopy(msg, off, data, 0, length);
		
		retOff.setValue(off + length);
		return data;
	}

} //public class StackReader
