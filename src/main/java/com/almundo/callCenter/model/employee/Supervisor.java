package com.almundo.callCenter.model.employee;

public class Supervisor extends Employee{
	
	public final static String EMPLOYEE_TYPE= "Supervisor";

	public Supervisor( String UUID, String nombre) {
		super(UUID, nombre);
	}

	@Override
	public String getType() {
		return EMPLOYEE_TYPE;
	}
}
