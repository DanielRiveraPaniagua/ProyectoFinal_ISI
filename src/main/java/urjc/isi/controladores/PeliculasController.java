package urjc.isi.controladores;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import spark.Request;
import spark.Response;

import urjc.isi.entidades.Peliculas;
import urjc.isi.entidades.Personas;
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
		Dictionary<String,String> filter = new Hashtable<String,String>();

		if(request.queryParams("actor")!= null)
			filter.put("actor",request.queryParams("actor"));
		if(request.queryParams("director")!= null)
			filter.put("director",request.queryParams("director"));
		if(request.queryParams("guionista")!= null)
			filter.put("guionista",request.queryParams("guionista"));
		if(request.queryParams("duracion")!=null)
			filter.put("duracion", request.queryParams("duracion"));
		if(request.queryParams("adultos")!=null)
			if(request.queryParams("adultos").equals("si") || request.queryParams("adultos").equals("no"))
				filter.put("adultos", request.queryParams("adultos"));
		if(request.queryParams("titulo")!=null)
			filter.put("titulo", request.queryParams("titulo"));
		if(request.queryParams("year")!=null)
			filter.put("year", request.queryParams("year"));

		output = ps.getAllPeliculas(filter);

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

	public static String selectAllRanking(Request request, Response response) throws SQLException {
		List<Peliculas> output;
		String result = "";
		Dictionary<String,String> filter = new Hashtable<String,String>();

		String form = "Filtrar por: <br/><br/>"
					+ "<form action='/peliculas/ranking' method='get' enctype='multipart/form-data'>"
					+ "Actor: <input type=text name=actor size=30><br/><br/>"
					+ "Director: <input type=text name=director size=30><br/><br/>"
					+ "Guionista: <input type=text name=guionista size=30><br/><br/>"
					+ "Género: <input type=text name=genero size=30><br/><br/>"
					+ "<button type=submit>Enviar </button>"
					+ "</form>";

		if(request.queryParams("actor")!= null && !request.queryParams("actor").equals("")) {
			filter.put("actor",request.queryParams("actor"));
		}
		if(request.queryParams("director")!= null && !request.queryParams("director").equals("")) {
			filter.put("director",request.queryParams("director"));
		}
		if(request.queryParams("guionista")!= null && !request.queryParams("guionista").equals("")) {
			filter.put("guionista",request.queryParams("guionista"));
		}
		/*if(request.queryParams("genero")!=null)
			filter.put("duracion", request.queryParams("duracion"));
			result = result + "Peliculas del género " + request.queryParams("genero") + " mejor valoradas" + "<br/><br/>";**/

		output = ps.getAllRanking(filter);

		if(filter.isEmpty()) {
			result = result + form;
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
		}else {
			for(int i = 0; i < output.size(); i++) {
			    result = result + output.get(i).toHTMLString() +"</br>";
			}
		}
		return result;
	}

	public static String calificacion(Request request, Response response) throws SQLException {

		String output;
		String result =	"<form action='/peliculas/calificacion' method='get' enctype='multipart/form-data'>"
						+ "Pelicula: <input type=text name=pelicula size=30>"
						+ "<button type=submit value=Pelicula>Buscar </button><br/></form>";

		if(request.queryParams("pelicula") != null) {
			output = ps.getCalificacionForPelicula(request.queryParams("pelicula"));
			result = "";
		} else {
			output = "";
		}
		if (!output.equals("")) {
			if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
				response.type("application/json");
				JsonObject json = new JsonObject();
				json.addProperty("status", "SUCCESS");
				json.addProperty("serviceMessage", "La peticion se manejo adecuadamente");
				json.addProperty("Titulo", request.queryParams("pelicula"));
				json.addProperty("Calificacion", output);
				json.add("output", json);
				result = json.toString();
			} else {
				result = result + request.queryParams("pelicula") + ": " +  output +"</br>";
			}
		}
		return result;
	}
	public static String infoPeliculas(Request request, Response response) throws SQLException {
		Dictionary<String,Object> output;
		String result = "";

		result = ps.fullPeliculasInfo(request.queryParams("titulo"));
		Peliculas pelicula = (Peliculas)output.get("pelicula");
		List<Personas> actores = (List<Personas>)output.get("actores");
		List<Personas> guionistas = (List<Personas>)output.get("guionistas");
		List<Personas> directores = (List<Personas>)output.get("directores");

		/*if(request.queryParams("format")!= null && request.queryParams("format").equals("json")) {
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
		}else {*/
			result = "Información de:" + pelicula.getTitulo() + " (" + pelicula.getAño()+") </br>";
			result = result + "Dirigida por:</br>";
			for(int i = 0; i < directores.size(); i++) {
				result = result + directores.get(i).toHTMLString() +"</br>";
			}
			result = result + "Escrita por:</br>";
			for(int i = 0; i < guionistas.size(); i++) {
				result = result + guionistas.get(i).toHTMLString() +"</br>";
			}
			result = result+"Lista de actores:</br>";
			for(int i = 0; i < actores.size(); i++) {
				result = result + actores.get(i).toHTMLString() +"</br>";
			//}
		return result;
	}

	/**
	 * Metodo que se encarga de manejar todos los endpoints que cuelgan de /peliculas
	 */
	public void peliculasHandler() {
		get("/selectAll", PeliculasController::selectAllPeliculas);
		get("/uploadTable", PeliculasController::uploadTable);
		post("/upload", PeliculasController::upload);
		get("/ranking", PeliculasController::selectAllRanking);
		get("/calificacion", PeliculasController::calificacion);
		get("/info", PeliculasController::infoPeliculas);
	}

}
