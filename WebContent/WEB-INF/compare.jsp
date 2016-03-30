<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1>Compare answers</h1>

	<c:if test="${resultsToCompareSize==0}">
	No results selected/found for comparison.
	</c:if>
	<c:if test="${resultsToCompareSize>0}">
		<div>Results to compare: ${resultsToCompareSize}</div>
		<table class="CSSTableGenerator">
			<tr>
				<td>Question</td>
				<td>Correct Answer</td>
				<td>Point Value</td>

				<c:forEach var="result" items="${resultsToCompare}">
					<td>${result.teamName}(${result.teamCode})</td>
				</c:forEach>
			</tr>
			<c:forEach var="question" items="${questions}" varStatus="status">
				<tr>
					<td>${question.question}</td>
					<td>${question.correctAnswer}</td>
					<td>${question.pointValue}</td>
					<c:forEach var="i" begin="0" end="${resultsToCompareSize-1}">
						<td>${resultsToCompare[i].givenAnswers[status.index].givenAnswer}</td>
					</c:forEach>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<c:forEach var="result" items="${resultsToCompare}">
					<td>${result.score}</td>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</body>
</html>