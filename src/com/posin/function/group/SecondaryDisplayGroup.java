package com.posin.function.group;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Monitor;

import com.posin.function.base.BaseGroup;
import com.posin.function.font.MyFont;
import com.posin.function.module.secondary.SecDisplayImage;
import com.posin.function.module.secondary.SecDisplayText;
import com.posin.function.view.CircleButton;
import com.posin.function.view.CircleButton.OnClickListener;

/**
 * 副屏测试页面
 * 
 * @author Greetty
 * 
 */
public class SecondaryDisplayGroup extends BaseGroup implements OnClickListener {

	/**
	 * 副屏显示文字按钮校验码
	 */
	public static final int SEC_SHOW_TEXT = 102;
	/**
	 * 副屏显示图片按钮校验码
	 */
	public static final int SEC_SHOW_IMAGE = 103;

	private CircleButton cbtnShowText;
	private CircleButton cbtnShowImage;
	private SecDisplayImage mSecDisplayImage;
	private SecDisplayText mSecDisplayText;

	public SecondaryDisplayGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {

		GridLayout SecDisplayGridLayout = new GridLayout();
		SecDisplayGridLayout.numColumns = 2;
		SecDisplayGridLayout.horizontalSpacing = 200;
		// SecDisplayGridLayout.marginRight = 200;
		SecDisplayGridLayout.marginBottom = 200;
		// this.setLayout(new GridLayout(5, false));
		this.setLayout(SecDisplayGridLayout);

		cbtnShowText = new CircleButton(this, SWT.NONE, 180, "副屏显示文字",
				MyFont.fond_song_15);
		cbtnShowImage = new CircleButton(this, SWT.NONE, 180, "副屏显示图片",
				MyFont.fond_song_15);

		GridData displayTextGridData = new GridData();
		displayTextGridData.horizontalAlignment = SWT.RIGHT;
		displayTextGridData.verticalAlignment = GridData.CENTER; // 垂直方向充满
		displayTextGridData.horizontalSpan = 1;
		displayTextGridData.grabExcessHorizontalSpace = true;
		displayTextGridData.grabExcessVerticalSpace = true;
		cbtnShowText.setLayoutData(displayTextGridData);

		GridData displayImageGridData = new GridData();
		displayImageGridData.horizontalAlignment = SWT.LEFT;
		displayImageGridData.verticalAlignment = GridData.CENTER; // 垂直方向充满
		displayImageGridData.horizontalSpan = 1;
		displayImageGridData.grabExcessHorizontalSpace = true;
		displayImageGridData.grabExcessVerticalSpace = true;
		cbtnShowImage.setLayoutData(displayImageGridData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {
		cbtnShowText.addClickListener(this);
		cbtnShowImage.addClickListener(this);
	}

	/**
	 * 在副屏显示内容
	 */
	private void showOnSecondaryScreen(int showCode) {

		Monitor[] monitors = this.getDisplay().getMonitors();
		;
		if (monitors.length >= 2) {
			Monitor monitor = monitors[0];
			Monitor monitor1 = monitors[1];

			int mainWidth = monitor.getClientArea().width;
			int SecWidth = monitor1.getClientArea().width;

			if ((monitor != null) && (monitor1 != null)) {// 第二个屏幕
				if (showCode == SEC_SHOW_IMAGE) {
					if (mSecDisplayImage == null) {
						mSecDisplayImage = new SecDisplayImage(this.getShell(),
								mainWidth - SecWidth, 0,
								monitor1.getClientArea().width,
								monitor1.getClientArea().height);
					}
					mSecDisplayImage.open();
				} else {
					if (mSecDisplayText == null) {
						mSecDisplayText = new SecDisplayText(this.getShell(),
								mainWidth - SecWidth, 0,
								monitor1.getClientArea().width,
								monitor1.getClientArea().height);
					}
					mSecDisplayText.open();
				}
			}
		}
	}

	@Override
	public void onClick(String text) {
		if (text.equals("副屏显示文字")) {
			try {
				System.out.println("secondary display show text .");

				showOnSecondaryScreen(SEC_SHOW_TEXT);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (text.equals("副屏显示图片")) {
			try {
				System.out.println("secondary display show image .");

				showOnSecondaryScreen(SEC_SHOW_IMAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
