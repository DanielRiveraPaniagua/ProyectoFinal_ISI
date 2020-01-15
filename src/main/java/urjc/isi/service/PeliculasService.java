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

	public String fullPeliculasInfo(String titulo) throws SQLException{
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		PersonasDAO direcDAO = new DirectoresDAOImpl();
		PersonasDAO guioDAO = new GuionistasDAOImpl();
		PersonasDAO actorDAO = new ActoresDAOImpl();
		String id = pelisDAO.selectIDByTitle(titulo);


		Peliculas pelicula;
		List<Personas> actores;
		List<Personas> guionistas;
		List<Personas> directores;

		System.out.println(id);
		//Dictionary<String,Object> result = new Hashtable<String,Object>();
		String result ="";
		if(id.length()>0){
			//result.put("pelicula",(Object)pelisDAO.selectByID(id));
			pelicula = pelisDAO.selectByID(id);
			System.out.println("peliculas llega");
			//result.put("actores", (Object)actorDAO.selectByPeliculaID(id));
			actores = actorDAO.selectByPeliculaID(id);
			System.out.println("actores termina");
			//result.put("directores", (Object)direcDAO.selectByPeliculaID(id));
			guionistas = direcDAO.selectByPeliculaID(id);
			System.out.println("directores termina");
			//result.put("guionistas", (Object)guioDAO.selectByPeliculaID(id));
			directores = direcDAO.selectByPeliculaID(id);
			System.out.println("guionistas termina");

			result = "Información de:" + pelicula.getTitulo() + " (" + pelicula.getAño()+") </br>";
			result = result + "Dirigida por:</br>";
			for(int i = 0; i < directores.size(); i++) {
				result = result + directores.get(i).toHTMLString() +"</br>";
			}
			result = result + "Escrita por:</br>";
			for(int i = 0; i < guionistas.size(); i++) {
				result = result + guionistas.get(i).toHTMLString() +"</br>";
			}
			result = result+"Lista de actores:</br>";
			for(int i = 0; i < actores.size(); i++) {
				result = result + actores.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}
}
