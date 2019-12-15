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
  public void uploadTable(BufferedReader br, Connection c) throws IOException, SQLException;
  public void dropTable(Connection c) throws SQLException;
  public Boolean tableExists(Connection c) throws SQLException;
  public void insert(Connection c, T entity);
  public List<T> selectAll();
  public T selectByID(Connection c, String idpelicula);
  // deleteByID;
}
