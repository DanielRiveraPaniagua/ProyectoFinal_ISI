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
import urjc.isi.dao.implementaciones.*;
import urjc.isi.entidades.*;

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
	public List<Personas> getAllDirectores(Dictionary<String,String> conditions) throws SQLException{
		DirectoresDAOImpl directores = new DirectoresDAOImpl();
		List<Personas> result;
		if(!conditions.isEmpty()) {
			result = directores.selectAll(conditions);
		}else {
			result = directores.selectAll();
		}
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

	public 	Dictionary<String,Object> fullDirectoresInfo(String name) throws SQLException{
		DirectoresDAOImpl directoresDAO = new DirectoresDAOImpl();
		PeliculasDAOImpl peliDAO = new PeliculasDAOImpl();
		Personas persona = new Personas();
		persona = directoresDAO.selectByName(name);
		String id = persona.getId();

		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			result.put("director", (Object)directoresDAO.selectByID(id));
			result.put("peliculas", (Object)peliDAO.selectByDirectorID(id));
		}
		directoresDAO.close();
		peliDAO.close();
		return result;
	}
}
