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
import urjc.isi.service.GuionistasService;
import urjc.isi.entidades.Peliculas;

public class GuionistasController {
	private static GuionistasService as;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public GuionistasController() {
		as = new GuionistasService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /guionistas/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/guionistas/upload' method='post' enctype='multipart/form-data'>"
			    + "    <input type='file' name='uploaded_guionistas_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /guionistas/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return as.uploadTable(request);
	}

	/**
	 * Maneja las peticiones al endpoint /guionistas/selectAll
	 * @param request
	 * @param response
	 * @return La lista de actores que hay en la tabla Guioistas de la base de datos en formato HTML o JSON
	 * @throws SQLException
	 */
	public static String selectAllGuionistas(Request request, Response response) throws SQLException {
		List<Personas> output = as.getAllGuionistas();
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

	public static String selectGuioByFechaNac (Request request, Response response) throws SQLException {
		String fecha = request.queryParams ("fecha_nac");
		List<Personas> output = as.getGuionistasByFechaNac(fecha);
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

	public static String selectGuioMuertos (Request request, Response response) throws SQLException {
		List<Personas> output = as.getGuionistasMuertos();
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

	public static String selectGuioByIntervaloNac (Request request, Response response) throws SQLException {
		String fechaIn = request.queryParams ("fecha_in");
		String fechaFin = request.queryParams ("fecha_fin");
		List<Personas> output = as.getGuionistasByIntervaloNac(fechaIn, fechaFin);
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
	public static String infoGuionistas(Request request, Response response) throws SQLException {
		Dictionary<String,Object> output;
		String result = "";

		if(request.queryParams("fullnombre")== null){
			return "";
		}

		output = as.fullGuionistasInfo(request.queryParams("fullnombre"));
		//Peliculas pelicula = (Peliculas)output.get("pelicula");
		Personas guionista = (Personas)output.get("guionista");
		//List<Personas> actores = (List<Personas>)output.get("actores");
		List<Peliculas> pelis = (List<Peliculas>)output.get("peliculas");

		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			json.add("guionistadata", guionista.toJSONObject());
			JsonArray jpelis = new JsonArray();
			for(int i = 0; i < pelis.size(); i++) {
				jpelis.add(pelis.get(i).toJSONObject());;
			}
			json.add("peliculas",jpelis);
			result = json.toString();
		}else {
			result = "<b>Información de: " + guionista.getFullNombre() + " (" + guionista.getNacimiento() +"-" + guionista.getMuerte() +")</b> </br>";
			result = result + "<b>Guionista en las películas:</b></br>";
			for(int i = 0; i < pelis.size(); i++) {
				result = result + "&emsp;" + pelis.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}
	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /guionistas
	 */
	public void guionistasHandler() {
		get("/selectAll", GuionistasController::selectAllGuionistas);
		get("/uploadTable", GuionistasController::uploadTable);
		post("/upload", GuionistasController::upload);
		get("/selectGuioByFechaNac", GuionistasController::selectGuioByFechaNac);
		get("/selectGuioMuertos", GuionistasController::selectGuioMuertos);
		get("/selectGuioByIntervaloNac", GuionistasController::selectGuioByIntervaloNac);
		get("/info", GuionistasController::infoGuionistas);
	}

}
