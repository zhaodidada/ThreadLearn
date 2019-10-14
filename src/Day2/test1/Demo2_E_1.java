package Day2.test1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Demo2_E_1
{
	private static ConcurrentLinkedQueue<String> cq;
	
	static {
		cq = new ConcurrentLinkedQueue<>();
	}
	
	public boolean write(String str)
	{
		return cq.offer(str);
	}
	
	public String read()
	{
		return cq.poll();
	}
	
	public static void main(String[] args)
	{
		Demo2_E_1 ins = new Demo2_E_1();
		for(int i=0; i<3; i++)
		{
			new Thread(() -> {
				for(int j=0; j<10; j++)
				{
					String radm = String.valueOf(j);
					ins.write(radm);
					
				}
			}).start();
		}
		new Thread(() -> {
			while(true)
			{
				try
				{
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println(ins.read());
			}
		}).start();
	}
}
