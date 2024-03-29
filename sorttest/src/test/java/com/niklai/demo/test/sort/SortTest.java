package com.niklai.demo.test.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @DisplayName("冒泡排序")
    public void bubbleSortTest() {
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
    @DisplayName("选择排序")
    public void selectionSortTest() {
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
    @DisplayName("插入排序")
    public void insertionSortTest() {
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
    @DisplayName("归并排序")
    public void mergeSortTest() {
        afterPrint(splitArray(nums));
    }

    /**
     * 快速排序
     * 选择任意一项作为基准项，将所有比它小的值放在左边，比它大的值放在右边，相等的放在任意一边，然后分成两个区，分别对两个区再进行递归快速排序。
     */
    @Test
    @DisplayName("快速排序")
    public void quickSortTest() {

    }

    /**
     * 计数排序
     * 找出最大值和最小值，用最大和最小值区间生成一个从小到大的临时键值对空间。
     * 循环数组项出现的次数，并在对应键的值上加1。然后循环键值对空间，重新组合数组。
     */
    @Test
    @DisplayName("计数排序")
    public void countingSortTest() {
        // 找出最大值和最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < min) {
                min = nums[i];
            }

            if (nums[i] > max) {
                max = nums[i];
            }
        }

        // 生成键值对空间
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = min; i <= max; ++i) {
            map.put(i, 0);
        }

        // 累积数组项出现的次数
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], map.get(nums[i]) + 1);
        }

        // 重新输出数组
        int[] results = new int[nums.length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entity : map.entrySet()) {
            for (int i = 1; i <= entity.getValue(); ++i) {
                results[index] = entity.getKey();
                ++index;
            }
        }

        afterPrint(results);
    }

    /**
     * 桶排序
     * 将数组项按照一定规则分别放在一组有序的“桶”里，分别对每个不是空的桶里的项进行排序，最后把已排好序的项拼接起来
     */
    @Test
    @DisplayName("桶排序")
    public void bucketSortTest() {
        // 假定数组里的每一项都是[0,100)之间的整数
        Map<Integer, List<Integer>> buckets = new HashMap<>();
        for (int i = 0; i < 10; ++i) {
            buckets.put(i, new ArrayList<>());
        }

        for (int num : nums) {
            buckets.get(num / 10).add(num);
        }

        for (Map.Entry<Integer, List<Integer>> bucket : buckets.entrySet()) {
            if (bucket.getValue().size() != 0) {
                List<Integer> list = bucket.getValue();
                // 冒泡排序
                for (int i = list.size() - 1; i > 0; --i) {
                    for (int j = 0; j < i; ++j) {
                        if (list.get(j) > list.get(j + 1)) {
                            list.set(j + 1, list.get(j) + list.get(j + 1));
                            list.set(j, list.get(j + 1) - list.get(j));
                            list.set(j + 1, list.get(j + 1) - list.get(j));
                        }
                    }
                }
            }
        }

        int[] results = new int[nums.length];
        int index = 0;
        for (Map.Entry<Integer, List<Integer>> bucket : buckets.entrySet()) {
            for (int num : bucket.getValue()) {
                results[index] = num;
                ++index;
            }
        }

        afterPrint(results);
    }

    /**
     * 基数排序
     * 通用情况：先按照低优先级条件进行收集后排序，然后再按照高优先级条件再次收集后排序，直到最高优先级条件。
     * 当前例子：先找到最大数，计算位数，确定要收集排序几次，然后按照位数从低到高进行收集排序。
     */
    @Test
    @DisplayName("基数排序")
    public void radixSortTest(){

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
