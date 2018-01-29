package com.almundo.callCenter.model.employee;

public class Manager extends Employee{

	public final static String EMPLOYEE_TYPE= "Director";

	public Manager( String UUID, String nombre) {
		super(UUID, nombre);
	}

	@Override
	public String getType() {
		return EMPLOYEE_TYPE;
	}
}
