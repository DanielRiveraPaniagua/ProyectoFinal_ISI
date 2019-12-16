package urjc.isi.controladores;

import static spark.Spark.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import spark.Response;

import org.json.*;

import urjc.isi.entidades.Peliculas;
import urjc.isi.service.AdminService;

public class AdminController {

	private static AdminService as;

	/**
	 * Constructor por defecto
	 */
	public AdminController() {
		as = new AdminService();
	}

	/**
	 * Metodo manejador del endpoint /peliculas/crearTabla 
	 * @param request
	 * @param response
	 * @return 
	 */
	public static String crearTablaPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		return as.crearTablaPeliculas();
		/*Deberiamos devolver un JSON*/
	}

	public static String selectAllPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		try {
			List<Peliculas> output = as.getAllPeliculas();
			output.add(new Peliculas());
			return output.toString();
		}catch(SQLException e) {
			return "Ha ocurrido un error accediendo a las peliculas";
		}
	}

	public void adminHandler() {
		get("/crearTabla", AdminController::crearTablaPeliculas);
		get("/selectAll", AdminController::selectAllPeliculas);
	}


}
