<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BHVirusAnalyzer Server</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/style.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/results.css" />">
</head>
<body>
	<h1>Test results</h1>
	<c:if test="${resultSize==0}">
	No results are submitted yet.
	</c:if>
	<c:if test="${resultSize>0}">
		<div>Submitted results: ${resultSize}</div>
		<table class="CSSTableGenerator">
			<tr>
				<td></td>
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
					<td><input type="checkbox" value="${result.id}" class="ids"></td>
					<td>${result.id}</td>
					<td>${result.teamName}</td>
					<td>${result.teamCode}</td>
					<td>${result.ipAddress}</td>
					<td><fmt:formatDate value="${result.startSubmitTime}" pattern="HH:mm:ss" /></td>
					<td><fmt:formatDate value="${result.endSubmitTime}" pattern="HH:mm:ss" /></td>
					<td>${result.score}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="buttons">
			<button id="compareButton">Compare Selected Results</button>
			<button id="selectAllButton">Select All</button>
			<button id="clearSelectionButton">Clear Selection</button>
		</div>
	</c:if>
	<script type="text/javascript">
		var compareButton = document.getElementById("compareButton");
		var selectAllButton = document.getElementById("selectAllButton");
		var clearSelectionButton = document.getElementById("clearSelectionButton");
		
		compareButton.addEventListener('click', function(){
			var selected = document.querySelectorAll("input.ids:checked");
			if(selected.length > 0){
				url = "compare?";
				for(var i=0;i<selected.length;i++){
	                url += "ids="+selected[i].value+"&";
	            }
				window.location.href = url;
			}
		});
		
		selectAllButton.addEventListener('click', function(){
            var checkboxes = document.querySelectorAll("input.ids");
            for(var i=0;i<checkboxes.length;i++){
            	checkboxes[i].checked = true;
            }
        });
		
		clearSelectionButton.addEventListener('click', function(){
			var checkboxes = document.querySelectorAll("input.ids");
            for(var i=0;i<checkboxes.length;i++){
                checkboxes[i].checked = false;
            }
        });

	</script>
</body>
</html>