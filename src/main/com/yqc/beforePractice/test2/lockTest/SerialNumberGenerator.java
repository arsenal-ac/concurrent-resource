package com.yqc.beforePractice.test2.lockTest;

/**
 * java������������ԭ���Եģ��漰һ����������д����
 * 
 * @author yangqc
 * 
 */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
