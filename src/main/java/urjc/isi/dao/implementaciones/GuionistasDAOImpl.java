package urjc.isi.dao.implementaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import urjc.isi.dao.interfaces.PersonasDAO;
import urjc.isi.entidades.Personas;

public class GuionistasDAOImpl extends GenericDAOImpl<Personas> implements PersonasDAO {

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
		statement.executeUpdate("create table guionistas" + table);
		c.commit();
	}

	@Override
   public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists guionistas");
		c.commit();
	}

	@Override
	public void insert(Personas entity) {
	 	String sql = "INSERT INTO guionistas(idpersona,fullnombre,fnacimiento,fmuerte) VALUES(?,?,?,?)";

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

	@Override
	public List<Personas> selectAll() {
		List<Personas> personaslist = new ArrayList<>();
		  String sql = "SELECT * from guionistas";
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
	}

	public List<Personas> selectAll(Dictionary<String,String> conditions) {
		List<Personas> guionistasList = new ArrayList<>();
		  String sql = "SELECT distinct on (g.idpersona) * from guionistas as g ";
		  String cond = "WHERE ";
		  for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
				switch(k.nextElement()) {
					case "director":
						sql+= "join peliculasguionistas as pg on g.idpersona=pg.idpersona "+
								"join peliculasdirectores as pd	on pg.idpelicula=pd.idpelicula "+
								"join directores as d on d.idpersona=pd.idpersona ";
						cond+= "d.fullnombre =$$" + conditions.get("director")+"$$";
						break;
					case "actor":
						sql+= "join peliculasguionistas as pg2 on g.idpersona=pg2.idpersona "+
								"join peliculasactores as pa on pa.idpelicula=pg2.idpelicula "+
								"join actores as a on a.idpersona=pa.idpersona ";
						cond+= "a.fullnombre =$$" + conditions.get("actor")+"$$";
						break;
					case "id_guio":
						cond+= "g.idpersona = '" + conditions.get("id_guio")+"'";
						break;
					case "name":
						cond+= "g.fullnombre LIKE $$" + conditions.get("name")+"$$";
						break;
					case "fecha_nac":
						cond+= "g.fnacimiento = " + "'" + conditions.get("fecha_nac") + "'";
						break;
					case "intervalo_fecha_nac":
						if(conditions.get("intervalo_fecha_nac").indexOf("-") == -1) {
							cond+= "g.fnacimiento = " + "'" + conditions.get("intervalo_fecha_nac") + "'";
						}else {
							String[] intervalo = conditions.get("intervalo_fecha_nac").split("-");
							cond+= "g.fnacimiento >= " + "'" + intervalo[0] + "'" + " and " + "g.fnacimiento <= "+ "'"+ intervalo[1] + "'" ;
						}
						break;
					case "fecha_muer":
						cond+= "g.fmuerte = " + "'" + conditions.get("fecha_muer") + "'";
						break;
					case "intervalo_fecha_muer":
						if(conditions.get("intervalo_fecha_muer").indexOf("-") == -1) {
							cond+= "g.fmuerte = " + "'" + conditions.get("intervalo_fecha_muer") + "'";
						}else {
							String[] intervalo2 = conditions.get("intervalo_fecha_muer").split("-");
							cond+= "g.fmuerte >= " + "'" + intervalo2[0] + "'" + " and " + "g.fmuerte <= "+ "'"+ intervalo2[1] + "'" ;
						}
						break;
				}
				if(k.hasMoreElements()) {
					cond+=" AND ";
				}
		  }

		  System.out.println(sql+cond);
		  try (PreparedStatement pstmt = c.prepareStatement(sql+cond)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  while(rs.next()){
				  guionistasList.add(fromResultSet(rs));
			  }
	    } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return guionistasList;
	}


	@Override
	public Personas selectByID(String idpersona) {
		  String sql = "SELECT * from guionistas WHERE idpersona='" + idpersona+"'";
		  Personas persona = new Personas();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  if(rs.next())
				  persona = fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return persona;
	}

	@Override
	public void deleteByID(String idpersona) {
		  String sql = "DELETE from guionistas WHERE idpersona='" + idpersona+"'";
		  try (PreparedStatement pstmt = c.prepareStatement(sql)){
			  pstmt.executeUpdate();
			  c.commit();
		  } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
	}

	@Override
	public Personas selectByName(String name) {
		 String sql = "SELECT * from guionistas WHERE fullnombre='" + name+"'";
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  if(rs.next())
				  return fromResultSet(rs);
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return null;
	}
	
	@Override
	public List<Personas> selectByPeliculaID(String id){
		List<Personas> actores = new ArrayList<>();
		String sql = "SELECT * from guionistas as g "+
					"Inner join peliculasguionistas as pg on pg.idpersona=g.idpersona "+
					"WHERE pg.idpelicula='"+id+"'";
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
}
