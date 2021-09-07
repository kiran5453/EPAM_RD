<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vaccine Management System</title>
<link rel="stylesheet">
<style>
.container {
  height: 550px;
  position: relative;
  border: 2px solid green;
}

.menu {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.btn1,.btn2,.btn3,.btn4,.btn5,.btn6 {
  background-color: #87CEEB;
  width: 100%;
  border: none;
  color: black;
  padding: 16px 32px;
  text-align: center;
  font-size: 16px;
  margin: 4px 2px;
  opacity: 0.8;
  transition: 0.3s;
}
.btn1:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn2:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn3:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn4:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn5:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn6:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
</style>
</head>
<body>

<h1 align="center">Welcome to Vaccine Management System</h1>
  <div class="container">
  <div class="menu">
    <h3 align="center">Select any of the below operation</h3>
    <a href="manageVaccine"><button class="btn1">Manage Vaccine</button></a><br><br>
    <a href="managePeople"><button class="btn2">Manage People</button></a><br><br>
    <a href="getAllVaccines"><button class="btn3">Get Vaccine Details in Database</button></a><br><br>
    <a href="getAllPersons"><button class="btn4">Get People Details in Database</button></a><br><br>
   <!-- <a href="getVaccine"><button class="btn5">Get Vaccine Details based on Name</button></a><br><br>
    <a href="getPerson"><button class="btn6">Get People Details based on Aadhar</button></a><br><br> -->
  </div>
  </div>
</body>
</html>
