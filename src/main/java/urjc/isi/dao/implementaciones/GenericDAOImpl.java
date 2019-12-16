package urjc.isi.dao.implementaciones;

import java.sql.*;
import urjc.isi.dao.interfaces.GenericDAO;

//Cosasa muy genericas, alguno de los métodos
//se pueden quedar sin implementar, esos son los
//que pueden venir de la interfaz para darles
//caracter de obligatoriedad
public abstract class GenericDAOImpl<T> implements GenericDAO<T>{

	protected Connection c;

	public GenericDAOImpl() {
		this.c = connect();
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
	
	@Override
	public void createTable(String sql) throws SQLException{
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate(sql);
	}
	
	@Override
	public void dropTable(String sql) throws SQLException {
		Statement statement = c.createStatement();
		statement.setQueryTimeout(30);
		statement.executeUpdate(sql);
		//statement.executeUpdate("drop table if exists peliculas");
	}
}
