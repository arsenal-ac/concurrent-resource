package com.yqc.locks;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ����tryLock
 *
 * @author yangqc
 * 2016��8��27��
 */
public class TestTryLock {
	private ArrayList<Integer> arrayList = new ArrayList<>();
	private Lock lock = new ReentrantLock();

	public void insert(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + "�õ���!");
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(thread.getName() + "�ͷ���!");
				lock.unlock();
			}
		} else {
			System.out.println("��ȡ��ʧ��!");
		}
	}

	public static void main(String[] args) {
		final TestTryLock test = new TestTryLock();
		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			}
		}.start();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			}
		}.start();
	}
}
