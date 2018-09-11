package com.posin.function.group;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.posin.function.base.BaseGroup;
import com.posin.function.color.MyColor;
import com.posin.function.font.MyFont;
import com.posin.function.module.keystore.Keystore;
import com.posin.function.module.screen.TouchTest;
import com.posin.function.view.MyButton;
import com.posin.function.view.MyButton.OnBtnClickListener;
import com.posin.function.view.MyText;

public class SerialGroup extends BaseGroup implements OnBtnClickListener {

	private static final int BTN_PORT_CODE = 100;
	private static final int BTN_BAUDRATE_CODE = 101;
	private static final int BTN_OPEN_CODE = 102;
	private static final int BTN_SEND_CODE = 103;

	private Composite topComposite;
	private Composite sendActionComposite;
	private Composite receiverActionComposite;

	private GridData gridData;
	private FormLayout mFormLayout;
	private FormData mFormData;
	private GridLayout gridLayout;
	private MyButton mBtnPort; // �˿�
	private MyButton mBtnBaudRate; // ������
	private MyButton mBtnOpen; // ��
	private MyButton mbtnSend; // ����
	private MyButton mbtnClear; // ���
	// ���ͽ�������
	private MyText sendInputData;
	private Text receiverTextData;
	// ���ͽ����ı�����
	private Button btnReceiverText;
	private Button btnReceiverHex;
	private Button btnSendText;
	private Button btnSendHex;

