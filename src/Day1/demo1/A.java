package Day1.demo1;

public class A
{
	private int i = 0; 
	
	public A() {
		
	}
	
	public void a() {
		System.out.println("action a");
	}
	
	public void b() {
		System.out.println("action b");
	}
	
	public void increase() {
		for(int n=0; n<10000; n++) {
			i++;
			System.out.println("i=" + i);
		}
	}
	
}
