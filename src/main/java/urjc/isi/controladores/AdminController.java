package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.List;

import spark.Request;
import spark.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	
	public static String uploadTable(Request request, Response response) {
		return "<form action='/peliculas/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_films_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	public static String upload(Request request, Response response) {
		return as.uploadTable(request);
	}
	public static String selectAllPeliculas(Request request, Response response) throws SQLException {
		List<Peliculas> output = as.getAllPeliculas();
		String result = "";
		for(int i = 0; i < output.size(); i++) {
		    result = result + output.get(i).toHTMLString() +"</br>";
		}
		return result;
	}
	
	public void adminHandler() {
		//get("/crearTabla", AdminController::crearTablaPeliculas);
		get("/selectAll", AdminController::selectAllPeliculas);
		get("/uploadTable", AdminController::uploadTable);
		post("/upload", AdminController::upload);
	}
	
	/**
	 * Metodo manejador del endpoint /peliculas/crearTabla 
	 * @param request
	 * @param response
	 * @return 
	 */
	/*
	public static JsonObject crearTablaPeliculas(Request request, Response response) throws SQLException{
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String output = as.crearTablaPeliculas();
		response.type("application/json");
		JsonObject json = new JsonObject();
		json.addProperty("status", "SUCCESS");
		json.addProperty("serviceMessage", "La peticion se manejó adecuadamente");
		json.addProperty("output", output);
		return json;
	}*/
	/*public static JsonObject selectAllPeliculas(Request request, Response response){

		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		response.type("application/json");
		JsonObject json = new JsonObject();
		try {
			List<Peliculas> output = as.getAllPeliculas();
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
	}*/
}