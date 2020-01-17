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
import urjc.isi.dao.implementaciones.TituloIdiomasDAOImpl;
import urjc.isi.entidades.*;

public class TituloIdiomasService {

	/**
	 * Constructor por defecto
	 */
	public TituloIdiomasService() {}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Peliculas
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		TituloIdiomasDAOImpl tiDAO = new TituloIdiomasDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_tituloidiomas_file").getInputStream()) {
			tiDAO.dropTable();
			tiDAO.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			tiDAO.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		tiDAO.close();
		return result;
	}


	/**
	 * Metodo encargado de procesar un selectAll de la tabla TituloIdiomas
	 * @return Lista de títulos de películas en distintos idiomas de la tabla TituloIdiomas
	 * @throws SQLException
	 */
	public List<TituloIdiomas> getAllTituloIdiomas() throws SQLException{
		TituloIdiomasDAOImpl tiDAO = new TituloIdiomasDAOImpl();
		List<TituloIdiomas> result;
		result = tiDAO.selectAll();
		tiDAO.close();
		return result;
	}
}
