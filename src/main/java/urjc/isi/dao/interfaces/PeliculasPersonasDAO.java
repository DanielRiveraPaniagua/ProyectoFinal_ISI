package urjc.isi.dao.interfaces;

import urjc.isi.entidades.PeliculasPersonas;

public interface PeliculasPersonasDAO extends GenericDAO<PeliculasPersonas>{

  public PeliculasPersonas selectByIDPersona(String idpersona);

  public void deleteByIDPersona(String idpersona);
}
