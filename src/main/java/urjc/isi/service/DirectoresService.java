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
	 * Metodo encargado de procesar un selectAll de la tabla Directores
	 * @return Lista de Directores de la tabla Directores
	 * @throws SQLException
	 */
	public List<Personas> getAllDirectores() throws SQLException{
		DirectoresDAOImpl Directores = new DirectoresDAOImpl();
		List<Personas> result = Directores.selectAll();
		Directores.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Directores
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		DirectoresDAOImpl Directores = new DirectoresDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_Directores_file").getInputStream()) {
		    Directores.dropTable();
		    Directores.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			Directores.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		Directores.close();
		return result;
	}


}
