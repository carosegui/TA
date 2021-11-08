package model;

import java.sql.Date;

public class Evento {

	int IDEvento;
	Usuario IDUsuario;
	String asunto;
	Date fecha;
	
	public Evento(int iDEvento, Usuario iDUsuario, String asunto, Date fecha) {
		
		this.IDEvento = iDEvento;
		this.IDUsuario = iDUsuario;
		this.asunto = asunto;
		this.fecha = fecha;
	}

	public int getIDEvento() {
		return IDEvento;
	}

	public void setIDEvento(int iDEvento) {
		IDEvento = iDEvento;
	}

	public Usuario getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(Usuario iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Evento [IDEvento=" + IDEvento + ", IDUsuario=" + IDUsuario.getNombreUsuario() + ", asunto=" + asunto + ", fecha=" + fecha
				+ "]";
	}
	
	
	
	
	
}
