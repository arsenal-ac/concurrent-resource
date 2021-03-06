package com.yqc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangqc on 2017/6/6.
 */
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch countDownLatch) {
        super("Network Service", countDownLatch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP!");
    }
}
