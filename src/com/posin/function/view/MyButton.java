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
	 * ���췽��
	 * 
	 * @param parent
	 *            ������
	 * @param style
	 *            ���
	 * @param buttonWidth
	 *            ��ť���
	 * @param ��ť�߶�
	 *            Բ��ֱ��
	 * @param text
	 *            ��ʾ����
	 * @param txtFont
	 *            ���ָ�ʽ
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
	 * ��Ӷ����¼�
	 * 
	 * @param listener
	 */
	public void addBtnClickListener(OnBtnClickListener listener) {
		listeners.add(listener);
	}

	/**
	 * �Ƴ������¼�
	 * 
	 * @param listener
	 */
	public void removeBtnClickListener(OnBtnClickListener listener) {
		listeners.remove(listener);
	}

	/**
	 * �Զ��嶯���ӿ�
	 * 
	 * @author Lifeng-Leven
	 * 
	 */
	public interface OnBtnClickListener {
		public void onBtnClick(String text);
	}

	/**
	 * ���ð�ť�������ı�����ɫ
	 * 
	 * @param color
	 */
	public void setBackGound(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * ����������ɫ
	 * 
	 * @param color
	 */
	public void setextNormalColor(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * ����ѡ��ʱ��ɫ
	 * 
	 * @param color
	 */
	public void setextPressColor(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * ��ť������ɫ
	 * 
	 * @param color
	 */
	public void setButtonNormalColorr(Color color) {
		this.mBackgroundColor = color;
	}

	/**
	 * ��ťѡ����ɫ
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
