package dao;

import java.util.List;

public interface GenericoDAO <O>
{
	public void crear(O o);
	public void eliminar(O o);
	public void modificar (O o);
	public List <O> getAll();
	public Object getOne(int ID);
	
}
