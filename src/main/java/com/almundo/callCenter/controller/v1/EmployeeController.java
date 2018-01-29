package com.almundo.callCenter.controller.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callCenter.model.employee.Employee;
import com.almundo.callCenter.model.employee.Manager;
import com.almundo.callCenter.model.employee.Operator;
import com.almundo.callCenter.model.employee.Supervisor;
import com.almundo.callCenter.service.EmployeeService;

/**
 * Controller que expone los servicios para los empleados
 * 
 * @author felipe.gutierrez
 *
 */

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/operator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> loginOperator(@RequestParam String name) {
		
		logger.info("[EmployeeController] loginOperator(" + name + ")");
		try {
			employeeService.loginEmployee(name, Operator.class);			
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Hola: " + name, HttpStatus.OK);
	}


	@RequestMapping(value = "/operator", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> logoutOperator(@RequestParam String name) {
		logger.info("[EmployeeController] logoutOperator(" + name + ")");
		
		try {
			employeeService.logoutEmployee(name, Operator.class);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Chao: " + name, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/manager", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> loginManager(@RequestParam String name) {
		
		logger.info("[EmployeeController] loginManager(" + name + ")");
		try {
			employeeService.loginEmployee(name, Manager.class);			
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Hola: " + name, HttpStatus.OK);
	}


	@RequestMapping(value = "/manager", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> logoutManager(@RequestParam String name) {
		logger.info("[EmployeeController] logoutManager(" + name + ")");
		
		try {
			employeeService.logoutEmployee(name, Manager.class);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Chao: " + name, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/supervisor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> loginSupervisor(@RequestParam String name) {
		
		logger.info("[EmployeeController] loginSupervisor(" + name + ")");
		try {
			employeeService.loginEmployee(name, Supervisor.class);			
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Hola: " + name, HttpStatus.OK);
	}


	@RequestMapping(value = "/supervisor", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> logoutSupervisor(@RequestParam String name) {
		logger.info("[EmployeeController] logoutSupervisor(" + name + ")");
		
		try {
			employeeService.logoutEmployee(name, Supervisor.class);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Chao: " + name, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Employee>> getEmployees() {
		logger.info("[EmployeeController] getEmployees()");
		
		try {
			List<Employee> employees = employeeService.getAllEmployees();
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
			
		} catch (RuntimeException e) {
			return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
