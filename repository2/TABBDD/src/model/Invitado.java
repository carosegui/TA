package model;

public class Invitado {
	
	int IDInvitado;
	Evento IDEvento;
	Usuario IDUsuario;
	
	public Invitado(int iDInvitado, Evento iDEvento, Usuario iDUsuario) 
	{
		IDInvitado = iDInvitado;
		IDEvento = iDEvento;
		IDUsuario = iDUsuario;
	}

	public int getIDInvitado() {
		return IDInvitado;
	}

	public void setIDInvitado(int iDInvitado) {
		IDInvitado = iDInvitado;
	}

	public Evento getIDEvento() {
		return IDEvento;
	}

	public void setIDEvento(Evento iDEvento) {
		IDEvento = iDEvento;
	}

	public Usuario getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(Usuario iDUsuario) {
		IDUsuario = iDUsuario;
	}

	@Override
	public String toString() {
		return "Invitado [IDInvitado=" + IDInvitado + ", IDEvento=" + IDEvento.getAsunto() + ", IDUsuario=" + IDUsuario.getNombreUsuario() + "]";
	}
	
}
