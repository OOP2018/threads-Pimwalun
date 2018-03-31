import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An accumulator for a sum.
 * 
 * @author Pimwalun Witchawanitchanun
 */
public class Counter {
	protected long total;

	public Counter() {
		total = 0;
	}

	/**
	 * Add an amount to the total.
	 */
	public void add(int amount) {
		total += amount;
	}

	/**
	 * Get the total value of counter.
	 */
	public long get() {
		return total;
	}
}

/**
 * An accumulator for a sum with lock.
 * 
 * @author Pimwalun Witchawanitchanun
 *
 */
class CounterWithLock extends Counter {
	private Lock lock = new ReentrantLock();

	public void add(int amount) {
		try {
			lock.lock();
			super.add(amount);
		} finally {
			lock.unlock();
		}
	}
}

/**
 * An accumulator for a sum by use synchronized method.
 * 
 * @author Pimwalun Witchawanitchanun
 *
 */
class SynchronousCounter extends Counter {
	@Override
	public synchronized void add(int amount) {
		super.add(amount);
	}
}

/**
 * An accumulator for a sum by use AtomicLong.
 * 
 * @author Pimwalun Witchawanitchanun
 *
 */
class AtomicCounter extends Counter {
	private AtomicLong total;

	public AtomicCounter() {
		total = new AtomicLong();
	}

	/** add amount to the total. */
	public void add(int amount) {
		total.getAndAdd(amount);
	}

	/** return the total as a long value. */
	public long get() {
		return total.get();
	}
}
