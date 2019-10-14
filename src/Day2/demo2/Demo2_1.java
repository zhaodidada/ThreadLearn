package Day2.demo2;

import java.util.Map;
import java.util.concurrent.*;
/**
 * ConcurrentHashMap
 * 
 * 比较一下ConcurrentHashMap和HashTable并发情况下往里添加元素的效率
 */
public class Demo2_1{
	
	public static void main(String[] args) throws InterruptedException	{
		Map<Integer, Object> map = new ConcurrentHashMap<Integer, Object>();
		Thread[] threads = new Thread[100];
		// 计数器为100的门栓
		CountDownLatch latch = new CountDownLatch(threads.length);
		long start = System.currentTimeMillis();
		for(int i=0; i<100; i++)		{
			threads[i] = new Thread(()->{
				for(int j=0; j<10000; j++) 			{
					map.put(Thread.currentThread().hashCode(), j);
				}
				latch.countDown();
			});
			threads[i].start();
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println(end-start + "ms");
	}

	void note()
	{
		/*
		 * HashTable:160ms-220ms
		 * 
		 * ConcurrentHashMap:90ms-110ms
		 * 
		 * HashTable在添加元素时，是要锁定整个对象，ConcurrentHashMap默认是把容器分成16段，每次往里添加时是锁定16分之一，把锁细化了
		 * 
		 * 
		 * 非并发Map里面要排序的话，就用TreeMap，或者用SortedMap（插入效率较低）
		 * 高并发并且排序：
		 * ConcurrentSkipListMap(跳表，插入效率会稍微低，使用空间换时间)
		 */
		
	}
}
