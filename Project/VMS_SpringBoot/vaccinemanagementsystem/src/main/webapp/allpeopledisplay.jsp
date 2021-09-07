<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccine Management System</title>
<link rel="stylesheet">
<style>
table {
  margin-left: auto;
  margin-right: auto;
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

.btn{
  background-color: #90ee90;
  width: 50%;
  border: none;
  color: black;
  padding: 16px 32px;
  text-align: center;
  font-size: 16px;
  opacity: 0.8;
  transition: 0.3s;
  margin: 0;
  position: absolute;
  left: 50%;
  -ms-transform: translateX(-50%);
  transform: translateX(-50%);
}

.btn:hover {
opacity: 1;
box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

tr:nth-child(even) {background-color: #f2f2f2;}
</style>
</head>
<body>
			<div>
				<h3 align="center">
					Details of Persons available
				</h3>
				<br> <br>
				<h3>${messageString}</h3>
				<br>
				<h2>${emptyMessage}</h2>
				<table class="table" style="color:${color}">
					<tr>
						<th>Name</th>
						<th>Aadhar</th>
						<th>Age</th>
						<th>FirstDose Date</th>
						<th>SecondDose Date</th>
						<th>Vaccine taken</th>
					</tr>
					<c:forEach items="${persons}" var="person">
						<tr>
							<td><c:out value="${person.name}" /></td>
							<td><c:out value="${person.aadharNumber}" /></td>
							<td><c:out value="${person.age}" /></td>
							<td><c:out value="${person.firstDoseDate}" /></td>
							<td><c:out value="${person.secondDoseDate}" /></td>
							<td><c:out value="${person.vaccineType}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<br><br><a href="returnToMenu"><button class="btn">Return To Main Menu</button></a><br><br>
</body>
</html>
