package urjc.etsit.isi.entity;

import java.sql.Date;

import urjc.etsit.isi.exception.InvalidParameter;

public class Codificacion {
	
	private int id;
	private String nombre;
	
	 /**
	  * Constructor por defecto
	  */
	public Codificacion (){
		
	}
	
	 /**
	  * Constructor de la clase Codificacion
	  * @param clave primaria del Codificacion
	  * @param nombre del Codificacion
	  * @author Noozh

	  */
	public Codificacion (int id, String nombre){
		this.setId(id);
		this.setNombre(nombre);
	}
	
	 /**
	  * Metodo getter para el atributo id
	  * @return clave primaria del Codificacion
	  * @author Noozh

	  */
	public int getId() {
		return this.id;
	}
	
	 /**
	  * Metodo setter para el atributo id
	  * @param clave primaria del Codificacion
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
	  * @return titulo del Codificacion
	  * @author Noozh

	  */
	public String getNombre() {
		return this.nombre;
	}
	
	 /**
	  * Metodo setter para el atributo nombre
	  * @param nombre del Codificacion
	  * @author Noozh

	  */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
