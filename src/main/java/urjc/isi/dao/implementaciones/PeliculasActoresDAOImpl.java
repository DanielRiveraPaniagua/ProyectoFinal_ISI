package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Relaciones;

import java.io.BufferedReader;
import java.io.IOException;

public class PeliculasActoresDAOImpl extends RelacionesDAOImpl<Relaciones>{

  	@Override
  	public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("create table peliculasactores (idpelicula INT, idpersona INT, PRIMARY KEY (idpelicula,idpersona))");
	}
  	@Override
  	public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate("drop table if exists peliculasactores");
	}
  	@Override
  	public void insert(Relaciones entity) {
	  String sql = "INSERT INTO peliculasactores(idpelicula,idpersona) VALUES(?,?)";

  		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
  			pstmt.setInt(1, entity.getIdtabla1()); //Peliculas
  			pstmt.setInt(2, entity.getIdtabla2()); //Persona
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
		}
		c.commit();
	}
}
