package com.yqc.readWriteLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * ���Զ�д��
 *
 * @author yangqc
 * 2016��8��18��
 */
public class Resource {
	private int value;

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static void main(String[] args) {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		final Lock readLock = lock.readLock();
		final Lock writeLock = lock.writeLock();
		final Resource resource = new Resource();
		for (int i = 0; i < 20; i++) { // д�߳�
			new Thread() {
				public void run() {
					writeLock.lock();
					try {
						resource.setValue(resource.getValue() + 1);
						System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - "
								+ Thread.currentThread() + "��ȡ��д������������Ϊ:" + resource.getValue());
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						writeLock.unlock();
					}
				}
			}.start();
		}
		for (int i = 0; i < 20; i++) {
			new Thread() {
				public void run() {
					readLock.lock();
					try {
						System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - "
								+ Thread.currentThread() + "��ȡ�˶�������ȡ������Ϊ��" + resource.getValue());
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						readLock.unlock();
					}
				}
			}.start();
		}
	}
}
