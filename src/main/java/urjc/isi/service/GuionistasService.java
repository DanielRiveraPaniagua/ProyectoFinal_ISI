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
	public List<Personas> getAllGuionistas(Dictionary<String,String> conditions) throws SQLException{
		GuionistasDAOImpl guionistas = new GuionistasDAOImpl();
		List<Personas> result;
		if(!conditions.isEmpty()) {
			result = guionistas.selectAll(conditions);
		}else {
			result = guionistas.selectAll();
		}
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

	public 	Dictionary<String,Object> fullGuionistasInfo(String engine,boolean isid) throws SQLException{
		GuionistasDAOImpl guionistasDAO = new GuionistasDAOImpl();
		PeliculasDAOImpl peliDAO = new PeliculasDAOImpl();
		String id;
		if (!isid) {
			Personas persona = guionistasDAO.selectByName(engine);
			id = persona!=null?persona.getId():"";
		}else {
			Personas persona = guionistasDAO.selectByID(engine);
			id = persona.getId()!=null?persona.getId():"";
		}

		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			result.put("guionista", (Object)guionistasDAO.selectByID(id));
			result.put("peliculas", (Object)peliDAO.selectByPersonaID("guionista",id));
		}
		guionistasDAO.close();
		peliDAO.close();
		return result;
	}
}
