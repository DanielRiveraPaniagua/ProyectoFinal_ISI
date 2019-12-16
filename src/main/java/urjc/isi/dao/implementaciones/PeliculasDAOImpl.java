package urjc.isi.dao.implementaciones;

import java.sql.*;
import java.sql.Statement;
import java.sql.PreparedStatement;

import urjc.isi.entidades.Peliculas;
import urjc.isi.dao.interfaces.PeliculasDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

//Aqui vienen las cosas propias de cada tabla,
//es decir, las queries o cosas que no se
//puedan implementar de manera genérica

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor
public class PeliculasDAOImpl extends GenericDAOImpl<Peliculas> implements PeliculasDAO{
  //Para meterlo como parte de interfaz hay que encontrar comodefinir que detecte
  // Peliculas como un objeto cualquiera, es decir que obligue a rellenar eso con
  //, por ejemplo, algo que extienda de Entidades

  @Override
  public void insert(Connection c, Peliculas entity) {
  	String sql = "INSERT INTO peliculas(idpelicula,titulo,año,duracion,rating,nvotos) VALUES(?,?,?,?,?,?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setInt(1, entity.getId());
  		pstmt.setString(2, entity.getNombre());
	    pstmt.setDate(3, entity.getDate());
	    pstmt.setDouble(4, entity.getDuracion());
	    pstmt.setDouble(5, entity.getRating());
	    pstmt.setInt(6, entity.getVotos());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }


  @Override
  public void uploadTable(BufferedReader br, Connection c) throws IOException, SQLException {
    String s;
    while ((s = br.readLine()) != null) {
	    Peliculas pelicula = new Peliculas(s);
	    insert(c, pelicula);
	    c.commit();
    }
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
  @Override
  public List<Peliculas> selectAll(){
	  List<Peliculas> filmList = new ArrayList<>();
	  String sql = "SELECT * from peliculas";
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  while(rs.next()){
			  Peliculas peli = new Peliculas();
			  peli.setId(Integer.parseInt(rs.getString("idpelicula")));
			  peli.setNombre(rs.getString("titulo"));
			  peli.setDate(rs.getDate("año"));
			  peli.setDuracion(Double.parseDouble(rs.getString("duracion")));
			  peli.setRating(Double.parseDouble(rs.getString("rating")));
			  peli.setVotos(Integer.parseInt(rs.getString("nvotos")));
			  // Añado la peli a la lista de pelis
			  filmList.add(peli);
		  }
    } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return filmList;
  }
  @Override
  public Peliculas selectByID (Connection c, String idpelicula){
	  String sql = "SELECT * from peliculas WHERE idpelicula=" + idpelicula;
	  Peliculas peli = new Peliculas();
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  while(rs.next()){
			  peli.setId(Integer.parseInt(rs.getString("idpelicula")));
			  peli.setNombre(rs.getString("titulo"));
			  peli.setDate(rs.getDate("año"));
			  peli.setDuracion(Double.parseDouble(rs.getString("duracion")));
			  peli.setRating(Double.parseDouble(rs.getString("rating")));
			  peli.setVotos(Integer.parseInt(rs.getString("nvotos")));
		  }
      } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return peli;
  }
  @Override
  public void deleteByID(Connection c, String idpelicula){
	  String sql = "DELETE from peliculas WHERE idpelicula=" + idpelicula;
	  try (PreparedStatement pstmt = c.prepareStatement(sql)){
		  pstmt.executeUpdate();
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
  }
}
