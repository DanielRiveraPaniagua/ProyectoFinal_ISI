package jdbc_spark.jdbc_spark;

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

// This code is quite dirty. Use it just as a hello world example 
// to learn how to use JDBC and SparkJava to upload a file, store 
// it in a DB, and do a SQL SELECT query
public class Actores {

    // Connection to the SQLite database. Used by insert and select methods.
    // Initialized in main
    private static Connection connection;

    // Used to illustrate how to route requests to methods instead of
    // using lambda expressions
    public static String doSelect(Request request, Response response) {
	return select (connection, request.params(":table"), 
                                   request.params(":film"));
    }

    public static String select(Connection conn, String table, String id) {
	String sql = "SELECT * FROM " + table + " WHERE id_actor=?";

	String result = new String();

	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
                // Commit after query is executed
		connection.commit();

		while (rs.next()) {
		    // read the result set
		    result += "id = " + rs.getString("id") + "\n";
		    System.out.println("id = "+rs.getString("id") + "\n");

		    result += "nombre = " + rs.getString("nombre") + "\n";
		    System.out.println("nombre = "+rs.getString("nombre")+"\n");

		    result += "apellido = " + rs.getString("apellido") + "\n";
		    System.out.println("apellido = "+rs.getString("apellido")+"\n");

		    result += "fecha_nac = " + rs.getString("fecha_nac") + "\n";
		    System.out.println("fecha_nac = "+rs.getString("fecha_nac")+"\n");

		    result += "fecha_muer = " + rs.getString("fecha_muer") + "\n";
		    System.out.println("fecha_muer = " + rs.getString ("fecha_muer")+"\n");

	    	    result += "pais = " + rs.getString("pais") + "\n";
		    System.out.println("pais = "+rs.getString("pais")+"\n");
		}
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	}

	return result;
    }


    public static void insert(Connection conn, int id, String nombre, String apellido, String fecha_nac, String fecha_muer, String pais) {
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


	// Connect to SQLite sample.db database
	// connection will be reused by every query in this simplistic example
	connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

	// SQLite default is to auto-commit (1 transaction / statement execution)
        // Set it to false to improve performance
	connection.setAutoCommit(false);


	// In this case we use a Java 8 method reference to specify
	// the method to be called when a GET /:table/:film HTTP request
	// Main::doWork will return the result of the SQL select
	// query. It could've been programmed using a lambda
	// expression instead, as illustrated in the next sentence.
	get("/:table/:film", Actores::doSelect);

	// In this case we use a Java 8 Lambda function to process the
	// GET /upload_films HTTP request, and we return a form
	get("/upload_films", (req, res) -> 
	    "<form action='/upload' method='post' enctype='multipart/form-data'>" 
	    + "    <input type='file' name='uploaded_films_file' accept='.txt'>"
	    + "    <button>Upload file</button>" + "</form>");
	// You must use the name "uploaded_films_file" in the call to
	// getPart to retrieve the uploaded file. See next call:


	// Retrieves the file uploaded through the /upload_films HTML form
	// Creates table and stores uploaded file in a two-columns table
	post("/upload", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_films_file").getInputStream()) { 
			// getPart needs to use the same name "uploaded_films_file" used in the form

			// Prepare SQL to create table
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("drop table if exists actores");
			statement.executeUpdate("create table actores (id_actor INT, nombre string, apellido string, fecha_nac string, fecha_muer string, pais string, PRIMARY KEY (id_actor))");



			// Read contents of input stream that holds the uploaded file
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {
			    System.out.println(s);

			    // Tokenize the film name and then the actors, separated by "/"
			    StringTokenizer tokenizer = new StringTokenizer(s, ",");

			    // First token is the film name(year)
			    String apellido = tokenizer.nextToken();

			    String nombre = tokenizer.nextToken();

 			    int id_pel = Integer.parseInt (tokenizer.nextToken());

 			    int id = Integer.parseInt(tokenizer.nextToken());

 			    String fecha_nac = tokenizer.nextToken();

 			    String fecha_muer = tokenizer.nextToken();

 			    String pais = tokenizer.nextToken();

			    if (id_pel == 501) {
				break;
			    }

			    insert(connection, id, nombre, apellido, fecha_nac, fecha_muer, pais);

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
	return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
