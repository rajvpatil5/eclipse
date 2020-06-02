package leetcode;

public class Solution {
	public int subarraySum(int[] nums, int k) {

		int sum1 = 0;
		int sum = 0;
		int temp = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j <= nums.length; j++) {
				for (int k1 = i; k1 < j; k1++) {
					// System.out.print(nums[k1]);
					sum1 = nums[k1];
					sum = sum + sum1;
				}
				// System.out.println();
				// System.out.println(sum+"----------");
				if (sum == k) {
					// System.out.println("equal");
					temp = temp + 1;

				}
				sum = 0;
			}
		}
		// System.out.println("total number equals is :"+temp);
		return temp;

	}

	public static void main(String[] args) {
		int nums[] = { 1, 2, 3, 4 };
		int k = 3;
		System.out.println(new Solution().subarraySum(nums, k));

	}
}
/*
Subarray Sum Equals K
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/