	public SerialGroup(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void initUI() {
		mFormLayout = new FormLayout();
		this.setLayout(mFormLayout);

		initTopUI();
		initSendUI();
		initSendDataUI();
		initReceiverUI();
		initReceiverDataUI();
	}

	/**
	 * ����������ť��ѡ��˿ڣ������ʣ��򿪻�رգ�
	 */
	private void initTopUI() {

		topComposite = new Composite(this, SWT.NONE);
		mFormData = new FormData();
		mFormData.top = new FormAttachment(0, 0);
		mFormData.left = new FormAttachment(0, 0);
		mFormData.right = new FormAttachment(100, 0);
		mFormData.bottom = new FormAttachment(6, 0);
		topComposite.setLayoutData(mFormData);
		topComposite.setBackground(MyColor.colorWhite);
		// topComposite.setBackground(MyColor.colorDeepSkyBlue);

		gridLayout = new GridLayout();
		gridLayout.marginHeight = 5;
		gridLayout.marginWidth = 0;
		// gridLayout.makeColumnsEqualWidth = true;
		gridLayout.numColumns = 5;
		topComposite.setLayout(gridLayout);

		mBtnPort = new MyButton(topComposite, SWT.NONE, "�˿�",
				MyFont.fond_song_12, BTN_PORT_CODE);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.horizontalSpan = 2;
		gridData.widthHint = 150;
		gridData.verticalAlignment = SWT.FILL;
		mBtnPort.setMyLayoutData(gridData);

		mBtnBaudRate = new MyButton(topComposite, SWT.NONE, "������",
				MyFont.fond_song_12, BTN_BAUDRATE_CODE);
		gridData = new GridData();
		gridData.widthHint = 150;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		mBtnBaudRate.setMyLayoutData(gridData);

		mBtnOpen = new MyButton(topComposite, SWT.NONE, "��",
				MyFont.fond_song_12, BTN_OPEN_CODE);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalSpan = 2;
		gridData.widthHint = 150;
		mBtnOpen.setMyLayoutData(gridData);

		Label lineOne = new Label(this, SWT.NONE);
		lineOne.setBackground(MyColor.colorMiddleBlack);
		mFormData = new FormData(100, 1);
		mFormData.top = new FormAttachment(topComposite, 0, SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 10);
		mFormData.right = new FormAttachment(100, -10);
		lineOne.setLayoutData(mFormData);
	}

	/**
	 * �����в���
	 */
	private void initSendUI() {

		sendActionComposite = new Composite(this, SWT.NONE);
		mFormData = new FormData();
		mFormData.top = new FormAttachment(topComposite, 5, SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 0);
		mFormData.right = new FormAttachment(100, 0);
		mFormData.bottom = new FormAttachment(11, 0);
		sendActionComposite.setLayoutData(mFormData);
		sendActionComposite.setBackground(MyColor.colorWhite);

		gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 20;
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 100;
		sendActionComposite.setLayout(gridLayout);

		CLabel sendTip = new CLabel(sendActionComposite, SWT.SHADOW_NONE);
		sendTip.setAlignment(SWT.CENTER);
		sendTip.setText("����: ");
		sendTip.setFont(MyFont.fond_song_14);
		sendTip.setBackground(MyColor.colorWhite);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		sendTip.setLayoutData(gridData);

		Composite sendTypeComposite = new Composite(sendActionComposite,
				SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		// gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		// gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalAlignment = SWT.FILL;
		sendTypeComposite.setLayoutData(gridData);
		sendTypeComposite.setBackground(MyColor.colorWhite);

		sendTypeComposite.setLayout(new RowLayout());

		btnSendText = new Button(sendTypeComposite, SWT.RADIO);
		btnSendText.setText("�ı�");
		// btnSendText.setSize(60, 40);
		btnSendText.setFont(MyFont.fond_song_12);
		btnSendText.setBackground(MyColor.colorWhite);
		RowData rowdata = new RowData(60, 40);
		btnSendText.setLayoutData(rowdata);

		btnSendHex = new Button(sendTypeComposite, SWT.RADIO);
		btnSendHex.setText("ʮ������");
		rowdata = new RowData(100, 40);
		btnSendHex.setFont(MyFont.fond_song_12);
		btnSendHex.setBackground(MyColor.colorWhite);
		btnSendHex.setLayoutData(rowdata);

		mbtnSend = new MyButton(sendActionComposite, SWT.NONE, " ���� ",
				MyFont.fond_song_12, BTN_SEND_CODE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		mbtnSend.setLayoutData(gridData);
	}

	/**
	 * ���������������
	 */
	private void initSendDataUI() {
		sendInputData = new MyText(this, SWT.BORDER | SWT.LEFT | SWT.MULTI);
		sendInputData.setFont(MyFont.fond_song_13);
		// sendInputData.setBackground(MyColor.colorDeepSkyBlue);

		mFormData = new FormData();
		mFormData.top = new FormAttachment(sendActionComposite, 10, SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 10);
		mFormData.right = new FormAttachment(100, -10);
		mFormData.bottom = new FormAttachment(17, 0);
		sendInputData.setLayoutData(mFormData);
	}

	/**
	 * ���������в���
	 */
	private void initReceiverUI() {

		receiverActionComposite = new Composite(this, SWT.NONE);
		mFormData = new FormData();
		mFormData.top = new FormAttachment(sendInputData, 5, SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 0);
		mFormData.right = new FormAttachment(100, 0);
		mFormData.bottom = new FormAttachment(22, 0);
		receiverActionComposite.setLayoutData(mFormData);
		receiverActionComposite.setBackground(MyColor.colorWhite);
		receiverActionComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 20;
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 100;
		receiverActionComposite.setLayout(gridLayout);

		CLabel receiverTip = new CLabel(receiverActionComposite,
				SWT.SHADOW_NONE);
		receiverTip.setAlignment(SWT.CENTER);
		receiverTip.setText("����: ");
		receiverTip.setFont(MyFont.fond_song_14);
		receiverTip.setBackground(MyColor.colorWhite);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		receiverTip.setLayoutData(gridData);

		Composite receiverTypeComposite = new Composite(
				receiverActionComposite, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		// gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		// gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalAlignment = SWT.FILL;
		receiverTypeComposite.setLayoutData(gridData);
		receiverTypeComposite.setBackground(MyColor.colorWhite);

		receiverTypeComposite.setLayout(new RowLayout());

		btnReceiverText = new Button(receiverTypeComposite, SWT.RADIO);
		btnReceiverText.setText("�ı�");
		// btnSendText.setSize(60, 40);
		btnReceiverText.setFont(MyFont.fond_song_12);
		btnReceiverText.setBackground(MyColor.colorWhite);
		RowData rowdata = new RowData(60, 40);
		btnReceiverText.setLayoutData(rowdata);

		btnReceiverHex = new Button(receiverTypeComposite, SWT.RADIO);
		btnReceiverHex.setText("ʮ������");
		rowdata = new RowData(100, 40);
		btnReceiverHex.setFont(MyFont.fond_song_12);
		btnReceiverHex.setBackground(MyColor.colorWhite);
		btnReceiverHex.setLayoutData(rowdata);

		mbtnClear = new MyButton(receiverActionComposite, SWT.NONE, " ��� ",
				MyFont.fond_song_12, BTN_SEND_CODE);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.CENTER;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		mbtnClear.setLayoutData(gridData);
	}

	/**
	 * ���������������
	 */
	private void initReceiverDataUI() {
		receiverTextData = new Text(this, SWT.BORDER | SWT.LEFT | SWT.MULTI
				| SWT.READ_ONLY | SWT.V_SCROLL);
		receiverTextData.setFont(MyFont.fond_song_15);
		receiverTextData.setEditable(false);

		mFormData = new FormData();
		mFormData.top = new FormAttachment(receiverActionComposite, 10,
				SWT.BOTTOM);
		mFormData.left = new FormAttachment(0, 10);
		mFormData.right = new FormAttachment(100, -10);
		mFormData.bottom = new FormAttachment(100, 0);
		receiverTextData.setLayoutData(mFormData);

	}

	@Override
	public void initData() {

	}

	@Override
	public void initEvent() {
		mBtnPort.addBtnClickListener(this);
		mBtnBaudRate.addBtnClickListener(this);
		mBtnOpen.addBtnClickListener(this);
	}

	@Override
	public void onBtnClick(int viewCode) {
		switch (viewCode) {
		case BTN_PORT_CODE:

			break;
		case BTN_BAUDRATE_CODE:

			break;
		case BTN_OPEN_CODE:

			break;

		default:
			break;
		}
	}

}
