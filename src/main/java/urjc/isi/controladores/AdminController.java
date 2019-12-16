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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public static JsonObject crearTablaPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String output = as.crearTablaPeliculas();
		response.type("application/json");
		JsonObject json = new JsonObject();
		json.addProperty("status", "SUCCESS");
		json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
		json.addProperty("output", output);
		return json;
	}

	public static JsonObject selectAllPeliculas(Request request, Response response) {
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		response.type("application/json");
		JsonObject json = new JsonObject();
		try {
			List<Peliculas> output = as.getAllPeliculas();
			output.add(new Peliculas());
			output.add(new Peliculas());
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toString());;
			}
			json.add("output", array);
		}catch(SQLException e) {
			json.addProperty("status", "ERROR");
			json.addProperty("serviceMessage", "Ocurrio un error accediendo a la base de datos");
		}
		return json;
	}

	public void adminHandler() {
		get("/crearTabla", AdminController::crearTablaPeliculas);
		get("/selectAll", AdminController::selectAllPeliculas);
	}


}
