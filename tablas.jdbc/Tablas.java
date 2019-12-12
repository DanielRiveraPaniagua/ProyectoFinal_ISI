package isi.final_prac;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.StringTokenizer;

import javax.servlet.MultipartConfigElement;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Tablas {
    
    
    private static Connection connection;

    
    public static String doSelect(Request request, Response response) {
	return select (connection, request.params(":table"), 
                                   request.params(":param"));
    }
    
    public static String doSelectTwoTables(Request request, Response response) {
    	return selectTwoTables (connection, request.params(":tabla1"), 
                                       request.params(":tabla2"),
                                       request.params(":param"));
        }

    public static String select(Connection conn, String table, String film) {
	String sql = "SELECT * FROM " + table + " WHERE id_pelicula=?";

	String result = new String();
	
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, Integer.parseInt(film));
		ResultSet rs = pstmt.executeQuery();
 
		connection.commit();

		while (rs.next()) {
		    result += "id_pelicula = " + rs.getString("id_pelicula") + "\n";

		    result += "nombre = " + rs.getString("nombre") + "\n";

		    result += "fecha = " + rs.getString("fecha") + "\n";

		    result += "duracion = " + rs.getString("duracion") + "\n";

		    result += "rating = " + rs.getString("rating") + "\n";
		}
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	}
	
	return result;
    }
    public static String selectTwoTables(Connection conn, String tabla1, String tabla2, String id) {
	String sql = "SELECT * FROM " + tabla2 + " WHERE id_actor=?";

	String result = new String();
	
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = pstmt.executeQuery();
 
		connection.commit();

		while (rs.next()) {
		    result += "id_actor = " + rs.getString("id_actor") + "\n";

		    result += "nombre = " + rs.getString("nombre") + "\n";

		    result += "apellido = " + rs.getString("apellido") + "\n";

		    result += "fecha_nac = " + rs.getString("fecha_nac") + "\n";

		    result += "fecha_muer = " + rs.getString("fecha_muer") + "\n";
		}
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	return result;
	}
    
    public static void insert_pel(Connection conn, int id, String nombre, String fecha, String duracion, int rating) {
	String sql = "INSERT INTO peliculas(id_pelicula, nombre, fecha, duracion, rating) VALUES(?,?,?,?,?)";

	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, id);
		pstmt.setString(2, nombre);
		pstmt.setString(3, fecha);
		pstmt.setString(4, duracion);
		pstmt.setInt(5, rating);
		pstmt.executeUpdate();
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	}
    }
    
    public static void insert_act(Connection conn, int id, String nombre, String apellido, String fecha_nac, String fecha_muer, String pais) {
    	String sql = "INSERT INTO actores(id_actor, nombre, apellido, fecha_nac, fecha_muer, pais) VALUES(?,?,?,?,?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, id);
    		pstmt.setString(2, nombre);
    		pstmt.setString(3, apellido);
    		pstmt.setString(4, fecha_nac);
    		pstmt.setString(5, fecha_muer);
    		pstmt.setString(6, pais);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
        }

    public static void main(String[] args) throws 
	ClassNotFoundException, SQLException {
	port(getHerokuAssignedPort());
	
	connection = DriverManager.getConnection("jdbc:sqlite:pelis.db");

	
	connection.setAutoCommit(false);

	
	get("/:table/:param", Tablas::doSelect);
	
	get("/:tabla1/:tabla2/:param", Tablas::doSelectTwoTables);

	
	get("/upload_peliculas", (req, res) -> 
	    "<form action='/upload' method='post' enctype='multipart/form-data'>" 
	    + "    <input type='file' name='uploaded_peliculas_file' accept='.txt'>"
	    + "    <button>Upload file</button>" + "</form>");
	
	post("/upload", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_peliculas_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); 
			statement.executeUpdate("drop table if exists peliculas");
			statement.executeUpdate("create table peliculas (id_pelicula INT, nombre string, fecha string, duracion string, rating INT, PRIMARY KEY (id_pelicula))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;
			    StringTokenizer tokenizer = new StringTokenizer(s, "|");

			    int id = Integer.parseInt(tokenizer.nextToken());

			    String nombre = tokenizer.nextToken();

 			    String fecha = tokenizer.nextToken();

 			    String duracion = tokenizer.nextToken();

 			    int rating = Integer.parseInt(tokenizer.nextToken());

			    insert_pel(connection, id, nombre, fecha, duracion, rating);
			   
			    connection.commit();			    
			}
			input.close();
		    }
		return result;
	    });
      
    get("/upload_actores", (req, res) -> 
    "<form action='/upload2' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_actores_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    post("/upload2", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_actores_file").getInputStream()) { 
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); 
			statement.executeUpdate("drop table if exists actores");
			statement.executeUpdate("create table actores (id_actor INT, nombre string, apellido string, fecha_nac string, fecha_muer string, pais string, PRIMARY KEY (id_actor))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;
				
			    StringTokenizer tokenizer = new StringTokenizer(s, ",");
			    
			    String apellido = tokenizer.nextToken();

			    String nombre = tokenizer.nextToken();

 			    int id_pel = Integer.parseInt (tokenizer.nextToken());

 			    int id = Integer.parseInt(tokenizer.nextToken());

 			    String fecha_nac = tokenizer.nextToken();

 			    String fecha_muer = tokenizer.nextToken();

 			    String pais = tokenizer.nextToken();

			    insert_act(connection, id, nombre, apellido, fecha_nac, fecha_muer, pais);

			    connection.commit();
			}
			input.close();
		    }
		return result;
	    });

    }

    static int getHerokuAssignedPort() {
	ProcessBuilder processBuilder = new ProcessBuilder();
	if (processBuilder.environment().get("PORT") != null) {
	    return Integer.parseInt(processBuilder.environment().get("PORT"));
	}
	return 4567;
    }
}
