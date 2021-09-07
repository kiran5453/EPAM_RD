import java.util.stream.*;
import java.util.*;
public class FilterStrings {
	public static List<String> search(List<String> list){
		return list.stream().filter(string -> string.startsWith("a")).filter(string -> string.length() == 3).collect(Collectors.toList());
	}
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter size of the input list : ");
		int size_of_input_list=sc.nextInt();
		
		System.out.println("Enter the list strings:");
		List<String> input_list=new ArrayList<String>(size_of_input_list);
		
		for(int i=0;i<size_of_input_list;i++)
		{
			input_list.add(sc.next());
		}
		
		List<String> result=search(input_list);
		
		if(result.size()==0)
			System.out.println("There are no elements that start with '\'a\' and have exactly 3 letters");
		else {
			System.out.println("Strings that start with \'a\' and have exactly 3 letters are :");
			for(String element:result)
				System.out.println(element);
		}
		
		sc.close();
	}
}
