class Base
{
    int Base() // Default Constructor with return type
    {
        System.out.println("Base Class Default Constructor");
        return 20;
    }
}
public class DefaultReturn
{
	public static void main(String[] args) 
	{
	    Base base_obj2=new Base(); // runs with No Error but prints nothing
	}
}
