package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import spark.Request;
import spark.Response;
import urjc.isi.service.PeliculasActoresService;

public class PeliculasActoresController {
	private static PeliculasActoresService pas;
	private static String adminkey = "1234";
	
	/**
	 * Constructor por defecto
	 */
	public PeliculasActoresController() {
		pas = new PeliculasActoresService();
	}
	
	/**
	 * Maneja las peticiones que llegan al endpoint /peliculasactores/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculasactores/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_peliculasactores_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	
	/**
	 * Metodo que se encarga de manejar las peticiones a /peliculasactores/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return pas.uploadTable(request);
	}
	
	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculasactores
	 */
	public void peliculasActoresHandler() {
		get("/uploadTable", PeliculasActoresController::uploadTable);
		post("/upload", PeliculasActoresController::upload);
	}
}
