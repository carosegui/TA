package model;

import java.sql.Date;

public class Legajo extends Usuario 
{
	int IDLegajo;
	Usuario usuarioPasante, usuarioLider, usuarioPadrino;
	Date FechaIngreso;
	Area area;
	
	public Legajo(Usuario usuarioPasante, Usuario usuarioPadrino, Area area, Usuario usuarioLider, Date fechaIngreso) 
	{
		super (usuarioPasante.getNombre(),usuarioPasante.getApellido(),usuarioPasante.getDNI(),usuarioPasante.getNombreUsuario(), usuarioPasante.getContraseña(), usuarioPasante.getArea(), usuarioPasante.getMail());
		this.usuarioPasante = usuarioPasante;
		this.usuarioLider = usuarioLider;
		this.usuarioPadrino = usuarioPadrino;
		this.area = area;
		FechaIngreso = fechaIngreso;
	}

	public int getIDLegajo() {
		return IDLegajo;
	}

	public void setIDLegajo(int iDLegajo) {
		IDLegajo = iDLegajo;
	}

	public Usuario getUsuarioPasante() {
		return usuarioPasante;
	}

	public void setUsuarioPasante(Usuario usuarioPasante) {
		this.usuarioPasante = usuarioPasante;
	}

	public Usuario getUsuarioLider() {
		return usuarioLider;
	}

	public void setUsuarioLider(Usuario usuarioLider) {
		this.usuarioLider = usuarioLider;
	}

	public Usuario getUsuarioPadrino() {
		return usuarioPadrino;
	}

	public void setUsuarioPadrino(Usuario usuarioPadrino) {
		this.usuarioPadrino = usuarioPadrino;
	}

	public Date getFechaIngreso() {
		return FechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Legajo [IDLegajo=" + IDLegajo + ", usuarioPasante=" + usuarioPasante.getNombreUsuario() + ", usuarioLider=" + usuarioLider.getNombreUsuario()
				+ ", usuarioPadrino=" + usuarioPadrino.getNombreUsuario() + ", FechaIngreso=" + FechaIngreso + ", area=" + area + "]";
	}	
}
