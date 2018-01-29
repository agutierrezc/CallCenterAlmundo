package com.almundo.callCenter.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.almundo.callCenter.model.employee.Employee;
import com.almundo.callCenter.model.employee.Manager;
import com.almundo.callCenter.model.employee.Operator;
import com.almundo.callCenter.model.employee.Supervisor;

/**
 * Ejecuta los test para los metodos login, logout y getAll del servicio EmployeeService
 * 
 * @author felipe.gutierrez
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Before
	public void logoutAllemployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		for (Employee employee : employees) {
			employeeService.logoutEmployee(employee.getName(), employee.getClass());
		}
	}
	
	@Test
	public void loginOperator() {
		String employeeName = "AndresOperador";
		employeeService.loginEmployee(employeeName, Operator.class);
		List<Employee> employees = employeeService.getAllEmployees();
		assertEquals(1, employees.size());
	}
	
	@Test
	public void loginManager() {
		
		String employeeName = "AndresManager";
		employeeService.loginEmployee(employeeName, Manager.class);
		List<Employee> employees = employeeService.getAllEmployees();
		assertEquals(1, employees.size());
	}
	
	@Test
	public void loginSupervisor() {
		
		String employeeName = "AndresSupervisorr";
		employeeService.loginEmployee(employeeName, Supervisor.class);
		List<Employee> employees = employeeService.getAllEmployees();
		assertEquals(1, employees.size());
	}
	
	@Test
	public void loginAll() {
		
		String operator = "AndresOperador";
		String supervisor = "AndresSupervisorr";
		String manager = "AndresManager";
		
		employeeService.loginEmployee(operator, Operator.class);
		employeeService.loginEmployee(supervisor, Supervisor.class);
		employeeService.loginEmployee(manager, Manager.class);
		
		List<Employee> employees = employeeService.getAllEmployees();
		
		assertEquals(3, employees.size());
	}
}
