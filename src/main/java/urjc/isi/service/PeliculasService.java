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
import urjc.isi.dao.implementaciones.DirectoresDAOImpl;
import urjc.isi.dao.implementaciones.PeliculasDAOImpl;
import urjc.isi.dao.interfaces.PersonasDAO;
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
	
	public String getCalificacionForPelicula(String name) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		String result = pelisDAO.selectCalificacionForPelicula(name);
		pelisDAO.close();
		return result;
	}

	public Dictionary<String,Object> fullPeliculasInfo(String titulo) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		PersonasDAO direcDAO = new DirectoresDAOImpl();
		PersonasDAO guioDAO = new DirectoresDAOImpl();
		PersonasDAO actorDAO = new DirectoresDAOImpl();
		String id = pelisDAO.selectIDByTitle(titulo);
		System.out.println(id);
		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			result.put("pelicula",(Object)pelisDAO.selectByID(id));
			result.put("actores", (Object)actorDAO.selectByPeliculaID(id));
			result.put("directores", (Object)direcDAO.selectByPeliculaID(id));
			result.put("guionistas", (Object)guioDAO.selectByPeliculaID(id));
		}
		return result;
	}
}
