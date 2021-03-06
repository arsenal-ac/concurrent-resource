package com.yqc.beforePractice.test2.getResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventChecker implements Runnable {

	private IntGenerator generator;
	private final int id;
	private Lock lock = new ReentrantLock();

	public EventChecker(IntGenerator g, int ident) {
		generator = g;
		id = ident;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			lock.lock();
			try {
				int val = generator.next();
				if (val % 2 == 0) {
					System.out.println(val + " not event!");
					generator.cancel();
				}
			} finally {
				lock.unlock();
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++)
			exec.execute(new EventChecker(gp, i));
		exec.shutdown();
	}

	public static void test(IntGenerator gp) {
		test(gp, 10);
	}
}
