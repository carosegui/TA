<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="java.util.List"%>
    
        <%@page import="model.Proceso"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu procesos</title>
</head>
<body>
   <h1>Procesos</h1>
    <hr>	
   <table border ="1" width="500" align="center">
         <tr bgcolor="00FF7F">
          <th><b>Legajo</b></th>
          <th><b>Estado</b></th>
          <th><b>Fecha Inicio</b></th>
          <th><b>Fecha Fin</b></th>
          <th>Editar</th>
          <th></th>
          
         </tr>
        <%List<Proceso> std = (List<Proceso>)request.getAttribute("procesos");
        for(Proceso s:std){%>
        <%-- Arranging data in tabular form
        --%>
            <tr>
                <td><%=s.getLegajo().getIDLegajo()%></td>
                <td><%=s.getEstado().getIDEstado()%></td>
                <td><%=s.getFechaInicio()%></td>
                <td><%=s.getFechaFin()%></td>
                <td>
	                <a href="Procesos?IDProceso=<%=s.getIDProceso()%>">Editar</a>
       			</td>
       			<td> 
	       			<form action="ProcesosServlet" method="post">
					    <button name="eliminar" value="<%=s.getIDProceso()%>" type="submit">Eliminar</button>
	       			</form>
       			</td>
       			
            </tr>
            <%}%>
        </table> 
        
  					<a href="Procesos">Dar de alta</a>
        
        
        
</body>
</html>