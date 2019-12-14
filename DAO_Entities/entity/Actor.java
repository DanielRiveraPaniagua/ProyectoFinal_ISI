package urjc.etsit.isi.entity;

import java.sql.Date;

public class Actor extends Persona{

	
	 /**
	  * Constructor por defecto
	  */
	public Actor (){
		
	}
	
	 /**
	  * Constructor de la clase Actor
	  * @param clave primaria de la Actor
	  * @param nombre de la Actor
	  * @param apellido de la Actor
	  * @param fecha de nacimiento de la Actor
	  * @param fecha de muerte de la Actor
	  * @param parais de origen de la Actor
	  * @author Noozh

	  */
	public Actor (int id, String nombre, String apellido, Date fNacimiento, Date fMuerte, String paisOrigen){
		this.setId(id);
		this.setNombre(nombre);
		this.setNacimiento(fNacimiento);
		this.setMuerte(fMuerte);
		this.setPaisOrigen(paisOrigen);
	}
}
