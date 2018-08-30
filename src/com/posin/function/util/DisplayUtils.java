package com.posin.function.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 屏幕显示工具类
 * 
 * @author Greetty
 * 
 */
public class DisplayUtils {

	/**
	 * 获取屏幕分辨率
	 * 
	 * 数组int[0]为宽 ， 数组int[1] 为高
	 * 
	 * @return 数组
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
