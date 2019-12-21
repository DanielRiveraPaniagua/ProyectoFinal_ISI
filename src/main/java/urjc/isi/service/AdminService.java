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
	public String crearTablaPeliculas()  throws SQLException{
			pelisDAO.createTable();
			return "Tabla creada con exito";
	}

	/**
	 * Obtiene todas las peliculas que estan en la base de datos
	 * @return
	 * @throws SQLException
	 */
	public List<Peliculas> getAllPeliculas() throws SQLException{
		return pelisDAO.selectAll();
	}
	
	public String uploadTable(Request req){
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_films_file").getInputStream()) {
			PeliculasDAOImpl peli = new PeliculasDAOImpl();
		    peli.dropTable();
		    peli.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			peli.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	
}
