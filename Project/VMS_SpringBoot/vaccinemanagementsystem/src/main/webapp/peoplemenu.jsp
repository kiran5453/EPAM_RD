<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage People</title>
<link rel="stylesheet">
<style>
.container {
  height: 550px;
  position: relative;
  border: 2px solid green;
}

.peoplemenu {
  margin: 0;
  position: absolute;
  top: 30%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.btn1,.btn2,.btn3 {
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
.btn{
  background-color: #90ee90;
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

.btn:hover {opacity: 1;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn1:hover {opacity: 1; box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn2:hover {opacity: 1;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
.btn3:hover {opacity: 1;box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);}
</style>
</head>
<body>
<h2 align="center">People Menu</h2>
<div class="container">
  <div class="peoplemenu">
    <h3 align="center">Select any of the below operation</h3>
    <a href="addPerson"><button class="btn1">Add Person</button></a><br><br>
    <!--  <a href="addAddressForPerson"><button class="btn2">Add Address for existing person</button></a><br><br> -->
    <a href="getVaccinated"><button class="btn3">Get Vaccinated</button></a><br><br>
      <br><br><br><a href="returnToMenu"><button class="btn">Return To Main Menu</button></a><br><br>
  </div>
  </div>
</body>
</html>