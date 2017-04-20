package com.yqc.locks;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���Կ�������
 *
 * @author yangqc 2016��8��27��
 */
public class TestReentrantLock {
	private ArrayList<Integer> arrayList = new ArrayList<>();
	Lock lock = new ReentrantLock();   //����̹߳���һ����

	public void insert(Thread thread) {
		// Lock lock = new ReentrantLock(); //ÿ���̶߳����Լ�����
		lock.lock();
		try {
			System.out.println(thread.getName() + "�õ���!");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "�ͷ���!");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final TestReentrantLock test = new TestReentrantLock();
		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			}
		}.start();

		new Thread() {
			public void run() {
				test.insert(Thread.currentThread());
			};
		}.start();
	}
}
