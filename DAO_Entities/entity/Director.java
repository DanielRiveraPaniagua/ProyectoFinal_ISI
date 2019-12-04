package urjc.etsit.isi.entity;

import java.sql.Date;

public class Director extends Persona{
	
	 /**
	  * Constructor por defecto
	  */
	public Director (){
		
	}
	
	 /**
	  * Constructor de la clase Director
	  * @param clave primaria de la Director
	  * @param nombre de la Director
	  * @param apellido de la Director
	  * @param fecha de nacimiento de la Director
	  * @param fecha de muerte de la Director
	  * @param parais de origen de la Director
	  * @author Noozh

	  */
	public Director (int id, String nombre, String apellido, Date fNacimiento, Date fMuerte, String paisOrigen){
		this.setId(id);
		this.setNombre(nombre);
		this.setNacimiento(fNacimiento);
		this.setMuerte(fMuerte);
		this.setPaisOrigen(paisOrigen);
	}

}
