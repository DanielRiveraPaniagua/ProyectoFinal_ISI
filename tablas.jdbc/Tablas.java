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
    
    public static void insert_pel(Connection conn, String id, String nombre, String fecha, String duracion, String rating) {
	String sql = "INSERT INTO peliculas(id_pelicula, nombre, fecha, duracion, rating) VALUES(?,?,?,?,?)";

	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setString(1, id);
		pstmt.setString(2, nombre);
		pstmt.setString(3, fecha);
		pstmt.setString(4, duracion);
		pstmt.setString(5, rating);
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
    	String sql = "INSERT INTO trabajan (id_pelicula, id_guionista) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, id_guionista);
    		pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	}
     }
    
    public static void insert_dirigen(Connection conn, String id_pelicula, String id_director) {
    	String sql = "INSERT INTO trabajan (id_pelicula, id_director) VALUES(?,?)";

    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id_pelicula);
    		pstmt.setString(2, id_director);
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

	
	get("/:table/:param", tablas::doSelect);
	
	get("/:tabla1/:tabla2/:param", tablas::doSelectTwoTables);


   		// PARA PELICULAS FICHERO PELICULAS_COMPLETO.TXT
	
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

			    String id = tokenizer.nextToken();

			    String nombre = tokenizer.nextToken();

 			    String fecha = tokenizer.nextToken();

 			    String duracion = tokenizer.nextToken();

 			    String rating = tokenizer.nextToken();

			    insert_pel(connection, id, nombre, fecha, duracion, rating);
			   
			    connection.commit();			    
			}
			input.close();
		    }
		return result;
	    });
      
		// PARA ACTORES FICHERO ACTORS.TXT

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
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;
				
			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");
			    
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

		// PARA TRABAJAN FICHERO RELACION_ID_PELI_ACTOR.TXT

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
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;

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
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;
				
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
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;
				
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
			statement.executeUpdate("drop table if exists trabajan");
			statement.executeUpdate("create table trabajan (id_pelicula INT, id_actor INT, PRIMARY KEY (id_pelicula, id_guionista), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY id_guionista REFERENCES guionistas (id_guionista))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();

			    String id_guionista = tokenizer.nextToken();

			    insert_trabajan(connection, id_pel, id_guionista);
			   
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
			statement.executeUpdate("drop table if exists trabajan");
			statement.executeUpdate("create table trabajan (id_pelicula INT, id_actor INT, PRIMARY KEY (id_pelicula, id_director), FOREIGN KEY id_pelicula REFERENCES peliculas (id_pelicula), FOREIGN KEY id_director REFERENCES directores (id_director))");


			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			String s;
			int iter = 1;
			while ((s = br.readLine()) != null && iter <= 500) {
				iter++;

			    StringTokenizer tokenizer = new StringTokenizer(s, "/t");

			    String id_pel = tokenizer.nextToken();

			    String id_director = tokenizer.nextToken();

			    insert_trabajan(connection, id_pel, id_director);
			   
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