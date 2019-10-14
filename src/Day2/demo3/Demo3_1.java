package Day2.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
/**
 * Phaser 按照不同阶段执行线程
 * 
 * 演示场景：结婚
 * 
 * 该类模拟人
 */
public class Demo3_1 implements Runnable
{	// 姓名
	private String name;
	// 阶段
	private Phaser phaser;
	// 初始化给一个名字
	public Demo3_1(String name, Phaser phaser)
	{
		this.name = name;
		this.phaser = phaser;
	}
	/**
	 * 到场
	 */
	void arrived()
	{
		try
		{
			TimeUnit.SECONDS.sleep(1);
			System.out.println(name + " 到场了");
			phaser.arriveAndAwaitAdvance();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 吃好了
	 */
	void ate()
	{
		
		try
		{
			TimeUnit.SECONDS.sleep(1);
			System.out.println(name + " 吃好了");
			phaser.arriveAndAwaitAdvance();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 离开了
	 */
	void leave()
	{
//		System.out.println(name + " 离开了");
//		phaser.arriveAndAwaitAdvance();
		
		if(name.equals("新郎") || name.equals("新娘"))
		{
			System.out.println(name + " 等待");
			phaser.arriveAndAwaitAdvance();
		}
		else
		{
			System.out.println(name + " 离开了");
			phaser.arriveAndDeregister();
		}
	}
	
	void hug()
	{
//		if(name.equals("新郎") || name.equals("新娘"))
//		{
			System.out.println(name + " 抱抱");
			phaser.arriveAndAwaitAdvance();
//		}
//		else
//		{
//			phaser.arriveAndDeregister();
//		}
	}
	
	@Override
	public void run()
	{
		arrived();
		
		ate();
		
		leave();
		
		hug();
		
	}
	
	static class marryPhaser extends Phaser
	{
		@Override
		protected boolean onAdvance(int phase, int registeredParties)
		{
			switch(phase)
			{
				case 0:
					System.out.println("所有人都到齐了");
					System.out.println();
					return false;
				case 1:
					System.out.println("所有人都吃完了");
					System.out.println();
					return false;
				case 2:
					System.out.println("所有人都离开了");
					System.out.println();
					return false;
				case 3:
					System.out.println("婚礼结束!");
					System.out.println();
					return false;
				default:
					return true;
			}
			
		}
	}
	
	public static void main(String[] args)
	{
		ExecutorService service = Executors.newFixedThreadPool(10);
		Phaser phaser = new marryPhaser();
		phaser.bulkRegister(10);
		
		Demo3_1 xl = new Demo3_1("新郎", phaser);
		service.submit(xl);
		
		Demo3_1 xn = new Demo3_1("新娘", phaser);
		service.submit(xn);
		
		for(int i=1; i<9;)
		{
			service.submit(new Demo3_1("宾客" + i++ , phaser));
		}
		
		service.shutdown();
	}
	
}
