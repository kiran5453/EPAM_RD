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
.btn1{
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

.btn1:hover {
opacity: 1;
box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
.btn:hover {
opacity: 1;
box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}
</style>
</head>
<body>
<h3 align="center">
			Please enter the Aadhar Number
		</h3>
		<div>
				<form action="inputaadhar" method="post">
					<table class="table">
						<tr>
							<th>Person Aadhar Number</th>
							<td><input type="text" name="aadharNumber"
								class="form-control" required pattern="[2-9]{1}[0-9]{11}" placeholder="12 digit number"></td>
						</tr>
						<tr>
							<th>Type of vaccine you wish to take</th>
							<td><input type="text" name="name"
								class="form-control" required></td>
						</tr>
						<tr>
							<td colspan=2><br>
							<button class="btn1" type="submit">Submit</button>
							</td>
						</tr>
					</table>
				</form>
		</div>
		<br><br><br><a href="returnToMenu"><button class="btn">Return To Main Menu</button></a><br><br>
</body>
</html>