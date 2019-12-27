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
	
	private ActoresDAOImpl actores;
	
	public ActoresService() {
		actores = new ActoresDAOImpl();
	}

	public List<Personas> getAllActores() throws SQLException{
		List<Personas> result = actores.selectAll();
		actores.close();
		return result;
	}
	
	public String uploadTable(Request req){
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
	
	
}
