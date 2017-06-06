package com.yqc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yangqc on 2017/6/6.
 */
public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch countDownLatch;
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(String serviceName, CountDownLatch countDownLatch) {
        super();
        this.countDownLatch = countDownLatch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace();
            serviceUp = false;
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }

    public abstract void verifyService();
}
