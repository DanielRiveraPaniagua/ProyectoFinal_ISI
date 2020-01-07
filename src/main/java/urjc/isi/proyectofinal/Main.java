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
       String result = new String("Film application is in WIP. THANKS!");
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
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());
        get("/welcome", Main::defaultResponse);
        path("/",() -> {tables();});
        redirect.get("*", "/welcome");
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
