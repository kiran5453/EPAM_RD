class Base
{
    int Base(int a,int b) // Parameterised Constructor with return type
    {
        System.out.println("Hi");
        return a+b;
    }
}
public class ParameterisedReturn
{
	public static void main(String[] args) 
	{
	    Base base_obj1=new Base(); // No Error
	    Base base_obj2=new Base(10,20); // Compilation Error
	}
}
