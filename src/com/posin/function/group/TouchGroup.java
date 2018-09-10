package com.posin.function.group;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.posin.function.base.BaseGroup;
import com.posin.function.font.MyFont;
import com.posin.function.module.screen.TouchTest;
import com.posin.function.util.DisplayUtils;
import com.posin.function.view.CircleButton;
import com.posin.function.view.CircleButton.OnClickListener;

public class TouchGroup extends BaseGroup implements OnClickListener {

	public static final int TOUCH_TEST_CODE = 105;

	private CircleButton ctouchTestBtn;
	private TouchTest mTouchTest;
	private int[] resolution;

	public TouchGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		this.setLayout(gridLayout);

		ctouchTestBtn = new CircleButton(this, SWT.NONE, 180, "¥•∆¡≤‚ ‘",
				MyFont.fond_song_15);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		ctouchTestBtn.setLayoutData(gridData);
	}

	@Override
	public void initData() {
		resolution = DisplayUtils.getResolution();
	}

	@Override
	public void initEvent() {
		ctouchTestBtn.addClickListener(this);
	}

	@Override
	public void onClick(String text) {
		mTouchTest = new TouchTest(this.getShell(), 0, 0, resolution[0],
				resolution[1]);
		mTouchTest.open();
	}
}
