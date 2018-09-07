package com.posin.function.module.secondary;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.posin.function.base.BaseDispaly;
import com.posin.function.color.MyColor;
import com.posin.function.font.MyFont;

/**
 * 副屏显示文字
 * 
 * @author Greetty
 * 
 */
public class SecDisplayText extends BaseDispaly {

	private Shell shell;
	private Display display;

	public SecDisplayText(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {
		super(parent, left, top, maxWidth, maxHeight);
	}

	@Override
	public void initUI(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {
		shell = new Shell(parent, SWT.EMBEDDED);
		shell.setSize(maxWidth, maxHeight);
		shell.setLocation(left, top);
		shell.setLayout(new FormLayout());
		display = this.shell.getDisplay();

		shell.setBackground(new Color(display, 255, 255, 255));
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		shell.setLayout(fillLayout);
		CLabel mLabel = new CLabel(shell, SWT.SHADOW_NONE);
		mLabel.setAlignment(SWT.CENTER);

		mLabel.setText("我是副屏显示文字页面.");
		mLabel.setFont(MyFont.fond_song_light_32);
		mLabel.setForeground(MyColor.colorRed);

	}

	@Override
	public void initData() {

	}

	@Override
	public void open() {
		this.shell.open();
		while (!this.shell.isDisposed()) {
			if (!this.display.readAndDispatch()) {
				this.display.sleep();
			}
		}
	}

	@Override
	public void clsoe() {
		shell.dispose();
	}

	@Override
	public void setVisiable(boolean visiable) {
		if (!shell.isDisposed()) {
			shell.setVisible(visiable);
		}
	}

	@Override
	public boolean getVisiable() {
		if (!shell.isDisposed()) {
			return shell.isVisible();
		}
		return false;
	}

	@Override
	public Shell getShell() {
		return shell;
	}

}
