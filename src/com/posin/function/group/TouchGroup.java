package com.posin.function.group;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

import com.posin.function.base.BaseGroup;
import com.posin.function.color.MyColor;
import com.posin.function.module.screen.TouchTest;
import com.posin.function.util.DisplayUtils;
import com.posin.function.view.ImageButton;
import com.posin.function.view.ImageButton.OnClickListener;

public class TouchGroup extends BaseGroup implements OnClickListener {

	public static final int TOUCH_TEST_CODE = 105;

	private ImageButton touchTestBtn;
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

		Image touchNormalImage = new Image(Display.getCurrent(), this
				.getClass().getResourceAsStream("/images/btn_touch_normal.png"));
		Image touchPressImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/btn_touch_press.png"));

		touchTestBtn = new ImageButton(this, touchNormalImage,
				touchNormalImage, touchPressImage, touchNormalImage,
				TOUCH_TEST_CODE);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		touchTestBtn.setLayoutData(gridData);
	}

	@Override
	public void initData() {
		resolution = DisplayUtils.getResolution();
	}

	@Override
	public void initEvent() {
		touchTestBtn.addClickListener(this);
	}

	public void onClick(int viewCode) {

		switch (viewCode) {
		case TOUCH_TEST_CODE:
			mTouchTest = new TouchTest(this.getShell(), 0, 0, resolution[0],
					resolution[1]);
			mTouchTest.open();
			break;

		default:
			break;
		}

	}
}
