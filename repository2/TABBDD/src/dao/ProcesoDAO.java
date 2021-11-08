package dao;

import model.Proceso;

public interface ProcesoDAO extends GenericoDAO<Proceso> {
	
	Proceso getByUserID(int UserID);
}
