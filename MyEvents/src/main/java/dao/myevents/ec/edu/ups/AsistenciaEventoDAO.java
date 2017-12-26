package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.AsistenciaEvento;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaEventoDAO.
 */
@Stateless
public class AsistenciaEventoDAO {

	/** The em. */
	@Inject
	private EntityManager em;
	
	
	
	/**
	 * Actualizar AsistenciaEvento.
	 *
	 * @param aevento the aevento
	 */
	
	public void guardar(AsistenciaEvento aevento) {
		AsistenciaEvento aux = selectAEvento(aevento.getCodigo());
		if(aux!=null) {
			updateAEvento(aevento);
		}else {
			insertAEvento(aevento);
		}
	}
	
	/**
	 * Insert A evento.
	 *
	 * @param ae the ae
	 */
	
	public void insertAEvento(AsistenciaEvento ae) {
		em.persist(ae);
		System.out.println("Asistencia-Evento Grabado");
	}

	/**
	 * Update A evento.
	 *
	 * @param ae the ae
	 */
	
	public void updateAEvento(AsistenciaEvento ae) {
		em.merge(ae);
		System.out.println("Asistencia-Evento Update");
	}

	/**
	 * Removes the A evento.
	 *
	 * @param id the id
	 */
	
	public void removeAEvento(int id) {
		em.remove(selectAEvento(id));
		System.out.println("Asistencia-Evento Remove");
	}

	/**
	 * Select A evento.
	 *
	 * @param id the id
	 * @return the asistencia evento
	 */

	public AsistenciaEvento selectAEvento(int id) {
		AsistenciaEvento aevento = em.find(AsistenciaEvento.class, id);
		return aevento;
	}

	/**
	 * List A evento.
	 *
	 * @return the list
	 */
	
	public List<AsistenciaEvento> listAEvento() {
		String sql = "select aev from AsistenciaEvento aev";
		TypedQuery<AsistenciaEvento> query = em.createQuery(sql, AsistenciaEvento.class);
		List<AsistenciaEvento> laevento = query.getResultList();
		return laevento;
	}

	
}
