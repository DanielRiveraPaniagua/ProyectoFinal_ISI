package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import spark.Request;
import spark.Response;
import urjc.isi.service.PeliculasActoresService;

public class PeliculasActoresController {
	private static PeliculasActoresService pas;
	private static String adminkey = "1234";
	
	public PeliculasActoresController() {
		pas = new PeliculasActoresService();
	}
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculasactores/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_peliculasactores_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	public static String upload(Request request, Response response) {
		return pas.uploadTable(request);
	}
	
	public void peliculasHandler() {
		get("/uploadTable", PeliculasActoresController::uploadTable);
		post("/upload", PeliculasActoresController::upload);
	}
}
