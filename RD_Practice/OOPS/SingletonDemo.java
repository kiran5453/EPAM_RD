class Singleton
{
    int value;
    private Singleton(int val)
    {
        value=val;
    }
    static Singleton instance=null;
    public static Singleton getInstance(int x)
    {
        if(instance == null)
            instance=new Singleton(x);
        return instance;
    }
}
public class SingletonDemo
{
	public static void main(String[] args) {
		Singleton obj1=Singleton.getInstance(10);
		Singleton obj2=Singleton.getInstance(20);
		System.out.println("Value of Object1 is "+obj1.value+"\nValue of Object2 is "+obj2.value);
		obj1.value=5;
		System.out.println("\nValue of Object1 is "+obj1.value+"\nValue of Object2 is "+obj2.value);
		obj2.value+=15;
		System.out.println("\nValue of Object1 is "+obj1.value+"\nValue of Object2 is "+obj2.value);
	}
}
