package edu.princeton.cs.other;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Mageek Chiu
 * @date 2018/3/4 0004:15:47
 */
public class Counter1 {
    static volatile List<String> list = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
//static List<String> list = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            int i = 0;
            while (list.get(1).equals("Córdoba")) {
                i++;
            }
        });
        t.start();

        TimeUnit.MILLISECONDS .sleep(50);
        list.set(1,"wwewwqe");
    }
}