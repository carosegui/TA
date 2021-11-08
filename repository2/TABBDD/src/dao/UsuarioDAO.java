package dao;

import model.Usuario;

public interface UsuarioDAO extends GenericoDAO <Usuario>
{
	Usuario validar(String usuario, String contrasena);
	
}
