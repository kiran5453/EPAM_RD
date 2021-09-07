<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccine Management System</title>
<link rel="stylesheet">
<style>
.table{
margin-left: auto;
margin-right: auto;
}
.btn{
  background-color: #87CEEB;
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
</style>
</head>
<body>
		<h3 align="center">Enter the details of the new vaccine</h3>
		<div>
				<form action="vaccine" method="post">
					<table class="table">
						<tr>
							<th>Vaccine name</th>
							<td><input type="text" name="vaccineName"
								class="form-control" required></td>
						</tr>
						<tr>
						<tr>
							<th>Enter stock</th>
							<td><input type="number" name="balanceVaccineCount" min="1" required></td>
						</tr>
						<tr>
							<th>Enter Date(YYYY-MM-DD)</th>
							<td><input type="text" name="dateOfVaccineArrival" pattern="[1-9]{1}[0-9]{3}[-]{1}[0-1]{1}[0-9]{1}[-]{1}[0-3]{1}[0-9]{1}"required></td>
						</tr>
						<tr>
							<td colspan=2><br>
							<button class="btn" type="submit">Add</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
</body>
</html>