package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.AsistenciaEvento;

@Stateless
public class AsistenciaEventoDAO {

	@Inject
	private EntityManager em;
	
	////////// Metodos CRUD ///////////////
	
	/// Ingresar o Actualizar Asistencia Evento
	public void guardar(AsistenciaEvento aevento) {
		AsistenciaEvento aux = selectAEvento(aevento.getCodigo());
		if(aux!=null) {
			updateAEvento(aevento);
		}else {
			insertAEvento(aevento);
		}
	}
	
	/// Insertar Asistencia Evento
	public void insertAEvento(AsistenciaEvento ae) {
		em.persist(ae);
		System.out.println("Asistencia-Evento Grabado");
	}

	///  Actuliza Asistencia Evento
	public void updateAEvento(AsistenciaEvento ae) {
		em.merge(ae);
		System.out.println("Asistencia-Evento Update");
	}

	/// Elimina Asistencia Evento
	public void removeAEvento(int id) {
		em.remove(selectAEvento(id));
		System.out.println("Asistencia-Evento Remove");
	}

	/// Lee Asistencia Evento
	public AsistenciaEvento selectAEvento(int id) {
		AsistenciaEvento aevento = em.find(AsistenciaEvento.class, id);
		return aevento;
	}

	/// Lista Asistencia Evento
	public List<AsistenciaEvento> listAEvento() {
		String sql = "select aev from AsistenciaEvento aev";
		TypedQuery<AsistenciaEvento> query = em.createQuery(sql, AsistenciaEvento.class);
		List<AsistenciaEvento> laevento = query.getResultList();
		return laevento;
	}

	
}
