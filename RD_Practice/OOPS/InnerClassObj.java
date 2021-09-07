class Outerclass
{
    Outerclass()
    {
        System.out.println("Inside Outerclass");
    }
    class Innerclass
    {
        Innerclass()
        {
            System.out.println("Inside Innerclass");
        }
    }
}
public class InnerClassObj
{
	public static void main(String[] args) 
	{
	    Outerclass outer_obj=new Outerclass();
	    Outerclass.Innerclass inner_obj=outer_obj.new Innerclass();
	}
}
