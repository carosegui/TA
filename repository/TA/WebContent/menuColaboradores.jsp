<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
    
        <%@page import="model.Usuario"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Colaboradores</title>
</head>
<body>
   <h1>Colaboradores</h1>
   <hr>
    	<form method="POST" action="ColaboradoresServlet">
		   <table border ="1" width="500" align="center">
		         <tr bgcolor="00FF7F">
		          <th><b>Nombre</b></th>
		          <th><b>Apellido</b></th>
		          <th><b>Area</b></th>
		          <th>Editar</th>
		         </tr>
		        <%List<Usuario> std = (List<Usuario>)request.getAttribute("usuarios");
		        for(Usuario s:std){%>
		            <tr>
		                <td><%=s.getNombre()%></td>
		                <td><%=s.getApellido()%></td>
		                <td><%=s.getArea().getNombre()%></td>
		                <td>
			                <a href="Colaboradores?UserId=<%=s.getIDUsuario()%>">Editar</a>
		       			</td>
		       			 <td>
			                <button name="eliminar" value="<%=s.getIDUsuario()%>" type="submit">Eliminar</button>
		       			</td>
		            </tr>
		            <%}%>
		        </table> 
			                <a href="Colaboradores">Dar de alta</a>
		        
   		</form>
        
        
</body>
</html>