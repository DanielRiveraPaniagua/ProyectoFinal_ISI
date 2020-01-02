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
import urjc.isi.dao.implementaciones.GenerosDAOImpl;
import urjc.isi.entidades.Generos;

public class GenerosService {

	/**
	 * Metodo encargado de procesar un selectAll de la tabla Generos
	 * @return Lista de generos de la tabla Generos
	 * @throws SQLException
	 */
	public List<Generos> getAllGeneros() throws SQLException{
		GenerosDAOImpl generos = new GenerosDAOImpl();
		List<Generos> result = generos.selectAll();
		generos.close();
		return result;
	}
	
	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Generos
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		GenerosDAOImpl generos = new GenerosDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_generos_file").getInputStream()) {
		    generos.dropTable();
		    generos.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			generos.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		generos.close();
		return result;
	}
	
	
}
