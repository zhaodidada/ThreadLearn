package Day2.demo1;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 有一列火车，10000张票，同时往外卖
 * 
 * 加同步，且效率比较高
 */
public class Demo1_5
{
	private Queue<Integer> tickets = null;
	
	/**
	 * 引入Queue - ConcurrentLinkedQueue
	 */
	public Demo1_5()
	{
		tickets = new ConcurrentLinkedQueue<Integer>();
		for(int i=0; i<1000; i++) 
		{
			tickets.add(i);
		}
	}
	
	protected Integer saller()
	{
		Integer ticket = tickets.poll();
		System.out.println("剩余 :" + (ticket == null ? 0:ticket));
		return ticket;
	}
	
	public static void main(String[] args)
	{
		Demo1_5 tran = new Demo1_5();
		for(int i=0; i<10; i++) 
		{
			new Thread(()-> {
				try
				{
					Thread.sleep(1000);
					while(true)
					{
						Integer ticket = tran.saller();
						if(ticket==null) {
							break;
						}
					}
					
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}).start();
		}
	}
}
