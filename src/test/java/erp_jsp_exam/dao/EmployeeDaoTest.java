package erp_jsp_exam.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jsp_exam.JdbcUtil;
import erp_jsp_exam.dao.impl.EmployeeDaoImpl;
import erp_jsp_exam.dto.Department;
import erp_jsp_exam.dto.Employee;
import erp_jsp_exam.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	private static Connection con;
	private static EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con.close();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() {
		System.out.printf("%s()%n", "test01SelectEmployeeByAll");

		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		list.parallelStream().forEach(System.out::println);
	}

	@Test
	public void test04SelectEmployeeByNo() {
		System.out.printf("%s()%n", "test04SelectEmployeeByNo");

		Employee emp = new Employee(4377);
		Employee searchEmp = dao.selectEmployeeByNo(emp);
		Assert.assertNotNull(searchEmp);
		System.out.println(searchEmp);
	}

	@Test
	public void test02InsertEmployee() {
		System.out.printf("%s()%n", "test02InsertEmployee");

		Employee emp = new Employee(9999, "홍길동", new Title(3), new Employee(1003), 3000000, new Department(1),
				new Date());
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(emp));
	}

	@Test
	public void test03UpdateEmployee() {
		System.out.printf("%s()%n", "test03UpdateEmployee");

		Employee emp = new Employee(9999, "홍동", new Title(2), new Employee(4377), 4000000, new Department(2),
				new Date());
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(emp));
	}

	@Test
	public void test05DeleteEmployee() {
		System.out.printf("%s()%n", "test05DeleteEmployee");

		Employee emp = new Employee(9999);
		int res = dao.deleteEmployee(emp);
		Assert.assertEquals(1, res);
		dao.selectEmployeeByAll().parallelStream().forEach(System.out::println);
	}
}
