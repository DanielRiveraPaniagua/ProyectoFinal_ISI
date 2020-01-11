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

	public List<Peliculas> selectAllWhereDirector(String name);

	public List<Peliculas> selectAllWhereGuionista(String name);
	
	//Ranking
	public List<Peliculas> selectBest10();
		
	public List<Peliculas> selectRankingWhereActor(String name);
	public List<Peliculas> selectRankingWhereDirector(String name);
	public List<Peliculas> selectRankingWhereGuionista(String name);
	public List<Peliculas> selectRankingWhereGenero(String name);

	//selecciona peliculas para adultos
	public List<Peliculas> selectPeliculasForAdultos();
	//selecciona peliculas para ninos
	public List<Peliculas> selectPeliculasForNinos();
	//devuelve la calificacion dada una pelicula
	public List<Peliculas> selectCalificacionForPelicula(String name);

}
