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

	/** Inyeccion de dependencias para el manejo de JPA */
	@Inject
	private EntityManager em;
	

	/**
	 * Guardar el evento si existe el eventoAsis se actualiza o sino el evento se inserta
	 * a través de la variables aux.
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
	 * Insert A eventoAsis.
	 *
	 * @param ae de AsistenciaEvento
	 */
	public void insertAEvento(AsistenciaEvento ae) {
		em.persist(ae);
		System.out.println("Asistencia-Evento Grabado");
	}


	/**
	 * Actualiza el eventoAsis a traves del em.merge
	 *
	 * @param ae de AsistenciaEvento
	 */
	public void updateAEvento(AsistenciaEvento ae) {
		em.merge(ae);
		System.out.println("Asistencia-Evento Update");
	}

	/**
	 * Eliminar el eventoAsis segun el id enviado.
	 *
	 * @param id
	 */
	public void removeAEvento(int id) {
		em.remove(selectAEvento(id));
		System.out.println("Asistencia-Evento Remove");
	}


	/**
	 * selectAEvento
	 *
	 * @param id
	 * @return el eventoAsis encontrado a traves del id.
	 */
	public AsistenciaEvento selectAEvento(int id) {
		AsistenciaEvento aevento = em.find(AsistenciaEvento.class, id);
		return aevento;
	}

	/**
	 * listAEvento()
	 *
	 * @return Listado de todos los eventos
	 */
	public List<AsistenciaEvento> listAEvento() {
		String sql = "select aev from AsistenciaEvento aev";
		TypedQuery<AsistenciaEvento> query = em.createQuery(sql, AsistenciaEvento.class);
		List<AsistenciaEvento> laevento = query.getResultList();
		return laevento;
	}

	
}
