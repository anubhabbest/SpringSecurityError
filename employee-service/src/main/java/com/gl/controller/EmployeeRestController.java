package com.gl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.model.Employee;
import com.gl.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

	private final EmployeeService employeeService;
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping
	public List<Employee> fetchAllEmployees() {
		return employeeService.fetchAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long id) {
		return employeeService.fetchEmployeeById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long id) {
		employeeService.deleteEmployeeById(id);
	}
}
