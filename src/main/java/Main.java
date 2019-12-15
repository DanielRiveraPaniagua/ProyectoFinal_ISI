package urjc.isi.proyectofinal

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

import java.sql.*;

import urjc.isi.controladores.AdminController;
import urjc.isi.dao.implementaciones.*;
import urjc.isi.dao.interfaces.*;
import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static String doWork(Request request, Response response) throws ClassNotFoundException, URISyntaxException {
       String result = new String("Film application is in WIP. THANKS!");

       return result;
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());
        get("/welcome", Main::doWork);
        AdminController adminController = new AdminController();
        adminController.adminHandler();

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
