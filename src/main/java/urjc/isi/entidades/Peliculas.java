package urjc.isi.entidades;

import java.sql.Date;
import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

//Solamente es la definición de la tabla
//sus campos y como trabajar con ellos
public class Peliculas {
	
	private int id;
	private String nombre;
	private Date anno;
	private double duracion;
	private double rating;
	private int nVotos;
	
	/**
	 * Constructor por defecto
	 */
	
	public Peliculas() {
		
	}
	
	/**
	 * Crea un objeto a partir de parametros inline
	 * @param line cadena que contiene los parametros separados por tabulador
	 */
	public Peliculas(String line){
		// Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setId(Integer.valueOf(tokenizer.nextToken()));
		this.setNombre(tokenizer.nextToken());
		this.setDate(Date.valueOf(tokenizer.nextToken()));
		this.setDuracion(Double.valueOf(tokenizer.nextToken()));
		this.setRating(Double.valueOf(tokenizer.nextToken()));
		this.setVotos(Integer.valueOf(tokenizer.nextToken()));
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
	public Peliculas (int id, String nombre, Date anno, double duracion, double rating){
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
	
	 /**
	  * Metodo getter para el atributo nVotos
	  * @return votos de la pelicula
	  * @author Noozh

	  */
	public int getVotos() {
		return this.nVotos;
	}
	
	 /**
	  * Metodo setter para el atributo nVotos
	  * @param numero de votos de la pelicula
	  * @throws InvalidParameter
	  * @author Noozh

	  */
	public void setVotos(int votos) {
		if (votos >= 0)
			this.nVotos = votos;
		else
			throw new InvalidParameter();
	}
	
	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Peliculas)) return false;
	
	    Peliculas otherP = (Peliculas)other;
	
	    return (this.id == otherP.id) &&
	              Objects.equals(this.nombre,otherP.nombre) &&
	              (this.anno == otherP.anno) &&
	              (this.duracion == otherP.duracion) &&
	              (this.rating == otherP.rating) &&
	              (this.nVotos == otherP.nVotos);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, anno, duracion, rating, nVotos);
	}
	
	@Override
	public String toString(){
		return "Id Película: "+getId()+"\tTitulo: " + getNombre() +
		" (" + getDate().toString() + ") " + "\tDuracion: "+getDuracion()+
		"\tRating: "+getRating()+"\tNumero de Votos: "+getVotos();
	}
}
