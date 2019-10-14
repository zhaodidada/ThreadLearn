package Day2.test1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Demo2_E_2 implements Delayed
{
	// level越小优先级越高
	private int level;
	// 初始的时间，用来表示某个任务产生的时间
	private long init;
	public Demo2_E_2(int level)
	{
		this.level = level;
		init = System.currentTimeMillis();
	}
	
	public static void main(String[] args)
	{
		BlockingQueue<Demo2_E_2> delayQ = new DelayQueue<>();
		
		Demo2_E_2 d1 = new Demo2_E_2(5);
		Demo2_E_2 d2 = new Demo2_E_2(10);
		
		try
		{
			delayQ.put(d1);
			delayQ.put(d2);
		} catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		
		new Thread(() -> {
			while(true)
			{
				try
				{
					TimeUnit.SECONDS.sleep(1);
					delayQ.forEach(d-> System.out.println(d.toString()));
					
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
		}).start();
		new Thread(() -> {
			while(true) {
				try
				{
					TimeUnit.SECONDS.sleep(1);
					Demo2_E_2 d = delayQ.poll();
					
					System.out.println("pool " + d);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
			
		}).start();
	}

	@Override
	public int compareTo(Delayed o)
	{
		if(this == o)
		{
			return 0;
		}
		if(o == null)
		{
			return -1;
		}
		if(o instanceof Demo2_E_2) 
		{
			Demo2_E_2 od = (Demo2_E_2) o;
			long comp = this.level - od.getLevel();
			return comp<0 ? -1 : (comp==0 ? 0 : 1);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public long getDelay(TimeUnit unit)
	{
		long time = level*1000;
		switch(unit)
		{
		case MILLISECONDS:
			break;
		case SECONDS:
			break;
		default:
			break;
		
		}
		return unit.convert(init+time,TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(),TimeUnit.MILLISECONDS);
	}


	public int getLevel()
	{
		return level;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Demo2_E_2 [level=");
		builder.append(level);
		builder.append(", ");
		builder.append(this.getDelay(TimeUnit.SECONDS));
		builder.append("]");
		return builder.toString();
	}
	
}
