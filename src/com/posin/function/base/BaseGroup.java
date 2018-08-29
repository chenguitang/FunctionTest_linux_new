package com.posin.function.base;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class BaseGroup extends Group {

	public BaseGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void checkSubclass() {
	}
}
