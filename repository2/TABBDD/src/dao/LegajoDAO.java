package dao;

import model.Legajo;
import model.Proceso;


public interface LegajoDAO extends GenericoDAO <Legajo>
{	
	Legajo getByUserID(int UserID);

	
}
