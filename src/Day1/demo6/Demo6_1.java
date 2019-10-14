package Day1.demo6;

import java.util.LinkedList;
import java.util.List;

/**
 * 写一个固定容量的同步容器，有put方法（放入）和get方法（取出），以及getCount方法（记录容器中有多少对象）
 * 
 * 要求，如果容器已经满了，调用put的线程就得等着；如果容器已经空了，调用get的线程也得等着。
 *
 */
public class Demo6_1<T>
{
	private List<T> list = new LinkedList<>();
	private int maxCount = 10;
	private int count = 0;

	public synchronized void put(T t) throws Exception
	{
		System.out.println("启动生产者线程");
		if(list.size()==maxCount) 
		{
			wait();
		}
		list.add(t);
		count++;
		notifyAll();
		System.out.println("结束生产者线程:" + list.size());
	}
	
	public synchronized T get() throws Exception
	{
		System.out.println("启动消费者线程");
		if(list.size()==0) 
		{
			wait();
		}
		T t = list.get(0);
		list.remove(0);
		count--;
		notifyAll();
		System.out.println("结束消费者线程：" + list.size());
		return t;
	}
	
	public static void main(String[] args)
	{
		Demo6_1<String> container = new Demo6_1<String>();
		
		for(int i=0; i<10; i++)
		{
			// 消费者线程
			new Thread(()-> {
				System.out.println("C-"+Thread.currentThread().getName());
				try
				{
					container.get();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}).start();
		}
		
		for(int i=0; i<2; i++)
		{
			// 生产者线程
			new Thread(()-> {
				System.out.println("P-"+Thread.currentThread().getName());
				try
				{
					container.put("s");
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}).start();
		}
	}

	// 要点：
	// while
	// notify
	
	void note()
	{
		/*
		 * 99.9%的wait()都是和while一起使用的
		 * 永远使用notifyAll而不是用notify
		 * 
		 * 除了wait、notify  还可以      使用condition
		 */
		
		
	}
}
