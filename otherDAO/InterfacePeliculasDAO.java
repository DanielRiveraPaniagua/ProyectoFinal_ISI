package urjc.etsit.isi.InterfacesDAO;

public interface InterfacePeliculasDAO {
	PeliculasDAO getPeliculabyID(int id);
	Set<PeliculasDAO> getAllPeliculas();
	boolean insertPelicula(PeliculasDAO pelicula);
}
