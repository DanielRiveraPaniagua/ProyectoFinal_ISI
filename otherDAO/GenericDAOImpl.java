package urjc.etsit.isi.InterfacesDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import urjc.etsit.isi.InterfacesDAO.*;
import urjc.etsit.isi.exception.*;

public class GenericDAOImpl implements InterfaceGenericDAO{
	
	private Connection con;
	
	public GenericDAOImpl() {
		con = this.connect();
	}

	public Connection connect() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:sample.db");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}
	
	/*
	public List <? extends Object>  getAll(String tableName) {
		String sql = "SELECT * FROM " + tableName + ';';
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		con.commit();
		while (rs.next()) {
			  int i = rs.getInt("userid");
			  String str = rs.getString("username");

			  //Assuming you have a user object

			  ll.add(user);
			}
		return 
	}
	*/
}
