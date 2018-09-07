package com.posin.function.base;

import org.eclipse.swt.widgets.Shell;

public abstract class BaseDispaly {

	public BaseDispaly(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {
		initUI(parent, left, top, maxWidth, maxHeight);
		initData();
	}

	public abstract void initUI(Shell parent, int left, int top, int maxWidth,
			int maxHeight);

	public abstract void initData();

	public abstract void open();

	public abstract void clsoe();

	public abstract void setVisiable(boolean visiable);

	public abstract boolean getVisiable();

	public abstract Shell getShell();
}
