package com.gl.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.model.Employee;
import com.gl.model.Role;
import com.gl.model.User;
import com.gl.repository.EmployeeRepository;
import com.gl.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event)
	{
		Employee ramesh=Employee.builder().name("Ramesh").email("r@gl.com").build();
		Employee suresh=Employee.builder().name("suresh").email("s@gl.com").build();
		employeeRepository.save(ramesh);
		employeeRepository.save(suresh);
		
		User ramesh1=User.builder().username("ramesh").password(passwordEncoder.encode("ramesh")).emailAddress("ramesh@gl.com").build();
		
		Role userRole=new Role();
		userRole.setRoleName("USER");
		
		Role adminRole=new Role();
		adminRole.setRoleName("ADMIN");
		
		userRole.setUsers(ramesh1);
		adminRole.setUsers(ramesh1);
		
		ramesh1.addRole(userRole);
		
		ramesh1.addRole(adminRole);  //has problem
		System.out.println("line 50");
		User suresh1=User.builder().username("suresh").password(passwordEncoder.encode("suresh")).emailAddress("suresh@gl.com").build();
		Role sureshUserRole=new Role();
		System.out.println("line 53");
		sureshUserRole.setRoleName("USER");
		
		suresh1.addRole(sureshUserRole);
		System.out.println("line 57");
		sureshUserRole.setUsers(suresh1);
		System.out.println("line 59");
		userRepository.save(ramesh1);
		System.out.println("last line");
		userRepository.save(suresh1);
		
//		Role user=Role.builder().roleName("USER").build();
////		user.setUsers(ramesh1);
////		user.setUsers(suresh1);
//		Role admin=Role.builder().roleName("ADMIN").build();
////		admin.setUsers(suresh1);
//		ramesh1.addRole(user);
//		suresh1.addRole(user);
//		suresh1.addRole(admin);
//		
//		
//		userRepository.save(ramesh1);
//		userRepository.save(suresh1);
	}
	
}
