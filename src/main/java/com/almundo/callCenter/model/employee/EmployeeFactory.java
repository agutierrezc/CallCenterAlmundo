package com.almundo.callCenter.model.employee;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EmployeeFactory {

	public static <T extends Employee> Employee createEmployee(String name, Class<T> employeeType ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		String employeeUUID = UUID.randomUUID().toString();
		
		Employee employee = employeeType.getConstructor(String.class, String.class).newInstance(employeeUUID, name);
		return employee;
	}
}
