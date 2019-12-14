package urjc.isi.interfacesdao;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos que consideremos esenciales
//propongo los siguientes
//Falta mucho que implementar por aquí,...,mucho.
public interface GenericDAOInterface{
  public void uploadTable(BufferedReader br, Connection c) throws IOException, SQLException;
  public void dropTable(Connection c) throws SQLException;
  public Boolean tableExists(Connection c) throws SQLException;
  // insert; Completado
  // selectAll; Completado
  // selectByID;
  // deleteByID;
}
