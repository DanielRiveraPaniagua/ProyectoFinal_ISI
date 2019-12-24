package urjc.isi.dao.interfaces;

import urjc.isi.entidades.Personas;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Personas
public interface PersonasDAO extends GenericDAO<Personas>{
	public String table = "(idpersona INT, fullnombre text, fnacimiento INT, fmuerte INT, PRIMARY KEY (idpersona))";
	public Personas selectByName(String name);
}
