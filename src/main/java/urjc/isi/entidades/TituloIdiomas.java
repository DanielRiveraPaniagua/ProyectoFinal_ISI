package urjc.isi.entidades;

import java.util.Objects;

import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

import com.google.gson.JsonObject;

//Solamente es la definición de la tabla
//sus campos y como trabajar con ellos

public class TituloIdiomas {

	private int id;
	private String idpelicula;
	private String titulo;
	private String idioma;
	private int isDefault;
	

	// Constructor
	public TituloIdiomas(){} //Constructor por defecto

	public TituloIdiomas(String line){// Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setId(Integer.valueOf(tokenizer.nextToken()));
		this.setIdPelicula(tokenizer.nextToken());
		this.setTitulo(tokenizer.nextToken());
		this.setIdioma(tokenizer.nextToken());
		this.setIsDefault(Integer.valueOf(tokenizer.nextToken()));
	}

	//Getters & Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id < 0){
			throw new InvalidParameter();
		}
		this.id = id;
	}
	
	public String getIdPelicula() {
		return idpelicula;
	}

	public void setIdPelicula(String idpelicula) {
		if(idpelicula == null){
			throw new InvalidParameter();
		}
		this.idpelicula = idpelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo == null){
			throw new InvalidParameter();
		}
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		if(idioma == null){
			throw new InvalidParameter();
		}
		this.idioma = idioma;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		if(isDefault != 0 && isDefault != 1){
			throw new InvalidParameter();
		}
		this.isDefault = isDefault;
	}

	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof TituloIdiomas)) return false;

	    TituloIdiomas otherTI = (TituloIdiomas)other;

	    return (this.id == otherTI.id) && 
	    		Objects.equals(this.idpelicula, otherTI.idpelicula) &&
	    		Objects.equals(this.titulo,otherTI.titulo) &&
	    		Objects.equals(this.idioma,otherTI.idioma) &&
	              (this.isDefault == otherTI.isDefault);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,idpelicula,titulo,idioma,isDefault);
	}
  
	@Override
	public String toString() {
		return "Id: " + getId() + "\tId Película: " + getIdPelicula() + "\tTítulo: " + getTitulo() + "\tIdioma: "
				+ getIdioma() + "\tNombre por defecto: " + getIsDefault();
	}
	
	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
		return "Id: " + getId() + "&emsp; Id Película: " + getIdPelicula() + "&emsp; Título: " + getTitulo() + "&emsp; Idioma: "
		+ getIdioma() + ",&emsp;  Nombre por defecto: " + getIsDefault();
	}
	
	public JsonObject toJSONObject () {
		JsonObject tituloIdiomaJSON = new JsonObject();
		tituloIdiomaJSON.addProperty("Id", getId());
		tituloIdiomaJSON.addProperty("IdPelicula", getIdPelicula());
		tituloIdiomaJSON.addProperty("Titulo", getTitulo());
		tituloIdiomaJSON.addProperty("Idioma", getIdioma());
		tituloIdiomaJSON.addProperty("IsDefault", getIsDefault());
		return tituloIdiomaJSON;
	}
	
}
