package erp_jsp_exam.service;

import java.sql.Connection;
import java.util.List;

import erp_jsp_exam.dao.impl.EmployeeDaoImpl;
import erp_jsp_exam.ds.JdbcUtil;
import erp_jsp_exam.dto.Employee;

public class EmployeeService {
	private Connection con = JdbcUtil.getConnection();
	private EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();

	public EmployeeService() {
		dao.setCon(con);
	}

	public List<Employee> showEmployees() {
		return dao.selectEmployeeByAll();
	}

	public Employee getEmployee(Employee employee) {
		return dao.selectEmployeeByNo(employee);
	}

	public void addEmployee(Employee employee) {
		dao.insertEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		dao.updateEmployee(employee);
	}

	public void removeEmployee(Employee employee) {
		dao.deleteEmployee(employee);
	}
}
