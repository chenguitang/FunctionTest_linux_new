package com.posin.function.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MyButton extends Composite implements PaintListener, MouseListener {

	private Color mBackgroundColor = new Color(Display.getDefault(), 255, 255,
			255);
	private Color mTextNormalColor = new Color(Display.getDefault(), 255, 255,
			255);
	private Color mTextPressColor = new Color(Display.getDefault(), 255, 255,
			255);
	private Color mButtonPressColor = new Color(Display.getDefault(), 4, 167,
			222);
	private Color mButtonNormalColor = new Color(Display.getDefault(), 0, 191,
			255);

	private List<OnBtnClickListener> listeners = new ArrayList<OnBtnClickListener>();

	private int mWidth;
	private int mHeight;
	private CLabel mLabel;

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            父布局
	 * @param style
	 *            类别
	 * @param buttonWidth
	 *            按钮宽度
	 * @param 按钮高度
	 *            圆的直径
	 * @param text
	 *            显示文字
	 * @param txtFont
	 *            文字格式
	 */
	public MyButton(Composite parent, int style, String text, Font txtFont) {
		super(parent, style);

		mLabel = new CLabel(this, SWT.SHADOW_NONE);
		mLabel.setAlignment(SWT.CENTER);
		mLabel.setText(text);
		mLabel.setForeground(mTextNormalColor);
		mLabel.setFont(txtFont);
		mLabel.setBackground(mButtonNormalColor);
		mLabel.setEnabled(false);

		this.addMouseListener(this);
		this.addPaintListener(this);
	}

	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		gc.setBackground(mBackgroundColor);
		gc.fillRectangle(0, 0, this.getBounds().width, this.getBounds().height);

		mLabel.setSize(this.getBounds().width, this.getBounds().height);
	}

	public void mouseDoubleClick(MouseEvent e) {

	}

	public void mouseDown(MouseEvent e) {

		GC gc = new GC(MyButton.this);
		gc.setBackground(mButtonPressColor);
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		gc.fillRectangle(0, 0, mWidth, mHeight);
		mLabel.setBackground(mButtonPressColor);
		mLabel.setForeground(mTextPressColor);
		gc.dispose();
	}

	public void mouseUp(MouseEvent e) {
		GC gc = new GC(MyButton.this);
		gc.setBackground(mButtonNormalColor);
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		gc.fillRectangle(0, 0, mWidth, mHeight);
		mLabel.setBackground(mButtonNormalColor);
		mLabel.setForeground(mTextPressColor);
		gc.dispose();
		for (OnBtnClickListener listener : listeners) {
			listener.onBtnClick(mLabel.getText());
		}
	}

	/**
	 * 添加动作事件
	 * 
	 * @param listener
	 */
	public void addBtnClickListener(OnBtnClickListener listener) {
		listeners.add(listener);
	}

	/**
	 * 移除动作事件
	 * 
	 * @param listener
	 */
	public void removeBtnClickListener(OnBtnClickListener listener) {
		listeners.remove(listener);
	}

	/**
	 * 自定义动作接口
	 * 
	 * @author Lifeng-Leven
	 * 
	 */
	public interface OnBtnClickListener {
		public void onBtnClick(String text);
	}

	/**
	 * 设置按钮父容器的背景颜色
	 * 
	 * @param color
	 */
	public void setBackGound(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * 字体正常颜色
	 * 
	 * @param color
	 */
	public void setextNormalColor(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * 字体选中时颜色
	 * 
	 * @param color
	 */
	public void setextPressColor(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * 按钮正常颜色
	 * 
	 * @param color
	 */
	public void setButtonNormalColorr(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * 按钮选中颜色
	 * 
	 * @param color
	 */
	public void setButtonPressColorr(Color color) {
		this.mBackgroundColor = color;
	}

	public void setMyLayoutData(Object btnData) {
		if (!this.isDisposed() && btnData != null) {

			MyButton.this.setLayoutData(btnData);
		}
	}
}
