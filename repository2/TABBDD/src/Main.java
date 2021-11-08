import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import conection.Conexion;
import dao.AreaDAO;
import dao.LegajoDAO;
import dao.NotificacionDAO;
import dao.ProcesoDAO;
import dao.UsuarioDAO;
import implementation.AreaDAOimp;
import implementation.LegajoDAOimp;
import implementation.NotificacionDAOimp;
import implementation.ProcesoDAOimp;
import implementation.UsuarioDAOimp;
import model.Area;
import model.Legajo;
import model.Notificacion;
import model.Proceso;
import model.Usuario;

public class Main {

	public static void main(String[] args) throws ParseException
	{
//		Conexion.crearConexion();
//		LegajoDAO legajo = new LegajoDAOimp();
//		UsuarioDAO us = new UsuarioDAOimp();
//		AreaDAO a = new AreaDAOimp();
//		
	//	us.crear (new Usuario ("Francisco", "Goñi", 41063465, "fgoni", "fgoni1",(Area) a.getOne(2),"fgoni@usal.com"));
//		legajo.crear(new Legajo ((Usuario)us.getOne(9),(Usuario)us.getOne(1),(Area)a.getOne(2),(Usuario)us.getOne(10),new java.sql.Date(System.currentTimeMillis())));
//	Usuario usuarios = (Usuario) us.getOne(1);
//		usuarios.setNombre("caro");
//		usuarios.setApellido("perez");
//		usuarios.setContraseña("1234");
//		usuarios.setDNI(27653925);
//		usuarios.setNombreUsuario(usuarios.getNombre().charAt(0)+usuarios.getApellido());
//		usuarios.setMail(usuarios.getNombre().charAt(0)+usuarios.getApellido()+"@gmail.com");
//		
//		us.modificar(usuarios);
//		Usuario usuario = us.validar("csegui", "admins");
//		if(usuario != null)
//			System.out.println(usuario.toString());
//		else
//			System.out.println("es nulo");
		
//		NotificacionDAO notiDAO = new NotificacionDAOimp();
//		
//		List<Notificacion> notificaciones = notiDAO.getAll();
//		
//		for (Notificacion notificacion : notificaciones) {
//			System.out.println(notificacion.toString());
//		}
		
		ProcesoDAO procesoDAO = new ProcesoDAOimp();
		Proceso p = (Proceso) procesoDAO.getOne(4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String date = "2021-10-15";
		java.util.Date parsed = null;
		parsed = sdf.parse(date);
		
		p.setFechaFin(new Date(parsed.getTime()));
		procesoDAO.modificar(p);
		
	}

}
