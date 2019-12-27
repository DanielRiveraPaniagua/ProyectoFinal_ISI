package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

import com.google.gson.JsonObject;

public class Personas {

	private String id;
	private String fullNombre;
	private String fNacimiento;
	private String fMuerte;


	// Constructor
	public Personas(){}

	public Personas (String id, String fullNombre, String fNacimiento, String fMuerte){
		this.setId(id);
		this.setFullNombre(fullNombre);
		this.setNacimiento(fNacimiento);
		this.setMuerte(fMuerte);
	}

	public Personas(String line){ // Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setId(tokenizer.nextToken());
		this.setFullNombre(tokenizer.nextToken());
		this.setNacimiento(tokenizer.nextToken());
		this.setMuerte(tokenizer.nextToken());
	}

	// Setter Methods
	public void setId(String id){
		if(id == null){
			throw new InvalidParameter();
	    }
		this.id = id;
	}
	
	public void setFullNombre(String fullNombre){
		if(fullNombre == null){
			throw new InvalidParameter();
		}
		this.fullNombre = fullNombre;
	}
	
	public void setNacimiento(String fNacimiento){
		this.fNacimiento = fNacimiento;
	}
	
	public void setMuerte(String fMuerte){
		this.fMuerte = fMuerte;
	}

	// Getter Methods
	public String getId() {
		return id;
	}
	
	public String getFullNombre() {
		return fullNombre;
	}
	
	public String getNacimiento(){
		return fNacimiento;
	}
	
	public String getMuerte(){
		return fMuerte;
	}
	
	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Personas)) return false;
	
	    Personas otherP = (Personas)other;
	
	    return Objects.equals(this.id,otherP.id) &&
	              Objects.equals(this.fullNombre,otherP.fullNombre) &&
	              Objects.equals(this.fNacimiento, otherP.fNacimiento) &&
	              Objects.equals(this.fMuerte, otherP.fMuerte);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,fullNombre,fNacimiento,fMuerte);
	}
	
    @Override
    public String toString(){
    	return "Id: "+getId()+"\tNombre: " + getFullNombre() +
    			"\tFecha Nacimiento: "+getNacimiento()+
    			"\tFecha Muerte: "+getMuerte();
    }
    
	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
		return "Id: "+getId()+"&emsp; Nombre: " + getFullNombre() +
				"&emsp; Fecha nacimiento: "+getNacimiento()+
				"&emsp; Fecha muerte: "+getMuerte();
    }
	
	public JsonObject toJSONObject () {
		JsonObject personaJSON = new JsonObject();
		personaJSON.addProperty("Nombre completo", getFullNombre());
		personaJSON.addProperty("Fecha de nacimiento", getNacimiento());
		personaJSON.addProperty("Fecha de muerte", getMuerte());
		return personaJSON;
	}
	
}
