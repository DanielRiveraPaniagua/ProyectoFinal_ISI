package urjc.isi.controladores;

import static spark.Spark.get;

import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Generos;
import urjc.isi.entidades.Peliculas;
import urjc.isi.entidades.RelacionesGeneros;
import urjc.isi.service.GenerosService;
import urjc.isi.service.PeliculasGenerosService;
import urjc.isi.service.PeliculasService;

public class PeliculasGenerosController {
	private static PeliculasGenerosService pgs;
	private static String adminkey = "1234";
	
	/**
	 * Constructor por defecto
	 */
	public PeliculasGenerosController() {
		pgs = new PeliculasGenerosService();
	}
	

	/**
	 * Maneja las peticiones que llegan al endpoint /peliculasgeneros/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculasgeneros/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_peliculasgeneros_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	
	
	/**
	 * Metodo que se encarga de manejar las peticiones a /peliculasgeneros/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return pgs.uploadTable(request);
	}
	
	
	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculasgeneros
	 */
	public void peliculasHandler() {
		get("/uploadTable", PeliculasGenerosController::uploadTable);
		post("/upload", PeliculasGenerosController::upload);
	}
	
}
