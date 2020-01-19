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
	public List<Personas> getAllActores(Dictionary<String,String> conditions) throws SQLException{
		ActoresDAOImpl actores = new ActoresDAOImpl();
		List<Personas> result;
		if(!conditions.isEmpty()) {
			result = actores.selectAll(conditions);
		}else {
			result = actores.selectAll();
		}
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

	public 	Dictionary<String,Object> fullActoresInfo(String engine, boolean isid) throws SQLException{
		ActoresDAOImpl actoresDAO = new ActoresDAOImpl();
		PeliculasDAOImpl peliDAO = new PeliculasDAOImpl();
		String id;
		if (!isid) {
			Personas persona = actoresDAO.selectByName(engine);
			id = persona!=null?persona.getId():"";
		}else {
			Personas persona = actoresDAO.selectByID(engine);
			id = persona.getId()!=null?persona.getId():"";
		}
	
		Dictionary<String,Object> result = new Hashtable<String,Object>();
		if(id.length()>0){
			result.put("actor", (Object)actoresDAO.selectByID(id));
			result.put("peliculas", (Object)peliDAO.selectByPersonaID("actor",id));
		}
		actoresDAO.close();
		peliDAO.close();
		return result;
	}

	public ST<Double, List<Personas>> getActoresByCercania (String actor_p, String dist_max_p, String factor_p) throws SQLException {
		final Integer DIST_MAX = 10; /*
						     * considering this number as the maximum
						     * distance of relation between actors
						     */
		final Double FACTOR = 1.0;  /*
						   * the part of the actors that will be
						   * chosen for each percentage based on
						   * their popularity
						   */
		final String DELIMITER = "/";
		final String EOL = ";";

		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		ActoresDAOImpl actoresDAO = new ActoresDAOImpl();
		List<Peliculas> peliculas = pelisDAO.selectAll();
		
		// Params control
		Integer dist_max = (dist_max_p.equals("None"))?DIST_MAX:Integer.valueOf(dist_max_p);
		Double factor = (factor_p.equals("None"))?FACTOR:Double.valueOf(factor_p);
		String actor_id = actoresDAO.selectByName(actor_p).getId();
        if (actor_id == null || dist_max < 0 || factor < 0.0 || factor > 1.0) {
        	ST<Double, List<Personas>> result = new ST<Double, List<Personas>>();
        	actoresDAO.close();
    		pelisDAO.close();
    		return result;
        }
        
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

        // create popularity data structure
        ST<String, Double> act_popularity = new ST<String, Double>();
        // create distances data structure
        ST<Double, SET<String>> act_distances = new ST<Double, SET<String>>();

        // run breadth first search
        PathFinder finder = new PathFinder(G, actor_id);

        // calculate the popularity and distance of each actor
        for (String actor : G.vertices()) {
        	Double dist = (double)finder.distanceTo(actor);
            if (dist % 2 != 0) continue;  // it's a movie vertex
        	Double popularity = G.popularity(actor);
            act_popularity.put(actor, popularity);
            if (actor.equals(actor_id)) continue;  // it's the same actor
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
        	
        	if (!act_distances.contains(percent)) {
            	act_distances.put(percent, new SET<String>());
            }
        	for (String actor : act_distances.get((double)d)) {
        		act_distances.get(percent).add(actor);
            }
        	act_distances.remove((double)d);
        }
        
        // Build and return the result
        ST<Double, List<Personas>> result = new ST<Double, List<Personas>>();
        for (Double p : act_distances) {
            int numb_act = (int) Math.ceil((double)act_distances.get(p).size()*factor);
            for (int i=1; i<=numb_act; i++) {
            	double pop = 0.0;
            	String id_actor = "";
            	for (String id_actor_i : act_distances.get(p)) {
	                if (act_popularity.get(id_actor_i) > pop) {
	                	pop = act_popularity.get(id_actor_i);
	                	id_actor = id_actor_i;
	                }
	            }
            	if (!result.contains(p)) {
                	result.put(p, new ArrayList<Personas>());
                }
            	result.get(p).add(actoresDAO.selectByID(id_actor));
            	act_distances.get(p).delete(id_actor);
            }
        }
        actoresDAO.close();
		pelisDAO.close();
		return result;
	}
}
