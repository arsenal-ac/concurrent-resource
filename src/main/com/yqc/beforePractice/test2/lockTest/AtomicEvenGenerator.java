package com.yqc.beforePractice.test2.lockTest;

import com.yqc.beforePractice.test2.getResource.EventChecker;
import com.yqc.beforePractice.test2.getResource.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicEvenGenerator extends IntGenerator {

	private AtomicInteger currentEvenValue=new AtomicInteger(0);
	@Override
	public int next() {
		return currentEvenValue.addAndGet(2);
	}
	public static void main(String[] args) {
		EventChecker.test(new AtomicEvenGenerator());
	}
}
