package com.namrata.EmployeeManagementSystem.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import com.namrata.EmployeeManagementSystem.Entity.Employee;
import com.namrata.EmployeeManagementSystem.Repository.EmployeeRepository;



@RestController
public class EmployeeController {
	
	@Autowired
	private  EmployeeRepository er;

	public EmployeeController(EmployeeRepository er) {
		this.er = er;
	}

	@PostMapping(path ="/addEmployee")
	public String addEmployee(@RequestBody Employee employee)
	{
		Optional<Employee> emp=er.findById(employee.getId());
		if(emp.isPresent())
		{
			return "Employee already exists in the database";
		}
		else {
			er.save(employee);
			return "Employee added successfully! ...";
		}
	}
	
	@GetMapping(path ="/updateEmployeeSalary/{id}/{salary}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@PathVariable double salary)
	{
	  Optional<Employee> emp=er.findById(id);
	  if(emp.isPresent()) {
		  Employee e=emp.get();
		  
		  e.setSalary(salary);
		  
		  return new ResponseEntity<> (er.save(e),HttpStatus.OK);
	  }
	  else {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	
	@GetMapping(path = "/findallEmployeeBySalary")
	public List<Employee> getEmployeeBySalary(){
		
        return er.findAll(Sort.by(Sort.Direction.DESC, "salary"));
        		
    }
	

	@GetMapping(path = "/findEmployeeById/{id}")
	public String getEmployeeById(@PathVariable Integer id){
		Optional<Employee> emp=er.findById(id);
		  if(emp.isPresent()) {
			  er.findById(id);
		 String s=emp.toString();
		 return s;
		  }
		  else
		  {
			  String s="Employee does not exists in the database";
			  return s;
		  }
	}

	@GetMapping(path = "/findallEmployees")
	public List<Employee> getEmployees(){
		return er.findAll();
	}
	
	@GetMapping(path = "/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Integer id){
		Optional<Employee> emp=er.findById(id);
		  if(emp.isPresent()) {
		 er.deleteById(id);
		 String s="Employee with id = "+id+" are Deleted";
		 return s;
		  }
		  else
		  {
			  String s="Employee does not exists in the database";
			  return s;
		  }
	}
	
	@GetMapping(path = "/deleteallEmployees")
	public String deleteEmployees(){
		 er.deleteAll();
		 String s="All Employees are Deleted";
		 return s;
	}

    
}
