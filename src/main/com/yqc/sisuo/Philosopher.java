package com.yqc.sisuo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);

	// ���ponderFactor��Ϊ0����pause()����������һ�����ʱ��
	// Ȼ���ȡ(take())�ұߺ���ߵ�Chopstick��Ȼ��Է�����һ�����ʱ�䣬֮���ظ��˹���
	private void pause() throws InterruptedException {
		if (ponderFactor == 0) {
			return;
		}
		TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}

	public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
		this.left = left;
		this.right = right;
		id = ident;
		ponderFactor = ponder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " " + "thinking");
				pause();
				right.take();
				System.out.println(this + " grabbing right");
				left.take();
				System.out.println(this + " grabing left");
				
				
				// ��������ʵ�־���һ���Ը�һ����ѧ�ҷ�����ֻ����
				// synchronized (left) {
				// synchronized (right) {
				// right.take();
				// System.out.println(this + " grabbing right");
				// left.take();
				// System.out.println(this + " grabing left");
				// }
				// }

				System.out.println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " exiting via interrupt");
		}
	}

	public String toString() {
		return "Philosopher " + id;
	}
}
