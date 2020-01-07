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
import urjc.isi.dao.implementaciones.GuionistasDAOImpl;
import urjc.isi.entidades.Personas;

public class GuionistasService {

	/**
	 * Constructor por defecto
	 */
	public GuionistasService() {}

	/**
	 * Metodo encargado de procesar un selectAll de la tabla guionistas
	 * @return Lista de guionistas de la tabla Guionistas
	 * @throws SQLException
	 */
	public List<Personas> getAllGuionistas() throws SQLException{
		GuionistasDAOImpl guionistas = new GuionistasDAOImpl();
		List<Personas> result = guionistas.selectAll();
		guionistas.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Guionistas
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		GuionistasDAOImpl guionistas = new GuionistasDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_guionistas_file").getInputStream()) {
		    guionistas.dropTable();
		    guionistas.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			guionistas.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		guionistas.close();
		return result;
	}


}
