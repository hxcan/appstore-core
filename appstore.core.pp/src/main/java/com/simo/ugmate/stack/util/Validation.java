package com.simo.ugmate.stack.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import android.util.Log;

public class Validation {
	
	private static final String TAG = "Validation";
	private static Map<String, Object> international_code = new HashMap<String, Object>();
	static {
		List<String> taiwan = new ArrayList<String>();
		taiwan.add("886");
		taiwan.add("0886");
		taiwan.add("00886");
		international_code.put("466", taiwan);
		
		List<String> xianggang = new ArrayList<String>();
		xianggang.add("852");
		xianggang.add("0852");
		xianggang.add("00852");
		international_code.put("454", xianggang);
		
		List<String> aomen = new ArrayList<String>();
		aomen.add("853");
		aomen.add("0853");
		aomen.add("00853");
		international_code.put("455", aomen);
		
		List<String> taiguo = new ArrayList<String>();
		taiguo.add("66");
		taiguo.add("066");
		taiguo.add("0066");
		international_code.put("520", taiguo);
		
		List<String> meiguo = new ArrayList<String>();
		meiguo.add("1");
		meiguo.add("01");
		meiguo.add("001");
		international_code.put("310", meiguo);
		international_code.put("311", meiguo);
		
		
		List<String> zhongguo = new ArrayList<String>();
		zhongguo.add("86");
		zhongguo.add("086");
		zhongguo.add("0086");
		international_code.put("460", zhongguo);
		international_code.put("461", zhongguo);
		
		List<String> yingguo = new ArrayList<String>();
		yingguo.add("44");
		yingguo.add("044");
		yingguo.add("0044");
		international_code.put("234", yingguo);
		
		List<String> danmai = new ArrayList<String>();
		danmai.add("45");
		danmai.add("045");
		danmai.add("0045");
		international_code.put("238", danmai);
		
		List<String> yidali = new ArrayList<String>();
		yidali.add("39");
		yidali.add("039");
		yidali.add("0039");
		international_code.put("222", yidali);
		
		List<String> yindu = new ArrayList<String>();
		yindu.add("91");
		yindu.add("091");
		yindu.add("0091");
		international_code.put("404", yindu);
	}
	
	public static boolean isEmail(String str) {
		/*	\w	匹配字母或数字或下划线或汉字
		 *	+	重复一次或更多次
		 *	[-+.]	匹配括号中的一个
		 *	×	重复零次或更多次
		 *	.	匹配除换行符以外的任意字符
		 * */
		Log.d(TAG, str);
		Pattern pat = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pat.matcher(str).matches();
	}
	
	public static boolean isPassword(String str){
		/*(?=^.{8,}$)(?=.*\d)(?=.*\W+)(?=.*[A-Z])(?=.*[a-z])(?!.*\n).*$
		由数字/大写字母/小写字母/标点符号组成，四种都必有，8位以上*/
		Log.d(TAG, str);
		Pattern pat = Pattern.compile("^[a-zA-Z0-9]{8,20}$");
		return pat.matcher(str).matches();
	}
	
	public static boolean isUSSD(String str){
		Log.d(TAG, str);
		boolean flag = false;
		if (str.length() == 1) {
			flag = true;
		}
		if (str.length() == 2 
				&& !str.equals("1*")
				&& !(!str.startsWith("*") && !str.startsWith("#") && str.endsWith("#"))) {
			flag = true;
		}
		if (str.length() > 2) {
			if (str.startsWith("*") && str.endsWith("#")) {
				flag = true;
			} else {
				Pattern pat = Pattern.compile("((\\*|#|\\*#|\\*\\*|##)(\\d{2,3})(\\*([^*#]*)(\\*([^*#]*)(\\*([^*#]*)(\\*([^*#]*))?)?)?)?#)(.*)");
				flag = pat.matcher(str).matches();
			}
		}
		return flag;
	}
	
	public static boolean isPhoneNumber(String str){
		Log.d(TAG, str);
		Pattern pat = Pattern.compile("^((\\d{2,4}[-_－―]?)?\\d{3,8}([-_－―]?\\d{3,8})?([-_－―]?\\d{1,7})?$)|(^0?1[0-9]\\d{9}$)");
		return pat.matcher(str).matches();
	}
	
	/**
	 * @param mcc
	 * @param number
	 * @return true is international call
	 */
	@SuppressWarnings("unchecked")
	public static boolean isInternationalCall(String mcc, String number){
		List<String> code_list = (List<String>) international_code.get(mcc);
		if (number.startsWith("+") || number.startsWith("00")) {
			if (code_list != null) {
				for (String code : code_list) {
					if (number.startsWith("+" + code) || number.startsWith("00" + code)) {
						return false;
					}
				}
			}
			return true;
		} else if (mcc.equals("454")) {
			if (number.startsWith("16") || number.startsWith("15")) {
				String num = number.substring(4);
				if (code_list != null) {
					for (String code : code_list) {
						if (num.startsWith(code)) {
							return false;
						}
					}
				}
			}
			return true;
		} else if (mcc.equals("310") || mcc.equals("311")) {
			if (number.startsWith("011")) {
				if (code_list != null) {
					for (String code : code_list) {
						if (number.startsWith("011" + code)) {
							return false;
						}
					}
				}
			}
			return true;
		} 
		return false;
	}
}
