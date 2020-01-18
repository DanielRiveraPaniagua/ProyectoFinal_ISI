package urjc.isi.dao.interfaces;


import java.util.Dictionary;
import java.util.List;

import urjc.isi.entidades.Peliculas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface PeliculasDAO extends GenericDAO<Peliculas>{

	
	public List<Peliculas> selectAll(Dictionary<String,String> conditions); //probablemente debería estar en genericDAO
	public String selectIDByTitle (String titulo);
	// Ranking
	public List<Peliculas> selectByRanking(Dictionary<String,String> conditions);
	public List<Peliculas> selectByRanking();
	// Calificacion
	public String selectCalificacionForPelicula(String name);

  //Seleccionar peliculas del genero ...
	public List<Peliculas> selectAllByGenero(String genero);
	
	public List<Peliculas> selectAllBestorWorstFilmByYear(Dictionary<String,String> conditions);
}
