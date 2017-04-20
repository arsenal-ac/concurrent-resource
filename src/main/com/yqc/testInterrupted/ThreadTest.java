package com.yqc.testInterrupted;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadTest {

	public static void main(String[] args) {
		ErrHandler handle = null;
		ThreadA a = null;
		a = new ThreadA();
		handle = new ErrHandler();
		a.setUncaughtExceptionHandler(handle);// ���붨���ErrHandler
		a.start();

	}

}

/**
 * �Զ����һ��UncaughtExceptionHandler
 */
class ErrHandler implements UncaughtExceptionHandler {
	/**
	 * ����������κ�����쳣�Ĵ���,�����¼��־�ȵ�
	 */
	public void uncaughtException(Thread a, Throwable e) {
		System.out.println("This is:" + a.getName() + ",Message:" + e.getMessage());
		e.printStackTrace();
	}
}

/**
 * ӵ��UncaughtExceptionHandler���߳�
 */
class ThreadA extends Thread {

	public ThreadA() {

	}

	public void run() {
		try {
			double i = 12 / 0;// �׳��쳣�ĵط�
		} finally {
			System.out.println("����ʧ��!");
		}
	}
}
