package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

public class Personas {

	private int id;
	private String fullNombre;
	private int fNacimiento;
	private int fMuerte;


  // Constructor
  public Personas(){
	  ;
  }

  public Personas (int id, String fullNombre, int fNacimiento, int fMuerte){
		this.setId(id);
		this.setFullNombre(fullNombre);
		this.setNacimiento(fNacimiento);
		this.setMuerte(fMuerte);
  }

  public Personas(String line){ // Se tokeniza la linea
	  StringTokenizer tokenizer = new StringTokenizer(line,"\t");
	  this.setId(Integer.valueOf(tokenizer.nextToken()));
	  this.setFullNombre(tokenizer.nextToken());
	  this.setNacimiento(Integer.valueOf(tokenizer.nextToken()));
	  this.setMuerte(Integer.valueOf(tokenizer.nextToken()));
  }

  // Setter Methods
  public void setId(int id){
    if(id <= 0){
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
  public void setNacimiento(int fNacimiento){
	if(fNacimiento < 1800 || fNacimiento > 2100){
	  throw new InvalidParameter();
    }
    this.fNacimiento = fNacimiento;
  }
  public void setMuerte(int fMuerte){
	if(fMuerte < 1800 || fMuerte > 2100){
	  throw new InvalidParameter();
    }
    this.fMuerte = fMuerte;
  }

  // Getter Methods
  public int getId() {
	  return id;
  }
  public String getFullNombre() {
	  return fullNombre;
  }
  public int getNacimiento(){
      return fNacimiento;
  }
  public int getMuerte(){
      return fMuerte;
  }
  // Overrided Methods
	@Override
	public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Personas)) return false;

    Personas otherP = (Personas)other;

    return (this.id == otherP.id) &&
              Objects.equals(this.fullNombre,otherP.fullNombre) &&
              (this.fNacimiento == otherP.fNacimiento) &&
              (this.fMuerte == otherP.fMuerte);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id,fullNombre,fNacimiento,fMuerte);
	}
    @Override
    public String toString(){
      return "Id: "+getId()+"\tNombre y apellido: " + getFullNombre() +
      "\tFecha Nacimiento: "+getNacimiento()+
      "\tFecha Muerte: "+getMuerte();
    }
}