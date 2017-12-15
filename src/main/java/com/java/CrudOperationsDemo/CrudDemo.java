package com.java.CrudOperationsDemo;

public class CrudDemo {

	public static void main(String[] args) {
	
		Operations<Student> op = new StudentOperations();
	
		
		Student s1 = new Student(101, "Monkey", 10, "Pune");
		Student s2 = new Student(102, "Dog", 12, "Mumbai");
		Student s3 = new Student(103, "Tiger", 45, "Chennai");
		Student s4 = new Student(104, "Lion", 1, "Germany");
		Student s5 = new Student(105, "Horse", 66, "Mumbai");			//----- Horse, Mumbai
		Student s6 = new Student(106, "Camel", 23, "UAE");
		Student s7 = new Student(107, "Goat", 78, "Delhi");
		Student s8 = new Student(108, "Leopard", 32, "Hyderabad");
		Student s9 = new Student(109, "Donkey", 90, "US");
		Student s10 = new Student(110, "Horse", 16, "Mumbai");		//----- Horse, Mumbai
		
		Student s11 = new Student(110, "Horse", 66, "Mumbai");
		
		/*-----C-----------Creating/Adding Objects to DB----------------*/
		
		/*op.add(s1);
		op.add(s2);op.add(s3);op.add(s4);op.add(s5);op.add(s6);op.add(s7);op.add(s8);op.add(s9);op.add(s10);
		
		System.out.println("Addition Successful");*/
		
		
		/*-----R-----------Reading Objects from DB----------------*/
		
		//System.out.println(op.get(102));
		for(Student s : op.getAll())
		System.out.println(s);
		

		/*-----U-----------Updating Objects in DB----------------*/
		
		//Student s = op.get(10);
		//System.out.println(op.update(s10));;
		//System.out.println();
		
		/*-----D-----------Deleting Objects in DB----------------*/
		
		//op.delete(102);
		
		
		
		/*-----S-----------Searching Objects in DB----------------*/
		
		//System.out.println(op.searchOnCriteria(s11, SearchCriteria.ALL));
		
		
	}

}
