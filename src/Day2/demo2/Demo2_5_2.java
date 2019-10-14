package Day2.demo2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
/**
 * SynchronousQueue 
 */
public class Demo2_5_2
{

	public static void main(String[] args)
	{
		BlockingQueue<String> q = new SynchronousQueue<>();
		
		new Thread(()->{
			String s = q.poll();
			System.out.println(s);
		}).start();
		
		try
		{
			q.put("hello");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		
	}

}
