package urjc.isi.interfacesdao;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
//Con esta interfaz obligamos a que se implmenten
//ciertos métodos que consideremos esenciales
//propongo los siguientes
//Falta mucho que implementar por aquí,...,mucho.
public interface GenericDAOInterface{
  public void uploadTable(BufferedReader br,Connection c) throws IOException, SQLException;
  // dropTable;
  // insert;
  // selectAll;
  // selectByID;
  // deleteByID;
}
