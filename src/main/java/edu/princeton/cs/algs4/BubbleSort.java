package edu.princeton.cs.algs4;

/**
 * @author Mageek Chiu
 * @date 2018-01-23:20:49
 */
public class BubbleSort {

    private BubbleSort(){}

    private static void show(Comparable[] a) {
        for (Comparable anA : a) {
            StdOut.print(anA+"\t");
        }
        StdOut.println("数组长度："+a.length);
    }

    private static void sort(Comparable[] a) throws IllegalAccessException, InstantiationException {
        Object tmp;
        boolean noChange = false;
        for (int i = 0;i<a.length-1 && !noChange;i++){
            noChange = true;//如果某一趟没有交换，说明已经排好序无需再进行接下来的排序
            for (int j=0;j<a.length-1-i;j++){
                if(a[j].compareTo(a[j+1])>0){
                    tmp =  a[j];
                    a[j] = a[j+1];
                    a[j+1] = (Comparable) tmp;
                    noChange = false;//有交换
                }
            }
            System.out.println(noChange);//展示跑了多少趟,几个打印就对应几趟
        }
    }

    /**
     *
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        Integer[] a = {3,4,7,12,3,7,8,9,18,33,24,6,15,45,27};
        Integer[] a = {3,4,7,9,12,32,13,16,20};
//        Double[] a = {3.0,4.4,7.5,12.1,3.3,7.4,8.4,9.4,18.5,33.6,24.8,6.6,15.2,45.1,27.2};
//        StdRandom.shuffle(a);
        show(a);
        sort(a);
        show(a);
    }
}
