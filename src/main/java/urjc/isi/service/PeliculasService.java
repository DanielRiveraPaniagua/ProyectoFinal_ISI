package urjc.isi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import urjc.isi.dao.implementaciones.PeliculasDAOImpl;
import urjc.isi.entidades.*;

public class PeliculasService {

	private PeliculasDAOImpl pelisDAO ;

	/**
	 * Constructor por defecto
	 */
	public PeliculasService() {}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Peliculas
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_films_file").getInputStream()) {
		    pelisDAO.dropTable();
		    pelisDAO.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			pelisDAO.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		pelisDAO.close();
		return result;
	}


	/**
	 * Metodo encargado de procesar un selectAll de la tabla Peliculas
	 * @return Lista de actores de la tabla Peliculas
	 * @throws SQLException
	 */
	public List<Peliculas> getAllPeliculas() throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectAll();
		pelisDAO.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar un la salida de todas la lista con todas las peliculas de un actor
	 * @return Lista de actores de la tabla Actores
	 * @throws SQLException
	 */
	public List<Peliculas> getAllPeliculasByActor(String name){
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectAllWhereActor(name);
		pelisDAO.close();
		return result;
	}

	/** Procesa todas las peliculas de un director **/
	public List<Peliculas> getAllPeliculasbyDirector(String name){
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectAllWhereDirector(name);
		pelisDAO.close();
		return result;
	}

	/** Procesa todas las peliculas de un guionista **/
	public List<Peliculas> getAllPeliculasbyGuionista(String name){
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectAllWhereGuionista(name);
		pelisDAO.close();
		return result;
	}

	public List<Peliculas> getBestPeliculas() throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectBest10();
		pelisDAO.close();
		return result;
	}

	/**
	 * Crea una tabla peliculas con el formato adecuado y devuelve si se ha creado con exito
	 * @return Estado de salida
	 */
	/*public String crearTablaPeliculas()  throws SQLException{
			pelisDAO.createTable();
			return "Tabla creada con exito";
	}*/
}
