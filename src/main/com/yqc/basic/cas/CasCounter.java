package com.yqc.basic.cas;
/**
 * ����CASʵ�ֵķ�����������
 *
 * @author yangqc
 * 2016��9��10��
 */
public class CasCounter {
	private SimulatedCAS value;

	public int getValue() {
		return value.get();
	}

	public int increment() {
		int v;
		do {
			v = value.get();
		} while (v != value.compareAndSwap(v, v + 1));
		return v + 1;
	}
}
