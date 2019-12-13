package urjc.isi.ProyectoFinal;

public interface InterfaceActoresDAO extends InterfaceGenericDAO {
	ActorDao getActorByID(int id);
	Set<ActorDao> getAllActors();
	boolean insertActor(ActoresDAO actor);
}
