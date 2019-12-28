package urjc.isi.entidades;

import java.util.Objects;

import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

public class RelacionesGeneros {

	private String idtabla1;
	private String genero;

	public RelacionesGeneros(){}

	public RelacionesGeneros (String id_pelicula, String genero){
		  this.setIdPelicula(id_pelicula);
		  this.setGenero(genero);
	}

	public RelacionesGeneros (String line){ // Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdPelicula(tokenizer.nextToken());
		this.setGenero(tokenizer.nextToken());
	}

	// Setter Methods
	public void setIdPelicula(String idtabla1){
		if(idtabla1 == null){
			throw new InvalidParameter();
		}
		this.idtabla1 = idtabla1;
	}
	
	public void setGenero(String idtabla2){
		if(idtabla2 == null){
			throw new InvalidParameter();
		}
		this.genero = idtabla2;
	}

	// Getter Methods
	public String getIdPelicula() {
		return idtabla1;
	}
	
	public String getGenero() {
		return genero;
	}

	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof RelacionesGeneros)) return false;
	
	    RelacionesGeneros otherR = (RelacionesGeneros)other;
	
	    return Objects.equals(this.idtabla1, otherR.idtabla1) &&
	              Objects.equals(this.genero,otherR.genero);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idtabla1,genero);
	}
	
    @Override
    public String toString(){
      return "Id Pelicula: "+getIdPelicula()+"\tGenero: " + getGenero();
    }
    
	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
  	  return "Id Pelicula: "+getIdPelicula()+"&emsp; Genero: " + getGenero();
    }
}
