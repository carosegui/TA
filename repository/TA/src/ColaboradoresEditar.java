

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AreaDAO;
import dao.UsuarioDAO;
import implementation.AreaDAOimp;
import implementation.UsuarioDAOimp;
import model.Area;
import model.Usuario;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * Servlet implementation class ColaboradoresEditar
 */
@WebServlet("/Colaboradores")
public class ColaboradoresEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColaboradoresEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    request = SetAttributes(request);		
		RequestDispatcher rd = request.getRequestDispatcher("editarColaboradores.jsp");
		rd.forward(request, response);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = (String) request.getParameter("nombre");
		String apellido = (String) request.getParameter("apellido");
		int dni = Integer.parseInt(request.getParameter("dni"));
		String contraseña = (String) request.getParameter("contraseña");
		String nombreUsuario = (String) request.getParameter("nombreUsuario");
		String mail = (String) request.getParameter("mail");
		int idArea = Integer.parseInt( request.getParameter("idArea"));
		AreaDAO areaDAO = new AreaDAOimp();
		UsuarioDAO us = new UsuarioDAOimp();
		System.out.println("ID: "+request.getParameter("id"));

		Area area = (Area) areaDAO.getOne(idArea);
		if(!Validar(request,response)) {
			if(request.getParameter("Editar") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				Usuario usuarios = (Usuario) us.getOne(id);
				
				if (nombre != null) {
					usuarios.setNombre(nombre);
				}
				if (apellido != null) {
					usuarios.setApellido(apellido);
				}
				
				 
				if (contraseña != null) {
					usuarios.setContraseña(contraseña);
				}
				if (dni != 0) {
					usuarios.setDNI(dni);
				}
				
				if(area != null) {
					usuarios.setArea(area);
				}
			
				if (nombreUsuario != null) {
					usuarios.setNombreUsuario(nombreUsuario);
				}
				if (mail != null) {
					usuarios.setMail(mail);
				}
				us.modificar(usuarios);
				Editar(usuarios);
			}else {
				Usuario usuario = new Usuario(nombre,apellido,dni,nombreUsuario,contraseña, area,mail);
				us.crear(usuario);
			}
			response.sendRedirect("/TA/ColaboradoresServlet");
		
		}
	}
	
	public Usuario getUser(int userID){
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();
		try {
			return (Usuario) usuarioDAO.getOne(userID);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void Editar(Usuario user) {
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();

		try {
			usuarioDAO.modificar(user);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Area> getAllAreas(){
		AreaDAO areaDAO = new AreaDAOimp();

		try {
			return areaDAO.getAll();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean Validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int UserId = 0;
		RequestDispatcher rd = null;
		Boolean redirect = false;
		if(request.getParameter("id") != null) {
			UserId = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("UserId", UserId);
		}else {
			request.setAttribute("UserId", null);
		}
		//Seteo los valores que entraron para devolverlos a la vista en caso de que no se cumpla una validacion.
		request = setAttributeValidation(request);
		if(request.getParameter("nombre").matches(".*\\d.*")) {
			//Verifica que el nombre no tenga numeros
			request.setAttribute("nombreInvalido", true);
			redirect = true;
		}
		
		if(request.getParameter("apellido").matches(".*\\d.*")) {
			//Verifica que el apellido no tenga numeros
			request.setAttribute("apellidoInvalido", true);
			redirect = true;
		}
		
		if(request.getParameter("dni").length() > 8 || request.getParameter("dni").length() < 7){
			request.setAttribute("dniInvalido", true);
			redirect = true;	
		}
		
		//Valida el formato del mail, se tuvo que bajar un jar para utilizar un modulo externo
		EmailValidator validator = EmailValidator.getInstance();
		if(!validator.isValid(request.getParameter("mail"))) {
			request.setAttribute("mailInvalido", true);
			redirect = true;
		}
		if(redirect) {
			if(request.getParameter("UserId") != null || request.getAttribute("UserId") != null){
				request.setAttribute("UserId", UserId);
			}
			rd = request.getRequestDispatcher("editarColaboradores.jsp");
			rd.forward(request, response);
		}
		return redirect;
	}
	
	public HttpServletRequest SetAttributes(HttpServletRequest request) {
		if(request.getParameter("UserId") != null) {
			int id = Integer.parseInt(request.getParameter("UserId"));
			Usuario user = getUser(id);
			request.setAttribute("nombre", user.getNombre());
			request.setAttribute("apellido", user.getApellido());
			request.setAttribute("dni", user.getDNI());
			request.setAttribute("contraseña", user.getContraseña());
			request.setAttribute("nombreUsuario", user.getNombreUsuario());
			request.setAttribute("mail", user.getMail());				
			request.setAttribute("id", id);
			request.setAttribute("areas", getAllAreas());
			request.setAttribute("Editar", true);
		}	else {
			request.setAttribute("nombre", "");
			request.setAttribute("apellido","");
			request.setAttribute("dni", 0);
			request.setAttribute("contraseña", "");
			request.setAttribute("nombreUsuario","");
			request.setAttribute("mail", "");				
			request.setAttribute("areas", getAllAreas());
			request.setAttribute("Alta", true);
			request.setAttribute("UserID", null);
		}
		
		return request;
		
	}
	
	public HttpServletRequest setAttributeValidation(HttpServletRequest request) {
		if(request.getParameter("Editar") != null) {
		request.setAttribute("nombre", request.getParameter("nombre"));
		request.setAttribute("apellido", request.getParameter("apellido"));
		request.setAttribute("dni", request.getParameter("dni"));
		request.setAttribute("contraseña", request.getParameter("contraseña"));
		request.setAttribute("nombreUsuario", request.getParameter("nombreUsuario"));
		request.setAttribute("mail", request.getParameter("mail"));				
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("areas", getAllAreas());
		request.setAttribute("Editar", true);
		
		System.out.println("NOMBRE: "+request.getAttribute("nombre"));


		}else {
			request.setAttribute("nombre", request.getParameter("nombre"));
			request.setAttribute("apellido",request.getParameter("apellido"));
			request.setAttribute("dni", request.getParameter("dni"));
			request.setAttribute("contraseña", request.getParameter("contraseña"));
			request.setAttribute("nombreUsuario",request.getParameter("nombreUsuario"));
			request.setAttribute("mail", request.getParameter("mail"));				
			request.setAttribute("areas", getAllAreas());
			request.setAttribute("Alta", true);
			request.setAttribute("UserID", null);
			System.out.println("NOMBRE: "+request.getAttribute("nombre"));
		}
		return request;
	}
	           
}
