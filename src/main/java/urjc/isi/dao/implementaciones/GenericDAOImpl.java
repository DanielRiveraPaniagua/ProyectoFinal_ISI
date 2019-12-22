package urjc.isi.dao.implementaciones;

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
