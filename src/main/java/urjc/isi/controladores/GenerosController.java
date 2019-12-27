package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Personas;
import urjc.isi.service.ActoresService;

public class GenerosController {
	private static GenerosService gs;
	private static String adminkey = "1234";
	
	public GenerosController() {
		gs = new GenerosService();
	}
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/generos/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_generos_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	public static String upload(Request request, Response response) {
		return gs.uploadTable(request);
	}
	
	public static String selectAllGeneros(Request request, Response response) throws SQLException {
		List<Generos> output = gs.getAllGeneros();
		String result = "";
		for(int i = 0; i < output.size(); i++) {
		    result = result + output.get(i).toHTMLString() +"</br>";
		}
		return result;
	}
	public void peliculasHandler() {
		get("/selectAll", GenerosController::selectAllGeneros);
		get("/uploadTable", GenerosController::uploadTable);
		post("/upload", GenerosController::upload);
	}
	
}
