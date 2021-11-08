package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conexion;
import dao.AreaDAO;
import dao.EstadoDAO;
import dao.LegajoDAO;
import dao.NotificacionDAO;
import dao.ProcesoDAO;
import dao.UsuarioDAO;
import model.Area;
import model.Estado;
import model.Legajo;
import model.Notificacion;
import model.Proceso;
import model.Usuario;

public class ProcesoDAOimp implements ProcesoDAO{

	@Override
	public void crear(Proceso o) {
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Proceso (IDLegajo, IDEstado, IDNotificacion, FechaInicio, FechaFin) VALUES (?,?,?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getLegajo().getIDLegajo());
            pst.setInt(2, o.getEstado().getIDEstado());
            pst.setInt(3, o.getNotificacion().getIDNotificacion()); 
            pst.setDate(4, o.getFechaInicio());
            pst.setDate(5, o.getFechaFin());
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
	public void eliminar(Proceso o) {
		String sentence = "DELETE FROM Proceso WHERE IDProceso = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDProceso());
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
	public void modificar(Proceso o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Proceso set IDLegajo=?, IDEstado=?, IDNotificacion=?, FechaInicio=?, FechaFin=? WHERE IDProceso =?";
		
		try {
			
	         conn = Conexion.crearConexion();
	            
	            pst = conn.prepareStatement(sentencia);
	            pst.setInt(1, o.getLegajo().getIDLegajo());
	            pst.setInt(2, o.getEstado().getIDEstado()); 
	            pst.setInt(3, o.getNotificacion().getIDNotificacion());
	            pst.setDate(4,o.getFechaInicio());
	            pst.setDate(5,o.getFechaFin());
	            pst.setInt(6, o.getIDProceso());

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
	public List<Proceso> getAll() {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Proceso";
		
		List <Proceso> procesos = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				procesos.add(convertidor(rs));
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
		
		return procesos;
	}

	@Override
	public Object getOne(int ID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Proceso WHERE IDProceso = ?";
		Proceso p = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			p = convertidor(rs);
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
		
		return p;
	}	
	
	public Proceso convertidor (ResultSet rs) throws SQLException 
	{
        LegajoDAO legajoDAO = new LegajoDAOimp();
        EstadoDAO estadoDAO = new EstadoDAOimp();
        NotificacionDAO notificacionDAO = new NotificacionDAOimp();

        int IDProceso = rs.getInt("IDProceso");
        
        Legajo legajo = (Legajo) legajoDAO.getOne(rs.getInt("IDLegajo"));
        Estado estado = (Estado) estadoDAO.getOne(rs.getInt("IDEstado"));
        Notificacion notificacion = (Notificacion) notificacionDAO.getOne(rs.getInt("IDNotificacion"));
        java.sql.Date fechainicio = rs.getDate("FechaInicio");
        java.sql.Date fechafin = rs.getDate("FechaFin");
       
        Proceso proceso = new Proceso();
        proceso.setEstado(estado);
        proceso.setIDProceso(IDProceso);
        proceso.setNotificacion(notificacion);
        proceso.setFechaFin(fechafin);
        proceso.setFechaInicio(fechainicio);
        proceso.setLegajo(legajo);
        
        return proceso;
    }

	@Override
	public Proceso getByUserID(int UserID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Proceso WHERE IDProceso = ?";
		Proceso p = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,UserID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			p = convertidor(rs);
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
		
		return p;
	}

}
