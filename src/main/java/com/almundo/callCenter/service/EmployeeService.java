package com.almundo.callCenter.service;

import java.util.List;

import com.almundo.callCenter.model.employee.Employee;

public interface EmployeeService {
	
	public <T extends Employee> Employee loginEmployee(String name, Class<T> employeeClass );
	
	public List<Employee> getAllEmployees();
	
	public <T extends Employee> void logoutEmployee(String name, Class<T> employeeClass);
	
}
