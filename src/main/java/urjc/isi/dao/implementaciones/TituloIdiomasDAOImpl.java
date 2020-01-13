package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.TituloIdiomas;
import urjc.isi.dao.interfaces.TituloIdiomasDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

//Aqui se implementan las interfaces própias y
//la genérica puesto que hay métodos que no
//pueden ser genéricos totalmente

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor
public class TituloIdiomasDAOImpl extends GenericDAOImpl<TituloIdiomas> implements TituloIdiomasDAO{

  public TituloIdiomas fromResultSet(ResultSet rs) throws  SQLException{
	  TituloIdiomas ti = new TituloIdiomas();

	    ti.setId(Integer.valueOf(rs.getString("id")));
		ti.setIdPelicula(rs.getString("idpelicula"));
		ti.setTitulo(rs.getString("titulo"));
		ti.setIdioma(rs.getString("idioma"));
		ti.setIsDefault(Integer.valueOf(rs.getString("isdefault")));
		return ti;
	}
	@Override
  public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.executeUpdate("create table tituloidiomas ( INT id, idpelicula text, titulo text, idioma text, isdefault INT, PRIMARY KEY (id))");
		c.commit();	
	}
	@Override
  public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists tituloidiomas");
		c.commit();
	}
  @Override
  public void insert(TituloIdiomas entity) {
  	String sql = "INSERT INTO tituloidiomas(id, idpeliculas, titulo, idioma, isdefault) VALUES(?)";

  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  		pstmt.setInt(1, entity.getId());
  		pstmt.setString(2, entity.getIdPelicula());
  		pstmt.setString(3, entity.getTitulo());
  		pstmt.setString(4, entity.getIdioma());
  		pstmt.setInt(5, entity.getIsDefault());
  		pstmt.executeUpdate();
    } catch (SQLException e) {
  	    System.out.println(e.getMessage());
  	}
  }
  @Override
  public void uploadTable(BufferedReader br) throws IOException, SQLException {
    String s;
    while ((s = br.readLine()) != null) {
    	TituloIdiomas tituloidioma = new TituloIdiomas(s);
      insert(tituloidioma);
      c.commit();
    }
  }
  
  @Override
  public List<TituloIdiomas> selectAll(){
	  List<TituloIdiomas> tituloIdiomaList = new ArrayList<>();
	  String sql = "SELECT * from tituloidiomas";
	  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
		  ResultSet rs = pstmt.executeQuery();
		  c.commit();
		  while(rs.next()){
			  tituloIdiomaList.add(fromResultSet(rs));
		  }
    } catch (SQLException e) {
		  System.out.println(e.getMessage());
	  }
	  return tituloIdiomaList;
  }
  @Override
  public TituloIdiomas selectByID (String id){
	  return null;
  }
  @Override
  public void deleteByID(String id){
	  ;
  }
}
