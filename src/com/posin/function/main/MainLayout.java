package com.posin.function.main;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import com.posin.function.base.BaseGroup;
import com.posin.function.color.MyColor;
import com.posin.function.constant.AppConfig;
import com.posin.function.font.MyFont;
import com.posin.function.group.AboutGroup;
import com.posin.function.group.CashDrawerGroup;
import com.posin.function.group.DateTimeGroup;
import com.posin.function.group.EthernetGroup;
import com.posin.function.group.HornGroup;
import com.posin.function.group.SecondaryDisplayGroup;
import com.posin.function.group.SerialGroup;
import com.posin.function.group.TouchGroup;
import com.posin.function.group.WifiGroup;
import com.posin.function.util.DisplayUtils;
import com.posin.function.util.StringUtils;

public class MainLayout implements MouseListener {

	protected Shell shell;
	// ����������
	private Composite titleComposite;
	// �˳���ť
	private Label exitAppLabel;
	// �����л�������
	private CTabFolder tabFolder;
	// �����л�ҳ��
	private CashDrawerGroup cashDrawerGroup;
	private SerialGroup serialGroup;
	private HornGroup hornGroup;
	private WifiGroup wifiGroup;
	private SecondaryDisplayGroup secondaryDisplayGroup;
	private DateTimeGroup dateTimeGroup;
	private EthernetGroup ethernetGroup;
	private TouchGroup touchGroup;
	private AboutGroup aboutGroup;
	// �����л�ѡ���
	private ArrayList<BaseGroup> listGroups;

	// ��
	private int mWidth = 1920;
	// ��
	private int mHeight = 1080;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// MainLayout window = new MainLayout();
			// window.open();
			MainLayout.getInstance().open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class MainLayoutHolder {
		private static final MainLayout INSTANCE = new MainLayout();
	}

	private MainLayout() {
	}

	public static final MainLayout getInstance() {
		return MainLayoutHolder.INSTANCE;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		initData();
		// shell = new Shell();
		shell = new Shell(SWT.NONE);
		// shell.setSize(1920, 1080);
		shell.setBounds(0, 0, mWidth, mHeight);
		shell.setText("���ܲ���");
		FormLayout formlayout = new FormLayout(); // ������񲼾ֶ���
		shell.setLayout(formlayout);

		displayTitle();
		displayTabPages();
		initEvent();
	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		int[] resolution = DisplayUtils.getResolution();
		mWidth = resolution[0];
		mHeight = resolution[1];
	}

	/**
	 * ��ʼ���¼�����
	 */
	private void initEvent() {
		exitAppLabel.addMouseListener(this);

	}

	/**
	 * ��������
	 */
	private void displayTitle() {
		titleComposite = new Composite(shell, SWT.NONE);
		titleComposite.setBackground(MyColor.colorDeepSkyBlue);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.left = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(5, 0);
		data.right = new FormAttachment(100, 0);
		titleComposite.setLayoutData(data);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		titleComposite.setLayout(gridLayout);

		// Ӧ������
		Label appNameLabel = new Label(titleComposite, SWT.CENTER);
		appNameLabel.setText(" ���ܲ���");
		appNameLabel.setFont(MyFont.fond_song_18);
		appNameLabel.setBackground(MyColor.colorDeepSkyBlue);
		appNameLabel.setForeground(MyColor.colorWhite);

		GridData appNamegridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		appNamegridData.horizontalSpan = 1;
		appNamegridData.verticalSpan = 1;
		appNamegridData.horizontalAlignment = GridData.BEGINNING;
		appNamegridData.verticalAlignment = GridData.CENTER;
		appNameLabel.setLayoutData(appNamegridData);

		// �˳���ť
		exitAppLabel = new Label(titleComposite, SWT.CENTER);
		exitAppLabel.setAlignment(SWT.CENTER);
		exitAppLabel.setText(" �� ��  ");
		exitAppLabel.setBackground(MyColor.colorDeepSkyBlue);
		exitAppLabel.setForeground(MyColor.colorWhite);
		exitAppLabel.setFont(MyFont.fond_song_18);

		GridData exitGriData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		exitGriData.horizontalSpan = 1;
		exitGriData.verticalSpan = 1;
		exitGriData.horizontalAlignment = GridData.END;
		exitGriData.verticalAlignment = GridData.CENTER;
		exitAppLabel.setLayoutData(exitGriData);
	}

