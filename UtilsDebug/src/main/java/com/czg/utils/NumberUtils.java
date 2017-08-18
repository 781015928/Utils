package com.czg.utils;

import java.text.DecimalFormat;

public class NumberUtils {
	/**
	 * 格式化价格，强制保留2位小数
	 * 
	 * @param price
	 * @return
	 */
	public static String formatPrice(String price) {
		if (price == null) {
			return "0.00";
		}
		double d = Double.parseDouble(price);// string转double
		DecimalFormat df = new DecimalFormat("0.00");
		String format =df.format(d);
		return format;
	}

	public static String formatPrice(double price) {
		DecimalFormat df = new DecimalFormat("######0.00");
		String format =df.format(price);
		return format;
	}
	/**
	 * string转Int类型
	 * 
	 * @param string
	 * @return
	 */
	public static int String2Int(String string) {
		return Integer.valueOf(string);
	}

	/**
	 * string转double类型
	 * 
	 * @param str
	 * @return double
	 */
	public static double String2double(String str){
		if (str == null) {
			return 0;
		}else if("".equals(str)){
			return 0;
		}
		return Double.parseDouble(str);

	}
	/**
	 * 格式化价格，强制保留2位小数
	 * 
	 * @param price
	 * @return
	 */
	public static String formatPrice3(String price) {
		if (price == null) {
			return "0.000";
		}
		double d = Double.parseDouble(price);// string转double
		DecimalFormat df = new DecimalFormat("0.000");
		String format =df.format(d);
		return format;
	}

	public static String formatBankNumLong(String cardNum) {
		if(cardNum.length()<5) {
			return cardNum;
		}
		return "**** **** **** "+ cardNum.substring(cardNum.length()-4,cardNum.length());
	}
}
