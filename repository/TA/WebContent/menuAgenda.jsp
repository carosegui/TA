<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
        <%@page import="java.util.List"%>
    
        <%@page import="model.Evento"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda</title>
</head>
<body>
   <h1>Eventos</h1>
   	<hr>	
   
   <table border ="1" width="500" align="center">
         <tr bgcolor="00FF7F">
          <th><b>Nombre</b></th>
          <th><b>Apellido</b></th>
          <th><b>AREA</b></th>
          <th>Editar</th>
         </tr>
        <%List<Evento> std = (List<Evento>)request.getAttribute("eventos");
        for(Evento s:std){%>
        <%-- Arranging data in tabular form
        --%>
            <tr>
                <td><%=s.getIDUsuario().getIDUsuario()%></td>
                <td><%=s.getAsunto()%></td>
                <td><%=s.getFecha()%></td>
                <td>
	                <a href="Colaboradores?UserId=<%=s.getIDEvento()%>">Editar</a>
       			</td>
            </tr>
            <%}%>
        </table> 
        
        
</body>
</html>