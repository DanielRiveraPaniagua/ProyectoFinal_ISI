package urjc.isi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import urjc.isi.dao.implementaciones.*;
import urjc.isi.dao.interfaces.*;
import urjc.isi.entidades.*;

public class PeliculasService {

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
	public List<Peliculas> getAllPeliculas(Dictionary<String,String> conditions) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result;
		if(!conditions.isEmpty()) {
			result = pelisDAO.selectAll(conditions);
		}else {
			result = pelisDAO.selectAll();
		}
		pelisDAO.close();
		return result;
	}

	public List<Peliculas> getAllRanking(Dictionary<String,String> conditions) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result;
		if(!conditions.isEmpty()) {
			result = pelisDAO.selectByRanking(conditions);
		}else {
			result = pelisDAO.selectByRanking();
		}
		pelisDAO.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar un la salida de todas la lista con todas las peliculas de un genero
	 * @return Lista de peliculas con ese genero
	 * @throws SQLException
	 */
	public List<Peliculas> getAllPeliculasByGenero(String genero){
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		List<Peliculas> result = pelisDAO.selectAllByGenero(genero);
		pelisDAO.close();
		return result;
	}

	public String getCalificacionForPelicula(String name) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		Peliculas pelicula = pelisDAO.selectFilmByTitle(name);
		pelisDAO.close();
		String result = pelicula!=null?Integer.toString(pelicula.getCalificacion()):"";
		return result;
	}


	public 	Dictionary<String,Object> fullPeliculasInfo(String engine,boolean isid) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		PersonasDAO direcDAO = new DirectoresDAOImpl();
		PersonasDAO guioDAO = new GuionistasDAOImpl();
		PersonasDAO actorDAO = new ActoresDAOImpl();
		GenerosDAOImpl generosDAO = new GenerosDAOImpl();
		String id;
		if (!isid) {
			Peliculas pelicula = pelisDAO.selectFilmByTitle(engine);
			id = pelicula!=null?pelicula.getIdPelicula():"";
		}else {
			id=engine;
		}
		Dictionary<String,Object> result = new Hashtable<String,Object>();
		
		if(id.length()>0){
			result.put("pelicula",(Object)pelisDAO.selectByID(id));
			result.put("actores", (Object)actorDAO.selectByPeliculaID(id));
			result.put("directores", (Object)direcDAO.selectByPeliculaID(id));
			result.put("guionistas", (Object)guioDAO.selectByPeliculaID(id));
			result.put("generos", (Object)generosDAO.selectByPeliculaID(id));
		}
		pelisDAO.close();
		direcDAO.close();
		actorDAO.close();
		guioDAO.close();
		generosDAO.close();
		return result;
	}

	public List<Peliculas> getWorstORBestFilmBy(Dictionary<String,String> conditions) throws SQLException{
		List<Peliculas> result;
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		result = pelisDAO.selectAllBestorWorstFilmByYear(conditions);
		pelisDAO.close();
		return result;
	}
}
