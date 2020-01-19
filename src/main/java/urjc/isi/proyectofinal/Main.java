package urjc.isi.proyectofinal;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

import java.sql.*;

import urjc.isi.controladores.*;

public class Main {

	/**
	 * Este metodo devuelve la respuesta por defecto a cualquier endpoint no contemplado en la API REST y al /welcome
	 * @param request
	 * @param response
	 * @return Respuesta por defecto de la aplicación
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public static String defaultResponse(Request request, Response response) throws ClassNotFoundException, URISyntaxException {

		String result = "";
		String result_Victor = "";
		result = "<html><body><meta charset=&quot;UTF-8&quot;>" + 
					"<h1 style=\"color: #5e9ca0;\">Bienvenido a la app de películas ISI 2019/2020</h1>\n" + 
					"<h2 style=\"color: #2e6c80;\">Como usar nuestra página:</h2>\n" + 
					
					"<p><span style=color: #0000ff;><strong>Películas</strong></span></p><ul>"+
					"<li><a href/peliculas/uploadTable?key=1234>Subir tablas</a></li>"+
					"<li><a href/peliculas/selectAll>Mostrar todas las películas</a></li> "+
					"<li>Ordenar por ranking con varios filtros --><a href=/peliculas/ranking>Ranking</a></li>"+
					"<li><a href/peliculas/info>Info completa de una pelicula</a></li>"+
					"<li><a href/peliculas/filmsbymood>Peliculas segun mood</a></li>" +
					"<li>Mejor o peor película del año --> /peliculas/filmoftheyear?year=año&score=best/worst</li>" +
					"<li> Filtrar películas segun su duración " +
					"<ul><li>/peliculas/selectAll?duracion=>d --> Peliculas de duracion mayor a d</li></ul>" +
					"<ul><li>/peliculas/selectAll?duracion=<d --> Peliculas de duracion menor a d</li></ul>" +
					"<ul><li>/peliculas/selectAll?duracion=d --> Peliculas de duracion d</li></ul>" +
					"<ul><li>/peliculas/selectAll?duracion=>d1-d2 --> Peliculas entre esas duraciones</li></ul></li>" +
					"<li> Opciones según el rating de las películas " +
					"<ul><li> /peliculas/selectAll?rating=r1-r2 --> Peliculas en ese rango de rating </li></ul>"+
					"<li>"+
					"</ul>" +
					"<p><strong><span style=color: #0000ff;>Actores</span></strong></p><ul>"+
					"<li><a href=/actores/uploadTable?key=1234>Subir tablas</a></li>"+
					"<li><a href=/actores/selectAll>Mostrar todos los actores</a></li> "+
					"</ul>" +
					"<p><span style=color: #0000ff;><strong>Guionistas</strong></span></p></ul>" +
					"<li><span style=color: #0000ff;><a href=/guionistas/uploadTable?key=1234>Subir tablas</a></span></li>" +
					"<li><span style=color: #0000ff;><a href=/guionistas/selectAll>Mostrar todos los guionistas </a></li></ul>"+
					"</ul>" +
					"<p><span style=color: #0000ff;><strong>Directores</strong></span></p><ul>"+
					"<li><span style=color: #0000ff;><a href=/directores/uploadTable?key=1234>Subir tablas</a></span></li>"+
					"<li><span style=color: #0000ff;><a href=/directores/selectAll>Mostrar todos los directores</a></span></li>"+
					"</ul>" +
					"<p><span style=color: #0000ff;><strong>Géneros</strong></span></p></ul>"+
					"<li><a href=/generos/uploadTable?key=1234>Subir tablas</a></span></li>"+
					"<li>Elegir peliculas según el <a href=/generos/searchByGenero>genero</a></li>"+
					"</body></html>";

		result_Victor = "<span style=bgcolor:#FF7247;</span><form action='/pag_principal' method='post' enctype='multipart/form-data'>"
        		+ "<h1 style=color:#1821EB;>Bienvenido a nuestra web de peliculas</h1>"
        		+ "<br>" + "<h2 style=color:#5B72EB;>ACTORES</h2>" + "<br>"
        		+ "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll>Imprimir todos los actores</a>"
        		+ "<p>Tambien puedes filtrar los actores por:</p>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?id_act=x>id</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?name=x>nombre</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?fecha_nac=x>fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?fecha_nac=x-x>intervalo fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?fecha_muer=x>fecha de muerte</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/actores/selectAll?fecha_muer=x-x>intervalo fecha de muerte</a>" + "<br>"
        		+ "<p>Sustituyendo las x por los valores deseados</p>"
        		+ "<br>" + "<h2 style=color:#5B72EB;>PELICULAS</H2>" + "<br>"
        		+ "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll>Imprimir todas las peliculas</a>"
        		+ "<p>Tambien puedes filtrar las peliculas por:</p>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?titulo=x>titulo</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?actor=x>actor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?director=x>director</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?guionista=x>guionista</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?year=x>año</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?year=x-x>intervalo de años</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?idioma=x>idioma</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?adultos=x>adultos(si/no)</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?duracion=>x>duracion mayor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?duracion=<x>duracion menor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?duracion=x>duracion</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?duracion=x-x>intervalo de duracion</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?rating=x-x>intervalo de puntuacion</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?rating=>x>puntuacion mayor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?rating=<x>puntuacion menor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/selectAll?rating=x>puntuacion</a>" + "<br>"
        		+ "<p>Sustiyendo las x por los valores deseados</p>"
        		+ "<br>" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/ranking>Diez peliculas mejor valoradas</a>"
        		+ "<p>Tambien puedes filtrar las peliculas por:</p>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/ranking?actor=x>actor</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/ranking?director=x>director</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/ranking?guionista=x>guionista</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/peliculas/ranking?genero=x>genero</a>" + "<br>"
        		+ "<p>Sustituyendo las x por los valores deseados</p>"
        		+ "<br>" + "<h2 style=color:#5B72EB;>DIRECTORES</h2>" + "<br>"
        		+ "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll>Imprimir todos los directores</a>"
        		+ "<p>Tambien puedes filtrar los directores por:</p>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?id_dir=x>id</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?name=x>nombre</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?fecha_nac=x>fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?fecha_nac=x-x>intervalo fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?fecha_muer=x>fecha de muerte</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/directores/selectAll?fecha_muer=x-x>intervalo fecha de muerte</a>" + "<br>"
        		+ "<p>Sustituyendo las x por los valores deseados</p>"
        		+ "<br>" + "<h2 style=color:#5B72EB;>GUIONISTAS</h2>" + "<br>"
        		+ "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll>Imprimir todos los guionistas</a>"
        		+ "<p>Tambien puedes filtrar los guionistas por:</p>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?id_guio=x>id</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?name=x>nombre</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?fecha_nac=x>fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?fecha_nac=x-x>intervalo fecha de nacimiento</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?fecha_muer=x>fecha de muerte</a>" + "<br>"
        		+ "-->" + "<a href=https://peliculasurjc.herokuapp.com/guionistas/selectAll?fecha_muer=x-x>intervalo fecha de muerte</a>" + "<br>"
        		+ "<p>Sustituyendo las x por los valores deseados</p>"
        		+ "<br>" + "<h2 style=color:#5B72EB;>GENEROS</h2>" + "<br>"
        		+ "<a href=https://peliculasurjc.herokuapp.com/generos/selectAll>Imprimir todos los generos</a>"
        		+ "</form>";

		return result;
    }


	/**
	 * Este metodo es un gestor de los endpoints asociados a cada una de las tablas de la base de datos
	 */
    public static void tables() {
    	path("peliculas",() -> {
        	PeliculasController Controller = new PeliculasController();
        	Controller.peliculasHandler();
        });
    	path("actores",()->{
    		ActoresController Controller = new ActoresController();
    		Controller.actoresHandler();
    	});
    	path("peliculasactores",()->{
    		PeliculasActoresController Controller = new PeliculasActoresController();
    		Controller.peliculasActoresHandler();
    	});
		path("directores", () ->{
			DirectoresController Controller = new DirectoresController();
			Controller.directoresHandler();
		});
		path("guionistas", () ->{
			GuionistasController Controller = new GuionistasController();
			Controller.guionistasHandler();
		});
		path("peliculasdirectores", () ->{
			PeliculasDirectoresController Controller = new PeliculasDirectoresController();
			Controller.peliculasDirectoresHandler();
		});
		path("peliculasguionistas", () ->{
			PeliculasGuionistasController Controller = new PeliculasGuionistasController();
			Controller.peliculasGuionistasHandler();
		});
		path("tituloidiomas",() -> {
			TituloIdiomasController Controller = new TituloIdiomasController();
	    	Controller.tituloIdiomasHandler();
		});
		path("generos",()->{
	  		GenerosController Controller = new GenerosController();
	  		Controller.generosHandler();
	  	});
	  	path("peliculasgeneros",()->{
	  		PeliculasGenerosController Controller = new PeliculasGenerosController();
	  		Controller.peliculasHandler();
	  	});
	  	notFound((req, res) -> {
	  		return "<htlm><body>" + 
	  				"<h1>Error 404</h1><br/>" + 
	  				"El recurso que has buscado no se encuentra en nuestra app<br/><br/>" +
	  				"Pulsa <a href=/welcome>aquí</a> para volver a la página principal " + 
	  				"</body><html>";
	  	});			
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());
        get("/welcome", Main::defaultResponse);
        path("/",() -> {tables();});
    }

    /**
     * Este metodo asigna el puerto en el que va a correr la aplicación en Heroku
     * @return puerto en el que va a correr la aplicación
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
