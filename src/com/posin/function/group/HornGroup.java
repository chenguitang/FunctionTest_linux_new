package com.posin.function.group;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.posin.function.base.BaseGroup;
import com.posin.function.font.MyFont;
import com.posin.function.view.CircleButton;
import com.posin.function.view.CircleButton.OnClickListener;
import com.posin.function.view.ImageButton;

/**
 * ¿Æ∞»≤‚ ‘“≥√Ê
 * 
 * @author Greetty
 * 
 */
public class HornGroup extends BaseGroup implements OnClickListener {

	private CircleButton cbtnPlay;
	private CircleButton cbtnStop;

	private AudioClip mAudioClip;

	public HornGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {

		GridLayout cashDrawerGridLayout = new GridLayout();
		cashDrawerGridLayout.numColumns = 2;
		cashDrawerGridLayout.horizontalSpacing = 300;
		// cashDrawerGridLayout.marginRight = 200;
		cashDrawerGridLayout.marginBottom = 200;
		// this.setLayout(new GridLayout(5, false));
		this.setLayout(cashDrawerGridLayout);

		cbtnPlay = new CircleButton(this, SWT.NONE, 180, "≤•∑≈",
				MyFont.fond_song_15);
		cbtnStop = new CircleButton(this, SWT.NONE, 180, "Õ£÷π",
				MyFont.fond_song_15);

		GridData PlayGridData = new GridData();
		PlayGridData.horizontalAlignment = SWT.RIGHT;
		PlayGridData.verticalAlignment = GridData.CENTER; // ¥π÷±∑ΩœÚ≥‰¬˙
		PlayGridData.horizontalSpan = 1;
		PlayGridData.grabExcessHorizontalSpace = true;
		PlayGridData.grabExcessVerticalSpace = true;
		cbtnPlay.setLayoutData(PlayGridData);

		GridData StopGridData = new GridData();
		StopGridData.horizontalAlignment = SWT.LEFT;
		StopGridData.verticalAlignment = GridData.CENTER; // ¥π÷±∑ΩœÚ≥‰¬˙
		StopGridData.horizontalSpan = 1;
		StopGridData.grabExcessHorizontalSpace = true;
		StopGridData.grabExcessVerticalSpace = true;
		cbtnStop.setLayoutData(StopGridData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {
		cbtnPlay.addClickListener(this);
		cbtnStop.addClickListener(this);
	}

	@Override
	public void onClick(String text) {
		if (text.equals("≤•∑≈")) {
			try {
				if (mAudioClip == null) {
					URL musicUrl = HornGroup.class
							.getResource("/music/test.wav");
					// System.out.println("path: " + musicUrl.getPath());
					mAudioClip = Applet.newAudioClip(musicUrl);
				}
				mAudioClip.play();
				System.out.println("start play music .");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (text.equals("Õ£÷π")) {
			try {
				if (mAudioClip != null) {
					mAudioClip.stop();
				}
				System.out.println("stop play music .");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
