package com.yqc.resource;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 使用AutoInteger消除线程安全问题
 *
 * @author yangqc
 * 2016年7月23日
 */
public class AtomicIntegerTest implements Runnable {

	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		return i.get();
	}

	private void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		evenIncrement();
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while (true) {
			int val = ait.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
