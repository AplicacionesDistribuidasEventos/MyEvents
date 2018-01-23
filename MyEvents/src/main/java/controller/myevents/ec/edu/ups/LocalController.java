package controller.myevents.ec.edu.ups;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;

import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalController.
 */
@ManagedBean
@SessionScoped
public class LocalController {

	/** Inyeccion de dependecias a los DAO que se van a utilizar */
	@Inject
	private LocalDAO locdao;

	@Inject
	private PersonaDAO pdao;

	/** Variables definidas para el manejo de los Locales */
	private Local local = new Local();;
	private Persona p;
	private List<Local> listlocal;
	private Local auxLocal;
	private int id;
	private int id2;

	/** Variables para el manejo de mapas */
	private String latitud = "-1";
	private String longitud = "-1";
	private String descripcion;
	private String elegimos;
	private String latituddes;
	private String longituddes;

	/** Variable donde se alamcenara la URL del path de la imagen en la BD */
	private Part fotoPerfil = null;

	/** The directorio perfil. Almacena la url donde se alamcenara la imagen */
	// private String directorioPerfil=
	// "C:\\Users\\sesla\\git\\MyEvents\\MyEvents\\src\\main\\webapp\\imagenes";
	// private String directorioPerfil=
	// "C:\\Users\\asus\\git\\MyEvents\\MyEvents\\src\\main\\webapp\\imagenes";
	private String directorioPerfil = "/var/www/html/MyEvents/upload";
	/** Variable que contendra el nombre del archivo */
	private String nombreArchivoPerfil;
	private List<String> imagenes;

	/**
	 * The idlocal: Recuperado para la posible utilizacion para la edicion de un
	 * local
	 */

	public static int idLocalel;

	/**
	 * The idl: Establece los Getters and Setters recepta el id que viene dado del
	 * JSF para la respectiva edicion del local
	 */
	private int idlel = 0;

	/**
	 * VARIABLES UTILZADAS PARA FILTRAR POR PALABRA EL CAMPO DEL NOMBRE DE LOCALES
	 */
	private String palabra;

	private List<Local> localesPerfil = new ArrayList<Local>();

	/**
	 * init(). Metodo para incializar los metodos de clase
	 */
	@PostConstruct
	public void init() {
		loadLocal();
		listlocal = locdao.listlocal();
	}

	/**
	 * Geters y Seters de las variables definidas.
	 */

