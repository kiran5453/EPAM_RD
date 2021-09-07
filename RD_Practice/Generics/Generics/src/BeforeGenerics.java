import java.util.*;
public class BeforeGenerics {
	public static void main(String []args) {
		
		List list = new ArrayList();    
		list.add("Sai");  
		list.add("Kiran");   
		list.add(20);
		String s1 = (String) list.get(1);
		System.out.println(s1);
		String s2 = (String) list.get(2); 
		//System.out.println(s2);
	}
}
