package urjc.isi.dao.implementaciones;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import urjc.isi.dao.interfaces.GenericDAO;

//Aquí se implementan los métodso que son totalmente
//genéricos a todas las clases y el constructor genérico
//de las dao (se usa por defecto, hay ejemplos en el service)
public abstract class GenericDAOImpl<T> implements GenericDAO<T>{

	protected Connection c;

	public GenericDAOImpl(){
		this.c = connect();
		try {
			c.setAutoCommit(false);
		}catch(SQLException e) {
	  	    System.out.println(e.getMessage());
		}
	}
	
	@Override
	public Connection connect() {
		URI dbUri;
		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		return DriverManager.getConnection(dbUrl, username, password);
		} catch (URISyntaxException | SQLException e) {
			throw new RuntimeException(e);
		}
		/*try {
			return DriverManager.getConnection("jdbc:sqlite:sample.db");
		} catch (SQLException e){
			throw new RuntimeException(e);
		}*/
	}
	
	@Override
	public void close() {
		try {
			c.close();
	  	}catch (SQLException e) {
	  		throw new RuntimeException(e);
	  	}
	}
	
	@Override
	  public Boolean tableExists(String table) throws SQLException {
		  DatabaseMetaData dbm = c.getMetaData();
		  ResultSet tables = dbm.getTables(null, null, table, null);
		  if (tables.next()) {
			  return true;
		  } else {
			  return false;
		  }
	  }
}
