package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

public class Relaciones {

	private int idtabla1;
	private int idtabla2;


  // Constructor
	public Relaciones(){
		;
	}

	public Relaciones (int id_pelicula, int id_persona){
		  this.setIdtabla1(id_pelicula);
		  this.setIdtabla2(id_persona);
	}

	public Relaciones (String line){ // Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdtabla1(Integer.valueOf(tokenizer.nextToken()));
		this.setIdtabla2(Integer.valueOf(tokenizer.nextToken()));
	}

  // Setter Methods
  public void setIdtabla1(int idtabla1){
    if(idtabla1 <= 0){
      throw new InvalidParameter();
    }
    this.idtabla1 = idtabla1;
  }
  public void setIdtabla2(int idtabla2){
    if(idtabla2 <= 0){
      throw new InvalidParameter();
    }
    this.idtabla2 = idtabla2;
  }

  // Getter Methods
  public int getIdtabla1() {
	return idtabla1;
  }
  public int getIdtabla2() {
	return idtabla2;
  }

  // Overrided Methods
	@Override
	public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Relaciones)) return false;

    Relaciones otherR = (Relaciones)other;

    return (this.idtabla1 == otherR.idtabla1) &&
              (this.idtabla2==otherR.idtabla2);
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
