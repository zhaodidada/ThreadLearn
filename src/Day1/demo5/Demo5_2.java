package Day1.demo5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock的其他用法
 */
public class Demo5_2
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
		Demo5_2 demo5 = new Demo5_2();
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
			boolean tryLock = lock.tryLock();
			if(tryLock)
			{
				try
				{
					demo5.m2();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				lock.unlock();
				System.out.println("get");
			}else {
				System.out.println("other");
			}
			
		}).start();
		
		new Thread(()-> {
			boolean tryLock = false;
			try
			{
				// 等5秒
				tryLock = lock.tryLock(5 ,TimeUnit.SECONDS);
			} catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			System.out.println(tryLock);
			
		}).start();
	}

}
