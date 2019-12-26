package prueba_jdbc.prueba_jdbc;


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


public class tablas {
    
    
    private static Connection connection;
    
    public static String doSelectAll(Request request, Response response) {
    	return selectAll (connection, request.params(":tabla"));
        }

    public static String selectAll(Connection conn, String tabla) {
	String sql = "SELECT * FROM " + tabla;

	String result = new String();
	
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		ResultSet rs = pstmt.executeQuery();
 
		connection.commit();

		while (rs.next()) {
		    result += "id_pelicula = " + rs.getString("id_pelicula") + "\n";

		    result += "titulo = " + rs.getString("titulo") + "\n";

		    result += "fecha = " + rs.getString("fecha") + "\n";

		    result += "duracion = " + rs.getString("duracion") + "\n";

		    result += "isAdult = " + rs.getString("isAdult") + "\n";
			
		    result += "rating = " + rs.getString("rating") + "\n";

		    result += "numVotos = " + rs.getString("numVotos") + "\n";
		}
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	return result;
	}
    
    public static void insert_pel(Connection conn, String id, String nombre, String fecha, String duracion, String isAdult, String rating, String numVotos) {
	String sql = "INSERT INTO peliculas(id_pelicula, titulo, fecha, duracion, isAdult, rating, numVotos) VALUES(?,?,?,?,?,?,?)";

	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, id);
		pstmt.setString(2, nombre);
		pstmt.setString(3, fecha);
		pstmt.setString(4, duracion);
		pstmt.setString(5, isAdult);
		pstmt.setString(6, rating);
		pstmt.setString(7, numVotos);
		pstmt.executeUpdate();
	    } catch (SQLException e) {
	    System.out.println(e.getMessage());
	}
    }
    
    public static void insert_act(Connection conn, String id, String nombre, String apellido, String fecha_nac, String fecha_muer) {
    	String sql = "INSERT INTO actores(id_actor, nombre, apellido, fecha_nac, fecha_muer) VALUES(?,?,?,?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id);
    		pstmt.setString(2, nombre);
    		pstmt.setString(3, apellido);
    		pstmt.setString(4, fecha_nac);
    		pstmt.setString(5, fecha_muer);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }
    
    public static void insert_trabajan(Connection conn, String id_pelicula, String id_actor) {
    	String sql = "INSERT INTO trabajan (id_pelicula, id_actor) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, id_actor);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }

    public static void insert_dir(Connection conn, String id, String nombre, String apellido, String fecha_nac, String fecha_muer) {
    	String sql = "INSERT INTO directores(id_director, nombre, apellido, fecha_nac, fecha_muer) VALUES(?,?,?,?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id);
    		pstmt.setString(2, nombre);
    		pstmt.setString(3, apellido);
    		pstmt.setString(4, fecha_nac);
    		pstmt.setString(5, fecha_muer);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
        }

    public static void insert_guio(Connection conn, String id, String nombre, String apellido, String fecha_nac, String fecha_muer) {
    	String sql = "INSERT INTO guionistas(id_guionista, nombre, apellido, fecha_nac, fecha_muer) VALUES(?,?,?,?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id);
    		pstmt.setString(2, nombre);
    		pstmt.setString(3, apellido);
    		pstmt.setString(4, fecha_nac);
    		pstmt.setString(5, fecha_muer);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
        }
    
    public static void insert_escriben(Connection conn, String id_pelicula, String id_guionista) {
    	String sql = "INSERT INTO escriben (id_pelicula, id_guionista) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, id_guionista);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }
    
    public static void insert_dirigen(Connection conn, String id_pelicula, String id_director) {
    	String sql = "INSERT INTO dirigen (id_pelicula, id_director) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, id_director);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }

    public static void insert_generos(Connection conn, String nombre) {
    	String sql = "INSERT INTO generos (nombre) VALUES(?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, nombre);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }

    public static void insert_pertenecen(Connection conn, String id_pelicula, String nombre) {
    	String sql = "INSERT INTO pertenecen (id_pelicula, nombre) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, nombre);
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

	
	get("/welcome", (req, res) -> 
	    "<form action='/pag_principal' method='post' enctype='multipart/form-data'>" 
	    + "</form>");

	post("/pag_principal", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "Bienvenidos a nuestra web de peliculas";
		return result;
	});
	
	get("/:tabla/selectAll", tablas::doSelectAll);


   		// PARA PELICULAS FICHERO DatosLimpios/DatosProcesadosFinales/peliculas.txt
	
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
			statement.executeUpdate("create table peliculas (id_pelicula INT, titulo string, fecha string, duracion string, isAdult INT, rating INT, numVotos INT, PRIMARY KEY (id_pelicula))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {
				
			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id = tokenizer.nextToken();

			    String nombre = tokenizer.nextToken();

 			    String fecha = tokenizer.nextToken();

 			    String duracion = tokenizer.nextToken();

			    String isAdult = tokenizer.nextToken();

 			    String rating = tokenizer.nextToken();

			    String numVotos = tokenizer.nextToken();

			    insert_pel(connection, id, nombre, fecha, duracion, isAdult, rating, numVotos);
			   
			    connection.commit();			    
			}
			input.close();
		    }
		return result;
	    });
      
		// PARA ACTORES FICHERO ACTORES.TXT

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
			statement.executeUpdate("create table actores (id_actor INT, nombre string, apellido string, fecha_nac string, fecha_muer string, PRIMARY KEY (id_actor))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			
			while ((s = br.readLine()) != null) {
				
			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();
			    
			    String id_act = tokenizer.nextToken();

			    String nombre_apellido = tokenizer.nextToken();
				
			    StringTokenizer token2 = new StringTokenizer(nombre_apellido, " ");

			    String nombre = token2.nextToken();

			    String apellido = token2.nextToken();

 			    String fecha_nac = tokenizer.nextToken();

 			    String fecha_muer = tokenizer.nextToken();

			    insert_act(connection, id_act, nombre, apellido, fecha_nac, fecha_muer);

			    connection.commit();
			}
			input.close();
		    }
		return result;
	    });

		// PARA TRABAJAN FICHERO RELACION_PELICULA_ACTOR.TXT

    get("/upload_trabajan", (req, res) -> 
    "<form action='/upload3' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_trabajan_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    
    post("/upload3", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_trabajan_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists trabajan");
			statement.executeUpdate("create table trabajan (id_pelicula INT, id_actor INT, PRIMARY KEY (id_pelicula, id_actor), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY id_actor REFERENCES actores (id_actor))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();

			    String id_actor = tokenizer.nextToken();

			    insert_trabajan(connection, id_pel, id_actor);
			   
			    connection.commit();
			    
			}
			input.close();
		    }
		return result;
	    });

		// PARA DIRECTORES FICHERO DIRECTORES.TXT

    get("/upload_directores", (req, res) -> 
    "<form action='/upload4' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_directores_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    post("/upload4", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_directores_file").getInputStream()) { 
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); 
			statement.executeUpdate("drop table if exists directores");
			statement.executeUpdate("create table directores (id_director INT, nombre string, apellido string, fecha_nac string, fecha_muer string, PRIMARY KEY (id_director))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {
				
			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");
			    
			    String id_dir = tokenizer.nextToken();

			    String nombre_apellido = tokenizer.nextToken();
				
			    StringTokenizer token2 = new StringTokenizer(nombre_apellido, " ");

			    String nombre = token2.nextToken();

			    String apellido = token2.nextToken();

 			    String fecha_nac = tokenizer.nextToken();

 			    String fecha_muer = tokenizer.nextToken();

			    insert_dir(connection, id_dir, nombre, apellido, fecha_nac, fecha_muer);

			    connection.commit();
			}
			input.close();
		    }
		return result;
	    });

		// PARA GUIONISTAS FICHERO GUIONISTAS.TXT

    get("/upload_guionistas", (req, res) -> 
    "<form action='/upload5' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_guionistas_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    post("/upload5", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_guionistas_file").getInputStream()) { 
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); 
			statement.executeUpdate("drop table if exists guionistas");
			statement.executeUpdate("create table guionistas (id_guionista INT, nombre string, apellido string, fecha_nac string, fecha_muer string, PRIMARY KEY (id_guionista))");

			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {	
				
			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");
			    
			    String id_gui = tokenizer.nextToken();

			    String nombre_apellido = tokenizer.nextToken();
				
			    StringTokenizer token2 = new StringTokenizer(nombre_apellido, " ");

			    String nombre = token2.nextToken();

			    String apellido = token2.nextToken();

 			    String fecha_nac = tokenizer.nextToken();

 			    String fecha_muer = tokenizer.nextToken();

			    insert_guio(connection, id_gui, nombre, apellido, fecha_nac, fecha_muer);

			    connection.commit();
			}
			input.close();
		    }
		return result;
	    });
    
    get("/upload_escriben", (req, res) -> 
    "<form action='/upload6' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_escriben_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    
    post("/upload6", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_escriben_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists escriben");
			statement.executeUpdate("create table escriben (id_pelicula INT, id_guionista INT, PRIMARY KEY (id_pelicula, id_guionista), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY id_guionista REFERENCES guionistas (id_guionista))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();

			    String id_guionista = tokenizer.nextToken();

			    insert_escriben(connection, id_pel, id_guionista);
			   
			    connection.commit();
			    
			}
			input.close();
		    }
		return result;
	    });
    
    get("/upload_dirigen", (req, res) -> 
    "<form action='/upload7' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_dirigen_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    
    post("/upload7", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_dirigen_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists dirigen");
			statement.executeUpdate("create table dirigen (id_pelicula INT, id_director INT, PRIMARY KEY (id_pelicula, id_director), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY id_director REFERENCES directores (id_director))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();

			    String id_director = tokenizer.nextToken();

			    insert_dirigen(connection, id_pel, id_director);
			   
			    connection.commit();
			    
			}
			input.close();
		    }
		return result;
	    });

	//PARA GENEROS FICHERO GENEROS.TXT

    get("/upload_generos", (req, res) -> 
    "<form action='/upload8' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_generos_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    
    post("/upload8", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_generos_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists generos");
			statement.executeUpdate("create table generos (nombre String, PRIMARY KEY (nombre))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String nombre = tokenizer.nextToken();

			    insert_generos(connection, nombre);
			   
			    connection.commit();
			    
			}
			input.close();
		    }
		return result;
	    });

	//PARA PERTENECEN FICHERO RELACION_PELICULAS_GENEROS.TXT

    get("/upload_pertenecen", (req, res) -> 
    "<form action='/upload9' method='post' enctype='multipart/form-data'>" 
    + "    <input type='file' name='uploaded_pertenecen_file' accept='.txt'>"
    + "    <button>Upload file</button>" + "</form>");
    
    
    post("/upload9", (req, res) -> {
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_pertenecen_file").getInputStream()) { 
		
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists pertenecen");
			statement.executeUpdate("create table pertenecen (id_pelicula INT, nombre String, PRIMARY KEY (id_pelicula,nombre), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY nombre REFERENCES generos (nombre))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			while ((s = br.readLine()) != null) {

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id = tokenizer.nextToken();

			    String nombre = tokenizer.nextToken();

			    insert_pertenecen(connection, id, nombre);
			   
			    connection.commit();
			    
			}
			input.close();
		    }
		return result;
	    });

	get("/*", (req, res) -> 
	    "<form action='/welcome' method='get' enctype='multipart/form-data'>" 
	    + "</form>");
    }
    
    static int getHerokuAssignedPort() {
	ProcessBuilder processBuilder = new ProcessBuilder();
	if (processBuilder.environment().get("PORT") != null) {
	    return Integer.parseInt(processBuilder.environment().get("PORT"));
	}
	return 4567;
    }
  }
