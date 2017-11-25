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
	
	private int id;

	@Inject
	private PersonaDAO pdao;

	private List<Persona> lpersonas;

	@PostConstruct
	public void init() {
		personas = new Persona();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}

	public String crear() {
		if(validarCedula()==true) {
			personas.setPerfil("USUARIO");
			personas.setEstado("A");
			pdao.guardar(personas);
			System.out.println("Almacenado Usuario ..:" + personas.getId());
		}else {
			System.out.println("Cedula incorrecta");
		}

		return "leer";
	}

	public String modificar() {
		System.out.println("Modificando Usuario..:" + personas);
		pdao.updatePersona(personas);
		return "leer";
	}

	public String leer(int id) {
		personas = pdao.selectPersona(id);
		return "crear";
	}

	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado Usuario ..:" + personas);
		return "actualizar";
	}

	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		return lpersonas;
	}

	public boolean validarCedula() {
		String ced = personas.getCedula();
		int sum_t = 0;
		int res = 0;
		for (int i = 0; i < 9; i++) {
			char b = ced.charAt(i);
			int a = b - 48;
			if (i == 0) {
				a = a * 2;
			} else {
				if (i % 2 == 0) {
					a = a * 2;
				} else {
					a = a * 1;
				}
			}
			if (a > 9) {
				a = a - 9;
			}
			sum_t = sum_t + a;
		}
		res = sum_t % 10;
		if (res != 0) {
			res = 10 - res;
		}
		boolean resultado = false;
		if (res == Integer.parseInt(ced.substring(9, 10))) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}
	
	public String loadDatosEditar(int id) {
		System.out.println("Cargando...Persona a Editar" + id);
		personas = pdao.selectPersona(id);
		return "recuperaPersona";
	}

}
