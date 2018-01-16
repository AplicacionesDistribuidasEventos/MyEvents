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
	
	/** Inyeccion de dependecias a los DAO que se van a utilizar */
	@Inject
	private Logger log;
	@Inject
	private PersonaDAO pdao;

	/** Variables de lista y de objetos utilizados */
	private Persona personas = null;
	private Validacion v;
	private int id;
	private String pactual;
	private int idEditUser;
	private String conincidencia;
	private String Loginexiste;
	private String nlocal;
	private String ndescripcion;
	private String ncapacidad;
	private String ncosto;
	private int idrecuprerar;

	@NotBlank(message = "Ingrese las contrasenias")
	private String contrasenia;

	private Persona myUser;
	private String enombre;
	private String efecha;
	private String edescripcion;
	/**
	 * The nusuario. Varibles donde se almacena los valores de la consulta
	 * maestro-detalles
	 */
	private String nusuario;
	
	/**PARA OBTENER EL LISTADO PERSONA y el listado de todos los objetos definidos en PERSONA, LOCAL, EVENTO*/
	private List<Persona> ListPerID;
	private List<PersonaLocalEvento> plelist;
	private List<Persona> lpersonas;

	
	/** `Variable utilizada la almacenar los locales que pertences unicamente al ADMIN del local */
	private List<Local> listLocales;

	/**
	 *  Metodo para inicializar los metodos e instancias.
	 */
	@PostConstruct
	public void init() {
		personas = new Persona();
		lpersonas = listaPersonas();
		ListPerID = new ArrayList<Persona>();
		plelist = new ArrayList<PersonaLocalEvento>();
		v = new Validacion();
		consultaLocalEventos();
		listLocales = new ArrayList<Local>();
		consultarLocalesADMIN(id);
	}

	/**
	 * Geters y Seters de las variables definidas.
	 */
	public String getNusuario() {
		return nusuario;
	}

	public List<Local> getListLocales() {
		return listLocales;
	}

	public void setListLocales(List<Local> listLocales) {
		this.listLocales = listLocales;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public String getNlocal() {
		return nlocal;
	}

	
	public void setNlocal(String nlocal) {
		this.nlocal = nlocal;
	}


	public String getNdescripcion() {
		return ndescripcion;
	}

	
	public void setNdescripcion(String ndescripcion) {
		this.ndescripcion = ndescripcion;
	}

	
	public String getNcapacidad() {
		return ncapacidad;
	}

	
	public void setNcapacidad(String ncapacidad) {
		this.ncapacidad = ncapacidad;
	}

	public String getNcosto() {
		return ncosto;
	}

	public void setNcosto(String ncosto) {
		this.ncosto = ncosto;
	}

	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	/**
	 * Sets the idrecuprerar.
	 *Metodo para setear el idrecuperar 
	 * @param idrecuprerar para recuperar el id de persona para navegailidad.           
	 */

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		loadid(idrecuprerar);
		System.out.println("BEFORE CONSULTALOCAEVENTOS");
		consultaLocalEventos();
	}

	public List<Persona> getListPerID() {
		return ListPerID;
	}

	public void setListPerID(List<Persona> listPerID) {
		ListPerID = listPerID;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConincidencia() {
		return conincidencia;
	}

	public void setConincidencia(String conincidencia) {
		this.conincidencia = conincidencia;
	}

	public String getLoginexiste() {
		return Loginexiste;
	}

	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
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

	public int getIdEditUser() {
		return idEditUser;
	}

	/**
	 * Sets the id edit user.
	 *
	 * @param idEditUser
	 * the new id edit user
	 */
	public void setIdEditUser(int idEditUser) {
		this.idEditUser = idEditUser;
		loadDatosEditar(idEditUser);
	}

	public PersonaDAO getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDAO pdao) {
		this.pdao = pdao;
	}

	public Persona getMyUser() {
		return myUser;
	}

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
	 * validacion. Método utiliza ajax através de JSF.
	 * @param coincidencia: Muestra mensajes de validacion en los campos mencionados para el registro de usuarios
	 */

	public void crear() {
		try {
			
		
		if (coincidirContrasenia() == true) {
			if (v.validarCedula(personas.getCedula()) == true) {
				if (v.validarCorreo(personas.getCorreo()) == true) {
					// SI ESQUE EXISTE UN MISMO EMAIL
					if (pdao.verificaCorreo(personas.getCorreo()).size() == 0) {
						if (pdao.existeCedula(personas.getCedula()).size() == 0) {
							personas.setPerfil("USUARIO");
							personas.setEstado("A");
//							pdao.guardar(personas);
							pdao.insertPersona(personas);
							inicializar();
							init();
							this.conincidencia = "Grabado exitoso!";
						} else {
							this.conincidencia = "La cedula ya se encuentra registrada";
						}
					} else {
						System.out.println("correo ya se encuentra registrado");
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
			System.out.println("Contrasenias ingresadas son diferentes");
			this.conincidencia = "Ingrese las mismas contrasenias";
		}
		} catch (Exception e) {
			System.out.println("Error general al crear el usuario" + e.getMessage());
			e.printStackTrace();
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
		try {
		
		if (personas.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
		
		} catch (Exception e) {
			System.out.println("Contrasenias diferentes"+ e.getMessage());
		}
		return false;
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
	 *            
	 * @return direccionalidad en JSP crearPersonas, método utilizado para el SUPERADMIN.
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
	 * @return una lista de todas la personas.
	 */

	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		return lpersonas;
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
	
	public void loadid(int id) {
		idrecuprerar = id;

	}
	public void loadidUser(int id) {
		idEditUser = id;
	}

	/**
	 * Load datos editar.
	 *
	 * @param id int id
	 * Obtener las personas segun su rol y cargar la pagina.
	 *           
	 * @return URL de navegabilidad
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
	public static int idUsuario;
	
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
			
			List<Persona> pers =pdao.login(personas.getCorreo(), personas.getContrasenia());
			idUsuario = pers.get(0).getId();

			
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
				
				consultarLocalesADMIN(idUsuario);
				System.out.println("consulta local adminn" + idUsuario);

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
	 * Metodo para comparar la sesion entre los diferentes usuarios en la aplicacion web
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

	
	/**
	 * Consulta local eventos: Retorna los eventos que se van a sucitar en cada uno de los salones de eventos, 
	 * lo visualiza su respectivo propietario(ADMIN)
	 * Variable global: plelist, que contiene los eventos y es cargada en el formulario
	 * @return the string
	 */
	public String consultaLocalEventos() {
		PersonaLocalEvento ple = new PersonaLocalEvento();
		plelist.clear();
//		System.out.println("CONSULTA LOCAL EVENTO ID: " + idrecuprerar + " " + "ENTRA");
		ListPerID = pdao.listPersonaID(idrecuprerar);

		/* PRIMER FOR: Recupero todo el Objeto Persona, Local, Evento */
		for (Persona p : ListPerID) {
			/* Segundo FOR: a partir de 'p', obtengo el objeto evento */
			for (Local l : p.getLocales()) {
				
				/* Condicionamiento cuando no hay ningun evento, agrego campos pro default */
				if (l.getEvento().isEmpty()) {
					ple = new PersonaLocalEvento();
					ple.setL_nombre(l.getNombre());
					ple.setL_descripcion(l.getDescripcion());
					ple.setL_capacidad(l.getCapacidad());
					ple.setL_costo(l.getCosto());
					ple.setE_nombre("No ha registrado eventos");
					ple.setE_descripcion(" ");
					ple.setE_fecha(" ");
					plelist.add(ple);
				} else {
					/* Tercer FOR: a partir de 'l' obtengo el objeto Evento */
					for (Evento ev : l.getEvento()) {
						/*
						 * Declaro un objeto para setear cada uno de los objeto y finalmente agregarlos
						 * en la lista respectiva
						 */
						ple = new PersonaLocalEvento();
						ple.setL_nombre(l.getNombre());
						ple.setL_descripcion(l.getDescripcion());
						ple.setL_capacidad(l.getCapacidad());
						ple.setL_costo(l.getCosto());
						ple.setE_nombre(ev.getNombre());
						ple.setE_descripcion(ev.getDescripcion());
						ple.setE_fecha(ev.getFechaEvento().toString());
						plelist.add(ple);
					}
				}
				/* Anadiendo cada uno de los objetos a la lista */
				
			}
		}
		return null;
	}

	
	/**
	 * consultarLocalesADMIN, recuperar una lista de los locales segun el ADMIN
	 * @param idUsuario, id. 
	 * @return listado de locales segun el ADMIN dentro de IniciarSesion, lo llamamos dentro de Accioneslocal.xhtml
	 */

	public String consultarLocalesADMIN(int id) {
		
		listLocales.clear();
			
			lpersonas = pdao.listPersonaID(id);
			for (Persona p : lpersonas) {
				for(Local loc : p.getLocales()) {
					Local l = new Local();
						l.setNombre(loc.getNombre());
						l.setCapacidad(loc.getCapacidad());
						l.setCosto(loc.getCosto());
						l.setDescripcion(loc.getDescripcion());
						l.setPuntuacion(loc.getPuntuacion()); 
						l.setFotoPerfil(loc.getFotoPerfil());
						l.setCodigo(loc.getCodigo());
						l.setComentario(loc.getComentario());
						
						
						listLocales.add(l);	
						
					}
				System.out.println("Lista de los locales que me imprime son:" + "" + listLocales);	
					}

			
			return null;
		}
}
