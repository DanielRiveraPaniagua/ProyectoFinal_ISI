package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

//Solamente es la definición de la tabla
//sus campos y como trabajar con ellos

public class Generos {

  private String idgenero;
  private String nombre;

  // Constructor
	public Generos(){} //Constructor por defecto
	public Generos(String idgenero, String nombre){
    this.setIdGenero(idgenero);
    this.setNombre(nombre);
	}
	public Generos(String line){// Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdGenero(tokenizer.nextToken());
		this.setNombre(tokenizer.nextToken());
	}

  // Setter Methods
  public void setIdGenero(String idgenero){
	  if(idgenero == null){
	      throw new InvalidParameter();
	    }
	  this.idgenero = idgenero;
  }
  public void setNombre(String nombre){
    if(nombre == null){
      throw new InvalidParameter();
    }
    this.nombre = nombre;
  }

  // Getter Methods
	public String getIdGenero() {
		return idgenero;
	}
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

    return Objects.equals(this.idgenero, otherG.idgenero) &&
    		Objects.equals(this.nombre,otherG.nombre);        
	}
  @Override
  public int hashCode() {
	return Objects.hash(idgenero,nombre);
  }
  @Override
  public String toString(){
    return "Id Género: "+getIdGenero()+"\tNombre: " + getNombre();
  }
  public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
	  return "Id Género: "+getIdGenero()+"&emsp; Nombre: " + getNombre();
  }
}
