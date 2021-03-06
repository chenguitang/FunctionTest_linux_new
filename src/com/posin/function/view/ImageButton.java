package com.posin.function.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.posin.function.color.MyColor;

/**
 * 自定义图片按钮
 * 
 * @author Greetty
 * 
 */
public class ImageButton {
	private Image normalImage;
	private Image mouseOverImage;
	private Image mouseDownImage;
	private Image disabledImage;
	// 默认鼠标样式
	private Cursor normalCursor;
	private Cursor handCursor = new Cursor(null, SWT.CURSOR_HAND);

	private Label label;
	/**
	 * 按钮是否可用
	 */
	private boolean isDisabled = false;

	private List<OnImageBtnClickListener> listeners = new ArrayList<OnImageBtnClickListener>();

	/**
	 * 自定义带图片的按钮控件
	 * 
	 * @param parent
	 * @param normalImage
	 * @param mouseOverImage
	 * @param mouseDownImage
	 */
	public ImageButton(Composite parent, Image normalImage,
			Image mouseOverImage, Image mouseDownImage, int viewCode) {
		this(parent, normalImage, mouseOverImage, mouseDownImage, null,
				viewCode);
	}

	/**
	 * 自定义带图片的按钮控件
	 * 
	 * @param parent
	 * @param normalImage
	 *            普通按钮背景图
	 * @param mouseOverImage
	 *            鼠标悬浮按钮背景图
	 * @param mouseDownImage
	 *            鼠标点击按钮背景图
	 * @param disabledImage
	 *            按钮失效背景图
	 * @param viewCode
	 *            按钮代码
	 */
	public ImageButton(final Composite parent, Image normalImage,
			Image mouseOverImage, Image mouseDownImage, Image disabledImage,
			final int viewCode) {
		label = new Label(parent, SWT.NONE);
		this.normalImage = normalImage;
		this.mouseOverImage = mouseOverImage;
		this.mouseDownImage = mouseDownImage;
		this.disabledImage = disabledImage;
		normalCursor = parent.getCursor();
		label.setImage(normalImage);
		label.setBackground(MyColor.colorWhite);
		label.setSize(normalImage.getBounds().width,
				normalImage.getBounds().height);
		label.addListener(SWT.MouseEnter, new Listener() {
			public void handleEvent(Event event) {
				if (!isDisabled && ImageButton.this.mouseOverImage != null) {
					label.setImage(ImageButton.this.mouseOverImage);
					parent.getShell().setCursor(handCursor);
				}
			}
		});
		label.addListener(SWT.MouseExit, new Listener() {
			public void handleEvent(Event event) {
				if (!isDisabled && ImageButton.this.normalImage != null) {
					label.setImage(ImageButton.this.normalImage);
					parent.getShell().setCursor(normalCursor);
				}
			}
		});
		label.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				if (!isDisabled && ImageButton.this.mouseDownImage != null) {
					label.setImage(ImageButton.this.mouseDownImage);
				}
			}
		});
		label.addListener(SWT.MouseUp, new Listener() {
			public void handleEvent(Event event) {
				if (!isDisabled) {
					if (event.widget.equals(label)) {
						if (ImageButton.this.mouseOverImage != null) {
							label.setImage(ImageButton.this.mouseOverImage);
						}
					}
					for (OnImageBtnClickListener listener : listeners) {
						listener.onImageBtnClick(viewCode);
					}
				}
			}
		});
	}

	/**
	 * 设置按钮是否可用状态
	 * 
	 * @param isDisabled
	 */
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
		if (isDisabled) {
			if (disabledImage != null) {
				label.setImage(disabledImage);
			}
		}
	}

	/**
	 * 处理无用资源
	 */
	public void dispose() {
		if (normalImage != null && !normalImage.isDisposed()) {
			normalImage.dispose();
		}
		if (mouseOverImage != null && !mouseOverImage.isDisposed()) {
			mouseOverImage.dispose();
		}
		if (mouseDownImage != null && !mouseDownImage.isDisposed()) {
			mouseDownImage.dispose();
		}
		if (disabledImage != null && !disabledImage.isDisposed()) {
			disabledImage.dispose();
		}
	}

	/**
	 * 添加动作事件
	 * 
	 * @param listener
	 */
	public void addImageBtnClickListener(OnImageBtnClickListener listener) {
		listeners.add(listener);
	}

	/**
	 * 移除动作事件
	 * 
	 * @param listener
	 */
	public void removeImageBtnClickListener(OnImageBtnClickListener listener) {
		listeners.remove(listener);
	}

	/**
	 * 自定义动作接口
	 * 
	 * @author Lifeng-Leven
	 * 
	 */
	public interface OnImageBtnClickListener {
		public void onImageBtnClick(int viewCode);
	}

	public void setLayoutData(Object btnData) {
		if (label != null && !label.isDisposed()) {
			label.setLayoutData(btnData);
		}
	}

	public Shell getShell() {
		if (label != null && !label.isDisposed()) {
			return label.getShell();
		}
		return null;
	}
}
