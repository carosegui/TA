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
import dao.UsuarioDAO;
import model.Area;
import model.Estado;
import model.Usuario;

public class EstadoDAOimp implements EstadoDAO {

	@Override
	public void crear(Estado o) {
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Estado (IDArea, IDResponsable, Comentario, Adjunto, FechaInicio, FechaFin) VALUES (?,?,?,?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getArea().getIDArea());
            pst.setInt(2, o.getResponsable().getIDUsuario());
            pst.setString(3, o.getComentario()); 
            pst.setBlob(4, o.getAdjunto());
            pst.setDate(5, o.getFechaInicio());
            pst.setDate(6, o.getFechaFin());
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
	public void eliminar(Estado o) {
		String sentence = "DELETE FROM Estado WHERE IDEstado = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDEstado());
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
	public void modificar(Estado o)
	{
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Estado set  IDArea=?, IDResponsable=?, Comentario=?, Adjunto=?, FechaInicio=?, FechaFin=? WHERE IDEstado =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setInt(1, o.getArea().getIDArea());
	         pst.setInt(2, o.getResponsable().getIDUsuario());
	         pst.setString(3, o.getComentario()); 
	         pst.setBlob(4, o.getAdjunto());
	         pst.setDate(5, o.getFechaInicio());
	         pst.setDate(6, o.getFechaFin());
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
	public List<Estado> getAll() {
		

		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Estado";
		
		List <Estado> estados = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			estados.add(convertidor(rs));
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
		
		return estados;
	}

	@Override
	public Object getOne(int ID) {
		
		
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Estado WHERE IDEstado = ?";
		Estado es = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			es = convertidor(rs);
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
		
		return es;
	}
	
	public Estado convertidor (ResultSet rs) throws SQLException 
	{
        AreaDAO areaDAO = new AreaDAOimp();
        UsuarioDAO usuarioDAO = new UsuarioDAOimp();
        
        int IDEstado = rs.getInt("IDEstado");
        Area area = (Area) areaDAO.getOne(rs.getInt("IDArea"));
        Usuario usuario = (Usuario) usuarioDAO.getOne(rs.getInt("IDResponsable"));
        String comentario = rs.getString ("comentario");
        com.mysql.cj.jdbc.Blob adjunto = (com.mysql.cj.jdbc.Blob) rs.getBlob("Adjunto");
        Date fechaInicio = rs.getDate("FechaInicio");
        Date fechaFin = rs.getDate("FechaFin");
        Estado estado = new Estado (area,usuario,comentario, adjunto,fechaInicio,fechaFin); 
        estado.setIDEstado (rs.getInt("IDEstado")); 
        return estado;
    }

}
