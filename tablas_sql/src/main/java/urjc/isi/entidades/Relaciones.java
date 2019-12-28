package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

public class Relaciones {

	private String idtabla1;
	private String idtabla2;

	public Relaciones(){}

	public Relaciones (String id_pelicula, String id_persona){
		  this.setIdtabla1(id_pelicula);
		  this.setIdtabla2(id_persona);
	}

	public Relaciones (String line){ // Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdtabla1(tokenizer.nextToken());
		this.setIdtabla2(tokenizer.nextToken());
	}

	// Setter Methods
	public void setIdtabla1(String idtabla1){
		if(idtabla1 == null){
			throw new InvalidParameter();
		}
		this.idtabla1 = idtabla1;
	}
	
	public void setIdtabla2(String idtabla2){
		if(idtabla2 == null){
			throw new InvalidParameter();
		}
		this.idtabla2 = idtabla2;
	}

	// Getter Methods
	public String getIdtabla1() {
		return idtabla1;
	}
	
	public String getIdtabla2() {
		return idtabla2;
	}

	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Relaciones)) return false;
	
	    Relaciones otherR = (Relaciones)other;
	
	    return Objects.equals(this.idtabla1, otherR.idtabla1) &&
	              Objects.equals(this.idtabla2,otherR.idtabla2);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idtabla1,idtabla2);
	}
	
    @Override
    public String toString(){
      return "Id tabla1: "+getIdtabla1()+"\tId tabla2: " + getIdtabla2();
    }
    
	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
  	  return "Id tabla1: "+getIdtabla1()+"&emsp; Id tabla2: " + getIdtabla2();
    }
}
