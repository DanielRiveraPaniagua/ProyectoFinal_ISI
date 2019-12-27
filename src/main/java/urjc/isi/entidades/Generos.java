package urjc.isi.entidades;

import java.util.Objects;

import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

import com.google.gson.JsonObject;

//Solamente es la definición de la tabla
//sus campos y como trabajar con ellos

public class Generos {

	private String nombre;

	// Constructor
	public Generos(){} //Constructor por defecto

	public Generos(String line){// Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setNombre(tokenizer.nextToken());
	}

	// Setter Methods

	public void setNombre(String nombre){
		if(nombre == null){
			throw new InvalidParameter();
		}
		this.nombre = nombre;
	}

	// Getter Methods

	public String getNombre() {
		return nombre;
	}

	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Generos)) return false;
	
	    Generos otherG = (Generos)other;
	
	    return Objects.equals(this.nombre,otherG.nombre);        
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
  
	@Override
	public String toString(){
		return "Nombre: " + getNombre();
	}
	
	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
		return "Nombre: " + getNombre();
	}
	
	public JsonObject toJSONObject () {
		JsonObject generoJSON = new JsonObject();
		generoJSON.addProperty("Nombre", getNombre());
		return generoJSON;
	}
	
}
