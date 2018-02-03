package edu.princeton.cs.other;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Mageek Chiu
 * @date 2018/2/1 0001:17:24
 */
public class GoHomeReality {

    public static void main(String[] args) throws InterruptedException {

        LocalDate today = LocalDate.now();
        LocalDate homeDay = LocalDate.of(2018,2,10);
        int daysToWait = (int) ChronoUnit.DAYS.between(today, homeDay);
        CountDownLatch countDownLatch = new CountDownLatch(daysToWait);

        new Thread( () -> {
            for(int i = 0; i < daysToWait;i++){
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();
        System.out.println("回家了！");
    }

}
