package com.yqc.execute;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * TimerTask�׳�δ�����쳣ʱ����ֹ��ʱ�̣߳�Timer����ָ��̵߳�ִ�У����Ǵ���� ��Ϊ����Timer����ȡ�����ˡ�
 * ���Ըó���ִ��һ��ͽ�����
 *
 * @author yangqc 2016��8��1��
 */
public class OutOfTime {
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(1);
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(5);
	}

	static class ThrowTask extends TimerTask {
		@Override
		public void run() {
			throw new RuntimeException();
		}
	}
}
