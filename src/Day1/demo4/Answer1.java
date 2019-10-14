package Day1.demo4;

import java.util.ArrayList;
import java.util.List;
/**
 * 一个失败的示例
 */
public class Answer1
{
	private List<Integer> list = new ArrayList<Integer>(10);
	
	public void add()
	{
		list.add(1);
	}
	
	public int size()
	{
		return list.size();
	}
	
	public static void main(String[] args)
	{
		Answer1 answer = new Answer1();
		new Thread(()->{
			while(answer.size()<5)
			{
			}
			System.out.println("end");
		}).start();
		new Thread(()->{
			for(int i=0; i<10; i++)
			{
				answer.add();
			}
		}).start();
		
	}

}
