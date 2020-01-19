package urjc.isi.dao.implementaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import urjc.isi.dao.interfaces.PersonasDAO;
import urjc.isi.entidades.Personas;

public class DirectoresDAOImpl extends GenericDAOImpl<Personas> implements PersonasDAO {

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
		statement.executeUpdate("create table directores" + table);
		c.commit();
	}

	@Override
   public void dropTable() throws SQLException {
		Statement statement = c.createStatement();
		statement.executeUpdate("drop table if exists directores");
		c.commit();
	}

	@Override
	public void insert(Personas entity) {
	 	String sql = "INSERT INTO directores(idpersona,fullnombre,fnacimiento,fmuerte) VALUES(?,?,?,?)";

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
		  String sql = "SELECT * from directores";
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
		List<Personas> directoresList = new ArrayList<>();
		  String sql = "SELECT distinct on (d.idpersona) * from directores as d ";
		  String cond = "WHERE ";
		  for(Enumeration<String> k = conditions.keys(); k.hasMoreElements();) {
				switch(k.nextElement()) {
					case "actor":
						sql+= "join peliculasdirectores as pd on d.idpersona=pd.idpersona "+
								"join peliculasactores as pa on pd.idpelicula=pa.idpelicula "+
								"join actpres as a on a.idpersona=pa.idpersona ";
						cond+= "a.fullnombre =$$" + conditions.get("actor")+"$$";
						break;
					case "guionista":
						sql+= "join peliculasdirectores as pd2 on d.idpersona=pd2.idpersona "+
								"join peliculasguionistas as pg	on pd2.idpelicula=pg.idpelicula "+
								"join guionistas as g on g.idpersona=pg.idpersona ";
						cond+= "g.fullnombre =$$" + conditions.get("guionista")+"$$";
						break;
					case "id_dir":
						cond+= "d.idpersona = '" + conditions.get("id_dir")+"'";
						break;
					case "name":
						cond+= "d.fullnombre LIKE $$" + conditions.get("name")+"%$$";
						break;
					case "fecha_nac":
						if(conditions.get("fecha_nac").indexOf("-") == -1) {
							cond+= "d.fnacimiento = " + "'" + conditions.get("fecha_nac") + "'";
						}else {
							String[] intervalo = conditions.get("fecha_nac").split("-");
							cond+= "d.fnacimiento >= " + "'" + intervalo[0] + "'" + " and " + "d.fnacimiento <= "+ "'"+ intervalo[1] + "'" ;
						}
						break;
					case "fecha_muer":
						if(conditions.get("fecha_muer").indexOf("-") == -1) {
							cond+= "d.fmuerte = " + "'" + conditions.get("fecha_muer") + "'";
						}else {
							String[] intervalo2 = conditions.get("fecha_muer").split("-");
							cond+= "d.fmuerte >= " + "'" + intervalo2[0] + "'" + " and " + "d.fmuerte <= "+ "'"+ intervalo2[1] + "'" ;
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
				  directoresList.add(fromResultSet(rs));
			  }
	    } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return directoresList;
	}


	@Override
	public Personas selectByID(String idpersona) {
		  String sql = "SELECT * from directores WHERE idpersona='" + idpersona+"'";
		  Personas persona = new Personas();
		  try (PreparedStatement pstmt = c.prepareStatement(sql)) {
			  ResultSet rs = pstmt.executeQuery();
			  c.commit();
			  if(rs.next()) {
				  persona = fromResultSet(rs);
			  }
	      } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
		  return persona;
	}

	@Override
	public void deleteByID(String idpersona) {
		  String sql = "DELETE from directores WHERE idpersona='" + idpersona+"'";
		  try (PreparedStatement pstmt = c.prepareStatement(sql)){
			  pstmt.executeUpdate();
			  c.commit();
		  } catch (SQLException e) {
			  System.out.println(e.getMessage());
		  }
	}

	@Override
	public Personas selectByName(String name) {
		 String sql = "SELECT * from directores WHERE fullnombre='" + name+"'";
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
		String sql = "SELECT * from directores as d "+
					"Inner join peliculasdirectores as pd on pd.idpersona=d.idpersona "+
					"WHERE pd.idpelicula='"+id+"'";
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
