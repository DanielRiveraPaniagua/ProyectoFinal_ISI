package urjc.isi.dao.interfaces;


import java.sql.SQLException;

import urjc.isi.entidades.Peliculas;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos exclusivos de Peliculas
public interface PeliculasDAO extends GenericDAO<Peliculas>{
  //Seleccionar peliculas que duren menos de ...
  //Seleccionar peliculas del genero ...
	public void createTable() throws SQLException;
}
