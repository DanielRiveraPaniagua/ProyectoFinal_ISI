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
import urjc.isi.dao.implementaciones.DirectoresDAOImpl;
import urjc.isi.entidades.Personas;

public class DirectoresService {

	/**
	 * Constructor por defecto
	 */
	public DirectoresService() {}

	/**
	 * Metodo encargado de procesar un selectAll de la tabla directores
	 * @return Lista de directores de la tabla Directores
	 * @throws SQLException
	 */
	public List<Personas> getAllDirectores() throws SQLException{
		DirectoresDAOImpl directores = new DirectoresDAOImpl();
		List<Personas> result = directores.selectAll();
		directores.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Directores
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		DirectoresDAOImpl directores = new DirectoresDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_directores_file").getInputStream()) {
		    directores.dropTable();
		    directores.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			directores.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		directores.close();
		return result;
	}


}
