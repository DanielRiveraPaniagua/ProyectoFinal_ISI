package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.PeliculasPersonas;
import urjc.isi.dao.interfaces.PeliculasPersonasDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

public class PeliculasActoresDAOImpl extends GenericDAOImpl<PeliculasPersonas> implements PeliculasPersonasDAO{

  public PeliculasPersonas fromResultSet(ResultSet rs) throws  SQLException{
		PeliculasPersonas pp = new PeliculasPersonas();

		pp.setIdPelicula(Integer.valueOf(rs.getString("idpelicula")));
		pp.setIdPersona(Integer.valueOf(rs.getString("idpersona")));
		return pp;
  }
  @Override
  public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("create table peliculasactores (idpelicula INT, idpersona INT, PRIMARY KEY (idpelicula,idpersona))");
	}

  public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("drop table if exists peliculasactores");
	}
  @Override
  public void insert(PeliculasPersonas entity) {
  	String sql = "INSERT INTO peliculasactores(idpelicula,idpersona) VALUES(?,?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setInt(1, entity.getIdPelicula());
  		pstmt.setInt(2, entity.getIdPersona());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }
  @Override
  public void uploadTable(BufferedReader br) throws IOException, SQLException {
    String s;
    while ((s = br.readLine()) != null) {
      PeliculasPersonas pp = new PeliculasPersonas(s);
      insert(pp);
      c.commit();
    }
  }
  @Override
  public List<PeliculasPersonas> selectAll(){
	  List<PeliculasPersonas> ppList = new ArrayList<>();
	  String sql = "SELECT * from peliculasactores";
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  while(rs.next()){
			  ppList.add(fromResultSet(rs));
		  }
    } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return ppList;
  }
  @Override
  public PeliculasPersonas selectByID (String idpelicula){ // Selecciona peliculas
	  String sql = "SELECT * from peliculasactores WHERE idpelicula=" + idpelicula;
	  PeliculasPersonas pp = new PeliculasPersonas();
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  pp = fromResultSet(rs);
      } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return pp;
  }
  @Override
  public void deleteByID(String idpelicula){
	  String sql = "DELETE from peliculasactores WHERE idpelicula=" + idpelicula;
	  try (PreparedStatement pstmt = c.prepareStatement(sql)){
		  pstmt.executeUpdate();
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
  }
  @Override
  public PeliculasPersonas selectByIDPersona (String idpersona){
	  String sql = "SELECT * from peliculasactores WHERE idpersona=" + idpersona;
	  PeliculasPersonas pp = new PeliculasPersonas();
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  pp = fromResultSet(rs);
      } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return pp;
  }
  @Override
  public void deleteByIDPersona(String idpersona){
	  String sql = "DELETE from peliculasactores WHERE idpersona=" + idpersona;
	  try (PreparedStatement pstmt = c.prepareStatement(sql)){
		  pstmt.executeUpdate();
	  } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
  }
}
