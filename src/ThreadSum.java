import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Find run-times and total counter of all the solutions.
 * 
 * @author Pimwalun Witchawanitchanun
 *
 */
public class ThreadSum {
	public static void main(String[] args) {
		// upper limit of numbers to add/subtract to Counter
		final int LIMIT = 10000000;
		// The counter that accumulates a total.
		Counter counter = new Counter();
		Counter counter2 = new CounterWithLock();
		Counter counter3 = new SynchronousCounter();
		Counter counter4 = new AtomicCounter();
		// counter.add(50);
		// counter.add(15);
		// System.out.println(counter.get());
		// counter.add(34);
		// System.out.println(counter.get());
		runThreads(5, counter, LIMIT);
		runThreads(5, counter2, LIMIT);
		runThreads(5, counter3, LIMIT);
		runThreads(5, counter4, LIMIT);
	}

	public static void runThreads(Counter counter, final int limit) {
		// two tasks that add and subtract values using same Counter
		AddTask addtask = new AddTask(counter, limit);
		SubtractTask subtask = new SubtractTask(counter, limit);
		// threads to run the tasks
		Thread thread1 = new Thread(addtask);
		Thread thread2 = new Thread(subtask);
		// start the tasks
		System.out.println("Starting threads");
		long startTime = System.nanoTime();
		thread1.start();
		thread2.start();
		// wait for threads to finish
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		// the sum should be 0. Is it?
		System.out.printf("Counter total is %d\n", counter.get());
	}

	/* Create 10 Thread. */
	public static void runThreads(int nthread, Counter counter, final int limit) {
		ExecutorService executor = Executors.newFixedThreadPool(2 * nthread);
		// start the tasks
		System.out.println("Starting threads");
		long startTime = System.nanoTime();
		for (int k = 1; k <= nthread; k++) {
			Runnable addtask = new AddTask(counter, limit);
			Runnable subtask = new SubtractTask(counter, limit);
			executor.submit(addtask);
			executor.submit(subtask);
		}
		// wait for threads to finish
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		System.out.println("All down");
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		// the sum should be 0. Is it?
		System.out.printf("Counter total is %d\n\n", counter.get());
	}
}
