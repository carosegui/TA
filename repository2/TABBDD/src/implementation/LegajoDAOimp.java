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
import dao.LegajoDAO;
import dao.UsuarioDAO;
import model.Area;
import model.Legajo;
import model.Proceso;
import model.Usuario;

public class LegajoDAOimp implements LegajoDAO 

{

	@Override
	public void crear(Legajo o) {
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Legajo (IDUsuario, IDPadrino, IDArea, IDLider, FechaIngreso) VALUES (?,?,?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setInt(1, o.getUsuarioPasante().getIDUsuario());
            pst.setInt(2, o.getUsuarioPadrino().getIDUsuario());
            pst.setInt(3, o.getArea().getIDArea()); 
            pst.setInt(4, o.getUsuarioLider().getIDUsuario());
            pst.setDate(5, o.getFechaIngreso());         
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
	public void eliminar(Legajo o) 
	{
		String sentence = "DELETE FROM Legajo WHERE IDLegajo = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDUsuario());
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
	public void modificar(Legajo o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Legajo set  IDUsuario=?, IDPadrino=?, IDArea=?, IDLider=?, FechaIngreso=? WHERE IDLegajo =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setInt(1, o.getUsuarioPasante().getIDUsuario());
	         pst.setInt(2, o.getUsuarioPadrino().getIDUsuario());
	         pst.setInt(3, o.getArea().getIDArea()); 
	         pst.setInt(4, o.getUsuarioLider().getIDUsuario());
	         pst.setDate(5, o.getFechaIngreso()); 
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
	public List<Legajo> getAll() 
	{
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Legajo";
		
		List <Legajo> legajos = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			legajos.add(convertidor(rs));
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
		
		return legajos;
	}

	@Override
	public Object getOne(int ID) 
	
	{
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Legajo WHERE IDLegajo = ?";
		Legajo l = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			l = convertidor(rs);
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
		
		return l;
	}
	
	public Legajo convertidor (ResultSet rs) throws SQLException 
	
	{
        int IDUsuario = rs.getInt("IDUsuario");
        int IDPadrino = rs.getInt("IDPadrino");
        int IDArea = rs.getInt("IDArea");
        int IDLider = rs.getInt("IDLider");
        Date FechaIngreso = rs.getDate("FechaIngreso");

        UsuarioDAO usuarioDAO = new UsuarioDAOimp();
        AreaDAO areaDAO = new AreaDAOimp();
        
        Area area = (Area) areaDAO.getOne(IDArea);
        Usuario usuarioPasante = (Usuario) usuarioDAO.getOne(IDUsuario);
        Usuario usuarioLider = (Usuario) usuarioDAO.getOne(IDLider);
        Usuario usuarioPadrino = (Usuario) usuarioDAO.getOne(IDPadrino);

        Legajo legajo = new Legajo(usuarioPasante,usuarioPadrino,area,usuarioLider,FechaIngreso); 
        legajo.setIDUsuario(IDUsuario);
        legajo.setIDLegajo(rs.getInt("IDLegajo")); 
        return legajo;
        
    }

	@Override
	public Legajo getByUserID(int UserID) {
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Legajo where IDUsuario = ";
		Legajo l = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,UserID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			l = convertidor(rs);
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
		
		return l;
	}

}
