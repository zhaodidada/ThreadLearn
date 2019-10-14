package Day2.demo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * CopyOnWriteArrayXXX
 */
public class Demo2_2
{
	/**
	 * vector的写操作
	 * @throws InterruptedException
	 */
	@Test
	public void vector_Write() throws InterruptedException
	{
		List<Integer> list = new Vector<Integer>();
		
		int size = 1000;
		Thread[] threads = new Thread[size];
		
		CountDownLatch latch = new CountDownLatch(size);
		long start = System.currentTimeMillis();
		for(int i=0; i<size; i++)
		{
			threads[i] = new Thread(()->{
				for(int j=0; j<100; j++) 
				{
					list.add(j);
					System.out.println("j");
				}
				latch.countDown();
			});
			threads[i].start();
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end-start + "ms");
		System.out.println(list.size());
	}
	/**
	 * copyOnWrite的写操作
	 * @throws InterruptedException
	 */
	@Test
	public void copyOnWrite_Write() throws InterruptedException
	{
		List<Integer> list = new CopyOnWriteArrayList<Integer>();
		
		int size = 1000;
		Thread[] threads = new Thread[size];
		
		CountDownLatch latch = new CountDownLatch(size);
		long start = System.currentTimeMillis();
		for(int i=0; i<size; i++)
		{
			threads[i] = new Thread(()->{
				for(int j=0; j<100; j++) 
				{
					list.add(j);
					System.out.println("j");
				}
				latch.countDown();
			});
			threads[i].start();
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end-start + "ms");
		System.out.println(list.size());
	}

	List<Integer> testList;
	ExecutorService service;
	
	@Before
	public void before()
	{
		int size = 10000;
		testList = new ArrayList<Integer>(size);
		for(int i = 0; i<size ; i++)
		{
			testList.add(i);
		}
		
		service = Executors.newFixedThreadPool(12);
	}
	
	@After
	public void close()
	{
		service.shutdownNow();
	}
	/**
	 * vector的读写耗时
	 */
	@Test
	public void vector_read() throws InterruptedException
	{
		Vector<Integer> v = new Vector<>(testList);
		CountDownLatch latch = new CountDownLatch(12);
		long start = System.currentTimeMillis();
		for(int i=0; i<10; i++)
		{
			service.submit(()->{
//				v.forEach(e->{
//					System.out.println(Thread.currentThread().getName() + " :" + e);
//					
//				});
				
				Iterator<Integer> iterator = v.iterator();
				while(iterator.hasNext())
				{
					System.out.println(Thread.currentThread().getName() + " :" + iterator.next());
					
				}
				latch.countDown();
				System.out.println(Thread.currentThread().getName() + " end");
			});	
		}
		for(int i=0; i<2; i++)
		{
			service.submit(()->{
				for(int j=0; j<1000; j++)
				{
					v.add(j);
					System.out.println(Thread.currentThread().getName() + " : add");
				}
				latch.countDown();
				System.out.println(Thread.currentThread().getName() + " end");
			});	
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println("vec 读完耗时：" + (end-start) + " 毫秒");
	}
	
	/**
	 * copyonwrite的读写耗时
	 * @throws InterruptedException
	 */
	@Test
	public void copyOnWrite_read() throws InterruptedException
	{
		List<Integer> v = new CopyOnWriteArrayList<>(testList);
		CountDownLatch latch = new CountDownLatch(12);
		long start = System.currentTimeMillis();
		for(int i=0; i<10; i++)
		{
			service.submit(()->{
				Iterator<Integer> iterator = v.iterator();
				while(iterator.hasNext())
				{
					System.out.println(iterator.next());
				}
				latch.countDown();
			});	
		}
		for(int i=0; i<2; i++)
		{
			service.submit(()->{
				for(int j=0; j<1000; j++)
				{
					v.add(j);
				}
				latch.countDown();
			});	
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println("cor 读完耗时：" + (end-start) + " 毫秒");
	}
	
	void note() 
	{
		Collections.synchronizedList(new ArrayList<Object>());
	}
}
