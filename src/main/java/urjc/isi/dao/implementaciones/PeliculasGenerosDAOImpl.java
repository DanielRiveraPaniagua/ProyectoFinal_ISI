package urjc.isi.dao.implementaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import urjc.isi.dao.interfaces.GenerosDAO;
import urjc.isi.dao.interfaces.RelacionesDAO;
import urjc.isi.dao.interfaces.RelacionesGenerosDAO;
import urjc.isi.entidades.Generos;
import urjc.isi.entidades.Peliculas;
import urjc.isi.entidades.RelacionesGeneros;

import java.io.BufferedReader;
import java.io.IOException;

public class PeliculasGenerosDAOImpl extends RelacionesDAOImpl<RelacionesGeneros> implements RelacionesGenerosDAO{
	  public RelacionesGeneros fromResultSet(ResultSet rs) throws  SQLException{
		  	RelacionesGeneros relacionesgene = new RelacionesGeneros();
			
		  	relacionesgene.setIdPelicula(rs.getString("id_pelicula"));
			relacionesgene.setGenero(rs.getString("genero"));
			return relacionesgene;
		}
		@Override
	  public void createTable() throws SQLException{
			Statement statement = c.createStatement();
			statement.executeUpdate("create table peliculasgeneros ( id_pelicula text, genero text, PRIMARY KEY (id_pelicula,genero))");
			c.commit();	
		}
		@Override
	  public void dropTable() throws SQLException {
			Statement statement = c.createStatement();
			statement.executeUpdate("drop table if exists peliculasgeneros");
			c.commit();
		}
	  @Override
	  public void insert(RelacionesGeneros entity) {
	  	String sql = "INSERT INTO peliculasgeneros(id_pelicula,genero) VALUES(?,?)";

	  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	  		pstmt.setString(1, entity.getIdPelicula());
	  		pstmt.setString(2, entity.getGenero());
	  		pstmt.executeUpdate();
	    } catch (SQLException e) {
	  	    System.out.println(e.getMessage());
	  	}
	  }
	  @Override
	  public void uploadTable(BufferedReader br) throws IOException, SQLException {
	    String s;
	    while ((s = br.readLine()) != null) {
	    	RelacionesGeneros genero = new RelacionesGeneros(s);
	      insert(genero);
	      c.commit();
	    }
	  }
	  
	  @Override
	  public List<RelacionesGeneros> selectAll(){
		  List<RelacionesGeneros> generoList = new ArrayList<>();
		  String sql = "SELECT * from peliculasgeneros";
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
	  public RelacionesGeneros selectByID(String id) {
		  // TODO Auto-generated method stub
		  return null;
	  }
	  @Override
	  public void deleteByID(String id) {
		  // TODO Auto-generated method stub

	  }
	  @Override
	  public Generos selectByName(String nombre) {
		  // TODO Auto-generated method stub
		  return null;
	  }





	}
