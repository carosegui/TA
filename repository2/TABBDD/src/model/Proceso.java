package model;

import java.sql.Date;

public class Proceso {

	int IDProceso;
	Legajo legajo;
	Estado estado;
	String comentario;
	Notificacion notificacion;
	Date fechaInicio, fechaFin;

	public Proceso(Legajo legajo, Estado estado, Notificacion notificacion, Date fechaInicio, Date fechaFin, String comentario) {

		this.legajo = legajo;
		this.estado = estado;
		this.notificacion = notificacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comentario = comentario;
	}
	
	public Proceso() {
		
	}
	public int getIDProceso() {
		return IDProceso;
	}
	public void setIDProceso(int iDProceso) {
		IDProceso = iDProceso;
	}
	public Legajo getLegajo() {
		return legajo;
	}
	public void setLegajo(Legajo legajo) {
		this.legajo = legajo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Notificacion getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Proceso [IDProceso=" + IDProceso + ", legajo=" + legajo + ", estado=" + estado + ", notificacion="
				+ notificacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
	
	
	
	
}