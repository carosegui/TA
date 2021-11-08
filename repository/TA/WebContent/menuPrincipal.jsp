
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Proceso de Seleccion de pasantes </title>
</head>
<body>

<h1>Proceso de seleccion de pasantes</h1>
 <hr>	

 <div name="Colaboradores">	
 	<form action="ColaboradoresServlet" method="GET">		
 	<h5>
 		Colaboradores
 	</h5>	
 	<input type="submit" value = "Ver colaboradores">
 	</form>
 </div>
  <div name="Procesos">
  
  <form action="ProcesosServlet" method="GET">
  	<h5>
 		Procesos 
 	</h5>
 	 <input type="submit" value = "Ver procesos">
  </form>
 	
 </div>
 <div name="Agenda">
 <form action="AgendaServlet" method="GET">
  	 <h5>
	 	Agenda
	 </h5>
	 <input type="submit" value = "Ver agenda">
 </form>
	
</div>
	
</body>
</html>