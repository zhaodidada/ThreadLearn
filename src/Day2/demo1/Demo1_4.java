package Day2.demo1;

import java.util.List;
import java.util.Vector;

/**
 * 有一列火车，10000张票，同时往外卖
 * 
 * 加同步，且效率比较高
 */
public class Demo1_4
{
	private List<Integer> tickets = null;
	
	public Demo1_4()
	{
		tickets = new Vector<Integer>();
		for(int i=0; i<10000; i++) 
		{
			tickets.add(i);
		}
	}
	
	public boolean hasTickets()
	{
		return tickets.size() == 0 ? false : true;
	}
	
	protected synchronized void saller()
	{
		int before = tickets.size();
		if(hasTickets())
		{
			tickets.remove(tickets.size()-1);
		}
		int after = tickets.size();
		System.out.println("sale :" + after + "/" + before);
	}
	
	public static void main(String[] args)
	{
		Demo1_4 tran = new Demo1_4();
		for(int i=0; i<10; i++) 
		{
			new Thread(()-> {
				try
				{
					Thread.sleep(1000);
					for(int j=0; j<50; j++) {
						tran.saller();
					}
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}).start();
		}
	}
}
