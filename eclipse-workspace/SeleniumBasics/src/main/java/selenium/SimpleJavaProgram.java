package selenium;

public class SimpleJavaProgram {
	 private String employee;
		 private int salary;
		 
		 public void set(String employee,int salary)
		  {
			  this.employee=employee;
			  this.salary=salary;
		  }
		 
		public void get()
		{
			System.out.println(employee);
			System.out.println(salary);
		}
		public static void main(String args[])
		{
			SimpleJavaProgram obj=new SimpleJavaProgram();
					obj.set("john",1200);
			        obj.get();
}
}
