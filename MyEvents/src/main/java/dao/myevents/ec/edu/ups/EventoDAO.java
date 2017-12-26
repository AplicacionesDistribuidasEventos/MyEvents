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

	/** The em. */
	@Inject
	private EntityManager em;
	
	
	/**
	 * Actualizar evento.
	 *
	 * @param e the e
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
	 * Insertar evento.
	 *
	 * @param e the e
	 */
	public void insertarEvento(Evento e) {
		em.persist(e);		
	}
	
	/**
	 * Update evento.
	 *
	 * @param e the e
	 */
	
	public void updateEvento(Evento e) {
		System.out.println("Updating............"+e.getCodigo()+
				e.getNombre());
		em.merge(e);
	} 
	
	/**
	 * Leer evento.
	 *
	 * @param id the id
	 * @return the evento
	 */
	
	public Evento leerEvento(int id) {
		
		Evento e = em.find(Evento.class, id);
		return e;
		
	}
	
	/**
	 * Delete evento.
	 *
	 * @param id the id
	 */
	
	public void deleteEvento (int id) {
		Evento e = leerEvento(id);
		em.remove(e);
	}
	
	/**
	 * List evento.
	 *
	 * @return the list
	 */
	
	public List<Evento> listEvento(){
		String sql = "Select e from Evento e";
		TypedQuery<Evento> query = em.createQuery(sql, Evento.class);
		List<Evento> levento = query.getResultList();
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