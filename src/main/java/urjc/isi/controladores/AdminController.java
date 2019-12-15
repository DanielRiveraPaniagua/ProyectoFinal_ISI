package urjc.isi.controladores;

import static spark.Spark.*;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import spark.Response;
import urjc.isi.service.AdminService;

public class AdminController {
	
	public static String crearTablaPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		AdminService service = new AdminService();
		return service.crearTablaPeliculas();
	}
	
	public static String selectAllPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		AdminService service = new AdminService();
		return service.crearTablaPeliculas();
	}
	
	public void adminHandler() {
		get("peliculas/crearTabla", AdminController::crearTablaPeliculas);
		get("peliculas/selectAll", AdminController::selectAllPeliculas);
	}


}
