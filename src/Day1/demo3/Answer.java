package Day1.demo3;

public class Answer
{
	public synchronized void m1()
	{
		System.out.println("m1");
		m2();
	}
	
	public synchronized void m2()
	{
		System.out.println("m2");
	}
	
	
	public static void main(String[] args)
	{
		Answer a = new Answer();
		Thread t = new Thread(()->{
			a.m1();
		});
		t.start();
	}
	
	void note()
	{
		/*
		 * 同一线程中synchronized的锁是可重入的，即可以再获得一遍。
		 * 
		 * 子类的同步方法可以调用父类的同步方法。
		 * 
		 * 程序执行中，默认，如果A线程出现异常，synchronized锁是会被释放的，那么其他线程就很有可能会获得A中处理一半的数据。
		 */
	}

}
