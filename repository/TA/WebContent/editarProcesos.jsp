<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@page import="model.Estado"%>
<%@page import="model.Notificacion"%>
<%@page import="model.Usuario"%>
<%@page import="model.Legajo"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Procesos</title>
</head>
<body>
	<%int id = 0;
		if(request.getParameter("IDProceso") != null ){	
			id = Integer.parseInt(request.getParameter("IDProceso"));
	  	}else if(request.getAttribute("IDProceso") != null){
			id = Integer.parseInt(request.getAttribute("IDProceso").toString());
		}%>
	<form action="Procesos" method="POST">
		<% if(request.getParameter("IDProceso") == null){%>
		<h1>Dar de alta</h1>
		<%}else{%>	
			<h1>Editar</h1>			
		<%}%>
		<hr>	
		<select name="idLegajo">
		 	<%List<Legajo> usuarios = (List<Legajo>)request.getAttribute("usuarios");
			        for(Legajo u : usuarios){%>
			          	<option value="<%=u.getIDLegajo()%>" > <%= u.getNombre()+" "+u.getApellido()%> lgjo: <%= u.getIDLegajo()%></option>
            <%}%>
	 	</select>
		<select name="idEstado" id="idEstado">
		 	<%List<Estado> estados = (List<Estado>)request.getAttribute("estados");
			        for(Estado e : estados){%>
			          	<option value="<%=e.getIDEstado()%>" ><%= e.getComentario() %></option>
            <%}%>
	 	</select>	
<%-- 		<select name="idResponsable">
		 	<%List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
			        for(Usuario u : usuarios){%>
			          	<option value="<%=u.getIDUsuario()%>" ><%= u.getNombre() +" "+u.getApellido() %></option>
            <%}%>
	 	</select>	 --%>
	 	<select name="idNotificacion" style="width: 120px!important">
		 	<%List<Notificacion> notis = (List<Notificacion>)request.getAttribute("notificaciones");
			        for(Notificacion u : notis){%>
			          	<option value="<%=u.getIDNotificacion()%>" ><%= u.getRazon() %></option>
            <%}%>
	 	</select>		
		<input type="date" value=<%=request.getAttribute("fechainicio")%> name="fechainicio">
		<input type="date" value=<%=request.getAttribute("fechafin")%> name="fechafin">
		<input type="hidden" value=<%=request.getAttribute("id")%> name="id"> 
			<% if(id != 0){		%>
					<input type="submit" name="Editar" value="Editar">

			<%}else{%>	
					<input type="submit" name="Alta" value="Alta">
			 <%}%>
	</form>
	<script type="text/javascript">
	var temp = '<%= request.getAttribute("idEstado") %>'
	var mySelect = document.getElementById('idEstado');
	for(var i, j = 0; i = mySelect.options[j]; j++) {
	    if(i.value == temp) {
	        mySelect.selectedIndex = j;
	        break;
	    }
	}
	</script>
</body>
</html>	