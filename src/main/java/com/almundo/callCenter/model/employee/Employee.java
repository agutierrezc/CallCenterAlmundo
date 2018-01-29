package com.almundo.callCenter.model.employee;

import java.util.Date;

import com.almundo.callCenter.model.notice.Notice;

/**
 * Clase abstracta que representa a un empleado
 * 
 * Si se necesita un comportamiento distinto segun el rol se puede sobreescribir
 * el metodo "run"
 * 
 * @author felipe.gutierrez
 *
 */
public abstract class Employee implements Runnable {

	protected String UUID;

	protected String name;

	protected Notice notice;

	protected Employee(String UUID, String name) {
		this.UUID = UUID;
		this.name = name;
	}

	public String getUUID() {
		return UUID;
	}

	public String getName() {
		return name;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public void run() {

		if (notice == null)
			return;

		notice.setStratTime(new Date().toString());
		try {

			Thread.sleep(notice.getDuration());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notice.setEndTime(new Date().toString());

		notice = null;
	}

	public boolean isFree() {
		if (notice != null) {
			return false;
		}
		return true;
	}

	public abstract String getType();

	@Override
	public String toString() {
		return "Employee {\"UUID\":\"" + UUID + "\", \"name\":\"" + name + "\", \"type\":\"" + getType() + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
