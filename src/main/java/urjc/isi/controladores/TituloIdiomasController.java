package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;

import urjc.isi.entidades.TituloIdiomas;
import urjc.isi.service.TituloIdiomasService;

public class TituloIdiomasController {

	private static TituloIdiomasService tis;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public TituloIdiomasController() {
		tis = new TituloIdiomasService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /tituloidiomas/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/tituloidiomas/upload' method='post' enctype='multipart/form-data'>"
			    + "    <input type='file' name='uploaded_tituloidiomas_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /tituloidiomas/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return tis.uploadTable(request);
	}

	/**
	 * Metodo encargado de manejar las peticiones a /tituloidiomas/selectAll
	 * @param request
	 * @param response
	 * @return Listado de títulos de películas en distintos idiomas en formato HTML o JSON
	 * @throws SQLException
	 */
	public static String selectAllTituloIdiomas(Request request, Response response) throws SQLException {
		List<TituloIdiomas> output;
		String result = "";
		output = tis.getAllTituloIdiomas();
		
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
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculasactores
	 */
	public void tituloIdiomasHandler() {
		get("/selectAll", TituloIdiomasController::selectAllTituloIdiomas);
		get("/uploadTable", TituloIdiomasController::uploadTable);
		post("/upload", TituloIdiomasController::upload);
	}

}
