package hw6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class PrimeModel {

	private static long startTime = 0;
	static List<Integer> calculationValue = Collections.synchronizedList(new ArrayList<Integer>());

	private static class Worker implements Runnable  {
		private final int startval;
		private final int endval;
		 //private volatile boolean cancel;
		public final Semaphore semaphore;
		private volatile boolean cancel;
		
		public Worker(int startval, int endval, Semaphore semaphore, boolean cancel) // , boolean cancel)// , boolean cancel)
		{
			this.semaphore = semaphore;
			this.endval = endval;
			this.startval = startval;
			this.cancel = cancel;

		}

		@Override
		public void run() {
			try {
				System.out.println("Current Thread Name to call isPrime: "+ Thread.currentThread().getName());
				isPrime(startval, endval, cancel); // , semaphore);// , cancel);
				semaphore.release();
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void isPrime (int startval, int endval, boolean cancel) {// , Semaphore semaphore) {// , boolean cancel){
		int n=startval;
		while (!cancel & n <= endval) {	
			System.out.println(cancel);
			n++; // while (cancel) {
		//for (int n = startval; n <= endval; n++) {
			 System.out.println("the number is" + n);

			int i, m = 0, flag = 0;
			m = n / 2;
			if (n == 0 || n == 1) {
				// System.out.println("the number is one or 0");
			} else {
				for (i = 2; i <= m; i++) {
					if (n % i == 0) {
						//System.out.println(n + " is not prime number");
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					//System.out.println(n + " is prime number");
					calculationValue.add(n);
					System.out.println("Current Thread Name from Semaphore: "+ Thread.currentThread().getName());
				}
			
			}
		}
		
	}

	public List<Integer> findPrimenum(int limit, int threadPoolSize, boolean tocancel)  throws InterruptedException {
		
		List<Integer> startlist = new ArrayList<Integer>();
		List<Integer> endlist = new ArrayList<Integer>();
		
		System.out.println("Current Thread Name: "+ Thread.currentThread().getName());

		int partsize = limit / threadPoolSize;
		System.out.println("partsize is" + partsize);
		startlist.add(0);

		int num;
		int end = threadPoolSize;

		for (num = 1; num < partsize; num++) { // number of parts -2
			end = threadPoolSize * num;
			endlist.add(end);
			int start = end + 1;
			startlist.add(start);
		}
		endlist.add(limit);
		 startTime = System.currentTimeMillis();
		Semaphore semaphore = new Semaphore(threadPoolSize); // no. of permites it maintains.

		System.out.println("new semaphore is made to control access to thread");
		System.out.println(startlist.size() + " is the number of total chunks we need to run");
		//while (!tocancel) {
		for (int k = 0; k < startlist.size(); k++) {
			

			System.out.println("new chunck interval is checked if it is prime by worker no. " + k);
			semaphore.acquire();
			System.out.println("each worker has its own start, end and is controlled by semaphore");
			Worker w = new Worker(startlist.get(k), endlist.get(k), semaphore, tocancel);// , cancel);
			new Thread(w).start(); // each worker will work in new thread
			;
			System.out.println(semaphore.availablePermits() + "available permits");
		}

		int numAcquired = 0;

		while (numAcquired < threadPoolSize) {
			semaphore.acquire();
			numAcquired++;
		}
		System.out.println("Time taken by " + threadPoolSize + " is " + ((System.currentTimeMillis() - startTime) / 1000f));
		
	
		return calculationValue;
	}

	//use this after user click cancel or calculation is complete
	public List<Integer> getCalculationValue() {
		return calculationValue;
	}
	
	public String getCalculationValuesize() {
		return "the size of the prime numbers is " + calculationValue.size() + "\n";
	}
	
	public String getthreadcancelmessage() {
		return "The thread was cancelled  at " + ((System.currentTimeMillis() - startTime) / 1000f) + "\n";
	}
	
	public long getstarttime() {
		return startTime;
	}

}

//release help to release the permit
