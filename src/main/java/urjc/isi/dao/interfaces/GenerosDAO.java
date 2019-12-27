package urjc.isi.dao.interfaces;

import urjc.isi.entidades.Generos;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Personas
public interface GenerosDAO extends GenericDAO<Generos>{
	public String table = "(idgenero text, nombre text, PRIMARY KEY (idgenero))";
	public Generos selectByName(String nombre);
}
