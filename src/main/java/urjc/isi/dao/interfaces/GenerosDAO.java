package urjc.isi.dao.interfaces;

import java.util.List;

import urjc.isi.entidades.Generos;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Personas
public interface GenerosDAO extends GenericDAO<Generos>{
	public List<Generos> selectByPeliculaID(String id);
}
