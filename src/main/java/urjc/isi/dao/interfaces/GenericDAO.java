package urjc.isi.dao.interfaces;

import java.sql.*;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos que consideremos esenciales
//propongo los siguientes
//Falta mucho que implementar por aquí,...,mucho.

public interface GenericDAO<T>{
	
	/**
	 * Crea un objeto que permite la conexion con la base de datos
	 * @return
	 */
	public Connection connect();
	
	/**
	 * Cierra la conexion con la base de datos de la interfaz
	 */
	public void close(); 
	
	/**
	 * Crea una tabla cualquiera
	 * @param Consulta que permite la creacion de la tabla
	 * @throws SQLException
	 */
	public void createTable(String sql) throws SQLException;
	
	/**
	 * Borra una tabla cualquiera
	 * @param Consulta que permite borrar la tabla
	 * @throws SQLException
	 */
	public void dropTable(String sql) throws SQLException;
	  
	public void uploadTable(BufferedReader br, Connection c) throws IOException, SQLException;
	  
	public Boolean tableExists(Connection c) throws SQLException;
	  
	public void insert(Connection c, T entity);
	  
	public List<T> selectAll();
	  
	public T selectByID(Connection c, String idpelicula);
	  
	// deleteByID;
}
