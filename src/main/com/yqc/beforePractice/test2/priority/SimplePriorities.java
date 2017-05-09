package com.yqc.beforePractice.test2.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �߳����ȼ�
 * java�е����ȼ������ϵͳ�е����ȼ�һ����˵����һһ��Ӧ
 * ���ʹ��Thread.MAX_PRIORITY NORM_PRIORITY MIN_PRIORITY
 * @author yangqc
 * 
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d;
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return Thread.currentThread() + ":" + countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);  //�������ȼ�
		while (true) {
			for (int i = 1; i < 10000000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0)
					Thread.yield(); //�ò�  ��Ҫ�Ŀ�����ò�Ҫ������yield
			}
			System.out.println(this);
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++)
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
