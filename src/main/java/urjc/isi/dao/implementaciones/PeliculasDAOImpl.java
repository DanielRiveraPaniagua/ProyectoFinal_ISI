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
		peli.setDuracion(Integer.valueOf(rs.getString("duracion")));
		peli.setCalificacion(Integer.valueOf(rs.getString("calificacion")));
		peli.setRating(Double.valueOf(rs.getString("rating")));
		peli.setNVotos(Integer.valueOf(rs.getString("nvotos")));
		return peli;
	}

	@Override
	public void createTable() throws SQLException{
		Statement statement = c.createStatement();
		statement.executeUpdate("create table peliculas (idpelicula text, titulo text,"
				+ " año INT, duracion INT, calificacion INT, rating Decimal(4,2),"
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
	  		pstmt.setInt(4, entity.getDuracion());
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
	    	if(s.length()>0) {
		      Peliculas pelicula = new Peliculas(s);
		      insert(pelicula);
		      c.commit();
	    	}
	    }
	}

	@Override
	public Peliculas selectByID (String idpelicula){
		String sql = "SELECT * from peliculas WHERE idpelicula='" + idpelicula+"'";
		Peliculas peli = new Peliculas();
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			if(rs.next()){
				peli = fromResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return peli;
	}

	@Override
	public List<Peliculas> selectByPersonaID(String type, String id){
		List<Peliculas> pelis = new ArrayList<>();
		String sql = "";
		switch(type) {
			case "actor":
				sql = "SELECT * from peliculas as a "+
						"Inner join peliculasactores as pa on pa.idpelicula=a.idpelicula "+
						"WHERE pa.idpersona='"+id+"'";
				break;
			case "guionista":
				sql = "SELECT * from peliculas as a "+
						"Inner join peliculasguionistas as pa on pa.idpelicula=a.idpelicula "+
						"WHERE pa.idpersona='"+id+"'";
				break;
			case "director":
				sql = "SELECT * from peliculas as a "+
						"Inner join peliculasdirectores as pa on pa.idpelicula=a.idpelicula "+
						"WHERE pa.idpersona='"+id+"'";
				break;
			
		}
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			 ResultSet rs = pstmt.executeQuery();
			 c.commit();
			 while(rs.next()){
				 pelis.add(fromResultSet(rs));
			 }
		 } catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return pelis;
	}

	@Override
	public void deleteByID(String idpelicula){
		String sql = "DELETE from peliculas WHERE idpelicula='" + idpelicula+"'";
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
		String order = " ORDER BY ";
		boolean add_order = false;

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
					cond+= "g.fullnombre LIKE "+"'"+conditions.get("guionista")+"'";
					break;
				case "duracion":
					order += add_order?" ,p.duracion DESC":"p.duracion DESC";
					add_order = true;
					if(conditions.get("duracion").indexOf("<") == 0) {
						cond+= "p.duracion <= "+"'"+conditions.get("duracion").split("<")[1]+"'";
						break;
					}else if(conditions.get("duracion").indexOf(">") == 0){
						cond+= "p.duracion >= "+"'"+conditions.get("duracion").split(">")[1]+"'";
						break;
					}
					if(conditions.get("duracion").indexOf("-") == -1) {
						cond+= "p.duracion = "+"'"+conditions.get("duracion")+"'";
					}else {
						String[] duracion = conditions.get("duracion").split("-");
						cond+= "p.duracion >= " + "'" + duracion[0] + "'" + " and " + "p.duracion <= "+ "'"+ duracion[1] + "'" ;
					}
					break;
				case "adultos":
					if(conditions.get("adultos").equals("si"))
						cond+= "calificacion::INTEGER = 1";
					if(conditions.get("adultos").equals("no"))
						cond+= "calificacion::INTEGER = 0";
				case "titulo":
					if(conditions.get("idioma") == null) {
						cond+= "p.titulo like "+"'"+conditions.get("titulo")+"%'";
					}else {
						cond+="true";
					}

					break;
				case "year":
					if(conditions.get("year").indexOf("-") == -1) {
						cond+= "p.año = "+"'"+conditions.get("year")+"'";
					} else {
						String[] years = conditions.get("year").split("-");
						cond+= "p.año >= " + "'" + years[0] + "'" + " and " + "p.año <= "+ "'"+ years[1] + "'" ;
					}
					break;
				case "order":
					order += add_order?" ,":"";
					add_order = true;
					if(conditions.get("order").contains("-desc")) {
						order += " p." + conditions.get("order").split("-desc")[0] + " desc ";
					}else {
						order += " p." + conditions.get("order");
					}
					cond += "true";
					break;
				case "idioma":
					String titulo = conditions.get("titulo")!=null?conditions.get("titulo"):"";
					sql+= "left join tituloidiomas as t on t.idpelicula=p.idpelicula "+
				            "and idioma='"+conditions.get("idioma")+"' ";
					cond+="(tituloenidioma like '"+titulo+"%' or "+
							"case when tituloenidioma is null "+
							"then titulo like '"+titulo+"%' end)";

					break;
				case "rating":
					if(conditions.get("rating").indexOf("<") == 0) {
						cond+= "p.rating <= "+"'"+conditions.get("rating").split("<")[1]+"'";
						break;
					}else if(conditions.get("rating").indexOf(">") == 0){
						cond+= "p.rating >= "+"'"+conditions.get("rating").split(">")[1]+"'";
						break;
					}
					if(conditions.get("rating").indexOf("-") == -1) {
						cond+= "p.rating = "+"'"+conditions.get("rating")+"'";
					}else {
						String[] rating = conditions.get("rating").split("-");
						cond+= "p.rating >= " + "'" + rating[0] + "'" + " and " + "p.rating <= "+ "'"+ rating[1] + "'" ;
					}

			}
			if(k.hasMoreElements()) {
				cond+=" AND ";
			}
		}
		if(add_order) {
			cond += order;
		}
		System.out.println(sql+cond);
		try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				Peliculas peli = fromResultSet(rs);
				if(conditions.get("idioma") != null && rs.getString("tituloenidioma") != null) {
					peli.setTitulo(rs.getString("tituloenidioma"));
				}
				filmList.add(peli);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}

	@Override
	public List<Peliculas> selectByRanking(){
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
		String cond2 = "ORDER BY p.rating DESC";
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
					cond+= "g.fullnombre LIKE "+"'"+conditions.get("guionista")+"'";
					break;
				/**case "genero":
					cond+= "p.duracion>"+"'"+conditions.get("duracion")+"'";
					break;**/
			}
			if(k.hasMoreElements()) {
				cond+=" AND ";
			}
		}
		try (PreparedStatement pstmt = c.prepareStatement(sql+cond+cond2)) {
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
	public List<Peliculas> selectAllByGenero(String genero) {
	  List<Peliculas> filmList = new ArrayList<>();

		String[] fields = genero.split("&");
		String[] t1 = fields[0].split("=");
		String generos =" pg.genero='" + t1[1] + "'";
		for (int i = 1; i < fields.length; ++i)
		{
		    String[] t = fields[i].split("=");
		    if (2 == t.length)
		    {
		        generos =  generos + " OR " + "pg.genero='" + t[1] + "'";
		    }
		}
	  String sql = "SELECT p.idpelicula, p.titulo , p.año , p.duracion , p.calificacion ,p.rating, p.nvotos from peliculas as p Inner join peliculasgeneros as pg on p.idpelicula=pg.id_pelicula where"  + generos;
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
	public List<Peliculas> selectAllBestorWorstFilmByYear(Dictionary<String,String> conditions){
		List<Peliculas> filmList = new ArrayList<>();
		String sql = "SELECT * from peliculas as p ";
		String cond = "WHERE ";
		String order = "ORDER BY ";

		order += "p.rating DESC LIMIT 1";
		for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
			switch(k.nextElement()) {
				case "year":
					cond += "p.año = "+"'"+conditions.get("year")+"' ";
					break;
				case "score":
					if(conditions.get("score").equals("worst")) {
						order = "ORDER BY p.rating ASC LIMIT 1";
						break;
					}
			}
		}
		cond += order;

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
	public Peliculas selectFilmByTitle (String titulo){
		String sql = "SELECT * from peliculas WHERE titulo= $$" + titulo+"$$";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			if(rs.next()){
				return fromResultSet(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
