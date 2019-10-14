package Day1.demo3;

public class Answer2
{
	public synchronized void m()
	{
		for(int i=3;i>=0;i--) 
		{
			System.out.println(Thread.currentThread());
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			int a = 1/i;
			System.out.println(a);
		}
		while(true) 
		{
		}
			
	}
	
	
	public static void main(String[] args)
	{
		Answer2 a2 = new Answer2();
		Thread t = new Thread(()->{
			a2.m();
		});
		t.start();
		
		Thread t2 = new Thread(()->{
			a2.m();
		});
		t2.start();
		
	}

}
