abstract class Abstract
{
    Abstract()
    {
        System.out.println("Inside Abstract Class Constructor");
    }
}
class Derived extends Abstract
{
    Derived()
    {
        System.out.println("Inside Derived Class Constructor");
    }
}
public class AbstractClassConstructor
{
	public static void main(String[] args) 
	{
	    Derived obj=new Derived();
	}
}
