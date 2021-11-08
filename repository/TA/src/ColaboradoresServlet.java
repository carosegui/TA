

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import implementation.UsuarioDAOimp;
import model.Usuario;

/**
 * Servlet implementation class ColaboradoresServlet
 */
@WebServlet("/ColaboradoresServlet")
public class ColaboradoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColaboradoresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("menuColaboradores.jsp");
		List<Usuario> usuarios = getAllUsuarios();
		request.setAttribute("usuarios", usuarios);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		if (request.getParameter("eliminar") != null) {
			int id = Integer.parseInt(request.getParameter("eliminar"));
			eliminarUsuario(id);
		
		}
		
		if(request.getParameter("alta") != null) {
			
			request.setAttribute("nombre", "");
			request.setAttribute("apellido", "");
			request.setAttribute("dni", "");
			request.setAttribute("contraseña", "");
			request.setAttribute("nombreUsuario", "");
			request.setAttribute("mail", "");				
			request.setAttribute("id", 0);
			rd = request.getRequestDispatcher("editarColaboradores.jsp");
			rd.forward(request, response);
			
		}
		
		response.sendRedirect("ColaboradoresServlet");

	}
		 
		
	public List<Usuario> getAllUsuarios(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();
		
		try {
			
			return usuarioDAO.getAll();
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		return null;
	}
	
	public void eliminarUsuario(int id) {
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();
		try {
		Usuario user = (Usuario) usuarioDAO.getOne(id);
		usuarioDAO.eliminar(user);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();
		try {
			usuarioDAO.crear(usuario);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}
	

}
