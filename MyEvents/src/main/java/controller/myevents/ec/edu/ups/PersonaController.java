package controller.myevents.ec.edu.ups;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotBlank;

import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Persona;
import utilidades.myevents.ec.edu.ups.SessionUtils;

@ManagedBean
@SessionScoped
public class PersonaController { 

	/*
	 * Variable para la validacion de la cedula
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";   
	
	private Persona personas = null;

	private int id;
	private String pactual;
	private int idEditUser;

	/*Definicion de variables para la validacion-coincidencia del numero de cedula ingresado
	 * */
	@NotBlank(message = "Ingrese las contrasenias")
	private String contrasenia;
	private String conincidencia;
	private String Loginexiste;
	
	/*Varibles donde almaceno los valores de la consulta maestro-detalles
	 * */
	private String nusuario;
	private String nlocal;
	private String ndescripcion;
	private String ncapacidad;
	private String ncosto;
	
	private int idrecuprerar; //-----------agregado
	
	/*DEFINICION DE VARIABLES PARA LA RECUPERACION DE LA LISTA(PERSONA-LOCAL-EVENTO)
	 * */
	 private List<Persona> ListPerID;

	@Inject
	private PersonaDAO pdao;

	private List<Persona> lpersonas;

	private Persona myUser;

	@PostConstruct
	public void init() {
		personas = new Persona();
		lpersonas = listaPersonas();
		ListPerID = new ArrayList<Persona>();
		consultaLocalEventos();
	}
	
	

