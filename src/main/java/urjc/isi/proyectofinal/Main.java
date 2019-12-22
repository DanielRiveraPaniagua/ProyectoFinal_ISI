package urjc.isi.proyectofinal;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

import java.sql.*;

import urjc.isi.controladores.PeliculasController;

public class Main {

	public static String doWork(Request request, Response response) throws ClassNotFoundException, URISyntaxException {
       String result = new String("Film application is in WIP. THANKS!");

       return result;
    }
    public static void tables() {
    	path("peliculas",() -> {
        	PeliculasController Controller = new PeliculasController();
        	Controller.peliculasHandler();
        });
    }
    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());
        get("/welcome", Main::doWork);
        path("/",() -> {tables();});
        redirect.get("*", "/welcome");
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
