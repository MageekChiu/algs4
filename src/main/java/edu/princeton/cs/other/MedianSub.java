package edu.princeton.cs.other;


import static java.lang.System.out;

class MedianSub extends Median {



   protected  void p1(){
       out.print("subp1");
   }


    public static void main (String ...args){
       new MedianSub().pr();//subp1parentpr  可见 子类的p1 确实覆盖了 父类的p1
    }
}
