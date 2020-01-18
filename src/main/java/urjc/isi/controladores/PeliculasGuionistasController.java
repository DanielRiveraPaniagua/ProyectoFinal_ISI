package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import spark.Request;
import spark.Response;
import urjc.isi.service.PeliculasGuionistasService;

public class PeliculasGuionistasController {
	private static PeliculasGuionistasService pas;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public PeliculasGuionistasController() {
		pas = new PeliculasGuionistasService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /peliculasguionistas/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculasguionistas/upload' method='post' enctype='multipart/form-data'>"
			    + "    <input type='file' name='uploaded_peliculasguionistas_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /peliculasguionistas/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return pas.uploadTable(request);
	}

	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculasguionistas
	 */
	public void peliculasGuionistasHandler() {
		get("/uploadTable", PeliculasGuionistasController::uploadTable);
		post("/upload", PeliculasGuionistasController::upload);
	}
}
