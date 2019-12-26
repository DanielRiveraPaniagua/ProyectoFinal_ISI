package urjc.isi.dao.interfaces;


import java.util.List;

import urjc.isi.entidades.Peliculas;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas
public interface PeliculasDAO extends GenericDAO<Peliculas>{
	public List<Peliculas> selectAllwhereActor(String name);
  //Seleccionar peliculas del genero ...
}
