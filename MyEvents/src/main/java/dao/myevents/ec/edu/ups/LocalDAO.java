package dao.myevents.ec.edu.ups;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

 
import modelo.myevents.ec.edu.ups.Local;


// TODO: Auto-generated Javadoc
/**
 * The Class LocalDAO.
 */
@Stateless
public class LocalDAO {

	/** The em. */
	@Inject
	private EntityManager em;
	

		
	/**
	 * Actualizar local.
	 *
	 * @param l the l
	 */
	public void guardarLocal(Local l) {
		
		Local auxlocal = leerLocal(l.getCodigo());
		if(auxlocal!=null) {
			updateLocal(l);
			
		}else {
			insertarLocal(l);
		}
	}

		
	/**
	 * Insertar local.
	 *
	 * @param l the l
	 */
	public void insertarLocal(Local l) {
		em.persist(l);		
	}
	
	/**
	 * Update local.
	 *
	 * @param l the l
	 */
	
	public void updateLocal(Local l) {
		System.out.println("Updating............"+l.getCodigo()+
				l.getNombre());
		em.merge(l);
	}
	
	/**
	 * Leer local.
	 *
	 * @param id the id
	 * @return the local
	 */
	
	public Local leerLocal(int id) {
		
		Local l = em.find(Local.class, id);
		return l;
		
	}
	
	/**
	 * Delete local.
	 *
	 * @param id the id
	 */
	
	public void deleteLocal (int id) {
		Local l = leerLocal(id);
		em.remove(l);
	}
	
	/**
	 * Listlocal.
	 *
	 * @return the list
	 */

	public List<Local> listlocal(){
		String sql = "Select l from Local l";
		TypedQuery<Local> query = em.createQuery(sql, Local.class);
		List<Local> llocal = query.getResultList();
		return llocal;
	}
	
}

	
	

