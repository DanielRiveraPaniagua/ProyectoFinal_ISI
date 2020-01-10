package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;

import urjc.isi.entidades.Peliculas;
import urjc.isi.service.PeliculasService;

public class PeliculasController {

	private static PeliculasService ps;
	private static String adminkey = "1234";

	/**
	 * Constructor por defecto
	 */
	public PeliculasController() {
		ps = new PeliculasService();
	}

	/**
	 * Maneja las peticiones que llegan al endpoint /peliculas/uploadTable
	 * @param request
	 * @param response
	 * @return El formulario para subir el fichero con las pseudoqueries o una redireccion al endpoint /welcome
	 */
	public static String uploadTable(Request request, Response response) {
		if(!adminkey.equals(request.queryParams("key"))) {
			response.redirect("/welcome"); //Se necesita pasar un parametro (key) para poder subir la tabla
		}
		return "<form action='/peliculas/upload' method='post' enctype='multipart/form-data'>"
			    + "    <input type='file' name='uploaded_films_file' accept='.txt'>"
			    + "    <button>Upload file</button>" + "</form>";
	}

	/**
	 * Metodo que se encarga de manejar las peticiones a /peliculas/upload
	 * @param request
	 * @param response
	 * @return Mensaje de estado sobre la subida de los registros
	 */
	public static String upload(Request request, Response response) {
		return ps.uploadTable(request);
	}

	/**
	 * Metodo encargado de manejar las peticiones a /peliculas/selectAll
	 * @param request
	 * @param response
	 * @return Listado de peliculas que estan en la tabla Peliculas de la base de datos en formato HTML o JSON
	 * @throws SQLException
	 */
	public static String selectAllPeliculas(Request request, Response response) throws SQLException {
		List<Peliculas> output;
		String result = "";
		if(request.queryParams("actor")!= null)
			output = ps.getAllPeliculasByActor(request.queryParams("actor"));
		else
			output = ps.getAllPeliculas();
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
	Método que muestra las 10 mejores peliculas ordenadas por ranking
	y da la posibilidad de elegir por qué filtro quieres ordenarlas
	*/
	public static String ranking(Request request, Response response) throws SQLException
	{
		List<Peliculas> output;
		
		String result = "Filtrar por: <br/><br/>"
						+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
						+ "Actor: <input type=text name=actor size=30>"
						+ "<button type=submit value=Actor>Actor </button><br/></form>"
						+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
						+ "Director: <input type=text name=director size=30>"
						+ "<button type=submit value=Director>Director </button><br/></form>"
						+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
						+ "Guionista: <input type=text name=guionista size=30>"
						+ "<button type=submit value=Guionista>Guionista </button><br/></form>"
						+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
						+ "Género: <input type=text name=genero size=30>"
						+ "<button type=submit value=Género>Género </button><br/></form>"
						+ "<br/>"
						+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
						+ "<button type=submit value=VolverAtrás>Volver atrás </button>"
						+ "</form>";
		
		if(request.queryParams("actor")!= null) {
			output = ps.getRankingByActor(request.queryParams("actor"));
			result = result + "Peliculas en las que participa " + request.queryParams("actor") + "<br/><br/>";
		} else if(request.queryParams("director")!= null) {
			output = ps.getRankingByDirector(request.queryParams("director"));
			result = result + "Peliculas en las que participa " + request.queryParams("director") + "<br/><br/>";
		} else if(request.queryParams("guionista")!= null) {
			output = ps.getRankingByGuionista(request.queryParams("guionista"));
			result = result + "Peliculas en las que participa " + request.queryParams("guionista") + "<br/><br/>";
		} else if(request.queryParams("genero")!= null) {
			output = ps.getRankingByGenero(request.queryParams("genero"));
			result = result + "Peliculas en las que participa " + request.queryParams("genero") + "<br/><br/>";
		} else {
			output = ps.getBestPeliculas();
			result = result + "10 peliculas mejor valoradas" + "<br/><br/>";
		}
		
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
		} else {	
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		
		return result;
				
	} 
	
	 
	
	//Devuelve peliculas en funcion de la calificacion
	
	public static String calificacion(Request request, Response response) throws SQLException {
		List<Peliculas> output;
		
		String result = "<form action='/peliculas/calificacion' method='get' enctype='multipart/form-data'>"
						+ "<button type=submit name=adultos value=adultos>Adultos </button><br/></form>"
						+ "<form action='/peliculas/calificacion' method='get' enctype='multipart/form-data'>"
						+ "<button type=submit name=ninos value=ninos>Niños </button><br/></form>"
						+ "<form action='/peliculas/calificacion' method='get' enctype='multipart/form-data'>"
						+ "<button type=submit value=VolverAtrás>Volver atrás </button>";
		
		if(request.queryParams("adultos") != null) {
			output = ps.getAllPeliculasForAdultos();
		} else {
			output = ps.getAllPeliculasForNinos();
		}
			
		if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
			response.type("application/json");
			JsonObject json = new JsonObject();
			json.addProperty("status", "SUCCESS");
			json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
			JsonArray array = new JsonArray();
			for(int i = 0; i < output.size(); i++) {
				array.add(output.get(i).toJSONObject());
			}
			json.add("output", array);
			result = json.toString();
		} else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculas
	 */
	public void peliculasHandler() {
		//get("/crearTabla", AdminController::crearTablaPeliculas);
		get("/selectAll", PeliculasController::selectAllPeliculas);
		get("/uploadTable", PeliculasController::uploadTable);
		post("/upload", PeliculasController::upload);
		get("/ranking", PeliculasController::ranking);
		get("/calificacion", PeliculasController::calificacion);
	}

}
