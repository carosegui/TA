<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="model.Area"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EDITAR COLABORADORES</title>
</head>
<body>
  <%int id = 0;
	if(request.getParameter("UserId") != null ){	
		id = Integer.parseInt(request.getParameter("UserId"));
  	}else if(request.getAttribute("UserId") != null){
		id = Integer.parseInt(request.getAttribute("UserId").toString());
	}%>
<% if(id != 0){%>
	<h1>Editar colaborador </h1>
	<%}else{%>	
	<h1>Dar de alta colaborador</h1>			
 <%}%>
 <hr>	
	
	<form action="Colaboradores" method="POST">
	<% if(request.getAttribute("nombreInvalido") != null){%>
			 	<label style="color: red;">El campo nombre no puede contener caracteres de tipo numerico.</label>
	<% }%>
	<% if(request.getParameter("UserId") != null  || request.getAttribute("UserId") != null){%>
		<label>nombre</label>
			
		<input type="text" value=<%=request.getAttribute("nombre")%> name="nombre">
		<% if(request.getAttribute("apellidoInvalido") != null){%>
			 	<label style="color: red;">El campo apellido no puede contener caracteres de tipo numerico.</label>
	<% }%>
		<label>apellido</label>
		<input type="text" value=<%=request.getAttribute("apellido")%> name="apellido">
		<label>DNI</label>
		<input type="number" value=<%=request.getAttribute("dni")%> name="dni" maxlength="8">
		<label>contraseña</label>
		<input type="text" value=<%=request.getAttribute("contraseña")%> name="contraseña">
		<label>nombre de usuario</label>
		<input type="text" value=<%=request.getAttribute("nombreUsuario")%> name="nombreUsuario">
			<% if(request.getAttribute("mailInvalido") != null){%>
			 	<label style="color: red;">El mail no tiene un formato valido.</label>
			<% }%>
		<label>mail</label>
		<input type="text" value=<%=request.getAttribute("mail")%> name="mail">
	<%}else{%>	
		<label>nombre</label>
	 	<input type="text" name="nombre">
		<label>apellido</label>
		<input type="text" min="7" max="8" name="apellido">
		<label>DNI</label>
		<input type="number" name="dni">
		<label>contraseña</label>
		<input type="text" name="contraseña">
		<label>nombre de usuario</label>
		<input type="text" name="nombreUsuario">
		<label>mail</label>
		<input type="text" name="mail">
	 <%}%>
				<label>Area</label>
		
		<select name="idArea">
	 	<%List<Area> std = (List<Area>)request.getAttribute("areas");
		        for(Area a : std){%>
		          	<option value="<%=a.getIDArea()%>" ><%= a.getNombre() %></option>
		            <%}%>
	 	</select>

		<input type="hidden" value=<%=id%> name="id"> 
	
			<% if(id != 0){		%>
					<input type="submit" name="Editar" value="Editar">

			<%}else{%>	
					<input type="submit" name="Alta" value="Alta">
			 <%}%>
				
	</form>
</body>
</html>