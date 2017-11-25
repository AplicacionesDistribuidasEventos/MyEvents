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
	
	public void guardarCategoria(Categoria c){
		
		Categoria aux = leerCategoria(c.getId());
		
		if(aux!=null){
			
			actualizarCategoria(c);
			
		}else {
			
			insetarCategoria(c);
		}
		
		
	}
	
	public void insetarCategoria(Categoria c){
		
		em.persist(c);
		
	}
	
	public void actualizarCategoria(Categoria c){
		
		//System.out.println("Updating............"+c.getId() +
		//		c.getDescipcion());
		em.merge(c);
		
	}
	
	public Categoria leerCategoria(int id){
		
		Categoria c = em.find(Categoria.class, id);
		return c;
		
	}
	
	public void eliminarCategoria(int id){
		
		Categoria c = leerCategoria(id);
		em.remove(c);
		
	}
	
	public List<Categoria> listCategoria(){
		
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		List<Categoria> listado = query.getResultList();
		return listado;
	}
	
	/*public List<Evento> listEvento(){
		String sql = "Select e from Evento e";
		TypedQuery<Evento> query = em.createQuery(sql, Evento.class);
		List<Evento> levento = query.getResultList();
		return levento;
	}*/

}
