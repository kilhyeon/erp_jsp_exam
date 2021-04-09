<%@page import="erp_jsp_exam.ds.JdbcUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBConnection</title>
</head>
<body>
연결테스트
<%=JdbcUtil.getConnection() %>
</body>
</html>