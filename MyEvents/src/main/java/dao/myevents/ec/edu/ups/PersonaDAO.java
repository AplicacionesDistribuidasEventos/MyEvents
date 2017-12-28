package dao.myevents.ec.edu.ups;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import modelo.myevents.ec.edu.ups.Persona;


// TODO: Auto-generated Javadoc
/**
 * The Class PersonaDAO.
 */
@Stateless
public class PersonaDAO {

	/** The em. */
	@Inject
	private EntityManager em;


	/**
	 * Insert persona.
	 *
	 * @param p the p
	 */
	
	public void insertPersona(Persona p) {
		em.persist(p);
	}

	/**
	 * Update persona.
	 *
	 * @param p the p
	 */
	
	public void updatePersona(Persona p) {
		System.out.println("Updating..."+p.getId()+p.getCorreo()+p.getContrasenia());
		em.merge(p);
	}
	
	/**
	 * Select persona.
	 *
	 * @param id the id
	 * @return the persona
	 */
	public Persona selectPersona(int id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}

	/**
	 * Delete persona.
	 *
	 * @param id the id
	 */
	
	public void deletePersona(int id) {
		Persona p = selectPersona(id);
		em.remove(p);
	}

	/**
	 * List personas.
	 *
	 * @return the list
	 */
	
	public List<Persona> listPersonas() {
		String sql = "select p from Persona p";
		TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
		List<Persona> lpersonas = query.getResultList();
		for(Persona p : lpersonas) {
			p.getAeventos().size();
			p.getSrecepciones().size();
		}
		return lpersonas;
	}
	
	/**
	 * Guardar.
	 *
	 * @param p the p
	 */
	public void guardar (Persona p) {
		Persona aux = selectPersona(p.getId());
		System.out.println("ID GUARDAR:" +p.getId());
		if(aux!=null) {
			updatePersona(p);
		}else {
			System.out.println("Grabando!");
			insertPersona(p);	
		}
	}
	
	/**
	 * Login.
	 *
	 * @param user the user
	 * @param pass the pass
	 * @return the list
	 */
	public List<Persona> login(String user, String pass) {
		System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "Select p from Persona p WHERE p.correo = '"+user+"' AND p.contrasenia='"+pass+"'";
		TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
		List<Persona> personas = query.getResultList();
		for(Persona p : personas) {
			p.getAeventos().size();
			p.getSrecepciones().size();
		}
		return personas;
	}
	
	/**
	 * Verifica correo.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<Persona> verificaCorreo(String user)
	{
		String sql="Select p from Persona p WHERE p.correo = '"+user+"'";
		TypedQuery<Persona> query=em.createQuery(sql,Persona.class);
		List<Persona>personas=query.getResultList();
		for(Persona p : personas) {
			p.getAeventos().size();
			p.getSrecepciones().size();
		}
		return personas;
	}
	
	public List<Persona> existeCedula(String cedula) {
		String jpql = "Select p from Persona p WHERE p.cedula = '"+cedula+"'";
		TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
		List<Persona> personas = query.getResultList();
		for(Persona p : personas) {
			p.getAeventos().size();
			p.getSrecepciones().size();
		}
		return personas;
	}
	
	
	/**
	 * List persona ID (Recuperar lista de personas por el id enviado).
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<Persona> listPersonaID(int id){
		int i = 0;
		String jpql = "Select p from Persona p WHERE p.id = '"+id+"' ";
		TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
		List<Persona>personas= query.getResultList();
		for(Persona p : personas) {
			i++;
			System.out.println("INGRESA FOR : "+i);
			p.getLocales().size();
			p.getAeventos().size();
			/*Comprobar para los eventos que se dan en dicho local*/
			p.getLocales().get(0).getEvento().size();
		}
		return personas;
	}
	
}
