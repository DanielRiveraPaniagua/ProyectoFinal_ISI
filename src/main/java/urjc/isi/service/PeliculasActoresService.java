package urjc.isi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import urjc.isi.dao.implementaciones.PeliculasActoresDAOImpl;

public class PeliculasActoresService {
	
	private PeliculasActoresDAOImpl peac ;
	
	/**
	 * Constructor por defecto
	 */
	public PeliculasActoresService() {}
	
	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla PeliculasActores
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		PeliculasActoresDAOImpl peac = new PeliculasActoresDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_peliculasactores_file").getInputStream()) {
		    peac.dropTable();
		    peac.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			peac.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		peac.close();
		return result;
	}
}
