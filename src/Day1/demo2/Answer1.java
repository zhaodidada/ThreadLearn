package Day1.demo2;

public class Answer1
{
	public synchronized void m1() throws Exception
	{
		Thread.sleep(10000);
	}
	
	public void m2() throws Exception
	{
		Thread.sleep(5000);
		System.out.println("m2");
	}
	
	public static void main(String[] args)
	{
		Answer1 a = new Answer1();
		Thread t1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					a.m1();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(() ->
		{
			try
			{
				a.m2();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		t2.start();
	}

}
