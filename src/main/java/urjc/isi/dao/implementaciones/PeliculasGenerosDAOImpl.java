package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Relaciones;

import java.io.BufferedReader;
import java.io.IOException;

public class PeliculasGenerosDAOImpl extends RelacionesDAOImpl<Relaciones>{
	  public Relaciones fromResultSet(ResultSet rs) throws  SQLException{
		  	Relaciones relacionesgene = new Relaciones();
			
		  	relacionesgene.setIdtabla1(rs.getString("id_pelicula"));
			relacionesgene.setIdtabla2(rs.getString("genero"));
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
	  public void insert(Relaciones entity) {
	  	String sql = "INSERT INTO peliculasgeneros(id_pelicula,genero) VALUES(?,?)";

	  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	  		pstmt.setString(1, entity.getIdtabla1());
	  		pstmt.setString(2, entity.getIdtabla2());
	  		pstmt.executeUpdate();
	    } catch (SQLException e) {
	  	    System.out.println(e.getMessage());
	  	}
	  }
	  @Override
	  public void uploadTable(BufferedReader br) throws IOException, SQLException {
	    String s;
	    while ((s = br.readLine()) != null) {
	    	Relaciones genero = new Relaciones(s);
	      insert(genero);
	      c.commit();
	    }
	  }


	}
