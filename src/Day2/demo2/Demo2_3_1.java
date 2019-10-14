package Day2.demo2;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class Demo2_3_1
{
	public static void main(String[] args)
	{
		/*
		 *  基于堆的优先级队列，完全二叉树
		 *  
		 *  完全二叉树要求除了倒数第一层其它层全部满额，最后一层即使不满额也必须从左到右
		 *  
		 */
		PriorityQueue<Integer> pt = new PriorityQueue<>();
		
		Random random = new Random();
		
		for(int i=0; i<10; i++)
		{
			int rd = random.nextInt(100);
			pt.offer(rd);
			System.out.println("add " + rd);
		}
//		pt.offer(1);
		pt.stream().forEach(i-> System.out.println(i));	
		
		@SuppressWarnings("unused")
		PriorityBlockingQueue<Object> pbq;
	}
}
