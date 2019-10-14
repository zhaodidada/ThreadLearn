package Day1.demo5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock 是可中断锁
 */
public class Demo5_3
{
	public void m1()
	{
		System.out.println("m1");
	}
	
	public void m2()
	{
		System.out.println("m2");
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Demo5_3 demo5 = new Demo5_3();
		Lock lock = new ReentrantLock();
		
		new Thread(()-> {
			System.out.println("t1.start");
			try
			{
				lock.lock();
				Thread.sleep(Integer.MAX_VALUE);
				demo5.m1();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			lock.unlock();
			System.out.println("t1.end");
		}).start();
		
		Thread t2 = new Thread(()-> {
			System.out.println("t2.start");
			try
			{
				lock.lockInterruptibly();
				demo5.m2();
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}finally {
				System.out.println("t2 中断了");
			}
			System.out.println("t2.end");
		});
		t2.start();
		
		Thread.sleep(3000);
		t2.interrupt();
	}

}
