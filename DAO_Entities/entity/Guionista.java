package urjc.etsit.isi.entity;

import java.sql.Date;

public class Guionista extends Persona{
	
	 /**
	  * Constructor por defecto
	  */
	public Guionista (){
		
	}
	
	 /**
	  * Constructor de la clase Guionista
	  * @param clave primaria de la Guionista
	  * @param nombre de la Guionista
	  * @param apellido de la Guionista
	  * @param fecha de nacimiento de la Guionista
	  * @param fecha de muerte de la Guionista
	  * @param parais de origen de la Guionista
	  * @author Noozh

	  */
	public Guionista (int id, String nombre, String apellido, Date fNacimiento, Date fMuerte, String paisOrigen){
		this.setId(id);
		this.setNombre(nombre);
		this.setNacimiento(fNacimiento);
		this.setMuerte(fMuerte);
		this.setPaisOrigen(paisOrigen);
	}

}
