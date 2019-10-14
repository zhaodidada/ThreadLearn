package Day1.demo3;

/**
 * 模拟一个银行账户的操作
 * @author zhengzx
 *
 */
public class Demo3_1
{
	/*
	 * 通常我们都知道对写操作加锁，但是对读操作要不要加锁呢
	 */
	
	// 账户人姓名
	@SuppressWarnings("unused")
	private String name;
	// 余额
	private double balance; 
	
	public Demo3_1()
	{
	}
	
	/**
	 * 设定账户里有多少钱
	 * @param name
	 * @param balance
	 */
	public synchronized void setAccount(String name, double balance) throws Exception
	{
		this.name = name;
		Thread.sleep(2000);
		this.balance = balance;
	}
	/**
	 * 通过名字来获取账户里的余额
	 * @param name
	 * @return
	 */
	public double getBalance(String name)
	{
		return this.balance;
	}
	
	public static void main(String[] args) throws Exception
	{
		Demo3_1 zhangsan = new Demo3_1();
		Thread t1 = new Thread(()->
		{
			try
			{
				zhangsan.setAccount("张三", 100.00);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		t1.start();
		
		Thread.sleep(1000);
		System.out.println(zhangsan.getBalance("张三"));
		Thread.sleep(2000);
		System.out.println(zhangsan.getBalance("张三"));
	}

	void note()
	{
		/*
		 * 以上就是“脏读”问题：由于对写进行了加锁，但是没有对读进行加锁，那就有可能读到在写之中读到未完成的数据
		 */
	}
}
