package urjc.isi.dao.interfaces;


import java.util.List;

import urjc.isi.entidades.TituloIdiomas;

//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas

public interface TituloIdiomasDAO extends GenericDAO<TituloIdiomas>{

	public List<TituloIdiomas> selectAll();
}

