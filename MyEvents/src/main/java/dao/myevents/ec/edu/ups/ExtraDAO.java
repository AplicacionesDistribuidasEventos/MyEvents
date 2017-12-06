package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.myevents.ec.edu.ups.*;

@Stateless
public class ExtraDAO {
	
	@Inject
	private EntityManager em;
	
	public void guardarExtra(Extra e){
		
		Extra aux = leerExtra(e.getId());
		
		if(aux!=null){
			
			actualizarExtra(e);
			
		}else {
			
			insetarExtra(e);
		}
		
		
	}
	
	public void insetarExtra(Extra e){
		
		em.persist(e);
		
	}
	
	public void actualizarExtra(Extra e){
		
		//System.out.println("Updating............"+c.getId() +
		//		c.getDescipcion());
		em.merge(e);
		
	}
	
	public Extra leerExtra(int id){
		
		Extra e = em.find(Extra.class, id);
		return e;
		
	}
	
	public void eliminarExtra(int id){
		
		Extra e = leerExtra(id);
		em.remove(e);
		
	}
	
	public List<Extra> listExtra(){
		
		Query query = em.createQuery("SELECT e FROM Extra e", Extra.class);
		List<Extra> listado = query.getResultList();
		return listado;
	}
	
	

}
