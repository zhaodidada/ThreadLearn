package Day2.test1;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;

public class Demo2_E_4
{
	static TransferQueue<String> transfer = new LinkedTransferQueue<>();
	
	public void get() throws InterruptedException
	{
		TimeUnit.MILLISECONDS.sleep(500);
		String s = transfer.take();
		System.out.println("take " +s);
	}
	
	public void add()
	{
		String s = "hello";
		transfer.add(s + " " + System.nanoTime());
		
		
	}
	
	public static void main(String[] args)
	{
		Demo2_E_4 inst = new Demo2_E_4();
		
		for(int i=0; i<5; i++)
		{
			new Thread(()->{
				
				try
				{
					inst.get();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}).start();
		}
		
		new Thread(()->{
			for(int i=0; i<100; i++) {
				inst.add();
			}
			try
			{
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	}

}
