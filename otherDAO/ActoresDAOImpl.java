package urjc.etsit.isi.InterfacesDAOImpl;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.StringTokenizer;
import javax.servlet.MultipartConfigElement;
import java.io.*;

public class ActoresDAOImpl extends GenericDAOImpl implements InterfaceActoresDAO{

	public ActoresDAO getActorbyID(int id){
		String query = "SELECT * FROM actores WHERE id_actor=" + id;
		GenericDAOImpl generic = new GenericDAOImpl();
		Connection conn = generic.connect();
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			ResultSet rs = pstmt.executeQuery();
			connection.commit(); // ESTE CONECTION NO SE SI FUNCIONARIA
			while(rs.next()){
				ActoresDAO Actor = new ActoresDAO();

				Actor.setId(rs.getInt("id_actor"));
				Actor.setNombre(rs.getString("nombre"));
				Actor.setApellido(rs.getString("apellido"));
				Actor.setNacimiento(rs.getDate("fnacimiento"));
				Actor.setMuerte(rs.getDate("fmuerte"));
				Actor.setPaisOrigen(rs.getString("pais"));

				return Actor;
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}

	public boolean insertActor(ActoresDAO actor){
		String query = "INSERT INTO actores(id_actor, nombre, apellido, fnacimiento, fmuerte, pais) VALUES(?,?,?,?,?,?)";
		GenericDAOImpl generic = new GenericDAOImpl();
		Connection conn = generic.connect();
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setInt(1, actor.getId());
			pstmt.setString(2, actor.getNombre());
			pstmt.setString(3, actor.getApellido());
			pstmt.setDate(4, actor.getNacimiento());
			pstmt.setDate(5, actor.getMuerte());
			pstmt.setString(6, actor.getPaisOrigen());

			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}

	}
}
