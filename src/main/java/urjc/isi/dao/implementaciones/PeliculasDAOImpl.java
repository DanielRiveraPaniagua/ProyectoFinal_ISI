package urjc.isi.dao.implementaciones;

import java.sql.*;

import urjc.isi.entidades.Peliculas;
import urjc.isi.dao.interfaces.PeliculasDAO;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

//Aqui se implementan las interfaces própias y
//la genérica puesto que hay métodos que no
//pueden ser genéricos totalmente

//A estos metodos son a los que llamaremos para
//implementar las distintas respuestas para el
//servidor

public class PeliculasDAOImpl extends GenericDAOImpl<Peliculas> implements PeliculasDAO{

	public Peliculas fromResultSet(ResultSet rs) throws  SQLException{
		Peliculas peli = new Peliculas();

		peli.setIdPelicula(rs.getString("idpelicula"));
		peli.setTitulo(rs.getString("titulo"));
		peli.setAño(Integer.valueOf(rs.getString("año")));
		peli.setDuracion(Double.valueOf(rs.getString("duracion")));
		peli.setCalificacion(Integer.valueOf(rs.getString("calificacion")));
		peli.setRating(Double.valueOf(rs.getString("rating")));
		peli.setNVotos(Integer.valueOf(rs.getString("nvotos")));
		return peli;
	}

