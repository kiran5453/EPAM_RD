public class TypePromotion
{
	public static void main(String[] args) {
		System.out.println(method(null));
	}
	public static String method(String s)
	{
	    return "String class Called";
	}
	public static String method(Object o)
	{
	    return "Object class Called";
	}
}
