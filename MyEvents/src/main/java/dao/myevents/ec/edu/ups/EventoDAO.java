package dao.myevents.ec.edu.ups;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.Evento;



@Stateless
public class EventoDAO {

	@Inject
	private EntityManager em;
	
	
	//---METODOS CRUD
	//Crear Evento
	
	public void insertarEvento(Evento e) {
		em.persist(e);		
	}
	
	//Metodo para Actualizar Evento
	public void updateEvento(Evento e) {
		System.out.println("Updating............"+e.getCodigo()+
				e.getNombre());
		em.merge(e);
	}
	
	//Metodo para Leer Evento
	public Evento leerEvento(int id) {
		
		Evento e = em.find(Evento.class, id);
		return e;
		
	}
	//Metodo para Eliminar Evento
	public void deleteEvento (int id) {
		Evento e = leerEvento(id);
		em.remove(e);
	}
	
	//Metodo para Listar Eventos
	public List<Evento> listEvento(){
		String sql = "Select e from Evento e";
		TypedQuery<Evento> query = em.createQuery(sql, Evento.class);
		List<Evento> levento = query.getResultList();
		return levento;
	}
	
}
