package Day1.demo5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 还是公平锁：
 * 
 * 有三个线程A、B、C，他们都要获取M的锁，假设，A先拿到了锁，然后过了B启动去获取锁，
 * 5秒后，C又启动去获取锁；又过了1秒，A释放了锁，此时是谁拿到了锁？
 * 
 * ①使用synchronized，它是非公平锁，即可能是B，也可能是C拿到锁，因为谁先抢到就是谁的，不存在排队问题（先到先得）
 * ②使用Lock，它是公平锁，即谁等的久，谁会先得到锁
 * 
 * 公平锁效率比较低，非公平锁效率比较高
 */
public class Demo5_4
{

	public static void main(String[] args)
	{
		// 传true表示为公平锁，不传默认非公平锁
		Lock lock = new ReentrantLock(true);
		
		new Thread(()->{
			for(int i=0; i<100; i++)
			{
				lock.lock();
				System.out.println(Thread.currentThread().getName());
				lock.unlock();
			}
		}).start();
		
		new Thread(()->{
			for(int i=0; i<100; i++)
			{
				lock.lock();
				System.out.println(Thread.currentThread().getName());
				lock.unlock();
			}
		}).start();
	}

	
	void note()
	{
		/*
		 * ReentrantLock可以完成synchronized的所有功能，所以它可以替代synchronized
		 * 
		 * 它比synchronized灵活，它可以tryLock，尝试锁定，根据锁定的结果来处理不同业务
		 * tryLock还可以指定时间，要求申请这么长时间，这么长时间要是锁定不了，就进行自己的处理
		 * 
		 * 还可以使用lockInterruptibly，在锁定之后，别人可以使用interrupt打断该线程
		 * 
		 * 还可以指定为公平锁，谁等的时间更长，谁优先获得锁
		 * 
		 */
	}
}
