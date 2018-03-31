## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  | 10000000           | 0.013792 sec    |
| Using ReentrantLock     | 10000000           | 0.782407 sec    |
| Synchronized method     | 10000000           | 0.578713 sec    |
| AtomicLong for total    | 10000000           | 0.246829 sec    |

## 1. Using unsynchronized counter object

1.1 Yes total should be a zero. Thread shouldn't run in the same time because it maybe use wrong value to count. The total it's not always same.

1.2 Average time is 0.014004

## 2. Implications for Multi-threaded Applications

   When we want deposit money is 100 in bank and the balance is 1100 from 1000. In the same time my mom want to withdraw money is 500 in the bank and the balance is 500 from 1000. But the balance should be 600 in bank account.

## 3. Counter with ReentrantLock

3.1 Complete one thread one by one.

3.2 Because thread isn't run in the same time.

3.3 It use lock thread1 until finish.

3.4 Because the program must unlock thread1 when finish it. In order to other thread can run.

## 4. Counter with synchronized method

4.1 Complete one thread one by one.

4.2 Because problem 4 we use synchronized to lock method add until finish.

4.3 Thread 1 and thread 2 do not run at the same time. It must wait for other thread finish. When we want to method run one thread until finish.

## 5. Counter with AtomicLong

5.1 AtomocLong have method getAndAdd to adds the given to he current value and have method get to gets the current value.

5.2 We use AtomicLong, AtomicDouble or AtomicInteger when we want to used by many threads concurrently.

## 6. Analysis of Results

6.1 Average run-times of ReentrantLock > Synchronized method > AtomicLong. The AtomicLong is fastest and ReentrantLock is slowest.

6.2 Synchronized because it will complete one thread one by one. For example we sent a secret code. After that the sender is blocked until a secret code is received.

## 7. Using Many Threads (optional)

