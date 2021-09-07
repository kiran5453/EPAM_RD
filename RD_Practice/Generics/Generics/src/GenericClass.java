public class GenericClass
{
	public static void main (String[] args)
	{
		SingleType <Integer> iObj = new SingleType<Integer>(15);
		System.out.println("SingleTypeDemo\n\nInteger Value : "+iObj.getObject());

		SingleType <String> sObj = new SingleType<String>("GeeksForGeeks");
		System.out.println("String Value : "+sObj.getObject());
		
		GenericInterface<String, Integer> obj1 = new MultipleType<String, Integer>("Even", 8);
		GenericInterface<String, String>  obj2 = new MultipleType<String, String>("hello", "world");
		
		System.out.println("\nMultipleTypeDemo\n\nObject 1 Key : "+obj1.getKey()+"\nObject 1 Value : "+obj1.getValue());
		System.out.println("\nObject 2 Key : "+obj2.getKey()+"\nObject 2 Value : "+obj2.getValue());
	}
}
