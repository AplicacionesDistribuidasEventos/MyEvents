package dao.myevents.ec.edu.ups;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.Evento;



@Stateless
public class EventoDAO {

	@Inject
	private EntityManager em;
	
	
	
	//Metodo para editar y guardar
	
	public void guardarEvento(Evento e) {
		
		
		Evento auxe = leerEvento(e.getCodigo());
		if(auxe!=null) {
			updateEvento(e);
		}else {
			insertarEvento(e);
		}
	}
	
	//---METODOS CRUD
	//Crear Evento
	
	public void insertarEvento(Evento e) {
		em.persist(e);		
	}
	
	//Metodo para Actualizar Evento
	public void updateEvento(Evento e) {
		System.out.println("Updating............"+e.getCodigo()+
				e.getNombre());
		em.merge(e);
	} 
	
	//Metodo para Leer Evento
	public Evento leerEvento(int id) {
		
		Evento e = em.find(Evento.class, id);
		return e;
		
	}
	//Metodo para Eliminar Evento
	public void deleteEvento (int id) {
		Evento e = leerEvento(id);
		em.remove(e);
	}
	
	//Metodo para Listar Eventos
	public List<Evento> listEvento(){
		String sql = "Select e from Evento e";
		TypedQuery<Evento> query = em.createQuery(sql, Evento.class);
		List<Evento> levento = query.getResultList();
		return levento;
	}
	
	///Filtro de busqueda para los eventos...
	
	
	
public List<Evento> getEventosPorNombre(String filtro){
		
		
		String sql = "SELECT p FROM Evento p "
					+ "WHERE nombre like ? ";
		
		Query q = em.createQuery(sql, Evento.class);
		q.setParameter(1, "%"+filtro+"%");
		List<Evento> alumno = q.getResultList();
		return alumno;
}
	
}
