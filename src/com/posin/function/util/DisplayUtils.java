package com.posin.function.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * ��Ļ��ʾ������
 * 
 * @author Greetty
 * 
 */
public class DisplayUtils {

	/**
	 * ��ȡ��Ļ�ֱ���
	 * 
	 * ����int[0]Ϊ�� �� ����int[1] Ϊ��
	 * 
	 * @return ����
	 */
	public static int[] getResolution() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		int[] resolution = new int[2];
		resolution[0] = scrnsize.width;
		resolution[1] = scrnsize.height;
		return resolution;
	}

	public static void main(String[] args) {
		int[] resolution = getResolution();
		
		System.out.println("width: "+resolution[0]);
		System.out.println("height: "+resolution[1]);
	}

}
