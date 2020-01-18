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
		result = "<html><body><meta charset=&quot;UTF-8&quot;>" + 
					"<h1 style=\"color: #5e9ca0;\">Bienvenido a la app de películas ISI 2019/2020</h1>\n" + 
					"<h2 style=\"color: #2e6c80;\">Como usar nuestra página:</h2>\n" + 
					"<ul>\n" + 
					"<li>/selectAll</li>\n" + 
					"<li><strong>&nbsp;</strong></li>\n" + 
					"</ul>\n" + 
					"<p><strong>Enjoy!</strong></p>\n" + 
					"<p><strong>&nbsp;</strong></p>" + 
					"</body></html>";
   
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
        /*redirect.get("*", "/welcome");*/
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
