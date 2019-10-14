package Day2.demo1;

/**
 * 线程安全的单例模式
 */
public class Demo1_1
{

	/**
	 * 无论有多少线程多少次调用该方法，拿到的都是同一个对象
	 * @return
	 */
	public static Demo1_1 getSingle()
	{
		return Singleton.SINGLETON;
	}
	
	private static class Singleton
	{
		private static Demo1_1 SINGLETON = new Demo1_1();
	}
	
	public static void main(String[] args)
	{

	}

}
