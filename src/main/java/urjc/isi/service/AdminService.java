package urjc.isi.service;

import java.sql.SQLException;
import java.util.List;

import urjc.isi.dao.implementaciones.PeliculasDAOImpl;
import urjc.isi.entidades.*;

public class AdminService {

	private PeliculasDAOImpl pelisDAO;

	/**
	 * Constructor por defecto
	 */
	public AdminService() {
		pelisDAO = new PeliculasDAOImpl();
	}

	/**
	 * Crea una tabla peliculas con el formato adecuado y devuelve si se ha creado con exito
	 * @return Estado de salida
	 */
	public String crearTablaPeliculas() {
		try{
			pelisDAO.createTable("create table peliculas (id_pelicula INT, nombre string, fecha string, duracion string, rating INT, PRIMARY KEY (id_pelicula))");
			return "Tabla creada con exito";
		}catch(SQLException e) {
			return "Hubo un fallo en la creacion de la tabla";
		}
	}

	/**
	 * Obtiene todas las peliculas que estan en la base de datos
	 * @return
	 * @throws SQLException
	 */
	public List<Peliculas> getAllPeliculas() throws SQLException{
		return pelisDAO.selectAll();
	}

}
