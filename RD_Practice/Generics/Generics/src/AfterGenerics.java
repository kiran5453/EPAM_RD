import java.util.*;
public class AfterGenerics {
	public static void main(String []args) {
	
		List<String> list = new ArrayList<String>();    
		list.add("Sai");  
		list.add("Kiran");   
		list.add(20);
		String s1 = list.get(1);
		System.out.println(s1);
	}
}