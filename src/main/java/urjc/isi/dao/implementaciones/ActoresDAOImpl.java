package urjc.isi.dao.implementaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import urjc.isi.dao.interfaces.PersonasDAO;
import urjc.isi.entidades.Personas;

public class ActoresDAOImpl extends GenericDAOImpl<Personas> implements PersonasDAO {

	public Personas fromResultSet(ResultSet rs) throws  SQLException{
		Personas persona = new Personas();

		persona.setId(rs.getString("idpersona"));
		persona.setFullNombre(rs.getString("fullnombre"));
		persona.setNacimiento(rs.getString("fnacimiento"));
		persona.setMuerte(rs.getString("fmuerte"));
		return persona;
	}

	@Override
	public void createTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("create table actores" + table);
		c.commit();
	}

	@Override
   public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists actores");
		c.commit();
	}

	@Override
	public void insert(Personas entity) {
	 	String sql = "INSERT INTO actores(idpersona,fullnombre,fnacimiento,fmuerte) VALUES(?,?,?,?)";

	  	try (PreparedStatement pstmt = c.prepareStatement(sql)) {
	  		pstmt.setString(1, entity.getId());
	  		pstmt.setString(2, entity.getFullNombre());
	  		pstmt.setString(3, entity.getNacimiento());
	      	pstmt.setString(4, entity.getMuerte());
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
		      Personas persona = new Personas(s);
		      insert(persona);
		      c.commit();
	    	}
	    }
	}

	/*@Override
	public List<Personas> selectAll() {
		List<Personas> personaslist = new ArrayList<>();
		  String sql = "SELECT * from actores";
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  while(rs.next()){
				  personaslist.add(fromResultSet(rs));
			  }
	    } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return personaslist;
	}*/

	@Override
	public List<Actores> selectAll(Dictionary<String,String> conditions){
		List<Actores> actorList = new ArrayList<>();
		String sql = "SELECT * from actores as a ";
		String cond = "WHERE ";
		for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
			switch(k.nextElement()) {
				case "pelicula":
					sql+="Inner join peliculasactores as pa on a.idpersona=pa.idpersona " +
					     "Inner join peliculas as p on pa.idpelicula=p.idpelicula ";
					cond+= "p.titulo LIKE "+"'"+conditions.get("pelicula")+"'";
					break;
				case "fNacimiento": //"duracion":
					cond+= "a.fNacimiento>"+"'"+conditions.get("fnacimiento")+"'";
					break;
				case "fMuerte": //"duracion":
					cond+= "a.fMuerte>"+"'"+conditions.get("fmuerte")+"'";
					break;
				/*case "titulo":
					cond+= "p.titulo like "+"'"+conditions.get("titulo")+"%'";
					break;
				case "year":
					if(conditions.get("year").indexOf("-") == -1) {
						cond+= "p.año = "+"'"+conditions.get("year")+"'";
					} else {
						String[] years = conditions.get("year").split("-");
						cond+= "p.año >= " + "'" + years[0] + "'" + " and " + "p.año <= "+ "'"+ years[1] + "'" ;
					}
					break;*/
			}
			if(k.hasMoreElements()) {
				cond+=" AND ";
			}
		}
		try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			while(rs.next()){
				actorList.add(fromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return filmList;
	}

	@Override
	public Personas selectByID(String idpersona) {
		  String sql = "SELECT * from actores WHERE idpersona=" + idpersona;
		  Personas persona = new Personas();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  persona = fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return persona;
	}

	@Override
	public void deleteByID(String idpersona) {
		  String sql = "DELETE from actores WHERE idpersona=" + idpersona;
		  try (PreparedStatement pstmt = c.prepareStatement(sql)){
			  pstmt.executeUpdate();
			  c.commit();
		  } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
	}

	@Override
	public Personas selectByName(String name) {
		 String sql = "SELECT * from actores WHERE fullnombre=" + name;
		  Personas persona = new Personas();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  persona = fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return persona;
	}

	@Override
	public List<Personas> selectPerByFechaNac(String fecha) {
		 List<Personas> actFechaNac = new ArrayList<>();
		 String sql = "SELECT * from actores WHERE fnacimiento=" + fecha;
		 try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			 ResultSet rs = pstmt.executeQuery();
			 c.commit();
			 while(rs.next()){
				 actFechaNac.add(fromResultSet(rs));
			 }
		 } catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return actFechaNac;
	}

	@Override
	public List<Personas> selectPerMuertas() {
		 List<Personas> actMuertos = new ArrayList<>();
		 String sql = "SELECT * from actores WHERE fmuerte < 2020";
		 try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			 ResultSet rs = pstmt.executeQuery();
			 c.commit();
			 while(rs.next()){
				 actMuertos.add(fromResultSet(rs));
			 }
		 } catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return actMuertos;
	}

	@Override
	public List<Personas> selectPerByIntervaloNac(String fechaIn, String fechaFin) {
		 List<Personas> actFechaInter = new ArrayList<>();
		 String sql = "SELECT * from actores WHERE fnacimiento>" + fechaIn + " AND fnacimiento<" + fechaFin ;
		 try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			 ResultSet rs = pstmt.executeQuery();
			 c.commit();
			 while(rs.next()){
				 actFechaInter.add(fromResultSet(rs));
			 }
		 } catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return actFechaInter;
	}
	public List<Personas> selectByPeliculaID(String id){
		List<Personas> actores = new ArrayList<>();
		String sql = "SELECT * from actores as a "+
					"Inner join peliculasactores as pa on pa.idpersona=a.idpersona "+
					"WHERE pa.idpelicula='"+id+"'";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			 ResultSet rs = pstmt.executeQuery();
			 c.commit();
			 while(rs.next()){
				 actores.add(fromResultSet(rs));
			 }
		 } catch (SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 return actores;
	}

	@Override
	public String selectIDByName (String name){
		String sql = "SELECT idpersona from actores WHERE fullnombre= '" + name+"'";
		String id="";
		try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			c.commit();
			if(rs.next()){
				id = rs.getString("idpersona");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
}
