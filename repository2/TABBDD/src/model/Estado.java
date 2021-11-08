package model;

import java.sql.Date;

import com.mysql.cj.jdbc.Blob;

public class Estado {

	Area area;
	Usuario responsable;
	String comentario;
	Blob adjunto;
	Date fechaInicio, fechaFin;
	int IDEstado;
	
	public Estado(Area area, Usuario responsable, String comentario, Blob adjunto, Date fechaInicio, Date fechaFin) {
		this.area = area;
		this.responsable = responsable;
		this.comentario = comentario;
		this.adjunto = adjunto;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public int getIDEstado() {
		return IDEstado;
	}

	public void setIDEstado(int iDEstado) {
		IDEstado = iDEstado;
	}

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Blob getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(Blob adjunto) {
		this.adjunto = adjunto;
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

	@Override
	public String toString() {
		return "Estado [area=" + area + ", responsable=" + responsable + ", comentario=" + comentario + ", adjunto="
				+ adjunto + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
}
