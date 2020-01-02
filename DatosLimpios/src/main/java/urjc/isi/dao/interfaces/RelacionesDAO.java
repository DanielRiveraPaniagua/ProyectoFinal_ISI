package urjc.isi.dao.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface RelacionesDAO<T>{
	 /**
	  * Crea la conexion con la base de datos
	  * @return Una nueva conexion con la base de datos
	  */
	public Connection connect();
	
	/**
	 * Cierra la conexion con la base de datos
	 */
	public void close();
	
	public void createTable() throws SQLException;
	public void dropTable() throws SQLException;
	public void insert(T entity);
	public void uploadTable(BufferedReader br) throws IOException, SQLException;
}
