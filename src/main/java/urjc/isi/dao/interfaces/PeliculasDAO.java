package urjc.isi.dao.interfaces;


import java.util.List;

import urjc.isi.entidades.Peliculas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface PeliculasDAO extends GenericDAO<Peliculas>{
	
	/**
	 * Obtiene todas las peliculas en las que ha trabajado un actor
	 * @param Nombre del actor por el que se desea buscar
	 * @return
	 */
	public List<Peliculas> selectAllWhereActor(String name);

	// Selecciona las peliculas dada una duracion
	public List<Peliculas> selectAllDuration(double value);
}
