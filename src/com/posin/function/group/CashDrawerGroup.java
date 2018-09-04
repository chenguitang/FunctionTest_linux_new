package com.posin.function.group;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.posin.function.base.BaseGroup;
import com.posin.function.color.MyColor;
import com.posin.function.util.Proc;
import com.posin.function.view.ImageButton;
import com.posin.function.view.ImageButton.OnClickListener;

public class CashDrawerGroup extends BaseGroup implements OnClickListener {

	/**
	 * pin2开钱箱按钮校验码
	 */
	public static final int PIN2_OPEN = 100;
	/**
	 * pin5开钱箱按钮校验码
	 */
	public static final int PIN5_OPEN = 101;
	private ImageButton btnPin2;
	private ImageButton btnPin5;

	public CashDrawerGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {

		Image normalPin2Image = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/btn_pin2_normal.png"));

		Image pressPin2Image = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/btn_pin2_press.png"));

		Image normalPin5Image = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/btn_pin5_normal.png"));

		Image pressPin5Image = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/btn_pin5_press.png"));

		GridLayout cashDrawerGridLayout = new GridLayout();
		cashDrawerGridLayout.numColumns = 2;
		cashDrawerGridLayout.horizontalSpacing = 200;
		// cashDrawerGridLayout.marginRight = 200;
		cashDrawerGridLayout.marginBottom = 200;
		// this.setLayout(new GridLayout(5, false));
		this.setLayout(cashDrawerGridLayout);

		btnPin2 = new ImageButton(this, normalPin2Image, normalPin2Image,
				pressPin2Image, normalPin2Image, CashDrawerGroup.PIN2_OPEN);
		btnPin5 = new ImageButton(this, normalPin5Image, normalPin5Image,
				pressPin5Image, normalPin5Image, CashDrawerGroup.PIN5_OPEN);

		GridData pin2GridData = new GridData();
		pin2GridData.horizontalAlignment = SWT.RIGHT;
		pin2GridData.verticalAlignment = GridData.CENTER; // 垂直方向充满
		pin2GridData.horizontalSpan = 1;
		pin2GridData.grabExcessHorizontalSpace = true;
		pin2GridData.grabExcessVerticalSpace = true;
		btnPin2.setLayoutData(pin2GridData);

		GridData pin5GridData = new GridData();
		pin5GridData.horizontalAlignment = SWT.LEFT;
		pin5GridData.verticalAlignment = GridData.CENTER; // 垂直方向充满
		pin5GridData.horizontalSpan = 1;
		pin5GridData.grabExcessHorizontalSpace = true;
		pin5GridData.grabExcessVerticalSpace = true;
		btnPin5.setLayoutData(pin5GridData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {
		btnPin2.addClickListener(this);
		btnPin5.addClickListener(this);
	}

	/**
	 * 图片按钮点击事件
	 */
	public void onClick(int viewCode) {
		switch (viewCode) {
		case PIN2_OPEN:
			try {
				System.out.println("open cashDrawer by pin2 .");
				Proc.createSuProcess("cashdrawer kickout pin2 100", "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case PIN5_OPEN:
			try {
				System.out.println("open cashDrawer by pin5 .");
				Proc.createSuProcess("cashdrawer kickout pin5 100", "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

}
