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
	
	public PeliculasService() {
		pelisDAO = new PeliculasDAOImpl();
	}
	
	public String uploadTable(Request req){
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

	public List<Peliculas> getAllPeliculas() throws SQLException{
		List<Peliculas> result = pelisDAO.selectAll();
		pelisDAO.close();
		return result;
	}
	
	public List<Peliculas> getAllPeliculasWithActor(String name){
		List<Peliculas> result = pelisDAO.selectAllwhereActor(name);
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
