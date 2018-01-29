package com.almundo.callCenter.model.employee;

public class Operator extends Employee{

	public final static String EMPLOYEE_TYPE= "Operador";

	public Operator( String UUID, String name) {
		super(UUID, name);
	}

	@Override
	public String getType() {
		return EMPLOYEE_TYPE;
	}
}
