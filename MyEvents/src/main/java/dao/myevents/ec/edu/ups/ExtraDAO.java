package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.myevents.ec.edu.ups.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtraDAO.
 */
@Stateless
public class ExtraDAO {
	
	/** The em. */
	@Inject
	private EntityManager em;
	
	/**
	 * Guardar extra.
	 *
	 * @param e the e
	 */
	public void guardarExtra(Extra e){
		
		Extra aux = leerExtra(e.getId());
		
		if(aux!=null){
			
			actualizarExtra(e);
			
		}else {
			
			insetarExtra(e);
		}
		
		
	}
	
	/**
	 * Insetar extra.
	 *
	 * @param e the e
	 */
	public void insetarExtra(Extra e){
		
		em.persist(e);
		
	}
	
	/**
	 * Actualizar extra.
	 *
	 * @param e the e
	 */
	public void actualizarExtra(Extra e){
		
		//System.out.println("Updating............"+c.getId() +
		//		c.getDescipcion());
		em.merge(e);
		
	}
	
	/**
	 * Leer extra.
	 *
	 * @param id the id
	 * @return the extra
	 */
	public Extra leerExtra(int id){
		
		Extra e = em.find(Extra.class, id);
		return e;
		
	}
	
	/**
	 * Eliminar extra.
	 *
	 * @param id the id
	 */
	public void eliminarExtra(int id){
		
		Extra e = leerExtra(id);
		em.remove(e);
		
	}
	
	/**
	 * List extra.
	 *
	 * @return the list
	 */
	public List<Extra> listExtra(){
		
		Query query = em.createQuery("SELECT e FROM Extra e", Extra.class);
		List<Extra> listado = query.getResultList();
		return listado;
	}
	
	

}
