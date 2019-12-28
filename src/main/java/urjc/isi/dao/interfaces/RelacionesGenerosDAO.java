package urjc.isi.dao.interfaces;

import java.util.List;

import urjc.isi.entidades.Generos;
import urjc.isi.entidades.Peliculas;
import urjc.isi.entidades.RelacionesGeneros;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Personas
public interface RelacionesGenerosDAO extends GenericDAO<RelacionesGeneros>{
	public String table = "(id_pelicula text, genero text PRIMARY KEY (id_pelicula,genero))";
	
	/**
	 * Selecciona un Genero por su nombre
	 * @param nombre
	 * @return
	 */
	public Generos selectByName(String nombre);

	List<RelacionesGeneros> selectAllByGenero(String genero);
}
