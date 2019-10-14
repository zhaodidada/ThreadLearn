package Day2.demo2;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
/**
 * TransferQueue 
 * 
 * 1. take()的阻塞 ：队列中没有元素，执行take的线程就会阻塞，等待生产者添加后再拿
 * 2. transfer() 和  tryTransfer()
 * 	      如果没有消费者线程，transfer就会一直阻塞
 * 	  tryTransfer更加灵活
 */
public class Demo2_5_1
{

	public static void main(String[] args) throws InterruptedException
	{
		TransferQueue<String> q1 = new LinkedTransferQueue<>();
		
		for(int i=0; i<5; i++)
		{
			new Thread(()-> {
				
				String msg = null;
				try
				{
					Thread.sleep(1000);
					msg = q1.take();
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println("消费者" + Thread.currentThread().getName() + ": " + msg);
				
			}).start();
		}
		
		Thread.sleep(5000);
		q1.transfer("hello");
//		boolean _try = q1.tryTransfer("hello");
//		System.out.println(_try);
	}

}
