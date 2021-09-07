import java.util.stream.*;
import java.util.*;
public class CommaSeperated {
	public static String getString(List<Integer> list) {
		return list.stream().map(i -> i % 2 == 0 ? "e" + i : "o" + i).collect(Collectors.joining(","));
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter size of the input list : ");
		int size_of_input_list=sc.nextInt();
		
		System.out.println("Enter the list integers :");
		List<Integer> input_list=new ArrayList<Integer>(size_of_input_list);
		
		for(int i=0;i<size_of_input_list;i++)
		{
			input_list.add(sc.nextInt());
		}
		
		String result=getString(input_list);
		
		System.out.println("Comma Seperated String is : "+result);
		
		sc.close();
	}
}