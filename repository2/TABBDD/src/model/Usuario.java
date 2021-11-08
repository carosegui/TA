package model;

public class Usuario 
{	
	int IDUsuario, DNI;

	String Nombre, Apellido, NombreUsuario, Contrase�a, Mail;
	Area area; 
	
	public Usuario (String nombre, String apellido, int dni, String nombreUsuario, String contrase�a, Area area,
			String mail) 
	{	Nombre = nombre;
		Apellido = apellido;
		DNI = dni;
		NombreUsuario = nombreUsuario;
		Contrase�a = contrase�a;
		Mail = mail; 
		this.area = area;
	}
	
	public Area getArea() {
		return area;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}

	public int getIDUsuario() {
		return IDUsuario;
	}
	public void setIDUsuario(int iDUsuario) {
		IDUsuario = iDUsuario;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	public String getContrase�a() {
		return Contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}

	@Override
	public String toString() {
		return "Usuario [IDUsuario=" + IDUsuario + ", DNI=" + DNI + ", Area:" + area.getNombre() + ", Nombre=" + Nombre
				+ ", Apellido=" + Apellido + ", NombreUsuario=" + NombreUsuario + ", Contrase�a=" + Contrase�a
				+ ", Mail=" + Mail + "]";
	}
	
	
	
	
}