	public String getNusuario() {
		return nusuario;
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
 


	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		loadid(idrecuprerar);
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
/*Recuperacion de los Ids que son pasados como URL
 * */
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

	public String redirectmainAdmin() {
		return "mainAmin.xhtml";
	}
	/*Creacion del objeto Persona condicinamiento segun las sentencias de validacion
	 * */
	public void crear() {
		if (coincidirContrasenia() == true) {
			if (validarCedula() == true) {
				if(validarCorreo()==true) {
					personas.setPerfil("USUARIO");
					personas.setEstado("A");
					pdao.guardar(personas);
					inicializar();
					init();
					this.conincidencia = "Grabado exitoso!";	
				}else {
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

	/*
	 * Comparacion de los 2 campos referentes a la contrasenia, devolucion(true/false), segun sea la cedula valida o no valida respectivamente.
	 */
	public boolean coincidirContrasenia() {
		if (personas.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario y dejar los h:inputText del JSF en blanco
	 */
	public void inicializar() {
		personas.setCedula("");
		personas.setApellido("");
		personas.setNombre("");
		personas.setContrasenia("");
	}

	/*
	 * Modificacion de los objetos de tipo Persona(USUARIO/ADMIN)
	 */
	public String modificar() {
		try{
			System.out.println(personas.getPerfil());
			if(myUser.getPerfil().equals("USUARIO")) {
				personas.setContrasenia(pactual);
				pdao.updatePersona(personas);
				return "mainUser";
			}else if(myUser.getPerfil().equals("ADMIN")) {
				personas.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :"+personas.getCedula());
				System.out.println("ELSE IF ADMIN");
				pdao.updatePersona(personas);
				return "mainAdmin";
	}return null;
		}catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
		return null;
		}
	}

	/*
	 * Metodo leer, donde dirije a el archivo crearPersona, dado como parametro un Id
	 */
	public String leer(int id) {
		personas = pdao.selectPersona(id);
		return "crearPersona";
	}

	/*
	 * Metodo eliminar, llama al metodo Delete de PersonaDAO, parametro Id, para eliminar un registro especifica
	 */
	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado Usuario ..:" + personas);
		return "actualizar";
	}

	/*
	 * Metodo listado, devuelve un objeto Listado de tipo Persona(Devuelve todas las personas)
	 */
	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		return lpersonas;
	}

	/*Metodo util en la validacion de la cedula
	 * */
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
	
	/*Metodo para la validacion de un correo electronico
	 * */
	public boolean validarCorreo() {
		String email = personas.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}


	//Metodo para cargar Datos de una persona, pasado un parametro Id especifico, navegacion hacia recuperarPersona
	public String loadDatosEditar(int id) {
		System.out.println("Cargando...Persona a Editar" + id);
		personas = pdao.selectPersona(id);
		pactual = personas.getContrasenia();
		if(personas.getPerfil().equals("USUARIO")) {
			return "recuperaPersona";	
		}else if(personas.getPerfil().equals("ADMIN")) {
			System.out.println("LOAD DATOS ");
			return "editAdmin";	
		}
		return null;
	}

	/*inicilizar una Sesion HTTP y establecimiento de parametros en session, FacesContext acceso tanto al contexto de JSF como HTTP
	 * */
	public void iniciarSesion() {
		if (pdao.login(personas.getCorreo(), personas.getContrasenia()).size() != 0) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil",pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado",pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getEstado());
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
			} 
			else if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil().equals("ADMIN-SUPER")) {
					//FacesContext contexAS= FacesContext.getCurrentInstance();
					try {
						contex.getExternalContext().redirect("pages-blank.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			else if(pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil().equals("ADMIN")){
				//FacesContext contexAS= FacesContext.getCurrentInstance();
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
	
	/*Cargar datos del usuario obtenidos en session(HTTP) y su respectiva validacion
	 * */
	public void cargarDatosUsuario() {
		myUser = new Persona();
		HttpSession session = SessionUtils.getSession();
		String nus = (String) session.getAttribute("username");
		System.out.println("NUS "+nus);
		try {		
			if(pdao.verificaCorreo(nus).size()!=0) {
				List<Persona> lusuario = new ArrayList<Persona>();
				lusuario = pdao.verificaCorreo(nus);
				myUser = lusuario.get(0);
				System.out.println("MYUSER EMAIL: "+myUser.getCorreo());
			}else {
				FacesContext contex = FacesContext.getCurrentInstance();
		        try {
					contex.getExternalContext().redirect( "index.xhtml" );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}catch(Exception e) {
			System.out.println("Error al cargar");
			e.printStackTrace();
		}

	}
	
	/*Metodo Utilizado para la eliminacion de una sesion HTTP, con su respectiva navegacion
	 * */
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index.xhtml";
	}
	
	/*Metodo Utilizado para la verificacion de la sesion establecida, con un respectivo contexto hacia la pagina de inicio 
	 * */
	 public void verificaSesion(){
		 HttpSession session = SessionUtils.getSession();
			String nusv = (String) session.getAttribute("username");
				if(nusv!=null){
					System.out.println("si tiene sesion");
					FacesContext contex = FacesContext.getCurrentInstance();
			        try {
			        	if(myUser.getPerfil().equals("USUARIO")) {
			        		contex.getExternalContext().redirect( "mainUser.html" );	
			        	}else if(myUser.getPerfil().equals("ADMIN")){
			        		contex.getExternalContext().redirect( "mainAdmin.html" );
			        	} 
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 }
	 /*OBTENCION DE LA LISTA MAESTRA (PERSONA)
	  * */
	 public String consultaLocalEventos() {
		 
		 System.out.println("ID: "+idrecuprerar+" "+ "ENTRA");
		 ListPerID = pdao.listPersonaID(idrecuprerar);
		 for(Persona p: ListPerID) {
			 System.out.println("CED===================================="+p.getCedula());
			 nusuario = p.getNombre();
			 nlocal = p.getLocales().get(0).getNombre();
			 ndescripcion = p.getLocales().get(0).getDescripcion();
			 ncapacidad = p.getLocales().get(0).getCapacidad();
			 ncosto = p.getLocales().get(0).getCosto();
		 }
		 return null;
	 }
	 public void loadid(int id) {
		 idrecuprerar=id;
		 
	 }
	 public void loadidUser(int id) {
		 idEditUser=id;
		 
	 }
}
