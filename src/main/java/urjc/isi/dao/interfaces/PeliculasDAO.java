package urjc.isi.dao.interfaces;


import java.util.Dictionary;
import java.util.List;

import urjc.isi.entidades.Peliculas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface PeliculasDAO extends GenericDAO<Peliculas>{

	public List<Peliculas> selectAll(Dictionary<String,String> conditions); //probablemente debería estar en genericDAO

	public List<Peliculas> selectAllWhereDirector(String name);

	public List<Peliculas> selectAllWhereGuionista(String name);
}
