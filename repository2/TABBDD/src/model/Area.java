package model;

public class Area {
	int IDArea;
	String Nombre;
	
	public Area(int iDArea, String nombre) {
		
		IDArea = iDArea;
		Nombre = nombre;
	}

	public int getIDArea() {
		return IDArea;
	}

	public void setIDArea(int iDArea) {
		IDArea = iDArea;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	
}
