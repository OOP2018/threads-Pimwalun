
/**
 * AddTask adds number 1 ... limit to the counter, then exits. 
 * @author Pimwalun Witchawanitchanun
 *
 */
public class AddTask implements Runnable {
	private Counter counter;
	private int limit;

	public AddTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	public void run() {
		for (int k = 1; k <= limit; k++) {
			counter.add(k);
		// If you want to see when a thread finishes, add this line:
//			System.out.println("Done "+Thread.currentThread().getName());
		}
	}
}
