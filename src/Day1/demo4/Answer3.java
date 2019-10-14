package Day1.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用门栓
 */
public class Answer3
{
	private List<Integer> list = new ArrayList<Integer>(10);
	
	public void add()
	{
		list.add(1);
	}
	
	public int size()
	{
		return list.size();
	}
	
	public static void main(String[] args)
	{
		Answer3 a3 = new Answer3();

		/*
		 * 门栓，往下数，初始是1（就一个门栓） ，countDown一次，到0，门栓就开了
		 * 
		 * 功能与wait()一样，但是不需要锁定任何对象！
		 */
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(()->	{
			if(a3.size()!=5)
			{
				try
				{
					// 门栓等待，门拴着，不让进了，后面的程序就在这等着，什么时候开门，什么时候再继续
					latch.await();
					// 也可以指定等待时间
					//latch.await(5000, TimeUnit.MILLISECONDS);
					
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("end");
			}
		}).start();
		
		new Thread(()->{
			for(int i=0; i<10; i++)
			{
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				a3.add();
				if(a3.size()==5) 
				{
					// 到5了往下数
					latch.countDown();
				}
			}
		}).start();
	}
	
	void note()
	{
		/*
		 * 使用门栓是最简单、最直观、并发效率最好的一种方法。
		 * 
		 * 栅栏  CyclicBarrier，与门栓类似，只不过门栓是往下减，而栅栏是往上加，
		 * 比如有5个人约好去玩，等5个人都到齐了，才出发。
		 * 
		 * 门栓是要主动去减的，而栅栏只要调用await()就可以加一个值。
		 */
	}

}
