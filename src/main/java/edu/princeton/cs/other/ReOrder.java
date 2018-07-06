    package edu.princeton.cs.other;

    /**
     * reorder
     * @author Mageek Chiu
     * @date 2018/5/25 0025:12:49
     */
    public class ReOrder {

        public int value ;

        private ReOrder(int value) {
            this.value = value;
        }

        public static void main(String... args){
            ReOrder reOrder = new ReOrder(111);
            ReOrder reOrder1 = new ReOrder(222);
            ReOrder reOrder2 = new ReOrder(333);
            System.out.println(add1(reOrder,reOrder1,reOrder2));
        }

        static int add1(ReOrder reOrder,ReOrder reOrder1,ReOrder reOrder2){
            int result = 0;

            result += reOrder.value;
            result += reOrder1.value;
//            result += reOrder2.value;// 注不注释都会先加载这个value

            result += reOrder.value;
            result += reOrder1.value;
            result += reOrder2.value;

            result += reOrder.value;
            result += reOrder1.value;
            result += reOrder2.value;

            return result;

        }

    }
