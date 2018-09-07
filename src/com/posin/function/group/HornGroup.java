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
import com.posin.function.view.ImageButton;
import com.posin.function.view.ImageButton.OnClickListener;

/**
 * ���Ȳ���ҳ��
 * 
 * @author Greetty
 * 
 */
public class HornGroup extends BaseGroup implements OnClickListener {

	/**
	 * pin2��Ǯ�䰴ťУ����
	 */
	public static final int PLAY_CODE = 100;
	/**
	 * pin5��Ǯ�䰴ťУ����
	 */
	public static final int STOP_CODE = 101;
	private ImageButton btnPlay;
	private ImageButton btnStop;

	private AudioClip mAudioClip;

	public HornGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {

		Image normalPlayImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/play_music_normal.png"));

		Image pressPlayImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/play_music_press.png"));

		Image normalStopImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/stop_normal.png"));

		Image pressStopImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/stop_press.png"));

		GridLayout cashDrawerGridLayout = new GridLayout();
		cashDrawerGridLayout.numColumns = 2;
		cashDrawerGridLayout.horizontalSpacing = 200;
		// cashDrawerGridLayout.marginRight = 200;
		cashDrawerGridLayout.marginBottom = 200;
		// this.setLayout(new GridLayout(5, false));
		this.setLayout(cashDrawerGridLayout);

		btnPlay = new ImageButton(this, normalPlayImage, normalPlayImage,
				pressPlayImage, normalPlayImage, HornGroup.PLAY_CODE);
		btnStop = new ImageButton(this, normalStopImage, normalStopImage,
				pressStopImage, normalStopImage, HornGroup.STOP_CODE);

		GridData PlayGridData = new GridData();
		PlayGridData.horizontalAlignment = SWT.RIGHT;
		PlayGridData.verticalAlignment = GridData.CENTER; // ��ֱ�������
		PlayGridData.horizontalSpan = 1;
		PlayGridData.grabExcessHorizontalSpace = true;
		PlayGridData.grabExcessVerticalSpace = true;
		btnPlay.setLayoutData(PlayGridData);

		GridData StopGridData = new GridData();
		StopGridData.horizontalAlignment = SWT.LEFT;
		StopGridData.verticalAlignment = GridData.CENTER; // ��ֱ�������
		StopGridData.horizontalSpan = 1;
		StopGridData.grabExcessHorizontalSpace = true;
		StopGridData.grabExcessVerticalSpace = true;
		btnStop.setLayoutData(StopGridData);
	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {
		btnPlay.addClickListener(this);
		btnStop.addClickListener(this);
	}

	/**
	 * ͼƬ��ť����¼�
	 */
	public void onClick(int viewCode) {
		switch (viewCode) {
		case PLAY_CODE:
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
			break;
		case STOP_CODE:
			try {
				if (mAudioClip != null) {
					mAudioClip.stop();
				}
				System.out.println("stop play music .");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

}
