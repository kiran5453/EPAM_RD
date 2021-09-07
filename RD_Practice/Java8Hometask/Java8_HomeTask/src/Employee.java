public class Employee {
	String employee_name;
	String employee_id;
	Double employee_salary;
	String employee_department;
	public Employee(String name,String id,Double salary,String department){
		this.employee_name=name;
		this.employee_id=id;
		this.employee_salary=salary;
		this.employee_department=department;
	}
	@Override
	public String toString() {
		return "Employee Name - "+this.employee_name
				+" ; Employee ID - "+this.employee_id
				+" ; Employee Salary - "+this.employee_salary
				+" ; Employee Department - "+this.employee_department+"\n";
	}
}