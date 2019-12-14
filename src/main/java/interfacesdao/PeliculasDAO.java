package urjc.isi.interfacesdao;

import java.sql.*;
import java.sql.Statement;
import java.sql.PreparedStatement;

import urjc.isi.entidades.Peliculas;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.MultipartConfigElement;

//Aqui vienen las cosas propias de cada tabla,
//es decir, las queries o cosas que no se
//puedan implementar de manera genérica

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor
public class PeliculasDAO extends GenericDAO{
  //Para meterlo como parte de interfaz hay que encontrar comodefinir que detecte
  // Peliculas como un objeto cualquiera, es decir que obligue a rellenar eso con
  //, por ejemplo, algo que extienda de Entidades
  public static void insert(Connection c, Peliculas pelicula) {
  	String sql = "INSERT INTO peliculas(idpelicula,titulo,año,duracion,rating,nvotos) VALUES(?,?,?,?,?,?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setInt(1, pelicula.getIdPelicula());
  		pstmt.setString(2, pelicula.getTitulo());
      pstmt.setInt(3, pelicula.getAño());
      pstmt.setDouble(4, pelicula.getDuracion());
      pstmt.setDouble(5, pelicula.getRating());
      pstmt.setInt(6, pelicula.getNVotos());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }

  @Override
  public void uploadTable(BufferedReader br, Connection c) throws IOException,SQLException {
    String s;
    while ((s = br.readLine()) != null) {
			    Peliculas pelicula = new Peliculas(s);
				insert(c, pelicula);
			    c.commit();
	}
  }

  @Override
  public void dropTable(Connection c) throws SQLException {
	  Statement statement = c.createStatement();
	  statement.setQueryTimeout(30);
	  statement.executeUpdate("drop table if exists peliculas");
  }

  @Override
  public Boolean tableExists(Connection c) throws SQLException {
	  DatabaseMetaData dbm = c.getMetaData();
	  ResultSet tables = dbm.getTables(null, null, "peliculas", null);
	  if (tables.next()) {
		  // La tabla existe
		  return true;
	  } else {
		  // No existe la tabla
		  return false;
	  }
  }
}
