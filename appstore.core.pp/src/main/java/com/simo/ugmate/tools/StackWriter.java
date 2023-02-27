package com.simo.ugmate.tools;

/**
 * 协议栈数据写类
 * @author justin
 *
 */
public class StackWriter 
{
	
	/**
	 * 以32位无符号数写入数组
	 */
	public static int WriteUint32(byte[] msg, int off, long data) 
	{
		return WriteUint32(msg, off, (int)data);
	} //public static int WriteUint32(byte[] msg, int off, long data)
	
	/**
	 * 写入32位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint32(byte[] msg, int off, int data) 
	{
		msg[off]   = (byte)(data >> 24); //第一个字节。
		msg[off+1] = (byte)(data >> 16); //第二个字节。
		msg[off+2] = (byte)(data >> 8); //第三个字节。
		msg[off+3] = (byte)(data); //第四个字节。
		
		return off + 4; //占用了4个偏移值。
	} //public static int WriteUint32(byte[] msg, int off, int data)
	
	/**
	 * 写入32位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint32(byte[] msg, int off, short data) 
	{
		msg[off]   = (byte)0;
		msg[off+1] = (byte)0;
		msg[off+2] = (byte)(data >> 8);
		msg[off+3] = (byte)(data);
		
		return off + 4;
	} //public static int WriteUint32(byte[] msg, int off, short data)
	
	/**
	 * 写入32位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint32(byte[] msg, int off, byte data) 
	{
		msg[off]   = (byte)0;
		msg[off+1] = (byte)0;
		msg[off+2] = (byte)0;
		msg[off+3] = data;
		
		return off + 4;
	} //public static int WriteUint32(byte[] msg, int off, byte data)
	
	/**
	 * 以16位无符号数写入数组
	 */
	public static int WriteUint16(byte[] msg, int off, long data) 
	{
		return WriteUint16(msg, off, (short)data);
	} //public static int WriteUint16(byte[] msg, int off, long data)
	
	/**
	 * 写入16位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint16(byte[] msg, int off, int data) 
	{
		return WriteUint16(msg, off, (short)data);
	} //public static int WriteUint16(byte[] msg, int off, int data)
	
	/**
	 * 写入16位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint16(byte[] msg, int off, short data) 
	{
		msg[off] = (byte)(data >> 8); //写入高字节。
		msg[off+1] = (byte)(data); //写入低字节。
		
		return off + 2; //占用了2个偏移值。
	} //public static int WriteUint16(byte[] msg, int off, short data)
	
	
	/**
	 * 写入16位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint16(byte[] msg, int off, byte data) 
	{
		msg[off] = (byte)0; //高字节填充为0.
		msg[off+1] = (data); //写入低字节。
		
		return off + 2; //占用了2字节的偏移值。
	} //public static int WriteUint16(byte[] msg, int off, byte data)
	
	
	/**
	 * 以8位无符号数写入数组
	 */
	public static int WriteUint8(byte[] msg, int off, long data) 
	{
		return WriteUint8(msg, off, (byte)data);
	} //public static int WriteUint8(byte[] msg, int off, long data)
	
	/**
	 * 写入8位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint8(byte[] msg, int off, int data) 
	{
		return WriteUint8(msg, off, (byte)data);
	} //public static int WriteUint8(byte[] msg, int off, int data)
	
	/**
	 * 写入8位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint8(byte[] msg, int off, short data) 
	{
		return WriteUint8(msg, off, (byte)data);
	} //public static int WriteUint8(byte[] msg, int off, short data)

	/**
	 * 写入8位无符号整数。
	 * @param msg 要向其写入的目标消息体。
	 * @param off 写入的偏移值。偏移值以字节为单位。
	 * @param data 要写入的具体数据。
	 * @return 新的可用偏移值。
	 */
	public static int WriteUint8(byte[] msg, int off, byte data) 
	{
		msg[off] = data;
		
		return off + 1;
	} //public static int WriteUint8(byte[] msg, int off, byte data)

	/**
	 * 写入byte数组
	 */
	public static int WriteByteArray(byte[] msg, int msgOff, byte[] data, int dataOff, int len) 
	{
		System.arraycopy(data, dataOff, msg, msgOff, len); //直接复制数组。
		return msgOff + len;
	} //public static int WriteByteArray(byte[] msg, int msgOff, byte[] data, int dataOff, int len)
	
	/**
	 * 写入byte数组
	 */
	public static int WriteByteArray(byte[] msg, int off, byte[] data) 
	{
		System.arraycopy(data, 0, msg, off, data.length);
		return off + data.length;
	} //public static int WriteByteArray(byte[] msg, int off, byte[] data)
	
	/**
	 * 将data按字节序写入byte数组
	 */
	public static int WriteBigIntegerToByte(byte[] msg, int off, int mLength, byte[] data){
		for (int i = 0; i < mLength; i++) {
			if (data.length - i > 0) {
				msg[off + mLength - 1 - i] = data[data.length - 1 - i];
			} else {
				msg[off + mLength - 1 - i] = 0;
			}
		}
		return off + mLength;
	}
}
