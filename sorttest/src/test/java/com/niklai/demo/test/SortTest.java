package com.niklai.demo.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
    private int[] nums;

    @BeforeEach
    private void initNums() {
        nums = new int[]{39, 5, 26, 36, 2, 83, 2, 8, 68, 16, 9, 48, 52, 85, 1};
        System.out.println("排序前：" + Arrays.toString(nums));
    }

    /**
     * 冒泡排序
     * 循环依次比较前后两个值，大的放在后面
     */
    @Test
    public void bubbleSortTest() {
        System.out.println("冒泡排序");
        for (int i = nums.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] > nums[j + 1]) {
                    nums[j + 1] = nums[j] + nums[j + 1];
                    nums[j] = nums[j + 1] - nums[j];
                    nums[j + 1] = nums[j + 1] - nums[j];
                }
            }
        }
        afterPrint(nums);
    }

    /**
     * 选择排序
     * 将第一个值依次与后面的值进行比较，找到最小的后放在第一个，然后循环
     */
    @Test
    public void selectionSortTest() {
        System.out.println("选择排序");
        for (int i = 0; i < nums.length; ++i) {
            int tmpIndex = i;
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] < nums[tmpIndex]) {
                    tmpIndex = j;
                }
            }

            if (nums[tmpIndex] < nums[i]) {
                nums[tmpIndex] = nums[tmpIndex] + nums[i];
                nums[i] = nums[tmpIndex] - nums[i];
                nums[tmpIndex] = nums[tmpIndex] - nums[i];
            }
        }
        afterPrint(nums);
    }

    /**
     * 插入排序
     * 循环依次比较当前值与之前的所有值，发现比之前的某个值小时，将当前值插入到比较值之前
     */
    @Test
    public void insertionSortTest() {
        System.out.println("插入排序");
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j > 0; --j) {
                if (nums[j] < nums[j - 1]) {
                    nums[j - 1] = nums[j] + nums[j - 1];
                    nums[j] = nums[j - 1] - nums[j];
                    nums[j - 1] = nums[j - 1] - nums[j];
                } else {
                    break;
                }
            }
        }
        afterPrint(nums);
    }

    /**
     * 归并排序
     * 分治法。将数组按照n/2分成两个子数组，子数组分别进行归并排序，将排列好的子数组合并形成最终已排序数组。
     */
    @Test
    public void mergeSortTest() {
        System.out.println("归并排序");
        afterPrint(splitArray(nums));
    }

    /**
     * 快速排序
     * 选择任意一项作为基准项，将所有比它小的值放在左边，比它大的值放在右边，相等的放在任意一边，然后分成两个区，分别对两个区再进行递归快速排序。
     */
    @Test
    public void quickSortTest() {
        System.out.println("快速排序");
    }

    private int[] splitArray(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        int leftLength = arr.length / 2;
        int rightLength = arr.length - leftLength;

        return merge(splitArray(leftArray(arr, leftLength)),
                splitArray(rightArray(arr, rightLength)));
    }

    private int[] leftArray(int[] arr, int leftLength) {
        int[] result = new int[leftLength];
        for (int i = 0; i < leftLength; ++i) {
            result[i] = arr[i];
        }

        return result;
    }

    private int[] rightArray(int[] arr, int rightLength) {
        int[] result = new int[rightLength];
        int index = 0;
        for (int i = arr.length - rightLength; i < arr.length; ++i) {
            result[index] = arr[i];
            ++index;
        }

        return result;
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        while (i != left.length || j != right.length) {
            if (i == left.length) {
                result[i + j] = right[j];
                ++j;
                continue;
            }

            if (j == right.length) {
                result[i + j] = left[i];
                ++i;
                continue;
            }

            if (left[i] < right[j]) {
                result[i + j] = left[i];
                ++i;
            } else {
                result[i + j] = right[j];
                ++j;
            }
        }

        return result;
    }

    private void afterPrint(int[] arrs) {
        System.out.println("排序后：" + Arrays.toString(arrs));
    }
}
