package com.posin.function.module.screen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.posin.function.base.BaseDispaly;
import com.posin.function.util.DisplayUtils;

/**
 * ´¥ÆÁ²âÊÔÒ³Ãæ
 * 
 * @author Greetty
 * 
 */
public class TouchTest extends BaseDispaly implements PaintListener,
		MouseListener, MouseMoveListener {

	private static final int BLOCK_BASE = (1024 / 21);

	private int BLOCK_SIZE_X = BLOCK_BASE;
	private int BLOCK_SIZE_Y = BLOCK_BASE;

	private Shell shell;
	private Display display;
	private Label exitLabel;
	private int mWidth;
	private int mHeight;

	public TouchTest(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {
		super(parent, left, top, maxWidth, maxHeight);
	}

	@Override
	public void initUI(Shell parent, int left, int top, int maxWidth,
			int maxHeight) {

		shell = new Shell(parent, SWT.EMBEDDED);
		shell.setSize(maxWidth, maxHeight);
		shell.setLocation(left, top);
		shell.setLayout(new FormLayout());
		display = this.shell.getDisplay();
		shell.setBackground(new Color(display, 255, 255, 255));

		Image exitBtnImage = new Image(Display.getCurrent(), this.getClass()
				.getResourceAsStream("/images/exit.png"));
		exitLabel = new Label(shell, SWT.NONE);
		exitLabel.setImage(exitBtnImage);
		exitLabel.setBackground(new Color(display, 255, 255, 255));

		initEvent();
	}

	private void initEvent() {
		shell.addPaintListener(this);
		shell.addMouseListener(this);
		shell.addMouseMoveListener(this);
		exitLabel.addMouseListener(this);
	}

	@Override
	public void initData() {
		int[] resolution = DisplayUtils.getResolution();
		mWidth = resolution[0];
		mHeight = resolution[1];

		if (mWidth >= 1920 || mHeight >= 1080) {
			BLOCK_SIZE_X = (int) (BLOCK_BASE * 1.5f);
			BLOCK_SIZE_Y = (int) (BLOCK_BASE * 1.5f);
		}
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
			shell.close();
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

	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		for (int y = 0; y < mHeight; y += BLOCK_SIZE_Y) {
			gc.drawLine(0, y, mWidth, y);
		}
		for (int x = 0; x < mWidth; x += BLOCK_SIZE_X) {
			gc.drawLine(x, 0, x, mHeight);
		}
	}

	public void mouseDoubleClick(MouseEvent e) {

	}

	public void mouseDown(MouseEvent e) {

		System.out.println("mouse down ...");
		if (e.widget.equals(shell)) {
			int downX = e.x;
			int downY = e.y;
			// ¸Ä±ä±³¾°ÑÕÉ«
			int x = (downX / BLOCK_SIZE_X) * BLOCK_SIZE_X;
			int y = (downY / BLOCK_SIZE_Y) * BLOCK_SIZE_Y;
			GC newGc = new GC(shell);
			newGc.setBackground(new Color(display, 0, 191, 255));
			newGc.fillRectangle(x + 1, y + 1, BLOCK_SIZE_X - 1,
					BLOCK_SIZE_Y - 1);
			newGc.dispose();
		} else {
			shell.setVisible(false);
			shell.close();
			shell.dispose();
		}

	}

	public void mouseUp(MouseEvent e) {

	}

	public void mouseMove(MouseEvent e) {
		System.out.println("mouse move ...");
		if (e.x > 0 && e.x < BLOCK_SIZE_X && e.y > 0 && e.y < BLOCK_SIZE_Y) {
			exitLabel.setBackground(new Color(display, 0, 191, 255));
		}
		int x = (e.x / BLOCK_SIZE_X) * BLOCK_SIZE_X;
		int y = (e.y / BLOCK_SIZE_Y) * BLOCK_SIZE_Y;
		GC mouseGc = new GC(shell);
		mouseGc.setBackground(new Color(display, 0, 191, 255));
		mouseGc.fillRectangle(x + 1, y + 1, BLOCK_SIZE_X - 1, BLOCK_SIZE_Y - 1);
		mouseGc.dispose();
	}

}
