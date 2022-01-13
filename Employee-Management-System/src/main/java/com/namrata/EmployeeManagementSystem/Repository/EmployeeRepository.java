package com.namrata.EmployeeManagementSystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.namrata.EmployeeManagementSystem.Entity.Employee;

import net.bytebuddy.description.annotation.AnnotationValue.Sort;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
