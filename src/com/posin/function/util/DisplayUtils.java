package com.posin.function.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

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
		// Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Dimension scrnsize = toolkit.getScreenSize();
		// int[] resolution = new int[2];
		// resolution[0] = scrnsize.width;
		// resolution[1] = scrnsize.height;
		// return resolution;

		int[] resolution = new int[2];
		Display display = Display.getDefault();
		Monitor[] monitors = display.getMonitors();

		if (monitors.length == 1) {
			resolution[0] = monitors[0].getClientArea().width;
			resolution[1] = monitors[0].getClientArea().height;
		} else if (monitors.length >= 2) {
			resolution[1] = monitors[0].getClientArea().height;
			resolution[0] = monitors[0].getClientArea().width;
			for (int i = 1; i < monitors.length; i++) {
				resolution[0] -= monitors[i].getClientArea().width;
			}
		}
		return resolution;

	}

	/**
	 * 获取屏幕分辨率
	 * 
	 * 数组int[0]为宽 ， 数组int[1] 为高
	 * 
	 * @return 数组
	 */
	public static int[] getResolution2() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		int[] resolution = new int[2];
		resolution[0] = scrnsize.width;
		resolution[1] = scrnsize.height;
		return resolution;
	}

	public static void main(String[] args) {
		int[] resolution = getResolution();

		System.out.println("width: " + resolution[0]);
		System.out.println("height: " + resolution[1]);
	}

}
