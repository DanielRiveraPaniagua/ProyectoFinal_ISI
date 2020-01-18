package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Personas;
import urjc.isi.service.DirectoresService;

public class DirectoresController {
	private static DirectoresService ds;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public DirectoresController() {
		ds = new DirectoresService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /Directores/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/directores/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_directores_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /Directores/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return ds.uploadTable(request);
	}

	/**
	 * Maneja las peticiones al endpoint /Directores/selectAll
	 * @param request
	 * @param response
	 * @return La lista de Directores que hay en la tabla Directores de la base de datos en formato HTML o JSON
	 * @throws SQLException
	 */
	public static String selectAllDirectores(Request request, Response response) throws SQLException {
		List<Personas> output;
		String result = "";
		Dictionary<String,String> filter = new Hashtable<String,String>();
		if(request.queryParams("id_dir")!= null)
			filter.put("id_dir",request.queryParams("id_dir"));
		if(request.queryParams("name")!= null)
			filter.put("name",request.queryParams("name"));
		if(request.queryParams("fecha_nac")!= null)
			filter.put("fecha_nac",request.queryParams("fecha_nac"));
		if(request.queryParams("intervalo_fecha_nac")!= null)
			filter.put("intervalo_fecha_nac",request.queryParams("intervalo_fecha_nac"));
		if(request.queryParams("fecha_muer")!= null)
			filter.put("fecha_muer",request.queryParams("fecha_muer"));
		if(request.queryParams("intervalo_fecha_muer")!= null)
			filter.put("intervalo_fecha_muer",request.queryParams("intervalo_fecha_muer"));
		
		output = ds.getAllDirectores(filter);
		
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
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /Directores
	 */
	public void directoresHandler() {
		get("/selectAll", DirectoresController::selectAllDirectores);
		get("/uploadTable", DirectoresController::uploadTable);
		post("/upload", DirectoresController::upload);
	}

}
