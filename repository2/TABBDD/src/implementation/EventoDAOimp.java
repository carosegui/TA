package implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conexion;
import dao.AreaDAO;
import dao.EventoDAO;
import dao.UsuarioDAO;
import model.Area;
import model.Evento;
import model.Legajo;
import model.Usuario;

public class EventoDAOimp implements EventoDAO
{

	@Override
	public void crear(Evento o) {
		
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Evento (IDUsuario, Asunto, Fecha) VALUES (?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getIDUsuario().getIDUsuario());
            pst.setString(2, o.getAsunto());
            pst.setDate(3, o.getFecha()); 
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
	public void eliminar(Evento o) {
		String sentence = "DELETE FROM Evento WHERE IDEvento = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDEvento());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				pst.close();
				Conexion.desconectar(conn);
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void modificar(Evento o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Evento set  IDUsuario=?, Asunto=?, Fecha=? WHERE IDEvento =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setInt(1, o.getIDUsuario().getIDUsuario());
	         pst.setString(2, o.getAsunto());
	         pst.setDate(3, o.getFecha()); 
	  
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
	public List<Evento> getAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Evento";
		
		List <Evento> eventos = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			eventos.add(convertidor(rs));
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
		
		return eventos;
	}

	@Override
	public Object getOne(int ID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Evento WHERE IDEvento = ?";
		Evento ev = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			ev = convertidor(rs);
			}
			else 
			{
				throw new SQLException();
			}
			
		}catch(SQLException e)
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
		
		return ev;
	}

	public Evento convertidor (ResultSet rs) throws SQLException 
	{
        int IDEvento = rs.getInt("IDEvento");
        int IDusuario = rs.getInt("IDUsuario");
        String asunto = rs.getString("Asunto");
        Date fecha = rs.getDate("Fecha");
     
        UsuarioDAO usuarioDAO = new UsuarioDAOimp();
       
        Usuario IDUsuario = (Usuario) usuarioDAO.getOne(IDusuario);
        
        Evento evento = new Evento(IDEvento,IDUsuario,asunto,fecha); 
        evento.setIDEvento (rs.getInt("IDEvento")); 
        return evento;
    }
		
}
