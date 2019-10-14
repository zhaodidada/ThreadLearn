package Day1.demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * 使用信号灯
 */
public class Answer4
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
		 * 信号灯，使用方式差不多，不过它类似一个阻塞队列
		 * 
		 */
		Semaphore sp = new Semaphore(1);
		
		
		new Thread(()->	{
			if(a3.size()!=5)
			{
				try
				{
					// 获取信号灯
					sp.acquire();
					
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
					// 释放信号灯
					sp.release();
				}
			}
		}).start();
	}
}
