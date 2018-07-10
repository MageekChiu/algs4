package edu.princeton.cs.other;

import static java.lang.System.out;

/**

 * @author Mageek Chiu
 */
class RemoveElement {

    /**

     */
    public static int removeElement(int[] A, int val) {
        int i=0;
        int j=0;
        if(A.length==0 || (A.length==1 && A[0]==val)) {
            return 0;
        }
        if (A.length==1) return 1;

        while(i<A.length&&j<A.length){
            if(A[j]==val){
                j++;
            }else{
                A[i++]=A[j++];
            }
        }
        return i;
    }

    // 感受：
    public static void main (String ...args){
        int[] nums = {0,1,2,2,3,0,4,2};//
        out.println(removeElement(nums,2));
        for (int num : nums) {
            out.println(num);
        }
    }
}
