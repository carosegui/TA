package model;

public class Notificacion {
	int IDNotificacion;
	Usuario usuario;
	String Razon;
	String Comentario;
	public int getIDNotificacion() {
		return IDNotificacion;
	}
	public void setIDNotificacion(int iDNotificacion) {
		IDNotificacion = iDNotificacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getRazon() {
		return Razon;
	}
	public void setRazon(String razon) {
		Razon = razon;
	}
	public String getComentario() {
		return Comentario;
	}
	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	@Override
	public String toString() {
		return "Notificacion [IDNotificacion=" + IDNotificacion + ", usuario=" + usuario + ", Razon=" + Razon
				+ ", Comentario=" + Comentario + "]";
	}
	
	
}
