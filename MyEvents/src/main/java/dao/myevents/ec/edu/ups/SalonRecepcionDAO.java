package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.SalonRecepcion;

@Stateless
public class SalonRecepcionDAO {
	
	
	@Inject
	private EntityManager em;
	
	public void guardarSalonRepcion(){
		
		
	}
	
	
	public void insertarSalonRecepcion(){
		
		
	}
	
	
	public void ActualizaSalonRecepcion(){
		
	}
	
	public SalonRecepcion leerSalonRecepcion(int id){
		
		SalonRecepcion sr = em.find(SalonRecepcion.class, id);
		return sr;
		
	}
	
	public void eliminarSalonRecepcion(){
		
	}
	
	public List<SalonRecepcion> listSalonRecepcion(){
		Query query = em.createQuery("SELECT sr FROM SalonRecepcion sr", SalonRecepcion.class);
		List<SalonRecepcion> listado = query.getResultList();
		return listado;
	}

}
