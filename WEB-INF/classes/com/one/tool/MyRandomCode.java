package com.one.tool;

import java.util.Random;

/**
 * 随机码产生工具类
 * 
 * @author chao
 *
 */
public class MyRandomCode {
	/**
	 * 生成随机码，用于手机验证，通过邮箱找回密码
	 * 
	 * @return
	 */
	public static String get(int lenth) {
		int i;
		int count = 0;
		char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < lenth) {
			i = Math.abs(r.nextInt(str.length));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

}
