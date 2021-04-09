<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
<link rel="stylesheet" href="css/emplist.css">
</head>
<body>
	<h2>사원 목록</h2>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>직책</td>
				<td>직속상사</td>
				<td>급여</td>
				<td>부서</td>
				<td>입사일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${list }">
				<tr>
					<td>${emp.empNo }</td>
					<td>${emp.empName }</td>
					<td>${emp.title.titleName }</td>
					<td><c:if test="${emp.manager.empNo eq 0 }">

						</c:if> <c:if test="${emp.manager.empNo ne 0 }">
						${emp.manager.empName }(${emp.manager.empNo })
					</c:if></td>
					<td><fmt:formatNumber value="${emp.salary }" pattern="#,###" /></td>
					<td>${emp.dept.deptName }</td>
					<td><fmt:formatDate value="${emp.hireDate}"
							pattern="yyyy년 MM월 dd일" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>