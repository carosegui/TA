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
import dao.EstadoDAO;
import dao.EventoDAO;
import dao.InvitadoDAO;
import dao.LegajoDAO;
import dao.NotificacionDAO;
import dao.UsuarioDAO;
import model.Area;
import model.Estado;
import model.Evento;
import model.Invitado;
import model.Legajo;
import model.Notificacion;
import model.Proceso;
import model.Usuario;

public class InvitadoDAOimp implements InvitadoDAO{

	@Override
	public void crear(Invitado o) {
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Invitado (IDEvento, IDUsuario) VALUES (?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getIDEvento().getIDEvento());
            pst.setInt(2, o.getIDUsuario().getIDUsuario());
         
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
	public void eliminar(Invitado o) {
		String sentence = "DELETE FROM Invitado WHERE IDInvitado = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDInvitado());
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
	public void modificar(Invitado o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Invitado set  IDEvento=?, IDUsuario=? WHERE IDInvitado =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setInt(1, o.getIDEvento().getIDEvento());
	         pst.setInt(2, o.getIDUsuario().getIDUsuario());
	         
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
	public List <Invitado> getAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Invitado";
		
		List <Invitado> invitados = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			invitados.add(convertidor(rs));
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
		
		return invitados;
		}

	@Override
	public Object getOne(int ID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Invitado WHERE IDInvitado = ?";
		Invitado i = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			i = convertidor(rs);
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
		
		return i;	
		
		}
	
	
	public Invitado convertidor (ResultSet rs) throws SQLException 
	{
		UsuarioDAO usuarioDAO = new UsuarioDAOimp();
        EventoDAO eventoDAO = new EventoDAOimp();
		int IDInvitado = rs.getInt("IDInvitado");
		int IDevento = rs.getInt("IDEvento");
		int IDUsuario = rs.getInt("IDUsuario");
			       
		Evento evento = (Evento) eventoDAO.getOne(IDevento);
	    Usuario usuario = (Usuario) usuarioDAO.getOne(IDUsuario);
    
        Invitado invitado = new Invitado(IDInvitado,evento,usuario); 
        invitado.setIDInvitado (rs.getInt("IDInvitado"));
        return invitado;
    }

}
