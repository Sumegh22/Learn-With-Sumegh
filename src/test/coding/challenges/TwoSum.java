package test.coding.challenges;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TwoSum {

        public static int[] twoSum(int[] nums, int target) {
            int[] arr = new int[2];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] + nums[j] == target) {
                        arr[0]=i;
                        arr[1]=j;
                    }
                }
            }
            return arr ;
        }

    public static void main(String[] args) {

            int[] input = {1,5,6,4};

        System.out.println(Arrays.toString(twoSum(input,11)));
    }
}
