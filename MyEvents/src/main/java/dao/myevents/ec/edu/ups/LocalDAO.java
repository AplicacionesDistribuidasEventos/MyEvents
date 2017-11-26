package dao.myevents.ec.edu.ups;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import modelo.myevents.ec.edu.ups.Local;


@Stateless
public class LocalDAO {

	@Inject
	private EntityManager em;
	

	//Metodo para editar y guardar
	
	public void guardarLocal(Local l) {
		
		Local auxlocal = leerLocal(l.getCodigo());
		if(auxlocal!=null) {
			updateLocal(l);
			
		}else {
			insertarLocal(l);
		}
	}

	//---METODOS CRUD
	//Crear LOCAL
	
	public void insertarLocal(Local l) {
		em.persist(l);		
	}
	
	//Metodo para Actualizar LOCAL
	public void updateLocal(Local l) {
		System.out.println("Updating............"+l.getCodigo()+
				l.getNombre());
		em.merge(l);
	}
	
	//Metodo para Leer Loal
	public Local leerLocal(int id) {
		
		Local l = em.find(Local.class, id);
		return l;
		
	}
	//Metodo para Eliminar Loal
	public void deleteLocal (int id) {
		Local l = leerLocal(id);
		em.remove(l);
	}
	
	//Metodo para Listar Loal
	public List<Local> listlocal(){
		String sql = "Select l from Local l";
		TypedQuery<Local> query = em.createQuery(sql, Local.class);
		List<Local> llocal = query.getResultList();
		return llocal;
	}
	
}

	
	

