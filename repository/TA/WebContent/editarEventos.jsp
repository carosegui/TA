<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar eventos</title>
</head>
<body>
	<h1>Editar</h1>
	
	<form action="Eventos" method="POST">
		<input type="text" value=<%=request.getAttribute("nombre")%> name="nombre">
		<input type="text" value=<%=request.getAttribute("apellido")%> name="apellido">
		
		<input type="submit" >
	</form>
</body>
</html>