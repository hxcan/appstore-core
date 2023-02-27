package com.simo.ugmate.tools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密工具，文件中共有两个方法,加密、解密
 * 
 */
public class DesUtil {
	
	/**
	 * 对 Byte数组进行加密
	 * 
	 * @param str
	 *            要加密的数据
	 * @return 返回加密后的 byte 数组
	 */
	public static byte[] createEncryptor(byte[] mDesKey, byte[] data) {
		byte[] cipherByte = null;
		try {
			IvParameterSpec mIvParameterSpec = getIV();
			DESKeySpec mDESKeySpec = new DESKeySpec(mDesKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey deskey = keyFactory.generateSecret(mDESKeySpec);
			Cipher c = Cipher.getInstance("DES/CBC/PKCS7PADDING");
			c.init(Cipher.ENCRYPT_MODE, deskey, mIvParameterSpec);
			cipherByte = c.doFinal(data);
		} catch (Exception ex) {
			ex.printStackTrace();
			cipherByte = data;//防止程序崩溃
		} 
		return cipherByte;
	}

	/**
	 * 对 Byte 数组进行解密
	 * 
	 * @param buff
	 *            要解密的数据
	 * @return 返回加密后的 String
	 */
	public static byte[] createDecryptor(byte[] mDesKey, byte[] buff) {
		byte[] cipherByte = null;
		try {
			IvParameterSpec mIvParameterSpec = getIV();
			DESKeySpec mDESKeySpec = new DESKeySpec(mDesKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey deskey = keyFactory.generateSecret(mDESKeySpec);
			Cipher c = Cipher.getInstance("DES/CBC/PKCS7PADDING");
			c.init(Cipher.DECRYPT_MODE, deskey, mIvParameterSpec);
			cipherByte = c.doFinal(buff);
		} catch (Exception ex) {
			ex.printStackTrace();
			cipherByte = buff;//防止程序崩溃
		}
		return cipherByte;
	}
	
	private static IvParameterSpec getIV(){
		return new IvParameterSpec("simomate".getBytes());
	}
}