	/**
	 * �����б��л�ҳ��
	 */
	private void displayTabPages() {
		tabFolder = new CTabFolder(shell, SWT.NONE);// ����һ��ѡ�����
		tabFolder.setBackground(MyColor.colorGainsboro);
		tabFolder.setTabHeight(50);
		// tabFolder.setSelectionBackground(MyColor.colorDeepSkyBlue);
		tabFolder.setSelectionBackground(MyColor.colorWhite);

		tabFolder.setSelectionForeground(MyColor.colorDeepSkyBlue);
		tabFolder.setForeground(MyColor.colorMiddleBlack);

		// ���Ʋ��ִ�С
		FormData data = new FormData();
		data.top = new FormAttachment(titleComposite, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100, 0);
		tabFolder.setLayoutData(data);
		displayItems();
	}

	/**
	 * Item ����ѡ��ҳ
	 */
	private void displayItems() {

		// ʵ����ÿ������ѡ��
		cashDrawerGroup = new CashDrawerGroup(tabFolder, SWT.NONE);
		serialGroup = new SerialGroup(tabFolder, SWT.NONE);
		hornGroup = new HornGroup(tabFolder, SWT.NONE);
		wifiGroup = new WifiGroup(tabFolder, SWT.NONE);
		secondaryDisplayGroup = new SecondaryDisplayGroup(tabFolder, SWT.NONE);
		dateTimeGroup = new DateTimeGroup(tabFolder, SWT.NONE);
		ethernetGroup = new EthernetGroup(tabFolder, SWT.NONE);
		touchGroup = new TouchGroup(tabFolder, SWT.NONE);
		aboutGroup = new AboutGroup(tabFolder, SWT.NONE);

		// ��ÿ��ѡ����ӵ�������
		listGroups = new ArrayList<BaseGroup>();
		listGroups.add(cashDrawerGroup);
		listGroups.add(serialGroup);
		listGroups.add(hornGroup);
		listGroups.add(wifiGroup);
		listGroups.add(secondaryDisplayGroup);
		listGroups.add(dateTimeGroup);
		listGroups.add(ethernetGroup);
		listGroups.add(touchGroup);
		listGroups.add(aboutGroup);

		// ����ѡ������������
		String[] tabItemName = AppConfig.getTabItemName();

		// ѭ����ÿ��ѡ����ӵ����ؼ���
		for (int i = 0; i < tabItemName.length; i++) {
			if (listGroups.size() > i) {
				CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
				tabItem.setFont(MyFont.fond_song_13);
				String emptySpaceStr = null;
				if (mWidth >= 1920) {
					emptySpaceStr = StringUtils
							.emptySpaceSize((18 - tabItemName[i].length()) / 2);
					tabItem.setText(emptySpaceStr + tabItemName[i]
							+ emptySpaceStr + " ");
				} else {
					emptySpaceStr = StringUtils
							.emptySpaceSize((13 - tabItemName[i].length()) / 2);
					if (i == 3) {
						emptySpaceStr = emptySpaceStr + "   ";
					}
					tabItem.setText(emptySpaceStr + tabItemName[i]
							+ emptySpaceStr);
				}
				tabItem.setControl(listGroups.get(i));
			}

		}
	}

	/**
	 * ���˫��
	 */
	public void mouseDoubleClick(MouseEvent arg0) {

	}

	/**
	 * ��갴��
	 */
	public void mouseDown(MouseEvent arg0) {

	}

	/**
	 * ���̧��
	 */
	public void mouseUp(MouseEvent mouseEvent) {
		Widget actinView = mouseEvent.widget;

		if (actinView.equals(exitAppLabel)) {
			System.out.println("....   exit app ....");
			System.exit(0);
		}

	}

}
