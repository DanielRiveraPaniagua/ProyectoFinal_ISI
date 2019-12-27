package urjc.isi.dao.interfaces;

import java.sql.*;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos que consideremos esenciales para
//todas las clases

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
	public void createTable() throws SQLException;

	/**
	 * Borra una tabla cualquiera
	 * @param Consulta que permite borrar la tabla
	 * @throws SQLException
	 */
	public void dropTable() throws SQLException;

	/**
	 * Inserta registros en una tabla cualquiera
	 * @param br
	 * @throws IOException
	 * @throws SQLException
	 */
	public void uploadTable(BufferedReader br) throws IOException, SQLException;

	/**
	 * Comprueba si una tabla existe en la base de datos
	 * @param Nombre de la tabla a comprobar
	 * @return true si la tabla existe
	 * @throws SQLException
	 */
	public Boolean tableExists(String table) throws SQLException;

	/**
	 * Inserta en su tabla correspondiente una entidad de tipo generico
	 * @param entity
	 */
	public void insert(T entity);

	/**
	 * Selecciona todos los registros de una tabla cualquiera
	 * @return
	 */
	public List<T> selectAll();

	/**
	 * Selecciona un registro de cualquier tabla pasandole su id como parametro
	 * @param id
	 * @return El registro que se asocia con esa clave primaria
	 */
	public T selectByID(String id);

	/**
	 * Elimina un registro de cualquier tabla pasandole su id como parametro
	 * @param id
	 */
	public void deleteByID(String id);
}
