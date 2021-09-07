<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
<link rel="stylesheet">
<style>
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
</style>
</head>
<body>
<h1>${message }</h1>
<br><br><a href="returnToMenu"><button class="btn">Return To Main Menu</button></a><br><br>
</body>
</html>