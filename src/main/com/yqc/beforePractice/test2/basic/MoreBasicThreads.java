package com.yqc.beforePractice.test2.basic;

/**
 * �̵߳��Ȼ����Ƿ�ȷ���Ե�
 * 
 * @author yangqc
 * 
 */
public class MoreBasicThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.out.println("Waiting for LiftOff!");
	}
}
