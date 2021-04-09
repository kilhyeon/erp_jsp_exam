package erp_jsp_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import erp_jsp_exam.dao.EmployeeDao;
import erp_jsp_exam.dto.Department;
import erp_jsp_exam.dto.Employee;
import erp_jsp_exam.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();
	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	public EmployeeDaoImpl() {
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empNo, empName, titleNo, titleName, managerNo, "
				+ "managerName, salary, deptNo, deptName, floor, " + "hireDate from vw_full_employee";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<Employee>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empNo");
		String empName = rs.getString("empName");
		Title title = new Title(rs.getInt("titleNo"));
		Employee manager = new Employee(rs.getInt("managerNo"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("deptNo"));
		Date hireDate = rs.getTimestamp("hireDate");
		try {
			title.setTitleName(rs.getString("titleName"));
		} catch (SQLException e) {
		}

		try {
			manager.setEmpName(rs.getString("managerName"));
		} catch (SQLException e) {
		}

		try {
			dept.setDeptName(rs.getString("deptName"));
		} catch (SQLException e) {
		}

		try {
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {
		}
		return new Employee(empNo, empName, title, manager, salary, dept, hireDate);
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select empNo, empName, titleNo, titleName, managerNo, "
				+ "managerName, salary, deptNo, deptName, floor, " + "hireDate from vw_full_employee where empNo = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().getTitleNo());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee set empName = ?, title = ?, manager = ?, salary = ?, dept = ?, hireDate = ? where empNo = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTitle().getTitleNo());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			pstmt.setTimestamp(6, new Timestamp(emp.getHireDate().getTime()));
			pstmt.setInt(7, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where empNo = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
