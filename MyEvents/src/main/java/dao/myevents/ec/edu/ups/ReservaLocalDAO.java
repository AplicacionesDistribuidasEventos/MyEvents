package dao.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
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
/*
	public List<ReservaLocal> listSRecepcion() {
		String sql = "select slr from SalonRecepcion slr";
		TypedQuery<ReservaLocal> query = em.createQuery(sql, ReservaLocal.class);
		List<ReservaLocal> lsrecepcion = query.getResultList();
		return lsrecepcion;
	}
*/	
	
	public int maxId() {
		String jpql = "Select rl FROM ReservaLocal rl";
		TypedQuery<ReservaLocal> q = em.createQuery(jpql, ReservaLocal.class);
		List<ReservaLocal> rll = q.getResultList();
		if(rll.size()==0) {
			return 1;
		}else {
			String jpqlm = "Select max(rl.id) FROM ReservaLocal rl";
			int id = (int) em.createQuery(jpqlm).getSingleResult();
			int idnew = id+1;
			return idnew;
		}
	}
	
	public List<ReservaLocal>localesRTrue(){
		List<ReservaLocal>rlfalse = new ArrayList<ReservaLocal>();
		String jpql = "Select rl FROM ReservaLocal rl WHERE rl.estado = 'true'";
		Query query = em.createQuery(jpql, ReservaLocal.class);
		rlfalse = query.getResultList();
		return rlfalse;
	}
		
	public List<ReservaLocal> resLocasID(int id) {
		String jpql = "Select r from ReservaLocal e WHERE r.codigo = '" + id + "' ";
		TypedQuery<ReservaLocal> query = em.createQuery(jpql, ReservaLocal.class);
		List<ReservaLocal> reslocal = query.getResultList();
		
		return reslocal;
	}
		
} // fin de la clase 