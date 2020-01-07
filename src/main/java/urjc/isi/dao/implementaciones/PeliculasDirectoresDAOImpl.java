package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Relaciones;

import java.io.BufferedReader;
import java.io.IOException;

public class PeliculasDirectoresDAOImpl extends RelacionesDAOImpl<Relaciones>{

  	@Override
  	public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.executeUpdate("create table peliculasdirectores (idpelicula text, idpersona text, PRIMARY KEY (idpelicula,idpersona))");
		c.commit();
  	}
  	@Override
  	public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists peliculasdirectores");
		c.commit();
  	}
  	@Override
  	public void insert(Relaciones entity) {
	  String sql = "INSERT INTO peliculasdirectores(idpelicula,idpersona) VALUES(?,?)";

  		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  			pstmt.setString(1, entity.getIdtabla1()); //Peliculas
  			pstmt.setString(2, entity.getIdtabla2()); //Persona
  			pstmt.executeUpdate();
  		} catch (SQLException e) {
    		System.out.println(e.getMessage());
  		}
  	}
  	@Override
	public void uploadTable(BufferedReader br) throws IOException, SQLException {
		String s;
		while ((s = br.readLine()) != null) {
			Relaciones pp = new Relaciones(s);
			insert(pp);
			c.commit();
		}
	}
}
