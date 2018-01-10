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
	
	/** Inyeccion de dependencias para el manejo de JPA */
	@Inject
	private EntityManager em;
	
	/**
	 * Guardar el Categoria si existe la Categoria se actualiza o sino la Categoria se inserta
	 * a través de la variables aux.
	 *
	 * @param aevento the aevento
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
	 * @param c de Categoria.
	 */
	public void insetarCategoria(Categoria c){
		
		em.persist(c);
		
	}
	

	/**
	 * Actualiza la categoria a traves del em.merge
	 *
	 * @param c de Categoria
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
	 * Eliminar categoria segun el id enviado.
	 *
	 * @param id
	 */
	public void eliminarCategoria(int id){
		
		Categoria c = leerCategoria(id);
		em.remove(c);
		
	}
	
	/**
	 * listCategoria()
	 *
	 * @return Listado de todos las categorias.
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
	/**
	 * listSoloCategorias()
	 *
	 * @return Listado de todos las categorias.
	 */
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
	
	/*
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
	*/
	/**
	 * listCategoriaID enviando como parametor id
	 *
	 * @return Listado de todos las categorias segun el id.
	 */
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
	
	
}//fin clase CategoriaDAO