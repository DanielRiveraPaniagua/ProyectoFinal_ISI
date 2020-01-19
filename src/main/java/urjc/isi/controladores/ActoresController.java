package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Personas;
import urjc.isi.grafos.SET;
import urjc.isi.grafos.ST;
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
		List<Personas> output;
		String result = "";
		Dictionary<String,String> filter = new Hashtable<String,String>();
		if(request.queryParams("director")!=null)
			filter.put("director", request.queryParams("director"));
		if(request.queryParams("guionista")!=null)
			filter.put("guionista", request.queryParams("guionista"));
		if(request.queryParams("id_act")!= null)
			filter.put("id_act",request.queryParams("id_act"));
		if(request.queryParams("name")!= null)
			filter.put("name",request.queryParams("name"));
		if(request.queryParams("fecha_nac")!= null)
			filter.put("fecha_nac",request.queryParams("fecha_nac"));
		if(request.queryParams("fecha_muer")!= null)
			filter.put("fecha_muer",request.queryParams("fecha_muer"));
		
		output = as.getAllActores(filter);

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

		if(request.queryParams("nombre")== null & request.queryParams("id")==null){
			return "Por favor introduce un nombre para buscar el actor que deseas"+
					"<form action='/actores/info' method='get' enctype='multipart/form-data'>"
					+ "Nombre de Actor: <input type=text name=nombre size=30>"
					+ "<button type=submit value=Actor>Buscar </button><br/></form>";
		}
		if(request.queryParams("id")!=null) {
			output = as.fullActoresInfo(request.queryParams("id"),true);
		}else {
			output = as.fullActoresInfo(request.queryParams("nombre"),false);
		}
		if(output.isEmpty()) {
			response.redirect("/actores/info");
			return "El actor no se encuentra en la base de datos";
		}
		
		Personas actor = (Personas)output.get("actor");
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
			result = "<b>Información de: " + actor.getFullNombre() + " (" + actor.getNacimiento() +"-" + actor.getMuerte() +")</b>";
			result +="<b>&emsp;IDActor: </b>"+actor.getId()+"</br>";
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

		ST<Double, List<Personas>> output = as.getActoresByCercania(actor, dist_max, factor);

		String result = "";
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			for (Double p : output.descendingKeys()) {
				JsonArray array = new JsonArray();
				for(int i = 0; i < output.get(p).size(); i++) {
					array.add(output.get(p).get(i).toJSONObject());;
				}
				json.add(p + "%", array);
			}
			result = json.toString();
		}else {
			for (Double p : output.descendingKeys()) {
				result = result + "Actores con porcentaje de similitud " + p + "%:</br>";
				for (int i = 0; i < output.get(p).size(); i++) {
					result = result + output.get(p).get(i).toHTMLString() +"</br>";
	            }
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
		get("/info", ActoresController::infoActores);
		get("/selectActByCercania", ActoresController::selectActByCercania);
	}

}
