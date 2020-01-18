package urjc.isi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import spark.Request;
import urjc.isi.dao.implementaciones.*;
import urjc.isi.entidades.*;
import urjc.isi.grafos.*;

public class ActoresService {
	
	/**
	 * Constructor por defecto
	 */
	public ActoresService() {}

	/**
	 * Metodo encargado de procesar un selectAll de la tabla actores
	 * @return Lista de actores de la tabla Actores
	 * @throws SQLException
	 */
	public List<Personas> getAllActores() throws SQLException{
		ActoresDAOImpl actores = new ActoresDAOImpl();
		List<Personas> result = actores.selectAll();
		actores.close();
		return result;
	}

	/**
	 * Metodo encargado de procesar la subida de los registros de la tabla Actores
	 * @param req
	 * @return Estado de la subida
	 */
	public String uploadTable(Request req){
		ActoresDAOImpl actores = new ActoresDAOImpl();
		req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
		String result = "File uploaded!";
		try (InputStream input = req.raw().getPart("uploaded_actores_file").getInputStream()) {
		    actores.dropTable();
		    actores.createTable();
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(isr);
			actores.uploadTable(br);
		} catch (IOException | ServletException | SQLException e) {
			System.out.println(e.getMessage());
		}
		actores.close();
		return result;
	}

	public List<Personas> getActoresByFechaNac (String fecha) throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerByFechaNac (fecha);
		actores.close();
		return result;
	}

	public List<Personas> getActoresMuertos () throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerMuertas ();
		actores.close();
		return result;
	}

	public List<Personas> getActoresByIntervaloNac (String fechaIn, String fechaFin) throws SQLException {
		ActoresDAOImpl actores = new ActoresDAOImpl ();
		List<Personas> result = actores.selectPerByIntervaloNac (fechaIn, fechaFin);
		actores.close();
		return result;
	}

	public 	Dictionary<String,Object> fullActoresInfo(String name) throws SQLException{
		ActoresDAOImpl actoresDAO = new ActoresDAOImpl();
		PeliculasDAOImpl peliDAO = new PeliculasDAOImpl();
		Personas persona = new Personas();
		persona = actoresDAO.selectByName(name);
		String id = persona.getId();

		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			result.put("actor", (Object)actoresDAO.selectByID(id));
			result.put("peliculas", (Object)peliDAO.selectByActorID(id));
		}
		actoresDAO.close();
		peliDAO.close();
		return result;
	}
	
	public List<Personas> getActoresByCercania (String actor_p, String dist_max_p, String factor_p) throws SQLException {
		final Integer DIST_MAX = 10; /* 
						     * considering this number as the maximum 
						     * distance of relation between actors
						     */
		final Integer FACTOR = 5;  /*
						   * the part of the actors that will be 
						   * chosen for each percentage based on 
						   * their popularity
						   */
		final String DELIMITER = "/";
		final String EOL = ";";
		
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		ActoresDAOImpl actoresDAO = new ActoresDAOImpl();
		List<Peliculas> peliculas = pelisDAO.selectAll();
		
		Integer dist_max = (dist_max_p.equals("None"))?DIST_MAX:Integer.valueOf(dist_max_p);
		Integer factor = (factor_p.equals("None"))?FACTOR:Integer.valueOf(factor_p);
		
		// Create Graph
		String str_graph = "";
		for (int i = 0; i < peliculas.size(); i++) {
			String idpeli = peliculas.get(i).getIdPelicula();
			str_graph = str_graph + idpeli + DELIMITER;
			List<Personas> actores = actoresDAO.selectByPeliculaID(idpeli);
			for (int j = 0; j < actores.size(); j++) {
				String idactor = actores.get(j).getId();
				str_graph = str_graph + idactor;
				if (j == actores.size()-1) {
					str_graph = str_graph + EOL;
				}else {
					str_graph = str_graph + DELIMITER;
				}
			}
		}
		Graph G = new Graph(str_graph, DELIMITER, EOL);
		
		for (String v : G.vertices()) {
            		System.out.print(v + ": ");
            		for (String w : G.adjacentTo(v)) {
            			System.out.print(w + " ");
            		}
            		System.out.println();
        	}
		
        // create popularity data structure
        ST<String, Double> act_popularity = new ST<String, Double>();
        // create distances data structure
        ST<Double, SET<String>> act_distances = new ST<Double, SET<String>>();
        
        // run breadth first search
        PathFinder finder = new PathFinder(G, actor_p);
        
        // calculate the popularity and distance of each actor
        for (String actor : G.vertices()) {
        	Double dist = (double)finder.distanceTo(actor);
        	Double popularity = G.popularity(actor);
            if (dist % 2 != 0) continue;  // it's a movie vertex
            
            act_popularity.put(actor, popularity);
            
            if (actor.equals(actor_p)) continue;  // it's the same actor
            if (!act_distances.contains(dist/2)) {
            	act_distances.put(dist/2, new SET<String>());
            }
            act_distances.get(dist/2).add(actor);
        }

        // convert distances to percent
        Integer max = act_distances.max().intValue();
        for (Integer d=1; d<=max; d++) {
        	Double percent;
        	if (d < dist_max) {
        		percent = (1- (double)d/(double)dist_max) * 100;
        	}else {
        		percent = 0.0;
        	}
        	
        	act_distances.put(percent, act_distances.get((double)d));
        	act_distances.remove((double)d);
        }
        
        List<Personas> result = new ArrayList<Personas>();
        for (Double p : act_distances) {
            int numb_act = (int) Math.ceil((double)act_distances.get(p).size()/factor);
            for (int i=1; i<=numb_act; i++) {
            	double pop = 0.0;
            	String id_actor = "";
            	for (String id_actor_i : act_distances.get(p)) {
	                if (act_popularity.get(id_actor_i) > pop) {
	                	pop = act_popularity.get(id_actor_i);
	                	id_actor = id_actor_i;
	                }
	            }
            	result.add(actoresDAO.selectByID(id_actor));
            	act_distances.get(p).delete(id_actor);
            }
        }
        actoresDAO.close();
		pelisDAO.close();
		return result;
	}
}
