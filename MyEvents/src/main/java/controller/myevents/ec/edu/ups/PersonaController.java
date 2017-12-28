package controller.myevents.ec.edu.ups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.hibernate.validator.constraints.NotBlank;

import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;
import modelo.myevents.ec.edu.ups.PersonaLocalEvento;
import utilidades.myevents.ec.edu.ups.SessionUtils;
import validacionesnegocio.myevents.ec.edu.ups.Validacion;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonaController.
 */
@ManagedBean
@SessionScoped
public class PersonaController {
	@Inject
	private Logger log;

	/** The personas. */
	private Persona personas = null;
	private Validacion v;

	/** The id. */
	private int id;

	/** The pactual. */
	private String pactual;

	/** The id edit user. */
	private int idEditUser;

	/** The contrasenia. */

	@NotBlank(message = "Ingrese las contrasenias")
	private String contrasenia;

	/** The conincidencia. */
	private String conincidencia;

	/** The Loginexiste. */
	private String Loginexiste;

	/**
	 * The nusuario. Varibles donde se almacena los valores de la consulta
	 * maestro-detalles
	 */
	private String nusuario;

	/** The nlocal. */
	private String nlocal;

	/** The ndescripcion. */
	private String ndescripcion;

	/** The ncapacidad. */
	private String ncapacidad;

	/** The ncosto. */
	private String ncosto;

	/** The idrecuprerar. */
	private int idrecuprerar;
	
	/*PARA OBTENER EL LISTADO PERSONA y el listado de todos los objetos definidos en PERSONA, LOCAL, EVENTO
	**/
	/** The List per ID. */
	private List<Persona> ListPerID;
	
	/** The */
	private List<PersonaLocalEvento> plelist;

	/** The pdao. */
	@Inject
	private PersonaDAO pdao;

	/** The lpersonas. */
	private List<Persona> lpersonas;

	/** The my user. */
	private Persona myUser;

