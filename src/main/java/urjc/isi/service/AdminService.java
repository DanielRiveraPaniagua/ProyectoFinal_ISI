package urjc.isi.service;

import java.sql.SQLException;

import urjc.isi.dao.implementaciones.PeliculasDAOImpl;

public class AdminService {
	
	public String crearTablaPeliculas() {
		PeliculasDAOImpl pelisDAO = new PeliculasDAOImpl();
		try{
			pelisDAO.createTable();
			return "Tabla creada con exito";
		}catch(SQLException e) {
			return "Hubo un fallo en la creacion de la tabla";
		}
	}

}
