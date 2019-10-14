package Day1.demo7;

public class Demo7_1
{
	/*
	 * ThreadLocal-线程局部变量
	 */
	
	private volatile String name;
	
	public Demo7_1(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public static void main(String[] args)
	{
		Demo7_1 demo = new Demo7_1("张三");
		ThreadLocal<Demo7_1> tl = new ThreadLocal<Demo7_1>();
		new Thread(()->{
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(tl.get());
			System.out.println(demo.getName());
			
		}).start();
		new Thread(()->{
			
			tl.set(new Demo7_1("张三"));
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			demo.setName("李四");
			
		}).start();
	}

	void note()
	{
		/*
		 * ThreadLocal相当于在自己线程内部对数据做了一个拷贝，然后自己进行维护
		 * 它是使用空间换时间，相比锁来说（时间换空间），效率会更高一些
		 * 
		 * spring、hibernate中大量使用了ThreadLocal，它提高了效率
		 * 
		 * ThreadLocal在内存中是两个对象
		 * 
		 * ThreadLocal可能会导致内存泄漏
		 */
	}
}
