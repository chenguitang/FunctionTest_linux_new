package com.posin.function.base;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.posin.function.color.MyColor;

public abstract class BaseGroup extends Group {

	public BaseGroup(Composite parent, int style) {
		super(parent, style);
//		setBackground(MyColor.colorWhite);
	}

	@Override
	protected void checkSubclass() {
	}
	
	public abstract void initUI();
	public abstract void initData();
	public abstract void initEvent();
}
