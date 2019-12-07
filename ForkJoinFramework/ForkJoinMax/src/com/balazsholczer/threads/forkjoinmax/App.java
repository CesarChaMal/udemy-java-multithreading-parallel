package com.balazsholczer.threads.forkjoinmax;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static int THREASHOLD = 0;
	
	public static void main(String[] args) {
		
		long[] nums = initializeNums();
		THREASHOLD =  nums.length / Runtime.getRuntime().availableProcessors();
		
		SequentialMaxFind normalMaxFind = new SequentialMaxFind();
		
		long start = System.currentTimeMillis();
		System.out.println("Max: " + normalMaxFind.sequentialMaxFinding(nums, nums.length));
		System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");

		System.out.println();
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		MaximumFindTask findTask = new MaximumFindTask(nums, 0, nums.length);
		
		start = System.currentTimeMillis();
		System.out.println("Max: " + forkJoinPool.invoke(findTask));
		System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static long[] initializeNums() {
		
		Random random = new Random();

//		int cont = 300000000;
		int cont = 30000000;
		long[] nums = new long[cont];
		
		for(int i = 0; i< cont; ++i)
			nums[i] = random.nextInt(100);
		
		return nums;
	}
}
