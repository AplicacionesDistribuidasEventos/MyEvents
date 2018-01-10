package dao.myevents.ec.edu.ups;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.Evento;



// TODO: Auto-generated Javadoc
/**
 * The Class EventoDAO.
 */
@Stateless
public class EventoDAO {

	/** Inyeccion de dependencias para el manejo de JPA */
	@Inject
	private EntityManager em;
	
	
	/**
	 * Guardar el evento si existe el evento se actualiza o sino el evento se inserta
	 * a través de la variables aux.
	 *
	 * @param aevento the aevento
	 */
	public void guardarEvento(Evento e) {
		
		
		Evento auxe = leerEvento(e.getCodigo());
		if(auxe!=null) {
			updateEvento(e);
		}else {
			insertarEvento(e);
		}
	}
	
	
	/**
	 * Insert A evento.
	 *
	 * @param ae del Evento
	 */
	public void insertarEvento(Evento e) {
		em.persist(e);		
	}
	
	/**
	 * Actualiza el evento a traves del em.merge
	 *
	 * @param e del Evento
	 */
	public void updateEvento(Evento e) {
		System.out.println("Updating............"+e.getCodigo()+
				e.getNombre());
		em.merge(e);
	} 
	
	/**
	 * leerEvento
	 *
	 * @param id
	 * @return el evento encontrado a traves del id.
	 */
	public Evento leerEvento(int id) {
		
		Evento e = em.find(Evento.class, id);
		return e;
		
	}
	
	/**
	 * Delete evento.
	 *Remueve un evento haciendo uso de JPA
	 * @param envio del parametro ID
	 */
	
	public void deleteEvento (int id) {
		Evento e = leerEvento(id);
		em.remove(e);
	}
	
	/**
	 * List evento.
	 *
	 * @return De una coleccion tipo Evento
	 */
	
	public List<Evento> listEvento(){
		String sql = "Select e from Evento e";
		TypedQuery<Evento> query = em.createQuery(sql, Evento.class);
		List<Evento> levento = query.getResultList();
		for(Evento eve : levento) {
			eve.getAsistenciaEventos().size();
		}
		return levento;
	}
	

	/**
	 * Gets the eventos por nombre.
	 *
	 * @param filtro the filtro
	 * @return the eventos por nombre
	 */
  
public List<Evento> getEventosPorNombre(String filtro){		
		String sql = "SELECT p FROM Evento p "
					+ "WHERE nombre like ? ";
		
		Query q = em.createQuery(sql, Evento.class);
		q.setParameter(1, "%"+filtro+"%");
		List<Evento> alumno = q.getResultList();
		return alumno;

}
	
}