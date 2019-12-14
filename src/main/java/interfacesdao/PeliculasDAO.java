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
//implentar las distintas respuestas para el
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
  public void uploadTable(BufferedReader br, Connection c)throws IOException,SQLException{
    Peliculas pelicula = new Peliculas();
    String s;
    c.prepareStatement("create table peliculas(idpelicula int,titulo string, año int, duracion double, rating double, nvotos int)");
    while ((s = br.readLine()) != null) {
			    StringTokenizer tokenizer = new StringTokenizer(s,"\t");
          //Probablemente crear constructor que reciba una linea y
          // la tokenize en Peliculas.java
			     pelicula.setIdPelicula(Integer.valueOf(tokenizer.nextToken()));
           pelicula.setTitulo(tokenizer.nextToken());
           pelicula.setAño(Integer.valueOf(tokenizer.nextToken()));
           pelicula.setDuracion(Double.valueOf(tokenizer.nextToken()));
           pelicula.setRating(Double.valueOf(tokenizer.nextToken()));
           pelicula.setNVotos(Integer.valueOf(tokenizer.nextToken()));
				   insert(c, pelicula);
			    c.commit();
			}
  }

}
