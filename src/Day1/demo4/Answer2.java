package Day1.demo4;

import java.util.ArrayList;
import java.util.List;
/**
 * 最简单的示例
 */
public class Answer2
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
		Answer2 w = new Answer2();
		Thread t2 = new Thread(()-> {
			synchronized(w) {
				try
				{
					if(w.size()!=5) {
						w.wait();
					}
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				System.out.println("end");
				//w.notify();
			}
			
		
		});
		t2.start();
		Thread t1 = new Thread(()-> {
			synchronized(w) 
			{
				for(int j=0; j<10; j++) 
				{
					// 睡一会，效果更明显
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					w.add();
					if(w.size()==5) 
					{
						try
						{
							w.notify();
							//w.wait();
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		});
		t1.start();
	}
	
	void note()
	{
		/*
		 * wait、notify、notifyAll 方法是Object的本地final方法，无法被重写。
		 * 一般配合synchronized 关键字使用，即，一般在synchronized 同步代码块里使用 wait()、notify/notifyAll() 方法。
		 * 
		 * wait:使当前线程阻塞，前提是必须先获得锁，方法执行后，释放该锁
		 * notify:唤醒一个wait的线程，不释放锁
		 * notifyAll:唤醒所有wait的线程，不释放锁
		 * 
		 * notify只会唤醒等待中的一个线程，而notifyAll会唤醒所有等待线程，让它们竞争。
		 * 
		 */
	}
}
