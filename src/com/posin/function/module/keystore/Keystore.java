package com.posin.function.module.keystore;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.posin.function.base.BaseDispaly;
import com.posin.function.color.MyColor;

public class Keystore extends BaseDispaly {

	private Shell shell;
	private Display display;

	public Keystore(Shell parent, int left, int top, int maxWidth, int maxHeight) {
		super(parent, left, top, maxWidth, maxHeight);
	}

	@Override
	public void initUI(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {
		shell = new Shell(parent, SWT.EMBEDDED);
		shell.setSize(maxWidth, maxHeight);
		shell.setLocation(left, top);
		display = this.shell.getDisplay();
		shell.setBackground(MyColor.colorRed);
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
		if (!shell.isDisposed()) {
			shell.dispose();
		}
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
