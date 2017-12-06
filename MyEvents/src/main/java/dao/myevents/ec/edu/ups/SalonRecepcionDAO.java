package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.myevents.ec.edu.ups.SalonRecepcion;

@Stateless
public class SalonRecepcionDAO {
	
	
	@Inject
	private EntityManager em;
	
	/*
	 * 
	 * Metodos CRUD
	 * 
	 */
	
	
	/// Insertar y Actualizar Salon Recepcion
	public void guardar(SalonRecepcion srecepcion) {
		SalonRecepcion aux = selectSRecepcion(srecepcion.getId());
		if(aux!=null) {
			updateSRecepcion(srecepcion);
		}else {
			insertarSRecepcion(srecepcion);
		}
	}
	
	/// Agregar Salon Recepcion
	public void insertarSRecepcion(SalonRecepcion sr){
		em.merge(sr);
		System.out.println("Salon-Recepcion Grabado");		
	}
	
	/// Actualizar Salon Recepcion
	public void updateSRecepcion(SalonRecepcion sr) {
		em.merge(sr);
		System.out.println("Salon-Recepcion Update");
	}
	
	/// Eliminar Salon Recepcion
	public void removeSRecepcion(int id) {
		em.remove(selectSRecepcion(id));
		System.out.println("Salon-Recepcion Remove");
	}

	/// Leer Salon Recepcion
	public SalonRecepcion selectSRecepcion(int id) {
		SalonRecepcion  srecepcion = em.find(SalonRecepcion.class, id);
		return srecepcion;
	}

	/// Listar Salon Recepcion
	public List<SalonRecepcion> listSRecepcion() {
		String sql = "select slr from SalonRecepcion slr";
		TypedQuery<SalonRecepcion> query = em.createQuery(sql, SalonRecepcion.class);
		List<SalonRecepcion> lsrecepcion = query.getResultList();
		return lsrecepcion;
	}

	
} // fin de la clase 