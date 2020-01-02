package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Generos;
import urjc.isi.dao.interfaces.GenerosDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

//Aqui se implementan las interfaces própias y
//la genérica puesto que hay métodos que no
//pueden ser genéricos totalmente

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor
public class GenerosDAOImpl extends GenericDAOImpl<Generos> implements GenerosDAO{

  public Generos fromResultSet(ResultSet rs) throws  SQLException{
		Generos gene = new Generos();

		gene.setNombre(rs.getString("nombre"));
		return gene;
	}
	@Override
  public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.executeUpdate("create table generos ( nombre text, PRIMARY KEY (nombre))");
		c.commit();	
	}
	@Override
  public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists generos");
		c.commit();
	}
  @Override
  public void insert(Generos entity) {
  	String sql = "INSERT INTO generos(nombre) VALUES(?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setString(1, entity.getNombre());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }
  @Override
  public void uploadTable(BufferedReader br) throws IOException, SQLException {
    String s;
    while ((s = br.readLine()) != null) {
      Generos genero = new Generos(s);
      insert(genero);
      c.commit();
    }
  }
  
  @Override
  public List<Generos> selectAll(){
	  List<Generos> generoList = new ArrayList<>();
	  String sql = "SELECT * from generos";
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  while(rs.next()){
			  generoList.add(fromResultSet(rs));
		  }
    } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return generoList;
  }
  @Override
  public Generos selectByID (String idgenero){
	  return null;
  }
  @Override
  public void deleteByID(String idgenero){
	  ;
  }
  
  @Override
	public Generos selectByName(String nombre) {
		 String sql = "SELECT * from generos WHERE nombre=" + nombre;
		  Generos genero = new Generos();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  genero = fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return genero;
	}
}
