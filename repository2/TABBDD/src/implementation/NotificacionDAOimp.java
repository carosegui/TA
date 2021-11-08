package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conexion;
import dao.AreaDAO;
import dao.NotificacionDAO;
import dao.UsuarioDAO;
import model.Area;
import model.Notificacion;
import model.Usuario;

public class NotificacionDAOimp implements NotificacionDAO {

	@Override
	public void crear(Notificacion o) {
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Notificacion (IDUsuario,Razon,Comentario) VALUES (?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getUsuario().getIDUsuario());
            pst.setString(2, o.getRazon()); 
            pst.setString(3, o.getComentario());
            
            pst.executeUpdate();
            
            try 
            {
                pst.close();
            }
            catch(SQLException e) 
            {
                e.printStackTrace();
            }

        }
        catch(SQLException SQL) 
        {
            SQL.printStackTrace();
        }		
	}

	@Override
	public void eliminar(Notificacion o) {
		String sentence = "DELETE FROM Notificacion WHERE IDNotificacion = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDNotificacion());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				Conexion.desconectar(conn);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void modificar(Notificacion o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Usuario set  IDNotificacion=?, IDUsuario=?, Razon=?, Comentario=?WHERE IDNotificacion =?";
		
		try {
			
	         conn = Conexion.crearConexion();
	            
	            pst = conn.prepareStatement(sentencia);
	            pst.setInt(1, o.getUsuario().getIDUsuario());
	            pst.setInt(2, o.getUsuario().getIDUsuario());
	            pst.setString(3, o.getRazon()); 
	            pst.setString(4, o.getComentario());
	            
	            pst.executeUpdate();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				pst.close();
				Conexion.desconectar(conn);
			}
			
			catch(SQLException sql)
			{
				
			}
		}		
	}

	@Override
	public List<Notificacion> getAll() 
	{
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Notificacion";
		
		List <Notificacion> notificaciones = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			notificaciones.add(convertidor(rs));
			}			
		}
		
		catch(SQLException e) 
		
		{
			e.printStackTrace();
			
		}
		finally 
		{
			try {
				pst.close();
				Conexion.desconectar(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return notificaciones;
	}

	@Override
	public Object getOne(int ID) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Notificacion WHERE IDNotificacion = ?";
		Notificacion n = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			n = convertidor(rs);
			}
			else 
			{
				throw new SQLException();
			}
			
		}
			catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pst.close();
				Conexion.desconectar(conn);
			}
			
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
		}
		
		return n;
	}

	public Notificacion convertidor (ResultSet rs) throws SQLException 
	{
        int IDNotificacion = rs.getInt("IDNotificacion");
        UsuarioDAO usuarioDAO = new UsuarioDAOimp();
        Usuario usuario = (Usuario) usuarioDAO.getOne(rs.getInt("IDUsuario"));
        String Razon = rs.getString("Razon");
        String Comentario = rs.getString("Comentario");
       
        Notificacion notificacion = new Notificacion();
        notificacion.setComentario(Comentario);
        notificacion.setRazon(Razon);
        notificacion.setUsuario(usuario);
        notificacion.setIDNotificacion(IDNotificacion);        
        
        return notificacion;
    }
}
