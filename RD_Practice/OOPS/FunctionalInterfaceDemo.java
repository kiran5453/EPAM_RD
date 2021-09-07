@FunctionalInterface
interface Square
{
	int calculate(int x);
}

public class FunctionalInterfaceDemo
{
	public static void main(String args[])
	{
		int a = 5;
		Square s = (int x)->x*x;
		int ans = s.calculate(a);
		System.out.println(ans);
	}
}
