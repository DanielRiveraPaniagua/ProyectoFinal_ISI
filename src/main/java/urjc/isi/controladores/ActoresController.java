package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Personas;
import urjc.isi.entidades.Peliculas;
import urjc.isi.service.ActoresService;

public class ActoresController {
	private static ActoresService as;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public ActoresController() {
		as = new ActoresService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /actores/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/actores/upload' method='post' enctype='multipart/form-data'>"
			    + "    <input type='file' name='uploaded_actores_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /actores/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return as.uploadTable(request);
	}

	/**
	 * Maneja las peticiones al endpoint /actores/selectAll
	 * @param request
	 * @param response
	 * @return La lista de actores que hay en la tabla Actores de la base de datos en formato HTML o JSON
	 * @throws SQLException
	 */
	public static String selectAllActores(Request request, Response response) throws SQLException {
		List<Personas> output = as.getAllActores();
		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());;
			}
			json.add("output", array);
			result = json.toString();
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	public static String selectActByFechaNac (Request request, Response response) throws SQLException {
		String fecha = request.queryParams ("fecha_nac");
		List<Personas> output = as.getActoresByFechaNac(fecha);
		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());;
			}
			json.add("output", array);
			result = json.toString();
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	public static String selectActMuertos (Request request, Response response) throws SQLException {
		List<Personas> output = as.getActoresMuertos();
		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());;
			}
			json.add("output", array);
			result = json.toString();
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	public static String selectActByIntervaloNac (Request request, Response response) throws SQLException {
		String fechaIn = request.queryParams ("fecha_in");
		String fechaFin = request.queryParams ("fecha_fin");
		List<Personas> output = as.getActoresByIntervaloNac(fechaIn, fechaFin);
		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());;
			}
			json.add("output", array);
			result = json.toString();
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static String infoActores(Request request, Response response) throws SQLException {
		Dictionary<String,Object> output;
		String result = "";

		if(request.queryParams("fullnombre")== null){
			return "";
		}

		output = as.fullActoresInfo(request.queryParams("fullnombre"));
		//Peliculas pelicula = (Peliculas)output.get("pelicula");
		Personas actor = (Personas)output.get("actor");
		//List<Personas> actores = (List<Personas>)output.get("actores");
		List<Peliculas> pelis = (List<Peliculas>)output.get("peliculas");

		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			json.add("actordata", actor.toJSONObject());
			JsonArray jpelis = new JsonArray();
			for(int i = 0; i < pelis.size(); i++) {
				jpelis.add(pelis.get(i).toJSONObject());;
			}
			json.add("peliculas",jpelis);
			result = json.toString();
		}else {
			result = "<b>Información de: " + actor.getFullNombre() + " (" + actor.getNacimiento() +"-" + actor.getMuerte() +")</b> </br>";
			result = result + "<b>Participa en las películas:</b></br>";
			for(int i = 0; i < pelis.size(); i++) {
				result = result + "&emsp;" + pelis.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}
	
	public static String selectActByCercania (Request request, Response response) throws SQLException {
		
		if(request.queryParams("actor")== null){
			return "";
		}
		
		String actor = request.queryParams ("actor");
		String dist_max = (request.queryParams("dist_max")!= null)?request.queryParams("dist_max"):"None";
		String factor = (request.queryParams("factor")!= null)?request.queryParams("factor"):"None";
		
		List<Personas> output = as.getActoresByCercania(actor, dist_max, factor);
		
		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());;
			}
			json.add("output", array);
			result = json.toString();
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /actores
	 */
	public void actoresHandler() {
		get("/selectAll", ActoresController::selectAllActores);
		get("/uploadTable", ActoresController::uploadTable);
		post("/upload", ActoresController::upload);
		get("/selectActByFechaNac", ActoresController::selectActByFechaNac);
		get("/selectActMuertos", ActoresController::selectActMuertos);
		get("/selectActByIntervaloNac", ActoresController::selectActByIntervaloNac);
		get("/info", ActoresController::infoActores);
		get("/selectActByCercania", ActoresController::selectActByCercania);
	}

}
