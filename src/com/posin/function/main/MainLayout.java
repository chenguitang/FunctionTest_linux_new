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
	// 顶部标题栏
	private Composite titleComposite;
	// 退出按钮
	private Label exitAppLabel;
	// 功能切换导航栏
	private CTabFolder tabFolder;
	// 功能切换页面
	private CashDrawerGroup cashDrawerGroup;
	private SerialGroup serialGroup;
	private HornGroup hornGroup;
	private WifiGroup wifiGroup;
	private SecondaryDisplayGroup secondaryDisplayGroup;
	private DateTimeGroup dateTimeGroup;
	private EthernetGroup ethernetGroup;
	private TouchGroup touchGroup;
	private AboutGroup aboutGroup;
	// 功能切换选项集合
	private ArrayList<BaseGroup> listGroups;

	// 宽
	private int mWidth = 1920;
	// 高
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
		shell.setText("功能测试");
		FormLayout formlayout = new FormLayout(); // 创建表格布局对象
		shell.setLayout(formlayout);

		displayTitle();
		displayTabPages();
		initEvent();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		int[] resolution = DisplayUtils.getResolution();
		mWidth = resolution[0];
		mHeight = resolution[1];
	}

	/**
	 * 初始化事件监听
	 */
	private void initEvent() {
		exitAppLabel.addMouseListener(this);

	}

	/**
	 * 顶部标题
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

		// 应用名称
		Label appNameLabel = new Label(titleComposite, SWT.CENTER);
		appNameLabel.setText(" 功能测试");
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

		// 退出按钮
		exitAppLabel = new Label(titleComposite, SWT.CENTER);
		exitAppLabel.setAlignment(SWT.CENTER);
		exitAppLabel.setText(" 退 出  ");
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
	 * 功能列表切换页面
	 */
	private void displayTabPages() {
		tabFolder = new CTabFolder(shell, SWT.NONE);// 声明一个选项卡容器
		tabFolder.setBackground(MyColor.colorGainsboro);
		tabFolder.setTabHeight(50);
		// tabFolder.setSelectionBackground(MyColor.colorDeepSkyBlue);
		tabFolder.setSelectionBackground(MyColor.colorWhite);

		tabFolder.setSelectionForeground(MyColor.colorDeepSkyBlue);
		tabFolder.setForeground(MyColor.colorMiddleBlack);

		// 控制布局大小
		FormData data = new FormData();
		data.top = new FormAttachment(titleComposite, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100, 0);
		tabFolder.setLayoutData(data);
		displayItems();
	}

	/**
	 * Item 标题选项页
	 */
	private void displayItems() {

		// 实例化每个功能选项
		cashDrawerGroup = new CashDrawerGroup(tabFolder, SWT.NONE);
		serialGroup = new SerialGroup(tabFolder, SWT.NONE);
		hornGroup = new HornGroup(tabFolder, SWT.NONE);
		wifiGroup = new WifiGroup(tabFolder, SWT.NONE);
		secondaryDisplayGroup = new SecondaryDisplayGroup(tabFolder, SWT.NONE);
		dateTimeGroup = new DateTimeGroup(tabFolder, SWT.NONE);
		ethernetGroup = new EthernetGroup(tabFolder, SWT.NONE);
		touchGroup = new TouchGroup(tabFolder, SWT.NONE);
		aboutGroup = new AboutGroup(tabFolder, SWT.NONE);

		// 把每个选项添加到集合中
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

		// 功能选项栏标题数组
		String[] tabItemName = AppConfig.getTabItemName();

		// 循环把每个选项添加到父控件中
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
	 * 鼠标双击
	 */
	public void mouseDoubleClick(MouseEvent arg0) {

	}

	/**
	 * 鼠标按下
	 */
	public void mouseDown(MouseEvent arg0) {

	}

	/**
	 * 鼠标抬起
	 */
	public void mouseUp(MouseEvent mouseEvent) {
		Widget actinView = mouseEvent.widget;

		if (actinView.equals(exitAppLabel)) {
			System.out.println("....   exit app ....");
			System.exit(0);
		}

	}

}
