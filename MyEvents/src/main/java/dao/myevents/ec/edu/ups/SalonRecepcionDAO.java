package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.myevents.ec.edu.ups.SalonRecepcion;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcionDAO.
 */
@Stateless
public class SalonRecepcionDAO {
	
	
	/** The em. */
	@Inject
	private EntityManager em;
	
	
	
	
	/**
	 * Actualizar.
	 *
	 * @param srecepcion the srecepcion
	 */
	
	public void guardar(SalonRecepcion srecepcion) {
		SalonRecepcion aux = selectSRecepcion(srecepcion.getId());
		if(aux!=null) {
			updateSRecepcion(srecepcion);
		}else {
			insertarSRecepcion(srecepcion);
		}
	}
	
	/**
	 * Insertar Srecepcion.
	 *
	 * @param sr the sr
	 */
	
	public void insertarSRecepcion(SalonRecepcion sr){
		em.merge(sr);
		System.out.println("Salon-Recepcion Grabado");		
	}
	
	/**
	 * Update Srecepcion.
	 *
	 * @param sr the sr
	 */
	
	public void updateSRecepcion(SalonRecepcion sr) {
		em.merge(sr);
		System.out.println("Salon-Recepcion Update");
	}
	
	/**
	 * Removes the Srecepcion.
	 *
	 * @param id the id
	 */

	public void removeSRecepcion(int id) {
		em.remove(selectSRecepcion(id));
		System.out.println("Salon-Recepcion Remove");
	}

	/**
	 * Select Srecepcion (leer salon).
	 *
	 * @param id the id
	 * @return the salon recepcion
	 */
	
	public SalonRecepcion selectSRecepcion(int id) {
		SalonRecepcion  srecepcion = em.find(SalonRecepcion.class, id);
		return srecepcion;
	}

	/**
	 * List Srecepcion.
	 *
	 * @return the list
	 */

	public List<SalonRecepcion> listSRecepcion() {
		String sql = "select slr from SalonRecepcion slr";
		TypedQuery<SalonRecepcion> query = em.createQuery(sql, SalonRecepcion.class);
		List<SalonRecepcion> lsrecepcion = query.getResultList();
		return lsrecepcion;
	}

	
} 