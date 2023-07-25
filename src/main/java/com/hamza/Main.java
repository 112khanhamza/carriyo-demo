package com.hamza;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        int[] arr = { 1, 2, 3, 3, 3, 4 };
        System.out.println(duplicateOpt(arr));
    }

    public static int duplicateParams(int[] arr) {
        int size = arr.length;
        int range = size - 1;
        return -1;
    }

    public static int duplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                int correctIndex = nums[i] - 1;
                if (nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int duplicateOpt(int[] arr) {
        if (arr == null) return -1;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) return arr[i];
            set.add(arr[i]);
        }
        return -1;
    }
}
