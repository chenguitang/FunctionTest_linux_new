package com.posin.function.group;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.posin.function.base.BaseGroup;
import com.posin.function.color.MyColor;
import com.posin.function.font.MyFont;
import com.posin.function.view.MyButton;

public class SerialGroup extends BaseGroup {

	private Composite topComposite;
	private GridData gridData;
	private FormLayout mFormLayout;
	private FormData mFormData;
	private GridLayout gridLayout;

	public SerialGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {
		mFormLayout = new FormLayout();
		this.setLayout(mFormLayout);

		topComposite = new Composite(this, SWT.NONE);
		mFormData = new FormData();
		mFormData.top = new FormAttachment(0, 0);
		mFormData.left = new FormAttachment(0, 0);
		mFormData.right = new FormAttachment(100, 0);
		mFormData.bottom = new FormAttachment(6, 0);

		topComposite.setLayoutData(mFormData);

		topComposite.setBackground(MyColor.colorWhite);
		// topComposite.setBackground(MyColor.colorDeepSkyBlue);

		gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.numColumns = 5;
		topComposite.setLayout(gridLayout);
		// topComposite.setLayout(new FillLayout());
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.horizontalSpan = 2;
		gridData.widthHint = 100;
		gridData.verticalAlignment = SWT.FILL;

		MyButton mBtnPort = new MyButton(topComposite, SWT.NONE, "端口",
				MyFont.fond_song_12);
		mBtnPort.setMyLayoutData(gridData);

		gridData = new GridData();
		gridData.widthHint = 100;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		MyButton mBtnBaudRate = new MyButton(topComposite, SWT.NONE, "波特率",
				MyFont.fond_song_12);
		mBtnBaudRate.setMyLayoutData(gridData);

		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalSpan = 2;
		gridData.widthHint = 100;
		// Button btnOpen = new Button(topComposite, SWT.PUSH);
		// btnOpen.setText("打开");
		// btnOpen.setLayoutData(gridData);

		MyButton mBtnOpen = new MyButton(topComposite, SWT.NONE, "打开",
				MyFont.fond_song_12);
		mBtnOpen.setMyLayoutData(gridData);

		// CLabel cLabel = new CLabel(topComposite, SWT.SHADOW_NONE);
		// cLabel.setAlignment(SWT.CENTER);
		// cLabel.setText("打开");
		// cLabel.setLayoutData(gridData);

		Label lineOne = new Label(this, SWT.NONE);
		lineOne.setBackground(MyColor.colorMiddleBlack);
		mFormData = new FormData(100, 1);
		mFormData.top = new FormAttachment(topComposite, 5, SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 10);
		mFormData.right = new FormAttachment(100, -10);
		lineOne.setLayoutData(mFormData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {

	}

}
