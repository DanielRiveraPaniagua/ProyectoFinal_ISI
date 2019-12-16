package urjc.etsit.isi.InterfacesDAOImpl;

import static spark.Spark.*;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.StringTokenizer;
import javax.servlet.MultipartConfigElement;
import java.io.*;

public class PeliculasDAOImpl extends GenericDAOImpl implements InterfacePeliculasDAO{

	public PeliculasDAO getPeliculabyID(int id){
		String query = "SELECT * FROM peliculas WHERE id_pelicula=" + id;
		GenericDAOImpl generic = new GenericDAOImpl();
		Connection conn = generic.connect();
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			ResultSet rs = pstmt.executeQuery();
			connection.commit(); // ESTE CONECTION NO SE SI FUNCIONARIA
			while(rs.next()){
				PeliculasDAO Pelicula = new PeliculasDAO();

				Pelicula.setId(rs.getInt("id_pelicula"));
				Pelicula.setNombre(rs.getString("nombre"));
				Pelicula.setAnno(rs.getDate("fecha"));
				Pelicula.setDuracion(rs.getDouble("duracion"));
				Pelicula.setRating(rs.getDouble("rating"));

				return Pelicula;
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean insertPelicula(PeliculasDAO pelicula){
		String query = "INSERT INTO peliculas(id_pelicula, nombre, fecha, duracion, rating) VALUES(?,?,?,?,?)";
		GenericDAOImpl generic = new GenericDAOImpl();
		Connection conn = generic.connect();
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setInt(1, pelicula.getId());
			pstmt.setString(2, pelicula.getNombre());
			pstmt.setDate(3, pelicula.getAnno());
			pstmt.setDouble(4, pelicula.getDuracion());
			pstmt.setDouble(5, pelicula.getRating());

			pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
}
