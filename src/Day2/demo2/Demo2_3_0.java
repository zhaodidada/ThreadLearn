package Day2.demo2;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo2_3_0
{
	BlockingQueue<Object> bq;
	public static void main(String[] args)
	{
		// 无界队列
		Queue<Integer> linkedQueue = new ConcurrentLinkedQueue<Integer>();
		
		for(int i=0; i<10; i++)
		{
			linkedQueue.offer(i);  // 类似add，往里加
		}
		System.out.println("添加完毕：" + linkedQueue.size());
		
		linkedQueue.poll();  // 第一个往外拿，队列中删掉
		System.out.println("拿走一个后" + linkedQueue.size());
		
		linkedQueue.peek();  //	第一个往外拿，但不删
		System.out.println("看一下" + linkedQueue.size());
		
		
		Queue<Object> arrayQueue = new ArrayBlockingQueue<Object>(10);
		for(int i=0; i<10; i++)
		{
			arrayQueue.add(i);  // 类似add，往里加
		}
		
	}

}
