package Day1.demo2;

public class Demo2_1 implements Runnable
{
	public static int count = 10;
	
	@Override
	public void run()
	{
		count--;
		System.out.println("线程：" + Thread.currentThread().getName() + "#" + count);
	}

	/**
	 * 先看一下下面这段代码是否存在问题
	 */
	public static void main(String[] args)
	{
		Demo2_1 mt = new Demo2_1();
		
		for(int i=0; i<5; i++)
		{
			Thread t = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mt.run();
				}
			});
			t.start();
		}
	}
	
	void note()
	{
		/*
		 * 在多线程执行过程中，我们加入sleep来提高并发概率
		 * 
		 * 加入sleep后，可以看到有多个重复的数值
		 * 
		 * 这是线程重（chong）入的问题：线程执行一半，时间片被分给别人了，于是执行被打断了，
		 * 而当别的线程执行完毕，原来的线程抢到时间片开始继续执行代码的时候，他的数据却已经被改掉了
		 * 
		 * 如果想要等到一直，也很简单，加个锁。
		 * 
		 * 一个synchronized代码块，相当于一个原子操作，原子就是不可分的。
		 * 
		 * 在线程执行这段代码块的时候，持有了锁，是不可被打断的，只有这个线程执行完这个锁的代码块之后，
		 * 其他线程才可继续执行同样的代码。
		 */
	}
	
	
}
