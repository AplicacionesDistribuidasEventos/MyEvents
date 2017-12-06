package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.myevents.ec.edu.ups.*;

@Stateless
public class CategoriaDAO {
	
	@Inject
	private EntityManager em;
	
	/*
	 * Metodo para guardar y editar
	 */
	public void guardarCategoria(Categoria c){
		
		Categoria aux = leerCategoria(c.getId());
		
		if(aux!=null){
			
			actualizarCategoria(c);
			
		}else {
			
			insetarCategoria(c);
		}
		
		
	}
	
	//---METODOS CRUD
	
	///Inserta una categoria
	public void insetarCategoria(Categoria c){
		
		em.persist(c);
		
	}
	
	////Actualizar Categoria
	public void actualizarCategoria(Categoria c){
		
		em.merge(c);
		
	}
	
	/// Lee o buscar categoria
	public Categoria leerCategoria(int id){
		
		Categoria c = em.find(Categoria.class, id);
		return c;
		
	}
	
	/// Eliminar Categoria
	public void eliminarCategoria(int id){
		
		Categoria c = leerCategoria(id);
		em.remove(c);
		
	}
	
	/// Listar categoria
	public List<Categoria> listCategoria(){
		
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> listado = query.getResultList();
		return listado;
	}
	
	
}
