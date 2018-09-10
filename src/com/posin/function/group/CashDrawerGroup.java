package com.posin.function.group;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.posin.function.base.BaseGroup;
import com.posin.function.font.MyFont;
import com.posin.function.util.Proc;
import com.posin.function.view.CircleButton;
import com.posin.function.view.CircleButton.OnClickListener;

/**
 * Ç®Ïä²âÊÔÒ³Ãæ
 * 
 * @author Greetty
 * 
 */
public class CashDrawerGroup extends BaseGroup implements OnClickListener {

	private CircleButton cbtnPin2;
	private CircleButton cbtnPin5;

	public CashDrawerGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {

		GridLayout cashDrawerGridLayout = new GridLayout();
		cashDrawerGridLayout.numColumns = 2;
		cashDrawerGridLayout.horizontalSpacing = 200;
		// cashDrawerGridLayout.marginRight = 200;
		cashDrawerGridLayout.marginBottom = 200;
		// this.setLayout(new GridLayout(5, false));
		this.setLayout(cashDrawerGridLayout);

		cbtnPin2 = new CircleButton(this, SWT.NONE, 180, "¿ªÇ®Ïä(pin2)",
				MyFont.fond_song_15);
		GridData pin2GridData = new GridData();
		pin2GridData.horizontalAlignment = SWT.RIGHT;
		pin2GridData.verticalAlignment = GridData.CENTER; // ´¹Ö±·½Ïò³äÂú
		pin2GridData.horizontalSpan = 1;
		pin2GridData.grabExcessHorizontalSpace = true;
		pin2GridData.grabExcessVerticalSpace = true;
		cbtnPin2.setMyLayoutData(pin2GridData);

		cbtnPin5 = new CircleButton(this, SWT.NONE, 180, "¿ªÇ®Ïä(pin5)",
				MyFont.fond_song_15);
		GridData pin5GridData = new GridData();
		pin5GridData.horizontalAlignment = SWT.LEFT;
		pin5GridData.verticalAlignment = GridData.CENTER; // ´¹Ö±·½Ïò³äÂú
		pin5GridData.horizontalSpan = 1;
		pin5GridData.grabExcessHorizontalSpace = true;
		pin5GridData.grabExcessVerticalSpace = true;
		cbtnPin5.setLayoutData(pin5GridData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {

		cbtnPin2.addClickListener(this);
		cbtnPin5.addClickListener(this);
	}

	public void onClick(String text) {
		if (text.equals("¿ªÇ®Ïä(pin2)")) {
			try {
				System.out.println("open cashDrawer by pin2 .");
				Proc.createSuProcess("cashdrawer kickout pin2 100", "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (text.equals("¿ªÇ®Ïä(pin5)")) {
			try {
				System.out.println("open cashDrawer by pin5 .");
				Proc.createSuProcess("cashdrawer kickout pin5 100", "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
