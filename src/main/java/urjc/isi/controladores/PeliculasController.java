package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.List;

import spark.Request;
import spark.Response;

import urjc.isi.entidades.Peliculas;
import urjc.isi.service.PeliculasService;

public class PeliculasController {

	private static PeliculasService ps;
	private static String adminkey = "1234";
	
	/**
	 * Constructor por defecto
	 */
	public PeliculasController() {
		ps = new PeliculasService();
	}
	
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculas/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_films_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	public static String upload(Request request, Response response) {
		return ps.uploadTable(request);
	}
	public static String selectAllPeliculas(Request request, Response response) throws SQLException {
		List<Peliculas> output = ps.getAllPeliculas();
		String result = "";
		for(int i = 0; i < output.size(); i++) {
		    result = result + output.get(i).toHTMLString() +"</br>";
		}
		return result;
	}
	
	public void peliculasHandler() {
		//get("/crearTabla", AdminController::crearTablaPeliculas);
		get("/selectAll", PeliculasController::selectAllPeliculas);
		get("/uploadTable", PeliculasController::uploadTable);
		post("/upload", PeliculasController::upload);
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