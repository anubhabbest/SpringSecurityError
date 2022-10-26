package com.gl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.model.Employee;
import com.gl.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	 
	public Employee saveEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	public List<Employee> fetchAllEmployees(){
		return employeeRepository.findAll();
	}
	public Employee fetchEmployeeById(long id)
	{
		return employeeRepository.findById(id).orElseThrow(null);
	}
	public void deleteEmployeeById(long id)
	{
		employeeRepository.deleteById(id);
	}
}
