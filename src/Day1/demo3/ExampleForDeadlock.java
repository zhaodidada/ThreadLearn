package Day1.demo3;

/**
 * 死锁示例
 * @author zhengzx
 *
 */
public class ExampleForDeadlock
{
	public static synchronized void m1() throws Exception
	{
		m2();
		Thread.sleep(2000);
		System.out.println("m1");
	}
	
	public static synchronized void m2() throws Exception
	{
		m1();
		Thread.sleep(5000);
		System.out.println("m2");
	}

	public static void main(String[] args) throws Exception
	{
		new Thread(()->{
			try
			{
				ExampleForDeadlock.m1();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}).start();
		
		new Thread(()->{
			try
			{
				ExampleForDeadlock.m2();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}).start();
	}

	void note()
	{
		/*
		 * 什么是活锁
		 * T1能使用资源A，但是它比较礼貌，让给了T2，T2此时能使用资源A，但是它也很绅士，选择让给T1，于是就这么让来让去，循环下去
		 * 
		 * 什么是饥饿
		 * 非公平锁的抢占机制就会可能导致饥饿，当T1线程占用了资源A，T2，T3，T4。。。都在等待锁的释放，
		 * 当锁释放的时候，T3抢到了资源，之后又释放锁，T4抢到了资源。。。T2 总是抢不到。这就是饿死
		 */
	}
}
