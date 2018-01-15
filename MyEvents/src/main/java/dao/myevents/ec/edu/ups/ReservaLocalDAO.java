package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.myevents.ec.edu.ups.ReservaLocal;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcionDAO.
 */
@Stateless
public class ReservaLocalDAO {
	
	
	/** The em. */
	@Inject
	private EntityManager em;
	

	/**
	 * Actualizar.
	 *
	 * @param srecepcion the srecepcion
	 */
	
	public void guardar(ReservaLocal srecepcion) {
		ReservaLocal aux = selectSRecepcion(srecepcion.getId());
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

	public void insertarSRecepcion(ReservaLocal sr){
		em.merge(sr);
		System.out.println("Salon-Recepcion Grabado");		
	}
	

	/**
	 * Update Srecepcion.
	 *
	 * @param sr the sr
	 */
	
	public void updateSRecepcion(ReservaLocal sr) {
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
  
	public ReservaLocal selectSRecepcion(int id) {
		ReservaLocal  srecepcion = em.find(ReservaLocal.class, id);
		return srecepcion;
	}


	/**
	 * List Srecepcion.
	 *
	 * @return the list
	 */

	public List<ReservaLocal> listSRecepcion() {
		String sql = "select slr from SalonRecepcion slr";
		TypedQuery<ReservaLocal> query = em.createQuery(sql, ReservaLocal.class);
		List<ReservaLocal> lsrecepcion = query.getResultList();
		return lsrecepcion;
	}


} // fin de la clase 

