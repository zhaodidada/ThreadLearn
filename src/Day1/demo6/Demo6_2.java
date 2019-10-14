package Day1.demo6;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量的同步容器，有put方法（放入）和get方法（取出），以及getCount方法（记录容器中有多少对象）
 * 
 * 要求，如果容器已经满了，调用put的线程就得等着；如果容器已经空了，调用get的线程也得等着。
 *
 */
public class Demo6_2<T>
{
	private static Lock lock = new ReentrantLock();
	private static Condition producer = lock.newCondition();
	private static Condition consumer = lock.newCondition();
	
	private List<T> list;
	private int maxCount = 10;
	private int count = 0;
	
	Demo6_2()
	{
		this.list = new LinkedList<>();
	}
	
	public void put(T t) throws InterruptedException
	{
		System.out.println("启动生产者线程");
		boolean bool = lock.tryLock();
		if(bool)
		{
			while(list.size()==maxCount) 
			{
				producer.await();//生产者全部等待
			}
			list.add(t);
			count++;
			consumer.signalAll();//消费者全部唤醒
			System.out.println("结束生产者线程:" + list.size());
			lock.unlock();
		}
	}
	
	public T get() throws Exception
	{
		System.out.println("启动消费者线程");
		T t = null;
		boolean bool = lock.tryLock();
		if(bool)
		{
			while(list.size()==0) 
			{
				consumer.await();
			}
			t = list.get(0);
			list.remove(0);
			count--;
			producer.signalAll();
			System.out.println("结束消费者线程：" + list.size());
			lock.unlock();
		}	
		return t;
	}
	
	/*
	 * 使用wait、notify  或      使用condition
	 */
	public static void main(String[] args)
	{
		Demo6_2<String> container = new Demo6_2<String>();
		
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
}
