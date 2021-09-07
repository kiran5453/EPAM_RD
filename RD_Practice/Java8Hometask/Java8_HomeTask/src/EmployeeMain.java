import java.util.*;
public class EmployeeMain {
	public static Double getAverageSalary(List<Employee> list) {
		return list.stream()
	             .mapToDouble(e->e.employee_salary)
	             .average()
	             .getAsDouble();
	}
	public static Double getHighestSalary(List<Employee> list) {
		return list.stream()
	             .mapToDouble(e->e.employee_salary)
	             .max()
	             .getAsDouble();
	}
	public static void sortOnName(List<Employee> list) {
		list.sort(Comparator.comparing(e->e.employee_name));
	}
	public static void sortOnID(List<Employee> list) {
		list.sort(Comparator.comparing(e->e.employee_id));
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String name,id,department;
		Double salary;
		Employee employee_object;
		System.out.print("Eneter number of employees : ");
		int number_of_employees=sc.nextInt();
		List<Employee> employee_list=new ArrayList<Employee>(number_of_employees);
		for(int employee_number=0;employee_number<number_of_employees;employee_number++) {
			System.out.println("Enter details of Employee "+(employee_number+1));
			System.out.print("Employee Name : ");
			name=sc.next();
			System.out.print("Employee Id :");
			id=sc.next();
			System.out.print("Employee Salary : ");
			salary=sc.nextDouble();
			System.out.print("Employee Department :");
			department=sc.next();
			employee_object=new Employee(name,id,salary,department);
			employee_list.add(employee_object);
		}
		System.out.println("Highest salary among all employees is : "+getHighestSalary(employee_list));
		System.out.println("Average Salary of Employees is : "+getAverageSalary(employee_list));
		sortOnName(employee_list);
		System.out.println("Sorted list based on employee name :\n"+employee_list);
		sortOnID(employee_list);
		System.out.println("Sorted list based on employee id :\n"+employee_list);
		sc.close();
	}
}
