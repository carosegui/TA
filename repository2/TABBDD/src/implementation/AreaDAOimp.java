package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conection.Conexion;
import dao.AreaDAO;
import model.Area;
import model.Legajo;
import model.Usuario;

public class AreaDAOimp implements AreaDAO {

	@Override
	public void crear(Area o) {
		
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Area (Nombre) VALUES (?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setString(1, o.getNombre());
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
	public void eliminar(Area o) {
		String sentence = "DELETE FROM Area WHERE IDArea = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDArea());
			pst.executeUpdate();
			
		} 
		catch (SQLException e)
		
		{
			e.printStackTrace();
		}
			finally
		{
			try {
				pst.close();
				Conexion.desconectar(conn);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void modificar(Area o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Area set  Nombre=? WHERE IDArea =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setString(1, o.getNombre());
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
	public List<Area> getAll() {
		
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Area";
		
		List <Area> areas = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			areas.add(convertidor(rs));
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
		
		return areas;
	}

	@Override
	public Object getOne(int ID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Area WHERE IDArea = ?";
		Area area = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
				
			{
				area = convertidor(rs);
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
		
		return area;
	}

	public Area convertidor (ResultSet rs) throws SQLException 
	{
        String Nombre = rs.getString ("Nombre");
        int IDArea = rs.getInt("IDArea");
        Area area = new Area(IDArea,Nombre);
        area.setIDArea(IDArea); 
        return area;
    }
}
