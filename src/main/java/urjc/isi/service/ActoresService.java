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
import urjc.isi.dao.implementaciones.ActoresDAOImpl;
import urjc.isi.entidades.Personas;

public class ActoresService {

	/**
	 * Constructor por defecto
	 */
	public ActoresService() {}

	/**
	 * Metodo encargado de procesar un selectAll de la tabla actores
	 * @return Lista de actores de la tabla Actores
	 * @throws SQLException
	 */
	public List<Personas> getAllActores() throws SQLException{
		ActoresDAOImpl actores = new ActoresDAOImpl();
		List<Personas> result = actores.selectAll();
		actores.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Actores
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		ActoresDAOImpl actores = new ActoresDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_actores_file").getInputStream()) {
		    actores.dropTable();
		    actores.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			actores.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		actores.close();
		return result;
	}

	public List<Personas> getActoresByFechaNac (String fecha) throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerByFechaNac (fecha);
		actores.close();
		return result;
	}

	public List<Personas> getActoresMuertos () throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerMuertas ();
		actores.close();
		return result;
	}

	public List<Personas> getActoresByIntervaloNac (String fechaIn, String fechaFin) throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerByIntervaloNac (fechaIn, fechaFin);
		actores.close();
		return result;
	}

	public 	Dictionary<String,Object> fullActoresInfo(String name) throws SQLException{
		// PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		ActoresDAOImpl actoresDAO = new ActoresDAOImpl();
		// PersonasDAO direcDAO = new DirectoresDAOImpl();
		PeliculasDAO peliDAO = new PeliculasDAOImpl();
		String id = actoresDAO.selectIDByName(name);

		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			//result.put("pelicula",(Object)pelisDAO.selectByID(id));
			result.put("actor", (Object)actoresDAO.selectByID(id));
			//result.put("actores", (Object)actorDAO.selectByPeliculaID(id));
			result.put("peliculas", (Object)peliDAO.selectByActorID(id));
		}
		actoresDAO.close();
		peliDAO.close();
		return result;
	}
}
