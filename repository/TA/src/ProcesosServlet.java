 

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcesoDAO;
import dao.UsuarioDAO;
import implementation.ProcesoDAOimp;
import implementation.UsuarioDAOimp;
import model.Proceso;
import model.Usuario;

/**
 * Servlet implementation class ProcesosServlet
 */
@WebServlet("/ProcesosServlet")
public class ProcesosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("menuProcesos.jsp");
		List<Proceso> procesos = getAllProcesos();
		request.setAttribute("procesos", procesos);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("eliminar") != null) {
			int id = Integer.parseInt(request.getParameter("eliminar"));
			eliminarProceso(id);
		
		}
		response.sendRedirect("ProcesosServlet");

	}
	
	public void eliminarProceso(int id) {
		ProcesoDAO procesoDAO = new ProcesoDAOimp();
		try {
		Proceso proceso = (Proceso) procesoDAO.getOne(id);
		procesoDAO.eliminar(proceso);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	List<Proceso> getAllProcesos(){
			
			ProcesoDAO procesoDAO = new ProcesoDAOimp();
			
			try {
				
				return procesoDAO.getAll();
				
			}catch(Exception ex) {
				
				ex.printStackTrace();
			}
			return null;
		}
	}
