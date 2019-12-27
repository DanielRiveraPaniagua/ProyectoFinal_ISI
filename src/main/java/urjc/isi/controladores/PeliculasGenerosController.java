package urjc.isi.controladores;

import static spark.Spark.get;

import static spark.Spark.post;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;
import urjc.isi.entidades.Generos;
import urjc.isi.service.GenerosService;

public class PeliculasGenerosController {
	private static GenerosService gs;
	private static String adminkey = "1234";
	
	/**
	 * Constructor por defecto
	 */
	public PeliculasGenerosController() {
		gs = new GenerosService();
	}
	
	/**
	 * Maneja las peticiones que llegan al endpoint /generos/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 * @throws SQLException 
	 */
	public static String selectGenero(Request request, Response response) throws SQLException {
		List<Generos> output = gs.getAllGeneros();
		String result = "<form action='/generos/upload' method='post' enctype='multipart/form-data'>" + "  <select name=\"item\">\n";
		{
			for(int i = 0; i < output.size(); i++) {
			    result = result + "<option value=" + i + "\">" + output.get(i).toHTMLString() + "</option>\n";
			}
		    result = result + "  </select>\n" + 
		    "  <input type=\"submit\" value=\"Submit\">"
		    + "</form>";
		}
		return result;
	}
	
	/**
	 * Metodo que se encarga de manejar las peticiones a /generos/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return gs.uploadTable(request);
	}
	
	/**
	 * Maneja las peticiones al endpoint /generos/selectAll
	 * @param request
	 * @param response
	 * @return La lista de generos que hay en la tabla Generos de la base de datos en formato HTML
	 * @throws SQLException
	 */
	public static String selectAllPeliculasGeneros(Request request, Response response) throws SQLException {
		List<Generos> output = gs.getAllGeneros();
		String result = "";
		{
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}
	
	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculasgeneros
	 */
	public void peliculasHandler() {
		get("/selectAll", PeliculasGenerosController::selectAllPeliculasGeneros);
		get("/uploadGenero", PeliculasGenerosController::selectGenero);
	}
	
}
