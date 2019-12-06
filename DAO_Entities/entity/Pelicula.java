package urjc.etsit.isi.entity;

import java.sql.Date;

import urjc.etsit.isi.exception.InvalidParameter;

public class Pelicula {
	
	private int id;
	private String nombre;
	private Date anno;
	private double duracion;
	private double rating;
	
	 /**
	  * Constructor por defecto
	  */
	public Pelicula (){
		
	}
	
	 /**
	  * Constructor de la clase pelicula
	  * @param clave primaria de la pelicula
	  * @param nombre de la pelicula
	  * @param año de estreno de la pelicula
	  * @param duracion de la pelicula
	  * @param valoracion de la pelicula
	  * @author Noozh

	  */
	public Pelicula (int id, String nombre, Date anno, double duracion, double rating){
		this.setId(id);
		this.setNombre(nombre);
		this.setDate(anno);
		this.setDuracion(duracion);
		this.setRating(rating);
	}
	
	
	 /**
	  * Metodo getter para el atributo id
	  * @return clave primaria de la pelicula
	  * @author Noozh

	  */
	public int getId() {
		return this.id;
	}
	
	 /**
	  * Metodo setter para el atributo id
	  * @param clave primaria de la pelicula
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
	  * @return titulo de la pelicula
	  * @author Noozh

	  */
	public String getNombre() {
		return this.nombre;
	}
	
	 /**
	  * Metodo setter para el atributo nombre
	  * @param nombre de la pelicula
	  * @author Noozh

	  */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	 /**
	  * Metodo getter para el atributo date
	  * @return fecha de estreno de la pelicula
	  * @author Noozh
	
	  */
	public Date getDate() {
		return this.anno;
	}
	
	 /**
	  * Metodo setter para el atributo fecha de estreno
	  * @param fecha de estreno de la pelicula
	  * @author Noozh

	  */
	public void setDate(Date anno) {
		this.anno = anno;
	}
	
	 /**
	  * Metodo getter para el atributo duracion
	  * @return duracion de la pelicula
	  * @author Noozh

	  */
	public double getDuracion() {
		return this.duracion;
	}
	
	 /**
	  * Metodo setter para el atributo duracion
	  * @param duracion de la pelicula
	  * @throws InvalidParameter
	  * @author Noozh

	  */
	public void setDuracion(double duracion) {
		if (duracion < 0.0)
			this.duracion = duracion;
		else
			throw new InvalidParameter();
	}
	
	 /**
	  * Metodo getter para el atributo rating
	  * @return rating de la pelicula
	  * @author Noozh

	  */
	public double getRating() {
		return this.rating;
	}
	
	 /**
	  * Metodo setter para el atributo rating
	  * @param valoracion d ela pelicula
	  * @throws InvalidParameter
	  * @author Noozh

	  */
	public void setRating(double rating) {
		if (rating < 0.0 || rating > 5.0)
			throw new InvalidParameter();
		else
			this.rating = rating;
	}

}
