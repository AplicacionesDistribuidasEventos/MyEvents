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

	/** Inyeccion de dependencias para el manejo de JPA */
	@Inject
	private EntityManager em;


	/**
	 * insertPersona
	 *
	 * @param p de Persona
	 */

	
	public void insertPersona(Persona p) {
		em.persist(p);
	}

	/**
	 * Actualiza la persona a traves del em.merge
	 *
	 * @param p de Persona
	 */
	public void updatePersona(Persona p) {
		System.out.println("Updating..."+p.getId()+p.getCorreo()+p.getContrasenia());
		em.merge(p);
	}
	
	/**
	 * selectAEvento
	 *
	 * @param id
	 * @return la persona encontrado a traves del id.
	 */
	public Persona selectPersona(int id) {
		Persona p = em.find(Persona.class, id);
		if(!p.getAeventos().isEmpty()) {
			p.getAeventos().size();
		}
		if(!p.getSrecepciones().isEmpty()) {
			p.getSrecepciones().size();
		}
		return p;
	}
	
	public Persona elejirPersona(int id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}

	/**
	 * Delete persona.
	 *
	 * @param id 
	 */
	
	public boolean deletePersona(int id) {
		Persona p = selectPersona(id);
		try {
			em.remove(p);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * List personas.
	 *
	 * @return una list de personas
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
	 * Guardar la persona si existe la persona se actualiza o sino la persona se inserta
	 * a través de la variables aux.
	 *
	 * @param p persona.
	 */
	
	public void guardar (Persona p) {
		Persona aux = selectPersona(p.getId());
		try {
		System.out.println("ID GUARDAR:" +p.getId());
		if(aux!=null) {
			updatePersona(p);
		}else {
			System.out.println("Grabando!");
			insertPersona(p);	
		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("coincidencia de datos" +e.getMessage());
		}
	}
	
	/**
	 * Login. SQL para encontrar a la personas a través de los parámetros.
	 *
	 * @param user the user
	 * @param pass the pass
	 * @return una list de personas con esos parámetros
	 */
	public List<Persona> login(String user, String pass) {
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
	/**
	*Verificar cedula
	*
	*@param user the user
	*@return Lista
	*/
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
		String jpql = "Select p from Persona p WHERE p.id = '"+id+"' ";
		TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
		List<Persona>personas= query.getResultList();
		System.out.println("LISTPERSID DAO");
		for(Persona p : personas) {
			p.getLocales().size();
			p.getAeventos().size();
			/*Comprobar para los eventos que se dan en dicho local*/
			if(!p.getLocales().isEmpty()) {
			p.getLocales().get(0).getEvento().size();
			}
		}
		return personas;
	}
	
}
