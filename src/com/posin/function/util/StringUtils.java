package com.posin.function.util;

public class StringUtils {

	/**
	 * �ո��λ�ַ���
	 * 
	 * @param length
	 *            �ַ�������
	 * @return
	 */
	public static String emptySpaceSize(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

}
