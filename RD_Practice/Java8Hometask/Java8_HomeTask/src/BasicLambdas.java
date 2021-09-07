import java.util.*;
public class BasicLambdas {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter size of the input array : ");
		int size_of_input_array=sc.nextInt();
		String[] input_array = new String[size_of_input_array];
		System.out.println("Enter array of strings");
		for(int i=0;i<size_of_input_array;i++)
		{
			input_array[i]=sc.next();
		}
	    Arrays.sort(input_array, (s,r) -> s.length() - r.length());
	    System.out.println("Ascending order of length: " + Arrays.asList(input_array));
	    
	    Arrays.sort(input_array, (s,r) -> r.length() - s.length());
	    System.out.println("Descending order of length: " + Arrays.asList(input_array));
	    
	    Arrays.sort(input_array);
	    System.out.println("Alphabetical order: " + Arrays.asList(input_array));
	    
	    Arrays.sort(input_array, Comparator.comparing(i -> (i.contains("e") || i.contains("E") ? 0 : 1)));
	    System.out.print("Strings that contain 'e' first: "+ Arrays.asList(input_array));
	    
	    sc.close();
	}
}
