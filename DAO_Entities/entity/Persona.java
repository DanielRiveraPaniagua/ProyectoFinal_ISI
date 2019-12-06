package urjc.etsit.isi.entity;

import java.sql.Date;

import urjc.etsit.isi.exception.InvalidParameter;

public class Persona {
	
	private int id;
	private String nombre;
	private String apellido;
	private Date fNacimiento;
	private Date fMuerte;
	private String paisOrigen;
	
	
	
	 /**
	  * Metodo getter para el atributo id
	  * @return clave primaria de la persona
	  * @author Noozh

	  */
	public int getId() {
		return this.id;
	}
	
	 /**
	  * Metodo setter para el atributo id
	  * @param clave primaria de la persona
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
	  * @return titulo de la persona
	  * @author Noozh

	  */
	public String getNombre() {
		return this.nombre;
	}
	
	 /**
	  * Metodo setter para el atributo nombre
	  * @param nombre de la persona
	  * @author Noozh

	  */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 /**
	  * Metodo getter para el atributo apellido
	  * @return apellido de la persona
	  * @author Noozh
	
	  */
	public String getApellido() {
		return this.apellido;
	}
	
	 /**
	  * Metodo setter para el atributo apellido
	  * @param apellido de la persona
	  * @author Noozh

	  */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	 /**
	  * Metodo getter para el atributo fNacimiento
	  * @return fecha de nacimiento de la persona
	  * @author Noozh
	
	  */
	public Date getNacimiento() {
		return this.fNacimiento;
	}
	
	 /**
	  * Metodo setter para el atributo fecha de nacimiento
	  * @param fecha de nacimiento de la persona
	  * @author Noozh

	  */
	public void setNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	
	 /**
	  * Metodo getter para el atributo fMuerte
	  * @return fecha de muerte de la persona
	  * @author Noozh
	
	  */
	public Date getMuerte() {
		return this.fMuerte;
	}
	
	 /**
	  * Metodo setter para el atributo fecha de muerte
	  * @param fecha de muerte de la persona
	  * @author Noozh

	  */
	public void setMuerte(Date fMuerte) {
		this.fMuerte = fMuerte;
	}
	
	 /**
	  * Metodo getter para el atributo paisOrigen
	  * @return pais de origen de la persona
	  * @author Noozh

	  */
	public String getPaisOrigen() {
		return this.paisOrigen;
	}
	
	 /**
	  * Metodo setter para el atributo paisOrigen
	  * @param pais de origen de la persona
	  * @author Noozh

	  */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

}
