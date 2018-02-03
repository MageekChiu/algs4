package edu.princeton.cs.other;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Mageek Chiu
 * @date 2018/2/1 0001:17:40
 */
public class goHomeImaginary {

    public static void main(String[] args) throws InterruptedException {

        LocalDate today = LocalDate.now();
        LocalDate homeDay = LocalDate.of(2018,2,10);
        int daysToWait = (int) ChronoUnit.DAYS.between(today, homeDay);
        CountDownLatch countDownLatch = new CountDownLatch(daysToWait);

        ExecutorService executor = Executors.newFixedThreadPool(daysToWait);
        for (int i = 0;i < daysToWait;i++){
            executor.execute(() -> {
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();
        System.out.println("回家了！");
    }

}
