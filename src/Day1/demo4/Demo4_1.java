package Day1.demo4;

/**
 * volatile 关键字
 */
public class Demo4_1
{
	private boolean running = true;
	
	public void m1()
	{
		while (running)
		{
			
		}
		System.out.println("end");
	}
	
	public void close()
	{
		running = false;
	}
	
	public static void main(String[] args) throws Exception
	{
		Demo4_1 t1 = new Demo4_1();
		
		Thread t2 = new Thread(()->{
			t1.m1();
		});
		t2.start();
		
		Thread.sleep(3000);
		t1.close();
	}

	void note()
	{
		/*
		 * volatile 是可见性的修饰，表示的是让该修饰词修饰的变量或参数，在线程之间可见。
		 * 
		 * 除此之外它还可以防止底层调用代码时的重排序（详见深入JVM）
		 * 
		 */
	}
}
