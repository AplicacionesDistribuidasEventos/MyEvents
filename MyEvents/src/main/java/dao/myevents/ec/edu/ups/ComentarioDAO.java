package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.Comentario;

@Stateless
public class ComentarioDAO {

	@Inject
	private EntityManager em;

	public void insertComentario(Comentario c) {
		Comentario comentario = leerComentario(c.getId());
		if (comentario == null) {
			em.persist(c);
		} else {
			// SE DEBE ACTUALIZAR LOS COMENTARIOS
		}
	}

	public Comentario leerComentario(int id) {
		return em.find(Comentario.class, id);
	}

	public void deleteComentario(int id) {
		em.remove(leerComentario(id));
	}

	public List<Comentario> listComentario() {
		String jpql = "Select c FROM Comentario c";
		TypedQuery<Comentario> query = em.createQuery(jpql, Comentario.class);
		List<Comentario> comentarios = query.getResultList();
		return comentarios;
	}

	public int MaxID() {
		String jpql = "Select c FROM Comentario c";
		TypedQuery<Comentario> q = em.createQuery(jpql, Comentario.class);
		List<Comentario> comentarios = q.getResultList();

		if (comentarios.size() == 0) {
			return 1;
		} else {
			String jpqls = "Select max(c.id) FROM Comentario c";
			int id = (int) em.createQuery(jpqls).getSingleResult();
			int mid = id + 1;
			return mid;
		}
	}

}
