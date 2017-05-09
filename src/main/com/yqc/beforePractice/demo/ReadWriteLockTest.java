package com.yqc.beforePractice.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * ��д��
 * 2015.10.27
 * @author Yangqc
 *
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 queue=new Queue3();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					queue.get();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					queue.get();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					queue.put("�ڳ�!");
				}
			}).start();
		}
	}
}

//ֻ��һ���߳�д���ݣ����ǿ����ж���̶߳�����
class Queue3{
	//���ﲻ����Lock
	private Object data=null;
	private ReentrantReadWriteLock rw1=new ReentrantReadWriteLock();
	public void get(){
		rw1.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+ " be ready to read data!");
			Thread.sleep((long) Math.random() * 1000);
			System.out.println(Thread.currentThread().getName()+ " have read data:" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rw1.readLock().unlock();
		}
	}
	
	public void put(Object data){
		rw1.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+ " be ready to write data!");
			Thread.sleep((long) Math.random() * 1000);
			this.data = data;
			System.out.println(Thread.currentThread().getName()+ " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rw1.writeLock().unlock();
		}
	}
}