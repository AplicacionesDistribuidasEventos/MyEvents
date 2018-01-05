package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.*;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaDAO.
 */
@Stateless
public class CategoriaDAO {
	
	/** The em. */
	@Inject
	private EntityManager em;
	
	/**
	 * Actualizar categoria.
	 *
	 * @param c the c
	 */
	public void guardarCategoria(Categoria c){
		
		Categoria aux = leerCategoria(c.getId());
		
		if(aux!=null){
			
			actualizarCategoria(c);
			
		}else {
			
			insetarCategoria(c);
		}
		
		
	}
	
	
	/**
	 * Insetar categoria.
	 *
	 * @param c the c
	 */
	public void insetarCategoria(Categoria c){
		
		em.persist(c);
		
	}
	

	/**
	 * Actualizar categoria.
	 *
	 * @param c the c
	 */
	public void actualizarCategoria(Categoria c){
		
		em.merge(c);
		
	}
	

	/**
	 * Leer categoria.
	 *
	 * @param id the id
	 * @return the categoria
	 */
	public Categoria leerCategoria(int id){
		
		Categoria c = em.find(Categoria.class, id);
		return c;
		
	}
	

	/**
	 * Eliminar categoria.
	 *
	 * @param id the id
	 */
	
	public void eliminarCategoria(int id){
		
		Categoria c = leerCategoria(id);
		em.remove(c);
		
	}
	
	/**
	 * List categoria.
	 *
	 * @return the list
	 */
	public List<Categoria> listCategoria(){
		System.out.println("CARGAR CATEGORIASS");
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> listado = query.getResultList();
		for(Categoria c : listado) {
			if(!c.getEventos().isEmpty()) {
				c.getEventos().size();	
			}
		}
		return listado;
	}
	
	public List<Categoria> listSoloCategorias(){
		System.out.println("Select solo Categorias");
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> listado = query.getResultList();
		for(Categoria c : listado) {
			if(!c.getEventos().isEmpty()) {
				c.getEventos().size();	
			}
		}
		return listado;
	}
	
	
	public List<Categoria> listCategoriaID(int id){
		System.out.println("listCategoriaID "+id);
		String jpql = "SELECT c FROM Categoria c WHERE c.id = '"+id+"'";
		TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
		List<Categoria> listado = query.getResultList();
		for(Categoria c : listado) {
			if(!c.getEventos().isEmpty()) {
				c.getEventos().size();	
			}
		}
		return listado;
	}
	
	
	public List<Categoria> listCategoriaID(int id){
		System.out.println("listCategoriaID "+id);
		String jpql = "SELECT c FROM Categoria c WHERE c.id = '"+id+"'";
		TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
		List<Categoria> listado = query.getResultList();
		for(Categoria c : listado) {
			if(!c.getEventos().isEmpty()) {
				c.getEventos().size();	
			}
		}
		return listado;
	}
	
	
}
