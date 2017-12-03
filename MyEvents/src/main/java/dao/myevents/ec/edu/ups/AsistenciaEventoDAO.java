package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.AsistenciaEvento;
import modelo.myevents.ec.edu.ups.Persona;

@Stateless
public class AsistenciaEventoDAO {

	@Inject
	private EntityManager em;

	public void insertAEvento(AsistenciaEvento ae) {
		em.persist(ae);
		System.out.println("Asistencia-Evento Grabado");
	}

	public void updateAEvento(AsistenciaEvento ae) {
		em.merge(ae);
		System.out.println("Asistencia-Evento Update");
	}

	public void removeAEvento(int id) {
		em.remove(selectAEvento(id));
		System.out.println("Asistencia-Evento Remove");
	}

	public AsistenciaEvento selectAEvento(int id) {
		AsistenciaEvento aevento = em.find(AsistenciaEvento.class, id);
		return aevento;
	}

	public List<AsistenciaEvento> listAEvento() {
		String sql = "select aev from AsistenciaEvento aev";
		TypedQuery<AsistenciaEvento> query = em.createQuery(sql, AsistenciaEvento.class);
		List<AsistenciaEvento> laevento = query.getResultList();
		return laevento;
	}

	public void guardar(AsistenciaEvento aevento) {
		AsistenciaEvento aux = selectAEvento(aevento.getCodigo());
		if(aux!=null) {
			updateAEvento(aevento);
		}else {
			insertAEvento(aevento);
		}
	}
}