	/** The e nombre */
	private String enombre;
	/** The e fecha */
	private String efecha;
	/** The e descripcion */
	private String edescripcion;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		personas = new Persona();
		lpersonas = listaPersonas();
		ListPerID = new ArrayList<Persona>();
		plelist = new ArrayList<PersonaLocalEvento>();
		v = new Validacion();
		consultaLocalEventos();
	}

	/*
	 * 
	 * Inicio de Getter and Setter
	 */

	/**
	 * Gets the nusuario.
	 *
	 * @return the nusuario
	 */
	public String getNusuario() {
		return nusuario;
	}

	/**
	 * Sets the nusuario.
	 *
	 * @param nusuario
	 *            the new nusuario
	 */
	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	/**
	 * Gets the nlocal.
	 *
	 * @return the nlocal
	 */
	public String getNlocal() {
		return nlocal;
	}

	/**
	 * Sets the nlocal.
	 *
	 * @param nlocal
	 *            the new nlocal
	 */
	public void setNlocal(String nlocal) {
		this.nlocal = nlocal;
	}

	/**
	 * Gets the ndescripcion.
	 *
	 * @return the ndescripcion
	 */
	public String getNdescripcion() {
		return ndescripcion;
	}

	/**
	 * Sets the ndescripcion.
	 *
	 * @param ndescripcion
	 *            the new ndescripcion
	 */
	public void setNdescripcion(String ndescripcion) {
		this.ndescripcion = ndescripcion;
	}

	/**
	 * Gets the ncapacidad.
	 *
	 * @return the ncapacidad
	 */
	public String getNcapacidad() {
		return ncapacidad;
	}

	/**
	 * Sets the ncapacidad.
	 *
	 * @param ncapacidad
	 *            the new ncapacidad
	 */
	public void setNcapacidad(String ncapacidad) {
		this.ncapacidad = ncapacidad;
	}

	/**
	 * Gets the ncosto.
	 *
	 * @return the ncosto
	 */
	public String getNcosto() {
		return ncosto;
	}

	/**
	 * Sets the ncosto.
	 *
	 * @param ncosto
	 *            the new ncosto
	 */
	public void setNcosto(String ncosto) {
		this.ncosto = ncosto;
	}

	/**
	 * Gets the idrecuprerar.
	 *
	 * @return the idrecuprerar
	 */
	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	/**
	 * Sets the idrecuprerar.
	 *
	 * @param idrecuprerar
	 *            the new idrecuprerar
	 */
	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		loadid(idrecuprerar);
		System.out.println("BEFORE CONSULTALOCAEVENTOS");
		consultaLocalEventos();
	}

	/**
	 * Gets the list per ID.
	 *
	 * @return the list per ID
	 */
	public List<Persona> getListPerID() {
		return ListPerID;
	}

	/**
	 * Sets the list per ID.
	 *
	 * @param listPerID
	 *            the new list per ID
	 */
	public void setListPerID(List<Persona> listPerID) {
		ListPerID = listPerID;
	}

	/**
	 * Gets the contrasenia.
	 *
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Sets the contrasenia.
	 *
	 * @param contrasenia
	 *            the new contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Gets the conincidencia.
	 *
	 * @return the conincidencia
	 */
	public String getConincidencia() {
		return conincidencia;
	}

	/**
	 * Sets the conincidencia.
	 *
	 * @param conincidencia
	 *            the new conincidencia
	 */
	public void setConincidencia(String conincidencia) {
		this.conincidencia = conincidencia;
	}

	/**
	 * Gets the loginexiste.
	 *
	 * @return the loginexiste
	 */
	public String getLoginexiste() {
		return Loginexiste;
	}

	/**
	 * Sets the loginexiste.
	 *
	 * @param loginexiste
	 *            the new loginexiste
	 */
	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
	}

	/**
	 * Gets the personas.
	 *
	 * @return the personas
	 */
	public Persona getPersonas() {
		return personas;
	}

	/**
	 * Sets the personas.
	 *
	 * @param personas
	 *            the new personas
	 */
	public void setPersonas(Persona personas) {
		this.personas = personas;
	}

	/**
	 * Gets the lpersonas.
	 *
	 * @return the lpersonas
	 */
	public List<Persona> getLpersonas() {
		return lpersonas;
	}

	/**
	 * Sets the lpersonas.
	 *
	 * @param lpersonas
	 *            the new lpersonas
	 */
	public void setLpersonas(List<Persona> lpersonas) {
		this.lpersonas = lpersonas;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
		loadDatosEditar(id);
	}

	/**
	 * Gets the id edit user.
	 *
	 * @return the id edit user
	 */
	public int getIdEditUser() {
		return idEditUser;
	}

	/**
	 * Sets the id edit user.
	 *
	 * @param idEditUser
	 *            the new id edit user
	 */
	public void setIdEditUser(int idEditUser) {
		this.idEditUser = idEditUser;
		loadDatosEditar(idEditUser);
	}

	/**
	 * Gets the pdao.
	 *
	 * @return the pdao
	 */
	public PersonaDAO getPdao() {
		return pdao;
	}

	/**
	 * Sets the pdao.
	 *
	 * @param pdao
	 *            the new pdao
	 */
	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}

	/**
	 * Gets the my user.
	 *
	 * @return the my user
	 */
	public Persona getMyUser() {
		return myUser;
	}

	/**
	 * Sets the my user.
	 *
	 * @param myUser
	 *            the new my user
	 */
	public void setMyUser(Persona myUser) {
		this.myUser = myUser;
	}

	/**
	 * Redirectmain admin.
	 *
	 * @return the string
	 */
	public String redirectmainAdmin() {
		return "mainAmin.xhtml";
	}

	/**
	 * Crear. Creacion del objeto Persona condicinamiento segun las sentencias de
	 * validacion
	 */

	public void crear() {
		if (coincidirContrasenia() == true) {
			if (v.validarCedula(personas.getCedula()) == true) {
				if (v.validarCorreo(personas.getCorreo()) == true) {
					// SI ESQUE EXISTE UN MISMO EMAIL
					if (pdao.verificaCorreo(personas.getCorreo()).size() == 0) {
						if (pdao.existeCedula(personas.getCedula()).size() == 0) {
							personas.setPerfil("USUARIO");
							personas.setEstado("A");
							pdao.guardar(personas);
							inicializar();
							init();
							this.conincidencia = "Grabado exitoso!";
						} else {
							this.conincidencia = "La cedula ya se encuentra registrada";
						}
					} else {
						this.conincidencia = "El correo ya se encuentra registrado";
					}
				} else {
					this.conincidencia = "El formato del correo es incorrecto";
				}
			} else {
				System.out.println("Cedula incorrecta");
				this.conincidencia = "La cedula es incorrecta";
			}
		} else {
			this.conincidencia = "Ingrese las mismas contrasenias";
		}
	}

	/**
	 * Coincidir contrasenia. Comparacion de los 2 campos referentes a la
	 * contrasenia, devolucion(true/false), segun sea la cedula valida o no valida
	 * respectivamente.
	 * 
	 * @return true, if successful
	 */

	public boolean coincidirContrasenia() {
		if (personas.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inicializar. Setea las variable como vacias, ocupado al momento de haber
	 * creado el usuario y dejar los h:inputText del JSF en blanco
	 */

	public void inicializar() {
		personas.setCedula("");
		personas.setApellido("");
		personas.setNombre("");
		personas.setContrasenia("");
	}

	/**
	 * Modificar. Modificacion de los objetos de tipo Persona(USUARIO/ADMIN)
	 * 
	 * @return the string
	 */

	public String modificar() {
		try {
			System.out.println(personas.getPerfil());
			if (myUser.getPerfil().equals("USUARIO")) {
				personas.setContrasenia(pactual);
				pdao.updatePersona(personas);
				return "mainUser";
			} else if (myUser.getPerfil().equals("ADMIN")) {
				personas.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + personas.getCedula());
				System.out.println("ELSE IF ADMIN");
				pdao.updatePersona(personas);
				return "mainAdmin";

			} else if (myUser.getPerfil().equals("ADMIN-SUPER")) {
				personas.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + personas.getCedula());
				System.out.println("ELSE IF ADMIN");
				pdao.updatePersona(personas);
				return "pages-blank";

			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo Leer. Dirije a el archivo crearPersona, dado como parametro un Id
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 */

	public String leer(int id) {
		personas = pdao.selectPersona(id);
		return "crearPersona";
	}

	/**
	 * Eliminar. Metodo eliminar, llama al metodo Delete de PersonaDAO, parametro
	 * Id, para eliminar un registro especifica
	 * 
	 * @param id
	 *            the id
	 * @return the string
	 */

	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado Usuario ..:" + personas);
		return "actualizar";
	}

	/**
	 * Lista personas, devuelve un objeto Listado de tipo Persona(Devuelve todas las
	 * personas).
	 *
	 * @return the list
	 */

	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		return lpersonas;
	}

	/**
	 * Load datos editar.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String loadDatosEditar(int id) {
		System.out.println("Cargando...Persona a Editar" + id);
		personas = pdao.selectPersona(id);
		pactual = personas.getContrasenia();
		if (personas.getPerfil().equals("USUARIO")) {
			return "recuperaPersona";
		} else if (personas.getPerfil().equals("ADMIN")) {
			System.out.println("LOAD DATOS ");
			return "editAdmin";
		}
		return null;
	}

	/**
	 * IniciarSesion inicilizar una Sesion HTTP y establecimiento de parametros en
	 * session, FacesContext acceso tanto al contexto de JSF como HTTP.
	 */

	public void iniciarSesion() {
		if (pdao.login(personas.getCorreo(), personas.getContrasenia()).size() != 0) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getEstado());
			this.Loginexiste = " ";
			FacesContext contex = FacesContext.getCurrentInstance();
			if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil().equals("USUARIO")) {

				System.out.println("CONTEXTO USER");
				try {
					contex.getExternalContext().redirect("mainUser.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN-SUPER")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("pages-blank.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil().equals("ADMIN")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				System.out.println("CONTEXTO ADMINN");
				try {
					contex.getExternalContext().redirect("mainAdmin.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		personas.setCorreo("");
		personas.setContrasenia("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}

	/**
	 * Cargar datos usuario obtenidos en session(HTTP) y su respectiva validacion.
	 */

	public void cargarDatosUsuario() {
		myUser = new Persona();
		HttpSession session = SessionUtils.getSession();
		String nus = (String) session.getAttribute("username");
		System.out.println("NUS " + nus);
		try {
			if (pdao.verificaCorreo(nus).size() != 0) {
				List<Persona> lusuario = new ArrayList<Persona>();
				lusuario = pdao.verificaCorreo(nus);
				myUser = lusuario.get(0);
				System.out.println("MYUSER EMAIL: " + myUser.getCorreo());
			} else {
				FacesContext contex = FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("Error al cargar");
			e.printStackTrace();
		}

	}

	/**
	 * Cerrar sesion. Metodo Utilizado para la eliminacion de una sesion HTTP, con
	 * su respectiva navegacion
	 * 
	 * @return the string
	 */

	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index.xhtml";
	}

	/**
	 * Verifica sesion.
	 */

	public void verificaSesion() {
		HttpSession session = SessionUtils.getSession();
		String nusv = (String) session.getAttribute("username");
		if (nusv != null) {
			System.out.println("si tiene sesion");
			FacesContext contex = FacesContext.getCurrentInstance();
			try {
				if (myUser.getPerfil().equals("USUARIO")) {
					contex.getExternalContext().redirect("mainUser.html");
				} else if (myUser.getPerfil().equals("ADMIN")) {
					contex.getExternalContext().redirect("mainAdmin.html");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* GETER AND SETTERSS: Nombre y Fecha del Evento */
	public String getEnombre() {
		return enombre;
	}

	public void setEnombre(String enombre) {
		this.enombre = enombre;
	}

	public String getEfecha() {
		return efecha;
	}

	public void setEfecha(String efecha) {
		this.efecha = efecha;
	}

	public String getEdescripcion() {
		return edescripcion;
	}

	public void setEdescripcion(String edescripcion) {
		this.edescripcion = edescripcion;
	}

	public List<PersonaLocalEvento> getPlelist() {
		return plelist;
	}

	public void setPlelist(List<PersonaLocalEvento> plelist) {
		this.plelist = plelist;
	}

	/**
	 * Consulta local eventos.
	 *
	 * @return the string
	 */
	public String consultaLocalEventos() {
		plelist.clear();
//		System.out.println("CONSULTA LOCAL EVENTO ID: " + idrecuprerar + " " + "ENTRA");
		ListPerID = pdao.listPersonaID(idrecuprerar);

		/* PRIMER FOR: Recupero todo el Objeto Persona, Local, Evento */
		for (Persona p : ListPerID) {
			/* Segundo FOR: a partir de 'p', obtengo el objeto evento */
			for (Local l : p.getLocales()) {
				PersonaLocalEvento ple = new PersonaLocalEvento();
				ple.setL_nombre(l.getNombre());
				ple.setL_descripcion(l.getDescripcion());
				ple.setL_capacidad(l.getCapacidad());
				ple.setL_costo(l.getCosto());
				/* Condicionamiento cuando no hay ningun evento, agrego campos pro default */
				if (l.getEvento().isEmpty()) {
					System.out.println("LOCAL SIN EVENTOS");
					ple.setE_nombre("Ningun Registro");
					ple.setE_descripcion(" ");
					ple.setE_fecha(" ");
				} else {
					/* Tercer FOR: a partir de 'l' obtengo el objeto Evento */
					for (Evento ev : l.getEvento()) {
						/*
						 * Declaro un objeto para setear cada uno de los objeto y finalmente agregarlos
						 * en la lista respectiva
						 */

						ple.setE_nombre(ev.getNombre());
						ple.setE_descripcion(ev.getDescripcion());
						ple.setE_fecha(ev.getFechaEvento().toString());
					}
				}
				/* Anadiendo cada uno de los objetos a la lista */
				plelist.add(ple);
			}
		}
		return null;
	}

	/**
	 * Loadid.
	 *
	 * @param id
	 *            the id
	 */
	public void loadid(int id) {
		idrecuprerar = id;

	}

	/**
	 * Loadid user.
	 *
	 * @param id
	 *            the id
	 */
	public void loadidUser(int id) {
		idEditUser = id;
	}
}
