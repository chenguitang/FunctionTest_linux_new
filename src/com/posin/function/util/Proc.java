package com.posin.function.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Proc {

	static final String TAG = "Proc";

	static MySuProcess mSu;

	public static interface Callback {
		public void readLine(String line);
	}

	public static class MySuProcess implements MyStreamWriter {
		final ArrayList<String> mOut = new ArrayList<String>();
		Process mProc = null;

		static final String CMD_FINISH = "**CMD-RESULT=";

		boolean mCmdFinish = false;
		int mCmdResult = 0;

		public MySuProcess() {
		}

		public void write(String line) {

			synchronized (mOut) {
				mOut.add(line);
				if (line != null && line.startsWith(CMD_FINISH)) {
					mCmdFinish = true;
					try {
						line = line.substring(CMD_FINISH.length());
						mCmdResult = Integer.parseInt(line);
					} catch (Throwable e) {
						e.printStackTrace();
					}
					return;
				}

				if (mOut.size() > 1024)
					mOut.remove(0);
			}
		}

		public int exec(String cmd, Callback callback, int timeout,
				String outEconding) throws IOException {

			if (mProc == null) {
				mProc = createSuProcess();
				(new Thread(new InputRunnable(mProc.getInputStream(), this)))
						.start();
			}

			System.out.println("exec : " + cmd);

			cmd += "\necho " + CMD_FINISH + "$?\n";

			int pos = 0;
			synchronized (mOut) {
				mOut.clear();
			}
			mCmdFinish = false;

			OutputStream os = mProc.getOutputStream();
			os.write(cmd.getBytes(outEconding));
			// os.write("exit\n".getBytes(outEconding));
			os.flush();

			long start = System.currentTimeMillis();
			while (!mCmdFinish) {
				if (timeout > 0
						&& (System.currentTimeMillis() - start) > timeout)
					break;

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}

				if (callback != null) {
					synchronized (mOut) {
						if (mOut.size() > pos) {
							for (; pos < mOut.size(); pos++) {
								callback.readLine(mOut.get(pos));
							}
						}
					}
				}
			}

			if (callback != null) {
				synchronized (mOut) {
					if (mOut.size() > pos) {
						for (; pos < mOut.size(); pos++) {
							callback.readLine(mOut.get(pos));
						}
					}
				}
			}

			if (mCmdFinish)
				return mCmdResult;
			else {
				if (timeout > 0) {
					mProc.destroy();
					mProc = null;
				}
			}
			return -1;
		}

		public int exec(String cmd, ArrayList<String> output, int timeout,
				String outEconding) throws IOException {

			if (mProc == null) {
				mProc = createSuProcess();
				(new Thread(new InputRunnable(mProc.getInputStream(), this)))
						.start();
			}

			System.out.println("exec : " + cmd);

			cmd += "\necho " + CMD_FINISH + "$?\n";

			synchronized (mOut) {
				mOut.clear();
			}
			mCmdFinish = false;
			OutputStream os = mProc.getOutputStream();
			os.write(cmd.getBytes(outEconding));
			// os.write("exit\n".getBytes(outEconding));
			os.flush();

			long start = System.currentTimeMillis();
			while (!mCmdFinish) {
				if (timeout > 0
						&& (System.currentTimeMillis() - start) > timeout)
					break;

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}

			if (output != null) {
				synchronized (mOut) {
					for (String s : mOut)
						output.add(s);
					mOut.clear();
				}
			}

			if (mCmdFinish)
				return mCmdResult;
			else {
				if (timeout > 0) {
					mProc.destroy();
					mProc = null;
				}
			}
			return -1;
		}

		public void exit() {
			if (mProc != null) {
				try {
					mProc.getOutputStream().write("exit\n".getBytes());
					Thread.sleep(50);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				mProc.destroy();
				mProc = null;
			}
		}
	}

	public static synchronized int suExecCallback(String cmd,
			Callback callback, int timeout, String outEconding)
			throws IOException {
		if (mSu == null)
			mSu = new MySuProcess();
		return mSu.exec(cmd, callback, timeout, outEconding);
	}

	public static synchronized int suExec(String cmd, ArrayList<String> output,
			int timeout, String outEconding) throws IOException {
		if (mSu == null)
			mSu = new MySuProcess();
		return mSu.exec(cmd, output, timeout, outEconding);
	}

	public static synchronized void suExit() {
		if (mSu != null) {
			mSu.exit();
			mSu = null;
		}
	}

	public static void test() {

	}

	public static Process createSuProcess() throws IOException {
		return Runtime.getRuntime().exec("su");
	}

	public static Process createSuProcess(String cmd, String outEconding)
			throws IOException {

		Process process = createSuProcess();
		DataOutputStream os = null;

		try {
			os = new DataOutputStream(process.getOutputStream());
			os.write((cmd + "\n").getBytes(outEconding));
			os.writeBytes("exit $?\n");
			os.flush();
		} catch (Throwable e) {
			if (os != null) {
				os.close();
			}
		}

		return process;
	}

	public static Process createSuProcess(String cmd, ArrayList<String> out,
			String outEconding) throws IOException {
		return createSuProcess(cmd, new MyStringArrayStreamWriter(out),
				outEconding);
	}

	public static interface MyStreamWriter {
		void write(String line);
	}

	public static class MyStringArrayStreamWriter implements MyStreamWriter {
		final ArrayList<String> mOut;

		public MyStringArrayStreamWriter(ArrayList<String> out) {
			mOut = out;
		}

		public void write(String line) {
			mOut.add(line);
		}

	}

	public static Process createSuProcess(String cmd, MyStreamWriter out,
			String outEconding) throws IOException {

		Process process = createSuProcess();
		DataOutputStream os = null;

		try {
			os = new DataOutputStream(process.getOutputStream());
			os.write((cmd + "\n").getBytes(outEconding));

			(new Thread(new InputRunnable(process.getInputStream(), out)))
					.start();

			Thread.sleep(100);

			os.writeBytes("exit $?\n");
			os.flush();
		} catch (Throwable e) {
			if (os != null) {
				os.close();
			}
		}

		return process;
	}

	static class InputRunnable implements Runnable {

		// final DataInputStream mIn;
		final BufferedReader mIn;
		final MyStreamWriter mOut;

		InputRunnable(InputStream in, MyStreamWriter out)
				throws UnsupportedEncodingException {
			mIn = new BufferedReader(new InputStreamReader(in));
			mOut = out;
		}

		public void run() {
			String line;
			try {
				while ((line = mIn.readLine()) != null) {
					mOut.write(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
