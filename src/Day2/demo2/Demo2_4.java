package Day2.demo2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
/**
 * DelayQueue的使用：定时执行任务
 */
public class Demo2_4 implements Delayed
{
	// 初始的时间，用来表示某个任务产生的时间
	private long init;
	
	public Demo2_4(long time)
	{
		init = time;
	}
	
	/* 要使用DelayQueue，往队列里添加的元素对象，必须实现Delayed接口，而Delayed接口又继承了Comparable<Delayed>接口
	 * 所以要重写compareTo(Delayed o)  和  getDelay(TimeUnit unit)方法
	 */
	@Override
	public int compareTo(Delayed o)
	{
		if(this == o)
		{
			return 0;
		}
		if(o == null)
		{
			return -1;
		}
		if(o instanceof Demo2_4) 
		{
			Demo2_4 od = (Demo2_4) o;
			long comp = this.getInit() - od.getInit();
			return comp<0 ? -1 : (comp==0 ? 0 : 1);
		}
		throw new IllegalArgumentException();
	}

	public long getInit()
	{
		return this.init;
	}
	
	/**
	 * 还要多久才能往外拿
	 */
	@Override
	public long getDelay(TimeUnit unit)
	{
		return unit.convert(init,TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(),TimeUnit.MILLISECONDS);
	}

	public static void main(String[] args) throws InterruptedException
	{
		long now = System.currentTimeMillis();
		Demo2_4 mytask1 = new Demo2_4(now + 2000);
		Demo2_4 mytask2 = new Demo2_4(now + 1000);
		Demo2_4 mytask3 = new Demo2_4(now + 5000);
		Demo2_4 mytask4 = new Demo2_4(now + 4000);
		Demo2_4 mytask5 = new Demo2_4(now + 3000);
		
		BlockingQueue<Demo2_4> que = new DelayQueue<Demo2_4>();
		
		que.put(mytask1);
		que.put(mytask2);
		que.put(mytask3);
		que.put(mytask4);
		que.put(mytask5);
		
		for(int i=0; i<5; i++) 
		{
			try
			{
				Thread.sleep(1100);
				// 如果没到时间就去取的话，会是null
				Demo2_4 task = que.poll();
				System.out.println(task.toString());
				System.out.println(task.getInit());
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	void note()
	{
		/*
		 * 场景：2小时后关机
		 */
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Demo2_4 [init=");
		builder.append(init);
		builder.append("]");
		return builder.toString();
	}
	
	
}
