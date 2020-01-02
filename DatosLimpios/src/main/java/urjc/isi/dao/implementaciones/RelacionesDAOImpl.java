package urjc.isi.dao.implementaciones;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import urjc.isi.dao.interfaces.RelacionesDAO;

public abstract class RelacionesDAOImpl<T> implements RelacionesDAO<T>{
	protected Connection c;

	public RelacionesDAOImpl(){
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
}
