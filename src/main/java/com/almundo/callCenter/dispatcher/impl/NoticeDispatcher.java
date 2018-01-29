package com.almundo.callCenter.dispatcher.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.almundo.callCenter.dispatcher.IDispatcher;
import com.almundo.callCenter.model.employee.Employee;
import com.almundo.callCenter.model.employee.Operator;
import com.almundo.callCenter.model.employee.Supervisor;
import com.almundo.callCenter.model.notice.Call;
import com.almundo.callCenter.model.notice.Notice;
import com.almundo.callCenter.service.EmployeeService;

@Component
public class NoticeDispatcher implements IDispatcher{
	
	private static final int MAXIMUM_DURATION = 100;
	private static final int MINIMUM_DURATION = 50;
//	private static final L
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	@Autowired
	private EmployeeService employeeService;
	
	public NoticeDispatcher() {
		threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	@Override
	public void dispatchNotice(Notice notice) throws NoSuchElementException {
		Employee employee = getFreeEmployee();
		
		if(employee == null)
			throw new NoSuchElementException("No hay empleados disponibles, por favor llame mas tarde");
		
		if(notice.getDuration() == null)
			notice.setDuration(getRandomNoticeDuration());
		if(notice instanceof Call) {
			String recordId = UUID.randomUUID().toString();
			((Call) notice).setRecordId(recordId);
		}
		
		employee.setNotice(notice);
		notice.setAttendedBy(employee.toString());
		threadPoolExecutor.execute(employee);
		
		System.out.println(employee.toString());
	}
	
	private Long getRandomNoticeDuration() {
		return ThreadLocalRandom.current().nextLong(MINIMUM_DURATION, MAXIMUM_DURATION) * 100;
	}
	
	
	/**
	 * Metodo que permite asignar el empleado que va a tender la llamada segun la prioridad.
	 *  
	 * @return Si encuentra un empleado disponible devuelve el empleado, si no returna null
	 */
	private Employee getFreeEmployee() {
		List<Employee> employees = employeeService.getAllEmployees();
		Employee bestEmployee = null;
		
		for (Employee employee : employees) {
			if(employee.isFree() == false)
				continue;
			
			if(employee instanceof Operator) {
				bestEmployee = employee;
				break;
			} else if(employee instanceof Supervisor) {
				bestEmployee = employee;
			} else if(bestEmployee == null)
				bestEmployee = employee;
		}
		
		return bestEmployee;
	}

}
