package Day1.demo1;
/**
 * 简单演示synchronized关键字的作用
 * @author zhengzx
 *
 */
public class Demo1_2
{
	/*
	 * synchronized 修饰的代码块变成同步的了，一个线程运行时，
	 * 其他线程要等待当前线程执行完毕这个代码块之后，才能竞争进入
	 */
	public static void main(String[] args)
	{
		A a = new A();
		for(int i=0; i<2; i++) 
		{
			Thread t = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						/*
						 * 要执行下面这段代码时，请线程先去申请这个对象（a）的锁
						 */
						synchronized (a)
						{
							Thread.sleep(5000);
							a.a();
						}
						
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
	}

	void note() 
	{
		/*
		 * 记住synchronized关键字锁的是对象，是new出来的这个对象（在堆内存中），且这个锁只是该堆内存对象的一个参数
		 * 比如：有锁参数值为1，无锁参数值为0
		 */
		
		// 如果我们把锁的对象的引用改变了，那么锁的“对象”也会随之改变
		A a = new A();
		A b = new A();
		synchronized (a)
		{
			a = b;
		} 
		
		/*
		 * 不要以字符串或字符串常量作为锁定对象，如果一个字符串 equals 一个常量，那么锁的是同一个对象。
		 * 曾经在Jetty（一个开源servlet容器，类似tomcat）中出现了一个非常隐蔽的死锁，就是因为开发人员刚好锁定了一个字符串常量导致的，
		 * 但是由于在类库中， 看不到代码，极难调试。
		 */
	}
}
	
