package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Persona;

@ManagedBean
public class PersonaController {

	private Persona personas;
	
	@Inject
	private PersonaDAO pdao;
	
	private List<Persona> lpersonas;
	
	@PostConstruct
	public void init() {
		personas= new Persona();
	}

	public Persona getPersonas() {
		return personas;
	}

	public void setPersonas(Persona personas) {
		this.personas = personas;
	}
	
	public List<Persona> getLpersonas() {
		return lpersonas;
	}

	public void setLpersonas(List<Persona> lpersonas) {
		this.lpersonas = lpersonas;
	}

	public String crear() {
		/*
		 * LLAMADO A LA CAPA DE NEGOCIO(VALIDAR, CONVERTIR ETC)
		 */
		personas.setPerfil("USUARIO");
		personas.setEstado("A");
		pdao.insertPersona(personas);
		System.out.println("Almacenado Usuario ..:"+personas);
		return "leer";
	}
	
	public String modificar() {
		System.out.println("Modificando Usuario..:"+personas);
		pdao.updatePersona(personas);
		return "leer";
	}
	
	public String leer(int id) {
		personas = pdao.selectPersona(id);
		return "persona";
	}
	
	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado Usuario ..:"+personas);
		return "eliminar";
	}
	
	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		
		return lpersonas;
	}
}
