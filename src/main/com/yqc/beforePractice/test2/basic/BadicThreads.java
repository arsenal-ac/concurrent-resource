package com.yqc.beforePractice.test2.basic;

/**
 * �߳���ͬʱ��������������main������LiftOff�е�run���� �����С�ͬʱ��ִ�еĴ���
 * 
 * @author yangqc
 * 
 */
public class BadicThreads {
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("Waiting for LiftOff!");
	}
}
