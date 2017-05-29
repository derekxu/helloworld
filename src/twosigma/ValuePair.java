package twosigma;

import java.util.*;
import java.util.stream.*;
/*
 * 1. Print out value pairs with smaller than 1.0 time difference 
 * from two data streams.这题加锁可以有不同方法加 会问
 * 
 * 2. 两个timestamp流输出差值小于一定范围的pair
 * 
 * 3. printtimestamps with diff < 1和website太慢应该怎么办……面经上对于这个题目的意思一直都说的很不清楚，
 * 正确的说法是有两个blocking queue，每个queue里面存的是一个tuple <timestamp, value>，
 * 我们想找到在这两个queue里面timestamp相差 <= 1的所有pair应该咋做。我一开始一直不理解为什么大家写的code都说要加锁，anyway我就先开始写了，
 * 反正大概就是先写calculatePair(queue<int> q1,queue<int> q2)的函数，然后假设是q2新来了一个timestamp，
 * 直接判断q1的front中的timestamp是否和q2.back()的timestamp相差值大于1，如果大于1不断pop即可。锁是出现在这儿的，
 * 因为两个queue都是blocking queue，也就是说如果调用int val= qs1.Take()，如果这个时候qs1没有来新data那么整个程序会block住，
 * 所以如果是一个while (true)的loop去调用calculate(q1, q2)然后calculate(q2, q1)的话整个程序很容易就block了，所以要开两个线程然后加个锁，
 * 这个锁应该锁在两个take()函数之后，然后运行完calculate()之后再release。答完之后他说this works，然后就问如果有多个queue，
 * 比如10个queue怎么办，我一看这个followup地上好像没说过……看来答的还可以，有戏……我就大概说了一个对每个queue都要维护一个pairwise的pointer 
 * headlist，这个可以用一个minHeap维护，然后如果队首的timestamp小于minHeap的最小值就把这个timestamp pop掉，他表示认可
 * 
 * 4. 是queue timestamp那个题目 然后和面经不太一样的地方在于 这里的queue是一个value, timestamp的pair 然后我们也不需要vector来乘返回值
 * 然后。 然后是要多线程
 * 
 * 5. 给两个infinite stream, 每个data是(t_i, v_i), 要求输出两个stream所有(t_i - t_j)不大于一的(v_i, v_j). 不要求上机调试,只需要伪代码
 * 给定的api是getNext(), 能够返回stream里面下一个data,但是如果没有data,就block.
 * 最后用了两个thread,两个thread-safe queue来实现. 每次call getNext(), 和另一个queue里存的每个 item相比, 相差大于一就删除, 否则输出
 * follow up: 5个stream, 用一个bit set记住需要删除的item,当所有thread都要求删除时从queue里面删去
 * 
 * 按照地里现在有的代码答案，假设你的两个stream是：
 * S1[1.0, 1.1, 1.2, 3.5]
 * S2[1.4, 1.6]. visit 1point3acres.com for more.
 * 然后你自己有两个q存着每次读出来的数，假设现代到了你说的
 * Q1[1.0,1.1]
 * Q2[]
 * 此时Q2为空，并且thread2从S2里读出了1.4，拿1.4去和所有Q1里的数比较，print出[1.0 1.4, 1.1 1.4]
 * 然后Q2变为[1.4]，Q1不变
 * 下次1.6进来的时候，做一样的事，拿1.6和所有的Q1比较。并不会重复输出。
 * 不会有机会让1.0，1.1和1.4重新比较，因为它们应该比较的时候Q2为空，什么都不会print出来。
 * 
 * 6. 两个 independent queue,每个 queue 都存着 timestamp,只能有 getNext()来取 queue
 * 里面的 timestamp,每个 timestamp 只能被取一次,比较这两个 queue 里的 timestamp,如
 * 果差值<1,print 这两个 timestamp。例如:
 * Q1 0.2, 1.4, 3.0
 * Q2 1.0 1.1, 3.5
 * output: (0.2, 1.0), (1.4, 1.0), (0.2, 1.1), (1.4, 1.1), (3.0, 3.5)
 * 提示： 多线程， 一个线程负责queue1, 一个负责queue2
 * 写伪代码就行
 * 
 * 7. 两queue,每个 queue 都存着 timestamp, 有一个take()来取里面的元素
 * 里面的 timestamp,每个 timestamp 只能被取一次,比较这两个 queue 里的 timestamp,如
 * 果time的差值<1,就要output 这两个 timestamp。例如:
 * Q1 0.2, 1.4, 3.0
 * Q2 1.0 1.1, 3.5
 * output: (0.2, 1.0), (1.4, 1.0), (0.2, 1.1), (1.4, 1.1), (3.0, 3.5)
 * 有一个要求就是，这两个queue可能是无限的，然后Take()这个function可能会被block，当queue里面暂时没有element的时候，如何处理这个block问题呢？
 * 提示： 多线程， 一个线程负责stream1, 一个负责stream2
 * 写伪代码就行
 * 
 * 8. 给你两个stream，分别有时间戳和相印的值。如果时间戳相差小于等于1则输出pair，
 * 我一开始用的一个deque来做的，后来面试官建议下用了两个deque来存，涉及锁和线程。
 * 
 * 9. 现在有两个数据流，每个数据流里存的是pair(time,value),如果两个流中的time相差小于1秒，就把这两个value输出出来。
 * 我写了一个比较如果差小于1秒，就输出，大于一秒，小的那个更新。然后他follow up说如果两个流一个快一个慢，会不会block，
 * 我说开3个线程，两个更新数据，一个处理数据，然后存两个buffer，每次在buffer里边找。然后把老的数据踢出去。
 */

/*
 * thread 1
process() {
        while (true) {
                LOCK L1
                x = s1.get()
                list1.add(x)
                UNLOCK L1

                LOCK L2
                for y in list2
                        if (abs(x - y) < 1)
                                print
                UNLOCK L2
        }

}
 * thread 2
process() {
        while (true) {
                LOCK L1
                x = s2.get()
                list2.add(x)
                UNLOCK L1

                LOCK L2
                for y in list1
                        if (abs(x - y) < 1)
                                print
                UNLOCK L2
        }

}
 */
public class ValuePair {

	public static void main(String[] args) {
		QuequeThread t0 = new QuequeThread(0, DoubleStream.builder().build());
		QuequeThread t1 = new QuequeThread(1, DoubleStream.builder().build());
		try {
			t0.run();
			t1.run();
		} catch (RuntimeException exception) {
			exception.printStackTrace();
		}
	}
	static class QuequeThread implements Runnable {
		public int tid;
		public DoubleStream stream;
		public List<Double> queue;
		public QuequeThread(int id, DoubleStream stream) {
			tid = id;
			this.stream = stream;
			queue = new ArrayList<>();
		}
		@Override
		public void run() {
			System.out.printf("Running thread %d\n", tid);
		}

		private void printFromQueue() {
			double ts;
			while (!stream.hasNext()) {
				System.out.println("wait");
			}
			ts = stream.getNext();
			queue.add(ts);
		}
	}
}
