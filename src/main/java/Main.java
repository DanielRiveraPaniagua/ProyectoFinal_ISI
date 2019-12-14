package urjc.isi.ProyectoFinal;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

import java.sql.*;
import urjc.isi.interfacesdao.GenericDAO;
import urjc.isi.interfacesdao.PeliculasDAO;
import javax.servlet.MultipartConfigElement;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

      private static Connection c;
      public static String doSelect(Request request, Response response) {
        return select (c, request.params(":table"),
          Integer.valueOf(request.params(":film")));
    }

    public static String select(Connection conn, String table, int idpelicula) {
      String sql = "SELECT * FROM " + table + " WHERE idpelicula=?";
      String result = new String();

      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setInt(1, idpelicula);
          ResultSet rs = pstmt.executeQuery();
          c.commit();

          while (rs.next()) {
          result += "id_pelicula = " + rs.getString("idpelicula") + "\n";
          result += "titulo = " + rs.getString("titulo") + "\n";
          result += "(" + rs.getString("año") + ")" + "\n";
          result += "duracion = " + rs.getString("duracion") + "\n";
          result += "rating = " + rs.getString("rating") + "\n";
          result += "numero de votos = " + rs.getString("nvotos") + "\n";
        }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return result;
    }

    public static String doWork(Request request, Response response) throws ClassNotFoundException, URISyntaxException {
	     String result = new String("Film application is in WIP. THANKS!");

	     return result;
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());

        //Esto es solo para el ejemplo, nos gustaría no tenerlo en
        //una expresión alfa y que detectase /upload_* y lo enviase al
        //handler correspondiente. /upload_* debería funcionar bien ya
        //que reconoce * para todas las redirecciones
        //Podemos hacer un handler para cada tabla o intentar homogeneizar todo.
        get("/:table/:film", Main::doSelect);
        c = DriverManager.getConnection("jdbc:sqlite:sample.db"); //Habría que usar el connect()
        c.setAutoCommit(false);
        PeliculasDAO tables = new PeliculasDAO(); //No se que decir al respecto de que tengamos que
            //instanciar un objeto para poder hacer uploadTable posteriormente...
            //Si pasamos de la interfaz y solo hacemos la clase abastracta podemos
            //hacer esos métodos static y no nos daría problemas. pero yo que se
        post("/upload", (req, res) -> {
          req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
          String result = "File uploaded!";
          try (InputStream input = req.raw().getPart("uploaded_films_file").getInputStream()) {

            Statement statement = c.createStatement();
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            statement.executeUpdate("drop table if exists films");
            statement.executeUpdate("create table peliculas(idpelicula int,titulo string, año int, duracion double, rating double, nvotos int)");
            
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);
            tables.uploadTable(br,c);
            input.close();
            return result;
          }
        });

        get("/upload_films", (req, res) ->
	       "<form action='/upload' method='post' enctype='multipart/form-data'>"
	       + "    <input type='file' name='uploaded_films_file' accept='.txt'>"
	       + "    <button>Upload file</button>" + "</form>");

           // spark server default message
           get("/welcome", Main::doWork);
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
