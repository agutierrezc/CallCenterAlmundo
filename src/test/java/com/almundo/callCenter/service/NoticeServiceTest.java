package com.almundo.callCenter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.NoSuchElementException;

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
import com.almundo.callCenter.model.notice.Call;
import com.almundo.callCenter.model.notice.Notice;

/**
 * Ejecuta los test para los metodos doNotice, getAllAttendNotice y getAllRejectedNotice del servicio NoticeService.
 * 
 * 
 * @author felipe.gutierrez
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeServiceTest {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Before
	public void logoutAllemployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		for (Employee employee : employees) {
			employeeService.logoutEmployee(employee.getName(), employee.getClass());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void doNoticeWithoutEmployees() {
		String clientId = "1019011235";
		noticeService.doNotice(clientId, Call.class);
	}
	
	@Test
	public void doNoticeWithOpertator() {
		String employeeName = "AndresOperador";
		Employee operator = employeeService.loginEmployee(employeeName, Operator.class);
		
		String clientId = "1";
		Notice notice = noticeService.doNotice(clientId, Call.class);
		
		assertEquals(operator.toString(), notice.getAttendedBy());
		
	}
	
	@Test
	public void doNoticeWithOpertatorAndSupervisor() {
		String operatorName = "AndresOperador";
		Employee operator = employeeService.loginEmployee(operatorName, Operator.class);
		
		String supervisorName = "AndresSupervisor";
		Employee supervisor = employeeService.loginEmployee(supervisorName, Supervisor.class);
		
		String clientId = "1";
		Notice operatorNotice = noticeService.doNotice(clientId, Call.class);
		
		clientId = "2";
		Notice supervisorNotice = noticeService.doNotice(clientId, Call.class);
		
		assertEquals(operator.toString(), operatorNotice.getAttendedBy());
		assertEquals(supervisor.toString(), supervisorNotice.getAttendedBy());
	}
	
	@Test
	public void doNoticeWithOpertatorSupervisorAndManager() {
		String operatorName = "AndresOperador";
		Employee operator = employeeService.loginEmployee(operatorName, Operator.class);
		
		String supervisorName = "AndresSupervisor";
		Employee supervisor = employeeService.loginEmployee(supervisorName, Supervisor.class);
		
		String managerName = "AndresManager";
		Employee manager = employeeService.loginEmployee(managerName, Manager.class);
		
		String clientId = "1";
		Notice operatorNotice = noticeService.doNotice(clientId, Call.class);
		
		clientId = "2";
		Notice supervisorNotice = noticeService.doNotice(clientId, Call.class);

		clientId = "3";
		Notice managerNotice = noticeService.doNotice(clientId, Call.class);
		
		assertEquals(operator.toString(), operatorNotice.getAttendedBy());
		assertEquals(supervisor.toString(), supervisorNotice.getAttendedBy());
		assertEquals(manager.toString(), managerNotice.getAttendedBy());
	}
	
	@Test
	public void doNoticeWithTwoOpertatorAndSupervisor() {
		String supervisorName = "AndresSupervisor";
		Employee supervisor = employeeService.loginEmployee(supervisorName, Supervisor.class);
		
		String operatorName = "AndresOperador";
		employeeService.loginEmployee(operatorName, Operator.class);
		
		String operator2Name = "AndresOperador2";
		employeeService.loginEmployee(operator2Name, Operator.class);
		
		String clientId = "1";
		Notice operatorNotice = noticeService.doNotice(clientId, Call.class);
		
		clientId = "2";
		Notice operator2Notice = noticeService.doNotice(clientId, Call.class);

		clientId = "3";
		Notice supervisorNotice = noticeService.doNotice(clientId, Call.class);
		
		assertNotEquals(supervisor.toString(), operatorNotice.getAttendedBy());
		assertNotEquals(supervisor.toString(), operator2Notice.getAttendedBy());
		assertEquals(supervisor.toString(), supervisorNotice.getAttendedBy());
	}
	
	/**
	 * Se deben tener todos los empleados para que se atiendan todas las llamadas
	 */
	@Test
	public void doTenNoticeWithTenEmployees() {
		String managerName = "AndresManager";
		employeeService.loginEmployee(managerName, Manager.class);
		
		String supervisorName = "AndresSupervisor";
		employeeService.loginEmployee(supervisorName, Supervisor.class);
		
		String supervisor2Name = "AndresSupervisor2";
		employeeService.loginEmployee(supervisor2Name, Supervisor.class);
		
		String supervisor3Name = "AndresSupervisor3";
		employeeService.loginEmployee(supervisor3Name, Supervisor.class);
		
		String supervisor4Name = "AndresSupervisor4";
		employeeService.loginEmployee(supervisor4Name, Supervisor.class);
		
		String operatorName = "AndresOperador";
		employeeService.loginEmployee(operatorName, Operator.class);
		
		String operator2Name = "AndresOperador2";
		employeeService.loginEmployee(operator2Name, Operator.class);
		
		String operator3Name = "AndresOperador2";
		employeeService.loginEmployee(operator3Name, Operator.class);
		
		String operator4Name = "AndresOperador2";
		employeeService.loginEmployee(operator4Name, Operator.class);
		
		String operator5Name = "AndresOperador2";
		employeeService.loginEmployee(operator5Name, Operator.class);
		
		String clientId = "1";
		Notice notice1 = noticeService.doNotice(clientId, Call.class);
		clientId = "2";
		Notice notice2 = noticeService.doNotice(clientId, Call.class);
		clientId = "3";
		Notice notice3 = noticeService.doNotice(clientId, Call.class);
		clientId = "4";
		Notice notice4 = noticeService.doNotice(clientId, Call.class);
		clientId = "5";
		Notice notice5 = noticeService.doNotice(clientId, Call.class);
		clientId = "6";
		Notice notice6 = noticeService.doNotice(clientId, Call.class);
		clientId = "7";
		Notice notice7 = noticeService.doNotice(clientId, Call.class);
		clientId = "8";
		Notice notice8 = noticeService.doNotice(clientId, Call.class);
		clientId = "9";
		Notice notice9 = noticeService.doNotice(clientId, Call.class);
		clientId = "10";
		Notice notice10 = noticeService.doNotice(clientId, Call.class);
		
		assertNotNull(notice1.getAttendedBy());
		assertNotNull(notice2.getAttendedBy());
		assertNotNull(notice3.getAttendedBy());
		assertNotNull(notice4.getAttendedBy());
		assertNotNull(notice5.getAttendedBy());
		assertNotNull(notice6.getAttendedBy());
		assertNotNull(notice7.getAttendedBy());
		assertNotNull(notice8.getAttendedBy());
		assertNotNull(notice9.getAttendedBy());
		assertNotNull(notice10.getAttendedBy());

	}
	
}
