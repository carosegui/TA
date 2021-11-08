package conection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Conexion 
{
	public Conexion() {
		
	}
	
	public static Connection crearConexion ()
	{
		Properties prop = new Properties (); 
		Connection conn = null;
		
		try 
		{
			prop.load (new FileReader("C:\\Users\\caros\\OneDrive\\Escritorio\\USAL\\2021\\SIP\\TA\\conf.properties"));
			
			Class.forName("com.mysql.jdbc.Driver");
					
			conn = DriverManager.getConnection(prop.getProperty("urlbd"));			
		} 
			catch (Exception sql) 
		
		{
			sql.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void desconectar(Connection conn)
	{
        if (conn != null) {
            try 
            {
               conn.close();
                              
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	public static void main(String[] args)
	{
		
	}

}
