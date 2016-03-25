<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BHVirusAnalyzer Server</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/style.css" />">
</head>
<body>
	<h1>Test results:</h1>
	
	 ${hello} !
	<table class="CSSTableGenerator">
		<tr>
			<td>ID</td>
			<td>Team Name</td>
			<td>Team Code</td>
			<td>IP address</td>
			<td>Start Time</td>
			<td>End Time</td>
			<td>Score</td>
		</tr>
		<c:forEach var="result" items="${results}">
			<tr>
				<td>${result.id}</td>
				<td>${result.teamName}</td>
				<td>${result.teamCode}</td>
				<td>${result.ipAddress}</td>
				<td><fmt:formatDate value="${result.startSubmitTime}"
						pattern="HH:mm:ss" /></td>
				<td><fmt:formatDate value="${result.endSubmitTime}"
						pattern="HH:mm:ss" /></td>
				<td>${result.score}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>