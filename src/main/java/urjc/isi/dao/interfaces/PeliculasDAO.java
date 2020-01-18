package urjc.isi.dao.interfaces;


import java.util.Dictionary;
import java.util.List;

import urjc.isi.entidades.Peliculas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface PeliculasDAO extends GenericDAO<Peliculas>{

	
	public List<Peliculas> selectAll(Dictionary<String,String> conditions);
	public Peliculas selectFilmByTitle (String titulo);
	
	// Ranking
	public List<Peliculas> selectByRanking(Dictionary<String,String> conditions);
	public List<Peliculas> selectByRanking();

  //Seleccionar peliculas del genero ...
	public List<Peliculas> selectAllByGenero(String genero);
	
	public List<Peliculas> selectAllBestorWorstFilmByYear(Dictionary<String,String> conditions);
	public List<Peliculas> selectByPersonaID(String type, String id);
}
