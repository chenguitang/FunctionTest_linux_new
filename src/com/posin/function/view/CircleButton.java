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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


public class CircleButton extends Composite implements PaintListener,
		MouseListener {

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

	private List<OnClickListener> listeners = new ArrayList<OnClickListener>();

	// 圆的直径
	private int mWidth;
	private CLabel mLabel;

	Font font = new Font(Display.getDefault(), "宋体", 14, SWT.BOLD);

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            父布局
	 * @param style
	 *            类别
	 * @param buttonWidth
	 *            圆的直径
	 * @param text
	 *            显示文字
	 * @param txtFont
	 *            文字格式
	 */
	public CircleButton(Composite parent, int style, int buttonWidth,
			String text, Font txtFont) {
		super(parent, style);

		this.mWidth = buttonWidth;
		this.setSize(buttonWidth, buttonWidth);
		this.setBackground(mBackgroundColor);
		mLabel = new CLabel(this, SWT.SHADOW_NONE);
		mLabel.setAlignment(SWT.CENTER);
		mLabel.setText(text);
		mLabel.setBounds(10, (mWidth - 20) / 2, buttonWidth - 20, 20);
		mLabel.setForeground(mTextNormalColor);
		mLabel.setFont(txtFont);
		mLabel.setBackground(mButtonNormalColor);
		mLabel.setEnabled(false);

		CLabel myLabel = new CLabel(this, SWT.SHADOW_NONE);
		myLabel.setBounds(mWidth, mWidth, 0, 0);
		myLabel.setEnabled(false);

		this.addPaintListener(this);
		this.addMouseListener(this);
	}

	public void paintControl(PaintEvent e) {
		// System.out.println("width: " + this.getBounds().width);
		// System.out.println("height: " + this.getBounds().height);

		GC gc = e.gc;
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		gc.setBackground(mButtonNormalColor);
		gc.fillOval(0, 0, mWidth, mWidth);
	}

	public void mouseDoubleClick(MouseEvent e) {

	}

	public void mouseDown(MouseEvent e) {

		// 计算点到圆心的距离
		int result = (int) Math.sqrt(Math.pow((e.x - mWidth / 2), 2)
				+ Math.pow((e.y - mWidth / 2), 2));

		if (result <= mWidth / 2) {
			GC gc = new GC(CircleButton.this);
			gc.setBackground(mButtonPressColor);
			gc.setAdvanced(true);
			gc.setAntialias(SWT.ON);
			gc.fillOval(0, 0, mWidth, mWidth);
			mLabel.setBackground(mButtonPressColor);
			mLabel.setForeground(mTextPressColor);
			gc.dispose();

		}
	}

	public void mouseUp(MouseEvent e) {
		GC gc = new GC(CircleButton.this);
		gc.setBackground(mButtonNormalColor);
		gc.setAdvanced(true);
		gc.setAntialias(SWT.ON);
		gc.fillOval(0, 0, mWidth, mWidth);
		mLabel.setBackground(mButtonNormalColor);
		mLabel.setForeground(mTextPressColor);
		gc.dispose();
		for (OnClickListener listener : listeners) {
			listener.onClick(mLabel.getText());
		}
	}

	/**
	 * 添加动作事件
	 * 
	 * @param listener
	 */
	public void addClickListener(OnClickListener listener) {
		listeners.add(listener);
	}

	/**
	 * 移除动作事件
	 * 
	 * @param listener
	 */
	public void removeClickListener(OnClickListener listener) {
		listeners.remove(listener);
	}

	/**
	 * 自定义动作接口
	 * 
	 * @author Lifeng-Leven
	 * 
	 */
	public interface OnClickListener {
		public void onClick(String text);
	}

	 /**
	 * 设置按钮父容器的背景颜色
	 *
	 * @param color
	 */
	 public void setMyBackGround(Color color) {
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
		if (!this.isDisposed()) {
			CircleButton.this.setLayoutData(btnData);
		}
	}

}
