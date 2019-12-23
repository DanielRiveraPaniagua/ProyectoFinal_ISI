package urjc.isi.dao.implementaciones;

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
		try {
			return DriverManager.getConnection("jdbc:sqlite:sample.db");
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
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
