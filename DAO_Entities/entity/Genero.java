package urjc.etsit.isi.entity;

import java.sql.Date;

import urjc.etsit.isi.exception.InvalidParameter;

public class Genero {
	
	private int id;
	private String nombre;
	
	 /**
	  * Constructor por defecto
	  */
	public Genero (){
		
	}
	
	 /**
	  * Constructor de la clase genero
	  * @param clave primaria del genero
	  * @param nombre del genero
	  * @author Noozh

	  */
	public Genero (int id, String nombre){
		this.setId(id);
		this.setNombre(nombre);
	}
	
	 /**
	  * Metodo getter para el atributo id
	  * @return clave primaria del genero
	  * @author Noozh

	  */
	public int getId() {
		return this.id;
	}
	
	 /**
	  * Metodo setter para el atributo id
	  * @param clave primaria del genero
	  * @throws InvalidParameter
	  * @author Noozh

	  */
	public void setId(int id) throws InvalidParameter{
		if(id > 0)
			this.id = id;
		else
			throw new InvalidParameter();
	}
	
	 /**
	  * Metodo getter para el atributo nombre
	  * @return titulo del genero
	  * @author Noozh

	  */
	public String getNombre() {
		return this.nombre;
	}
	
	 /**
	  * Metodo setter para el atributo nombre
	  * @param nombre del genero
	  * @author Noozh

	  */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
