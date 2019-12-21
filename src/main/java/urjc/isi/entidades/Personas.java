package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;

public class Personas {

	private int id;
	private String fullNombre;
	private int fNacimiento;
	private int fMuerte;
	private String paisOrigen;

  public Personas(){
	  ;
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
  public void setPaisOrigen(String paisOrigen){
    if(paisOrigen == null){
      throw new InvalidParameter();
    }
    this.paisOrigen = paisOrigen;
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
  public String getPaisOrigen(){
      return paisOrigen;
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
              (this.fMuerte == otherP.fMuerte) &&
              Objects.equals(this.paisOrigen,otherP.paisOrigen);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id,fullNombre,fNacimiento,fMuerte,paisOrigen);
	}
    @Override
    public String toString(){
      return "Id: "+getId()+"\tNombre y apellido: " + getFullNombre() +
      "\tFecha Nacimiento: "+getNacimiento()+
      "\tFecha Muerte: "+getMuerte()+"\tPaís Origen: "+getPaisOrigen();
    }
}
