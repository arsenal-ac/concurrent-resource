package com.yqc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * �׹��ڴ����������֮�󣬴�������һ���׹����֮��
 *
 * @author yangqc
 * 2016��7��25��
 */
class Car {
	private boolean waxOn = false;

	public synchronized void waxed() {  
		waxOn = true;   //׼���׹�
		notifyAll();
	}

	public synchronized void buffed() {   
		waxOn = false;   //׼����һ�ε��׹�
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {  //wax ���� �ȴ�����
		while (waxOn == false) {
			wait();
		}
	}

	public synchronized void waitForBuffing() throws InterruptedException {  //buff �ȴ��׹�
		while (waxOn == true) {
			wait();
		}
	}
}

class WaxOn implements Runnable {
	private Car car;

	public WaxOn(Car c) {
		car = c;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println("Wax On!");
			try {
				TimeUnit.SECONDS.sleep(2);
				car.waxed();   //��������
				car.waitForBuffing();  //�ȴ��׹�
			} catch (InterruptedException e) {
				System.out.println("Exiting via interrupt");
			}
			System.out.println("Ending Wax On task");
		}
	}
}

class WaxOff implements Runnable {

	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				car.waitForWaxing(); //�ȴ����������������ɣ�������������׹�
				System.out.println("Wax Off!");
				TimeUnit.SECONDS.sleep(2);
				car.buffed();  //�׹����
			} catch (InterruptedException e) {
				System.out.println("Exiting via interrupt");
			}
			System.out.println("Ending Wax Off task");
		}
	}
}

public class WaxOMatic {
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
