package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

public class PeliculasPersonas {

	private int id_pelicula;
	private int id_persona;


  // Constructor
	public PeliculasPersonas(){
		;
	}

	public PeliculasPersonas (int id_pelicula, int id_persona){
		  this.setIdPelicula(id_pelicula);
		  this.setIdPersona(id_persona);
	}

	public PeliculasPersonas (String line){ // Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdPelicula(Integer.valueOf(tokenizer.nextToken()));
		this.setIdPersona(Integer.valueOf(tokenizer.nextToken()));
	}

  // Setter Methods
  public void setIdPelicula(int id_pelicula){
    if(id_pelicula <= 0){
      throw new InvalidParameter();
    }
    this.id_pelicula = id_pelicula;
  }
  public void setIdPersona(int id_persona){
    if(id_persona <= 0){
      throw new InvalidParameter();
    }
    this.id_persona = id_persona;
  }

  // Getter Methods
  public int getIdPelicula() {
	return id_pelicula;
  }
  public int getIdPersona() {
	return id_persona;
  }

  // Overrided Methods
	@Override
	public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof PeliculasPersonas)) return false;

    PeliculasPersonas otherPP = (PeliculasPersonas)other;

    return (this.id_pelicula == otherPP.id_pelicula) &&
              Objects.equals(this.id_persona,otherPP.id_persona);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id_pelicula,id_persona);
	}
    @Override
    public String toString(){
      return "Id pelicula: "+getIdPelicula()+"\tId persona: " + getIdPersona();
    }
}
