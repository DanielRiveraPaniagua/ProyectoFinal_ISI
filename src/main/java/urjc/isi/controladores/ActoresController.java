package urjc.isi.controladores;

import static spark.Spark.get;
import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Personas;
import urjc.isi.service.ActoresService;

public class ActoresController {
	private static ActoresService as;
	private static String adminkey = "1234";
	
	public ActoresController() {
		as = new ActoresService();
	}
	
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/actores/upload' method='post' enctype='multipart/form-data'>" 
			    + "    <input type='file' name='uploaded_actores_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}
	
	public static String upload(Request request, Response response) {
		return as.uploadTable(request);
	}
	
	public static String selectAllActores(Request request, Response response) throws SQLException {
		List<Personas> output = as.getAllActores();
		String result = "";
		for(int i = 0; i < output.size(); i++) {
		    result = result + output.get(i).toHTMLString() +"</br>";
		}
		return result;
	}
	
	public void actoresHandler() {
		get("/selectAll", ActoresController::selectAllActores);
		get("/uploadTable", ActoresController::uploadTable);
		post("/upload", ActoresController::upload);
	}
	
}