	public int getIdlel() {
		return idlel;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public void setIdlel(int idlel) {
		this.idlel = idlel;
		loadLocalEditar(idlel);
	}

	public List<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public Part getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getNombreArchivoPerfil() {
		return nombreArchivoPerfil;
	}

	public void setNombreArchivoPerfil(String nombreArchivoPerfil) {
		this.nombreArchivoPerfil = nombreArchivoPerfil;
	}

	public String getDirectorioPerfil() {
		return directorioPerfil;
	}

	public void setDirectorioPerfil(String directorioPerfil) {
		this.directorioPerfil = directorioPerfil;
	}

	public int getId() {
		return id;
	}

	/**
	 * Seteamos el método insertarLocalAdmin() para manejar con el ID
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
		loadId(id);
		insertarLocalAdmin();
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public LocalDAO getLocdao() {
		return locdao;
	}

	public void setLocdao(LocalDAO locdao) {
		this.locdao = locdao;
	}

	public List<Local> getListlocal() {
		return listlocal;
	}

	public void setListlocal(List<Local> listlocal) {
		this.listlocal = listlocal;
	}

	public Local getAuxLocal() {
		return auxLocal;
	}

	public void setAuxLocal(Local auxLocal) {
		this.auxLocal = auxLocal;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getElegimos() {
		return elegimos;
	}

	public void setElegimos(String elegimos) {
		this.elegimos = elegimos;
	}

	public String getLatituddes() {
		return latituddes;
	}

	public void setLatituddes(String latituddes) {
		this.latituddes = latituddes;
	}

	public String getLongituddes() {
		return longituddes;
	}

	public void setLongituddes(String longituddes) {
		this.longituddes = longituddes;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;

	}

	public void loadLocal() {
		listlocal = locdao.listlocal();
	}

	/**
	 * Load local editar. Este método es llamado para hacer la edicion de local.
	 * Método utilozado para el SUPERADMIN.
	 *
	 * @param id
	 *            the id
	 * @return navegabilidad a traves de JSF CreaLocal.xhtml
	 */
	public String loadLocalEditar(int idledit) {
		local = locdao.leerLocal(idledit);
		return "CrearLocal";
	}

	/**
	 * Recuperar el Id de la navegabilidad del local con id2 para manejar el
	 * viewParam.
	 *
	 * @param id
	 *            the id
	 */
	public void loadId(int id) {
		id2 = id;
	}

	public List<Local> getLocalesPerfil() {
		return localesPerfil;
	}

	public void setLocalesPerfil(List<Local> localesPerfil) {
		this.localesPerfil = localesPerfil;
	}

	/**
	 * insertarLocalAdmin(). Método para ingresar un local referente a la personas
	 * logueada al sistema. Por lo que se utiliza el dao de la persona para
	 * actualizar el registro. Inicalmente se carga una foto de perfil por defecto
	 * si en el caso no se eligiera. Método utilizado para el Admin del local.
	 * 
	 * @return URL de navegabilidad hacia AccionesLocal.xhtml el cual contiene las
	 *         acciones sobre ese local.
	 */
	public String insertarLocalAdmin() {

		if (fotoPerfil != null) {
			saveFotoLocal();
			local.setFotoPerfil("upload/" + nombreArchivoPerfil);
		} else {

			local.setFotoPerfil("upload/local1.jpg");

		}

		p = pdao.selectPersona(id2);
		p.getLocales().add(local);
		pdao.updatePersona(p);
		return "AccionesLocal";

	}

	/**
	 * Guardar local. Método para insertar unicamente un local. Utilizado para el
	 * SUPERADMIN.
	 *
	 * @return URL null se renderiza la misma pagina.
	 */
	public String insertar() {

		if (fotoPerfil != null) {
			saveFotoLocal();
			local.setFotoPerfil("upload/" + nombreArchivoPerfil);
		} else {

			local.setFotoPerfil("upload/local1.jpg");

		}

		locdao.guardarLocal(local);
		loadLocal();
		init();
		return null;
	}

	/**
	 * Actualizar.Método para actulizar un local.
	 *
	 * @return URL null se renderiza la misma pagina.
	 */
	public String actualizar() {
		locdao.updateLocal(local);

		return null;
	}

	/**
	 * Leer.Este metodo lee cada local por ID recibido.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String leer(int id) {

		local = locdao.leerLocal(id);
		return null;
	}

	/**
	 * Eliminar.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String eliminar(int id) {
		locdao.deleteLocal(id);
		return "eliminarLocal";
	}

	/**
	 * Save foto local. Método para almacenar una copia a traves de la propiedad
	 * InputStream y File.copy. enviando como parámeto @directorioPerfil
	 * , @nombreArchivoPerfil, utilizados para almacenar la URL en la BD.
	 *
	 * @return the string
	 */

	public String saveFotoLocal() {

		try (InputStream input = fotoPerfil.getInputStream()) {

			nombreArchivoPerfil = fotoPerfil.getSubmittedFileName();

			Files.copy(input, new File(directorioPerfil, nombreArchivoPerfil).toPath());
			System.out.println("Uploaded file successfully saved in " + fotoPerfil);

		} catch (IOException e) {
			System.out.println("Error inesperado al subir foto" + e.getMessage());
		}
		return "";
	}

	/**
	 * Método para setear un ID.
	 *
	 * @param idL
	 */
	public void seteaId(int idL) {
		System.out.println("Este es el id qeu se esta seteando" + idL);
		LocalController.idLocalel = idL;
		System.out.println("Imprimee" + LocalController.idLocalel);
	}

	/**
	 * AUN EN DESARROLLOO. Cargar mapa.
	 *
	 * @param l
	 *            the l
	 * @return the string
	 */
	public String cargarMapa(Local l) {
		auxLocal = l;
		return ("mapa_localidades");
	}

	/**
	 * Cargar archivo.
	 *
	 * @param event
	 *            the event
	 */
	public void cargarArchivo(FileUploadEvent event) {

		local.setImagen(event.getFile().getContents());

		FacesMessage message = new FacesMessage("Exito", event.getFile().getFileName() + "Cargadoo..");
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	/**
	 * Registar img local.
	 *
	 * @param l
	 *            the l
	 * @return the string
	 */
	public String registarImgLocal(Local l) {

		try {

			locdao.insertarLocal(l);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Local cadastrado com sucesso!"));
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("entramoooooooooooo..........");
		}
		return null;
	}

	/**
	 * Lista locales. Contiene un lista de todos los locales ingresados que son
	 * leidos desde la BD.
	 *
	 * @return una lista de todos los locales.
	 */

	public List<Local> listaLocales() {
		if (activado == false) {
			listlocal = locdao.listlocal();
			System.out.println("CARGA TODA LA LISTA");
		} else {
			listlocal = filtroLocales();
			System.out.println("FILTRANDO");
		}
		return listlocal;
	}

	/**
	 * Metodo utilizado para el filtrado de los locales por nombre
	 */
	static List<Local> filtro;
	static boolean activado;

	public List<Local> filtroLocales() {
		List<Local> llocales = new ArrayList<Local>();
		filtro = new ArrayList<Local>();
		System.out.println("PALABRAAA:   " + palabra);
		llocales = locdao.listlocal();
		filtro.clear();
		activado = false;
		if (!palabra.isEmpty()) {
			activado = true;
			for (Local l : llocales) {
				if (l.getNombre().toUpperCase().contains(palabra.toUpperCase())) {
					filtro.add(l);
				} else {
					// si no han habido coincidencias
				}
			}
		} else {
			listaLocales();
		}
		return filtro;
	}

	/**
	 * PERMITE COLOCAR 3 IMAGENES DE PERFIL EN LA PANTALLA DE INICIO
	 */
	public List<Local> localImagenes() {
		List<Local> localinicio = new ArrayList<Local>();
		localinicio = locdao.listLocalPrincipal();
		System.out.println("LCOCALINICIO:   " + localinicio);
		return localinicio;
	}

	public static int id_loc_com;

	public String cargarPerfilLocal(int id) {
		id_loc_com = id;
		local = locdao.leerLocal(id);
		localesPerfil.add(local);
		return "perfilLocal";
	}
	
	/**METODO UTILIZADO PARA CARGAR EL CAROUSEL E INDICAR TODOS LOS LOCALES DISPONIBLES
	 * */
	public List<Local> obtlocales(){
		return locdao.listlocal();
	}

}