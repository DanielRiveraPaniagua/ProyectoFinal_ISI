package urjc.isi.service;

import java.sql.SQLException;
import java.util.List;

import urjc.isi.dao.implementaciones.PeliculasDAOImpl;
import urjc.isi.entidades.*;

public class AdminService {

	private PeliculasDAOImpl pelisDAO;

	public AdminService() {
		pelisDAO = new PeliculasDAOImpl();
	}

	public String crearTablaPeliculas() {
		try{
			pelisDAO.createTable();
			return "Tabla creada con exito";
		}catch(SQLException e) {
			return "Hubo un fallo en la creacion de la tabla";
		}
	}

	public List<Peliculas> getAllPeliculas() throws SQLException{
		return pelisDAO.selectAll();
	}

}
