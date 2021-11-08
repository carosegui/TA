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
import dao.UsuarioDAO;
import model.Area;
import model.Usuario;

public class UsuarioDAOimp implements UsuarioDAO 

{

	@Override
	public void crear(Usuario o) 
	{
		Connection conn;
        PreparedStatement pst = null;
        
        String sentence = "INSERT INTO Usuario (Nombre, Apellido, DNI, NombreUsuario, Contraseña, IDArea, Mail) VALUES (?,?,?,?,?,?,?)";
        
        try {

            conn = Conexion.crearConexion();
            
            pst = conn.prepareStatement(sentence);
            pst.setString(1, o.getNombre());
            pst.setString(2, o.getApellido());
            pst.setInt(3, o.getDNI()); 
            pst.setString(4, o.getNombreUsuario());
            pst.setString(5, o.getContraseña());
            pst.setInt(6, o.getArea().getIDArea());
            pst.setString(7, o.getMail());
         
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
	public void eliminar(Usuario o) {
		String sentence = "DELETE FROM Usuario WHERE IDUsuario = ?";
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1, o.getIDUsuario());
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
	public void modificar (Usuario o) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sentencia = "UPDATE Usuario set  Nombre=?, Apellido=?, DNI=?, NombreUsuario=?, Contraseña=?, Mail=?, IDArea=? WHERE IDUsuario =?";
		
		try {
			
			 conn = Conexion.crearConexion();
	         pst = conn.prepareStatement(sentencia);
	         pst.setString(1, o.getNombre());
	         pst.setString(2, o.getApellido());
	         pst.setInt(3, o.getDNI()); 
	         pst.setString(4, o.getNombreUsuario());
	         pst.setString(5, o.getContraseña());
	         pst.setString(6, o.getMail());
	         pst.setInt(7, o.getArea().getIDArea()	);
             pst.setInt(8, o.getIDUsuario());
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
	public List<Usuario> getAll() 
	
	{
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Usuario";
		
		List <Usuario> usuarios = new ArrayList<>();
		
		try {
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			usuarios.add(convertidor(rs));
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
		
		return usuarios;
	}

	@Override
	public Object getOne(int ID) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM Usuario WHERE IDUsuario = ?";
		Usuario u = null;
		
		try
		{
			
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setInt(1,ID);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
			u = convertidor(rs);
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
		
		return u;
	}
	
	//lo convierto en resultset
	
	public Usuario convertidor (ResultSet rs) throws SQLException 
	{
        String Nombre = rs.getString ("Nombre");
        String Apellido = rs.getString ("Apellido");
        int DNI = rs.getInt ("DNI");
        String NombreUsuario = rs.getString("NombreUsuario");
        String Contraseña = rs.getString("Contraseña");
        int IDArea = rs.getInt("IDArea");
        AreaDAO areaDao = new AreaDAOimp();
        Area area = (Area) areaDao.getOne(IDArea);
        String Mail = rs.getString("Mail");
        Usuario usuario = new Usuario (Nombre, Apellido, DNI, NombreUsuario,Contraseña,area,Mail); 
        usuario.setIDUsuario (rs.getInt("IDUsuario")); 
        return usuario;
    }

	@Override
	public Usuario validar(String usuario, String contrasena) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		final String sentence = "SELECT * FROM usuario WHERE usuario.NombreUsuario = ? && usuario.Contraseña = ?";
		Usuario u = null;
		
		try
		{
			conn = Conexion.crearConexion();
			pst = conn.prepareStatement(sentence);
			pst.setString(1, usuario);
			pst.setString(2, contrasena);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
		     return convertidor(rs);
			}
			else 
			{
				return null;
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
		
		return u;
	}

}
