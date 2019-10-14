package Day1.demo1;

public class Demo1_1
{
	Demo1_1() 
	{
	}
	
	/**
	 * 直接锁方法
	 */
	public synchronized void a() 
	{
		System.out.println("action a");
	}
	
	/**
	 * 块中锁自身
	 */
	public void b() 
	{
		synchronized (this)
		{
			// ……
		}
	}
	
	/**
	 * synchronized 用在静态方法
	 */
	public static synchronized void c() 
	{
		
	}
	
	/**
	 * 静态方法、属性是不用new对象出来访问的，所以当你锁定一个静态方法或属性时，
	 * 相当于锁了这个类的Class对象
	 */
	public static void d()
	{
		synchronized (Demo1_1.class)
		{
			
		}
	}
	
}
