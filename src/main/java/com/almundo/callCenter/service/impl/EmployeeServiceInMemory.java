package com.almundo.callCenter.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.almundo.callCenter.model.employee.Employee;
import com.almundo.callCenter.model.employee.EmployeeFactory;
import com.almundo.callCenter.service.EmployeeService;

@Service
public class EmployeeServiceInMemory implements EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceInMemory.class);

//	private List<Employee> employees;
	
	private CopyOnWriteArrayList<Employee> employees;

	public EmployeeServiceInMemory() {
		employees = new CopyOnWriteArrayList<Employee>();
	}

	@Override
	public <T extends Employee> Employee loginEmployee(String name, Class<T> employeeClass) {

		try {
			Employee employee = EmployeeFactory.createEmployee(name, employeeClass);
			employees.add(employee);
			return employee;
		} catch (Exception e) {
			logger.error("Error iniciando sesion del empleado", e);
			throw new RuntimeException("Error iniciando sesion del empleado", e);
		}
	}

	@Override
	public <T extends Employee> void logoutEmployee(String name, Class<T> employeeClass) {
		
		try {
			Employee employee = EmployeeFactory.createEmployee(name, employeeClass);
			employees.remove(employee);
		} catch (Exception e) {
			logger.error("Error cerrando sesion del empleado", e);
			throw new RuntimeException("Error cerrando sesion del empleado", e);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employees;
	}
}
