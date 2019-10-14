package Day1.demo5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Lock的使用
 *
 */
public class Demo5_1
{
	public void m1() throws InterruptedException
	{
		Thread.sleep(1000);
		System.out.println("m1");
	}
	
	public void m2() throws InterruptedException
	{
		Thread.sleep(1000);
		System.out.println("m2");
	}
	
	public static void main(String[] args)
	{
		Demo5_1 demo5 = new Demo5_1();
		Lock lock = new ReentrantLock();
		
		new Thread(()-> {
			lock.lock();
			for(int j=0; j<5; j++) 
			{
				try
				{
					demo5.m1();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			lock.unlock();
		}).start();
		
		new Thread(()-> {
			lock.lock();
			try
			{
				demo5.m2();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			lock.unlock();
		}).start();
	}

}
