package urjc.isi.dao.interfaces;

import urjc.isi.entidades.PeliculasPersonas;

public interface PeliculasPersonasDAO extends GenericDAO<PeliculasPersonas>{

  public PeliculasPersonas selectByIDActores(String idpersona);

  public void deleteByIDActores(String idpersona);
}
