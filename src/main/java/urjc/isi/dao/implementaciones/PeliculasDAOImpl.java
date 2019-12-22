package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Peliculas;
import urjc.isi.dao.interfaces.PeliculasDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

//Aqui se implementan las interfaces própias y
//la genérica puesto que hay métodos que no
//pueden ser genéricos totalmente

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor
public class PeliculasDAOImpl extends GenericDAOImpl<Peliculas> implements PeliculasDAO{

  public Peliculas fromResultSet(ResultSet rs) throws  SQLException{
		Peliculas peli = new Peliculas();

		peli.setIdPelicula(Integer.valueOf(rs.getString("idpelicula")));
		peli.setTitulo(rs.getString("titulo"));
		peli.setAño(Integer.valueOf(rs.getString("año")));
		peli.setDuracion(Double.valueOf(rs.getString("duracion")));
		peli.setRating(Double.valueOf(rs.getString("rating")));
		peli.setNVotos(Integer.valueOf(rs.getString("nvotos")));
		return peli;
	}
	@Override
  public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("create table peliculas (idpelicula INT, titulo string, año string, duracion string, rating INT, nvotos INT, PRIMARY KEY (idpelicula))");
	}
	@Override
  public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("drop table if exists peliculas");
	}
  @Override
  public void insert(Peliculas entity) {
  	String sql = "INSERT INTO peliculas(idpelicula,titulo,año,duracion,rating,nvotos) VALUES(?,?,?,?,?,?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setInt(1, entity.getIdPelicula());
  		pstmt.setString(2, entity.getTitulo());
  		pstmt.setInt(3, entity.getAño());
      	pstmt.setDouble(4, entity.getDuracion());
      	pstmt.setDouble(5, entity.getRating());
      	pstmt.setInt(6, entity.getNVotos());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }
  @Override
  public void uploadTable(BufferedReader br) throws IOException, SQLException {
    String s;
    while ((s = br.readLine()) != null) {
      Peliculas pelicula = new Peliculas(s);
      insert(pelicula);
      c.commit();
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
			  filmList.add(fromResultSet(rs));
		  }
    } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return filmList;
  }
  @Override
  public Peliculas selectByID (String idpelicula){
	  String sql = "SELECT * from peliculas WHERE idpelicula=" + idpelicula;
	  Peliculas peli = new Peliculas();
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  peli = fromResultSet(rs);
      } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return peli;
  }
  @Override
  public void deleteByID(String idpelicula){
	  String sql = "DELETE from peliculas WHERE idpelicula=" + idpelicula;
	  try (PreparedStatement pstmt = c.prepareStatement(sql)){
		  pstmt.executeUpdate();
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
  }
}