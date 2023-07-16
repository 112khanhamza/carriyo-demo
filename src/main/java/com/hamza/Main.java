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

    public static int duplicate(int[] arr) {
        if (arr == null) return -1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return arr[i];
                }
            }
        }
        return -1;
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