	@Override
	public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.executeUpdate("create table peliculas (idpelicula text, titulo text,"
				+ " año text, duracion text, calificacion INT, rating Decimal(4,2),"
				+ " nvotos INT, PRIMARY KEY (idpelicula))");
		c.commit();	
	}

	@Override
	public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists peliculas");
		c.commit();
	}

	@Override
	public void insert(Peliculas entity) {
	  	String sql = "INSERT INTO peliculas(idpelicula,titulo,año,duracion,calificacion,rating,nvotos) VALUES(?,?,?,?,?,?,?)";
	
	  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	  		pstmt.setString(1, entity.getIdPelicula());
	  		pstmt.setString(2, entity.getTitulo());
	  		pstmt.setInt(3, entity.getAño());
	  		pstmt.setDouble(4, entity.getDuracion());
	  		pstmt.setInt(5, entity.getCalificacion());
	  		pstmt.setDouble(6, entity.getRating());
	  		pstmt.setInt(7, entity.getNVotos());
	  		pstmt.executeUpdate();
	    } catch (SQLException e) {
	  	    System.out.println(e.getMessage());
	  	}
	}

	@Override
	public void uploadTable(BufferedReader br) throws IOException, SQLException {
		String s;
	    while ((s = br.readLine()) != null) {
	      Peliculas pelicula = new Peliculas(s);
	      insert(pelicula);
	      c.commit();
	    }
	}
	
	@Override
	public Peliculas selectByID (String idpelicula){
		String sql = "SELECT * from peliculas WHERE idpelicula=" + idpelicula;
		Peliculas peli = new Peliculas();
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			peli = fromResultSet(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return peli;
	}
	
	@Override
	public void deleteByID(String idpelicula){
		String sql = "DELETE from peliculas WHERE idpelicula=" + idpelicula;
		try (PreparedStatement pstmt = c.prepareStatement(sql)){
			pstmt.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Peliculas> selectAll(){
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}
	
	@Override
	public List<Peliculas> selectAll(Dictionary<String,String> conditions){
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p ";
		String cond = "WHERE ";
		for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
			switch(k.nextElement()) {
				case "actor":
					sql+="Inner join peliculasactores as pa on p.idpelicula=pa.idpelicula " +
					     "Inner join actores as a on pa.idpersona=a.idpersona ";
					cond+= "a.fullnombre LIKE "+"'"+conditions.get("actor")+"'";
					break;
				case "director":
					sql+="Inner join peliculasdirectores as pd on p.idpelicula=pd.idpelicula " +
						 "Inner join directores as d on pd.idpersona=d.idpersona ";
					cond+= "d.fullnombre LIKE "+"'"+conditions.get("director")+"'";
					break;
				case "guionista":
					sql+="Inner join peliculasguionistas as pg on p.idpelicula=pg.idpelicula " +
						 "Inner join guionistas as g on pg.idpersona=g.idpersona ";
					cond+= "g.fullnombre LIKE "+"'"+conditions.get("guionistas")+"'";
					break;
				case "duracion":
					cond+= "p.duracion>"+"'"+conditions.get("duracion")+"'";
					break;
			}
			if(k.hasMoreElements()) {
				cond+=" AND ";
			}
		}
		try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}
	
	@Override
	public List<Peliculas> selectBest10(){
		List<Peliculas> bestList = new ArrayList<>();
		String sql = "SELECT * from peliculas ORDER BY rating DESC LIMIT 10";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				bestList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bestList;
	}
	

	@Override
	public List<Peliculas> selectByRanking(Dictionary<String,String> conditions){
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p ";
		String cond = "WHERE ";
		for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
			switch(k.nextElement()) {
				case "actor":
					sql+="Inner join peliculasactores as pa on p.idpelicula=pa.idpelicula " +
						"Inner join actores as a on pa.idpersona=a.idpersona";
					cond+= "a.fullnombre LIKE "+"'"+conditions.get("actor")+"'";
					break;
				case "director":
					sql+="Inner join peliculasdirectores as pd on p.idpelicula=pd.idpelicula " +
						"Inner join directores as d on pd.idpersona=d.idpersona ";
					cond+= "d.fullnombre LIKE "+"'"+conditions.get("director")+"'";
					break;
				case "guionista":
					sql+="Inner join peliculasguionistas as pg on p.idpelicula=pg.idpelicula " +
						 "Inner join guionistas as g on pg.idpersona=g.idpersona ";
					cond+= "g.fullnombre LIKE "+"'"+conditions.get("guionistas")+"'";
					break;
				/**case "genero":
					cond+= "p.duracion>"+"'"+conditions.get("duracion")+"'";
					break;**/
			}
			if(k.hasMoreElements()) {
				cond+=" AND ";
			}
		}
		try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}
	
	
	/**@Override
	public List<Peliculas> selectRankingWhereActor(String name) {
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p " +
				"Inner join peliculasactores as pa on p.idpelicula=pa.idpelicula " +
				"Inner join actores as a on pa.idpersona=a.idpersona "+
				"where a.fullnombre="+"'"+name+"'" + 
				"ORDER BY p.rating DESC";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}
	
	@Override
	public List<Peliculas> selectRankingWhereDirector(String name) {
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p " +
				  "Inner join peliculasdirectores as pd on p.idpelicula=pd.idpelicula " +
				  "Inner join directores as d on pd.idpersona=d.idpersona "+
				  "where d.fullnombre="+"'"+name+"'" + 
				  "ORDER BY p.rating DESC";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
		
	}
	@Override
	public List<Peliculas> selectRankingWhereGuionista(String name) {
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p " +
				  "Inner join peliculasguionistas as pg on p.idpelicula=pg.idpelicula " +
				  "Inner join guionistas as g on pg.idpersona=g.idpersona "+
				  "where g.fullnombre="+"'"+name+"'" + 
				  "ORDER BY p.rating DESC";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				filmList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
		
	}
	@Override
	public List<Peliculas> selectRankingWhereGenero(String genero) {
		List<Peliculas> filmList = new ArrayList<>();
		return filmList;
		
	}**/
	
	@Override
	public List<Peliculas> selectPeliculasForAdultos(){
		List<Peliculas> adultList = new ArrayList<>();
		String sql = "SELECT * from peliculas  where calificacion::INTEGER = 1";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				adultList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return adultList;
	}
	
	@Override
	public List<Peliculas> selectPeliculasForNinos(){
		List<Peliculas> ninosList = new ArrayList<>();
		String sql = "SELECT * from peliculas where calificacion::INTEGER = 0";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				ninosList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ninosList;
	}
	
	@Override
	public List<Peliculas> selectCalificacionForPelicula(String name){
		List<Peliculas> calificacionList = new ArrayList<>();
		String sql = "SELECT * from peliculas where titulo="+"'"+name+"'";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				calificacionList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return calificacionList;
	}
}
