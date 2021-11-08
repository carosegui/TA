

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import dao.AreaDAO;
import dao.EstadoDAO;
import dao.LegajoDAO;
import dao.NotificacionDAO;
import dao.ProcesoDAO;
import dao.UsuarioDAO;
import implementation.AreaDAOimp;
import implementation.EstadoDAOimp;
import implementation.LegajoDAOimp;
import implementation.NotificacionDAOimp;
import implementation.ProcesoDAOimp;
import implementation.UsuarioDAOimp;
import model.Area;
import model.Estado;
import model.Legajo;
import model.Notificacion;
import model.Proceso;
import model.Usuario;

/**
 * Servlet implementation class ProcesosEditar
 */
@WebServlet("/Procesos")
public class ProcesosEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ProcesosEditar() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request = SetAttributes(request);
		RequestDispatcher rd = request.getRequestDispatcher("editarProcesos.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int idEstado = Integer.parseInt(request.getParameter("idEstado"));
		int idLegajo = Integer.parseInt(request.getParameter("idLegajo"));
		int idNotificacion = Integer.parseInt(request.getParameter("idNotificacion"));
	
		
		LegajoDAO legajoDAO = new LegajoDAOimp();
		EstadoDAO estadoDAO = new EstadoDAOimp();
		NotificacionDAO notificacionDAO = new NotificacionDAOimp();
		ProcesoDAO p = new ProcesoDAOimp();
		Proceso proceso = new Proceso();
		Notificacion n = (Notificacion) notificacionDAO.getOne(idNotificacion);
		java.sql.Date fechaInicio = java.sql.Date.valueOf(request.getParameter("fechainicio"));
		java.sql.Date fechaFin = java.sql.Date.valueOf(request.getParameter("fechafin"));
		System.out.println(idLegajo);

		if (idLegajo != 0) {
			System.out.println(idLegajo);
			Legajo l = (Legajo) legajoDAO.getOne(idLegajo);
			proceso.setLegajo(l);
		}
		if (idEstado != 0) {
			Estado e = (Estado) estadoDAO.getOne(idEstado);
			proceso.setEstado(e);
		}
		
		if (idNotificacion != 0) {
			Notificacion e = (Notificacion) notificacionDAO.getOne(idNotificacion);
			proceso.setNotificacion(e);
		}
		
		if(fechaFin != null) {
			proceso.setFechaFin(fechaFin);
		} 
		
		if(fechaInicio != null) {
			proceso.setFechaInicio(fechaInicio);
		} 
		
		
		if(request.getParameter("Editar") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			proceso.setIDProceso(id);
			Editar(proceso);
		}else {
			System.out.println(proceso.getFechaInicio());
		  p.crear(proceso);
		}
		response.sendRedirect("/TA/ProcesosServlet");
	}
	
	public Proceso getProceso(int procesoID){
		
		ProcesoDAO procesoDAO = new ProcesoDAOimp();
		
		try {
			return (Proceso) procesoDAO.getOne(procesoID);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void Editar(Proceso proceso) {
		ProcesoDAO procesoDAO = new ProcesoDAOimp();

		try {
			procesoDAO.modificar(proceso);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Estado> getAllEstados(){
		EstadoDAO estadosDAO = new EstadoDAOimp();

		try {
			return estadosDAO.getAll();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public List<Notificacion> getAllNotificaciones(){
		NotificacionDAO notiDao = new NotificacionDAOimp();

		try {
			return notiDao.getAll();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	public List<Usuario> getAllUsuarios(){
		UsuarioDAO userDAO = new UsuarioDAOimp();
		try {
			return userDAO.getAll();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean Validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int IDProceso = 0;
		RequestDispatcher rd = null;
		Boolean redirect = false;
		if(request.getParameter("id") != null) {
			IDProceso = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("IDProceso", IDProceso);
		}else {
			request.setAttribute("IDProceso", null);
		}
		//Seteo los valores que entraron para devolverlos a la vista en caso de que no se cumpla una validacion.
		request = setAttributeValidation(request);
		if(request.getParameter("nombre").matches(".*\\d.*")) {
			//Verifica que el nombre no tenga numeros
			request.setAttribute("nombreInvalido", true);
			redirect = true;
		}
		return redirect;
	}
	
	public HttpServletRequest setAttributeValidation(HttpServletRequest request) {
		if(request.getParameter("Editar") != null) {
		request.setAttribute("idLegajo", request.getParameter("idLegajo"));
		request.setAttribute("estados", getAllEstados());
		request.setAttribute("notificaciones", getAllNotificaciones());
		request.setAttribute("usuarios", getAllLegajos());
		request.setAttribute("fechainicio", request.getParameter("fechainicio"));
		request.setAttribute("fechafin", request.getParameter("fechafin"));
		request.setAttribute("Editar", true);
		}else {
			request.setAttribute("idLegajo", request.getParameter("idLegajo"));
			request.setAttribute("idResponsable", getAllEstados());
			request.setAttribute("idNotificacion", request.getParameter("idNotificacion"));
			request.setAttribute("fechainicio", request.getParameter("fechainicio"));
			request.setAttribute("fechafin", request.getParameter("fechafin"));
			request.setAttribute("Alta", true);
			request.setAttribute("UserID", null);
		}
		return request;
	}
	
	public HttpServletRequest SetAttributes(HttpServletRequest request) {
		if(request.getParameter("IDProceso") != null) {
			int id = Integer.parseInt(request.getParameter("IDProceso"));
			Proceso proceso = getProceso(id);
			System.out.println(proceso.getLegajo().getIDLegajo());
			request.setAttribute("IDLegajo", proceso.getLegajo().getIDLegajo());
			request.setAttribute("estados", getAllEstados());
			request.setAttribute("idEstado", proceso.getEstado().getIDEstado());
			request.setAttribute("notificaciones", getAllNotificaciones());
			request.setAttribute("usuarios", getAllLegajos());
			request.setAttribute("fechainicio", proceso.getFechaInicio());
			request.setAttribute("fechafin", proceso.getFechaFin());
			request.setAttribute("id", id);
		}else {
			request.setAttribute("idLegajo", 0);
			request.setAttribute("estados", getAllEstados());
			request.setAttribute("usuarios", getAllLegajos());
			request.setAttribute("notificaciones", getAllNotificaciones());
			request.setAttribute("fechainicio", new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			request.setAttribute("fechafin", new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			request.setAttribute("id", 0);
		}
		
		return request;
		
	}              
	
	public Legajo getLegajo(int idLegajo) {
		try {
			LegajoDAO legajoDAO = new LegajoDAOimp();
			return (Legajo) legajoDAO.getOne(idLegajo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Legajo> getAllLegajos(){
		try {
			LegajoDAO legajoDAO = new LegajoDAOimp();
			return (List<Legajo>) legajoDAO.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	}
	
	
}


