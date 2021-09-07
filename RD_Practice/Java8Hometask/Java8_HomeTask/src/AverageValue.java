import java.util.*;
public class AverageValue {
	public static Double average(List<Integer> list) {
		return list.stream().mapToInt(i -> i).average().getAsDouble();
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
		
		Double result=average(input_list);
		
		System.out.println("Average value of given list of integers is : "+result);
		
		sc.close();
	}
}
