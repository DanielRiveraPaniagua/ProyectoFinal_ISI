package urjc.isi.dao.interfaces;


import java.util.Dictionary;
import java.util.List;

import urjc.isi.entidades.Peliculas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface PeliculasDAO extends GenericDAO<Peliculas>{

	
	public List<Peliculas> selectAll(Dictionary<String,String> conditions); //probablemente debería estar en genericDAO
	
	//Ranking
	public List<Peliculas>selectByRanking(Dictionary<String,String> conditions);
	public List<Peliculas> selectBest10();
		
	/**public List<Peliculas> selectRankingWhereActor(String name);
	public List<Peliculas> selectRankingWhereDirector(String name);
	public List<Peliculas> selectRankingWhereGuionista(String name);
	public List<Peliculas> selectRankingWhereGenero(String name);**/

	//selecciona peliculas para adultos
	public List<Peliculas> selectPeliculasForAdultos();
	//selecciona peliculas para ninos
	public List<Peliculas> selectPeliculasForNinos();
	//devuelve la calificacion dada una pelicula
	public List<Peliculas> selectCalificacionForPelicula(String name);

}
