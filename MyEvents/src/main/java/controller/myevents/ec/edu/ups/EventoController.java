package controller.myevents.ec.edu.ups;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.LocalDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Local;

// TODO: Auto-generated Javadoc
/**
 * The Class EventoController.
 */
@ManagedBean
public class EventoController {

	/** Inyeccion de dependecias */
	@Inject
	private EventoDAO evendao;

	@Inject
	private LocalDAO locdao;

	@Inject
	private CategoriaDAO catedao;

	/** Variables definidas para el manejo de los Eventos */

	private Evento evento;
	private Local recupelocal;
	private Categoria c;
	private List<Evento> levento;
	private List<Evento> leventocercano;
	private List<Evento> leventofecha;

	/** The listado filtrado. */
	// Busqueda de locales
	private List<Evento> listadoFiltrado;
	private String filtro;

	/**Variables para controlar ID de la navegacion */
	private int id;
	private int id2;
	private int id3;
	private List<Categoria> categorias;
	
	/**Variable utilizada para seleccionar la categoria de la tabla maeestra */
	private String selectcat;
	//private String grabado;
	
	/**
	 * The idlocal: Recuperado para la posible utilizacion en la creacion de un
	 * evento
	 */
	public static int idLocalce;
	


	/**
	 * The idl: Establece los Getters and Setters recepta el id que viene dado del
	 * JSF para la respectiva creacion del evento
	 */
	private int idlcev = 0;
	
	/**
	 * init(). Método para inciaizar los métodos y variables.
	 */

	@PostConstruct
	public void init() {
		evento = new Evento();
		loadEvento();
		categorias = new ArrayList<Categoria>();
		// devuelveLista();
	}
	
	/**
	 * 	before(). 
	 *  Permite tomar el ID enviado de local, previamente a anadirle un evento
	 */
	public void before() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			System.out.println("PreRenderView: view parameters are available here");
			System.out.println("Id: " + idlcev);
			EventoController.idLocalce = idlcev;
			//EventoController.idLocalel = idlel;
		}
	}
	/**
	 * Getter y Setter de las variables como listas definidas.
	 */
	
	public int getIdlcev() {
		return idlcev;
	}

	public void setIdlcev(int idlcev) {
		this.idlcev = idlcev;
	}

	public String getSelectcat() {
		return selectcat;
	}
	
	public void setSelectcat(String selectcat) {
		this.selectcat = selectcat;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public int getId3() {
		return id3;
	}

	public List<Evento> getListadoFiltrado() {
		return listadoFiltrado;
	}

	public void setListadoFiltrado(List<Evento> listadoFiltrado) {
		this.listadoFiltrado = listadoFiltrado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void setId3(int id3) {
		this.id3 = id3;

	}

	public Local getRecupelocal() {
		return recupelocal;
	}


	public void setRecupelocal(Local recupelocal) {
		this.recupelocal = recupelocal;
	}


	public int getId() {
		return id;
	}

	public int getId2() {
		return id2;
	}
	

	public void setId2(int id2) {
		this.id2 = id2;
	}

	/**
	 * Settear el id para la navegabilidad.Y pasar de pagina a otra con el mismo ID.
	 *
	 * @param id
	 *          
	 */

	public void setId(int id) {
		this.id = id;
		loadEventoEditar(id);
		loadID(id);
		insertarEventoLocalGloba();
		loadCId(id);
		insertaCategoriaAdmin();

	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getLevento() {
		return levento;
	}

	public void setLevento(List<Evento> levento) {
		this.levento = levento;
	}

	public List<Evento> getLeventocercano() {
		return leventocercano;
	}

	public void setLeventocercano(List<Evento> leventocercano) {
		this.leventocercano = leventocercano;
	}


	public List<Evento> getLeventofecha() {
		return leventofecha;
	}

	public void setLeventofecha(List<Evento> leventofecha) {
		this.leventofecha = leventofecha;
	}

	public void loadEvento() {

		levento = evendao.listEvento();
	}

	public void loadID(int id) {
		id2 = id;
	}

	public void loadCId(int id) {
		id3 = id;
	}

	/**
	 * Load evento editar. Método para leer el código del evento haciendo un llamado al 
	 * DAO del evento evendao.leerEvento por el ID.
	 * @param id         
	 * @return URL para la navegabilidad hacia CrearEvento.xhtml
	 */
	public String loadEventoEditar(int id) {

		evento = evendao.leerEventoSalones(id);

		return "CrearEvento";
	}

	/**
	 * Metodo Insertar.
	 *Agrega un evento
	 * @return URL de redireccion listarEventos
	 */
	public String insertar() {
		evendao.guardarEvento(evento);
		loadEvento();
		return "listarEventos";
	}

	/**
	 * Actualizar.
	 *
	 * @return URL null no existe navegabilidad a trves de JSF
	 */
	public String actualizar() {
		evendao.updateEvento(evento);
		return null;
	}

	/**
	 * Leer.
	 *
	 * @param envio de parametro id para eliminarlo a traves del id
	 * @return null no existe navegabilidad a trves de JSF 
	 * @param id
	 * @return null renderiza la misma pagina en JSF
	 */
	public String leer(int id) {
		evento = evendao.leerEvento(id);
		return null;
	}

	/**
	 * Eliminar.
	 *
	 * @param envio de parametro id para
	 * eliminarlo a traves del id
	 * @return navegacion en JSF al eliminar un evento
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String eliminar(int id) {
		evendao.deleteEvento(id);
		return "eliminarEvento";
	}

	/**
	 * Lista eventos.
	 *
	 * @return una lista de eventos
	 */
	public List<Evento> listaEventos() {

		levento = evendao.listEvento();
		return levento;
	}

	/**
	 * Insertar evento local globa.
	 *Metodo para recuperar el idLocal para cargar el evento dentro del local y
	 *agregar la cateogoria a ese evento.
	 *Dar formato a la fecha String del modelo Evento.
	 * @return URl de navegacion con la cosulta de eventos y locales por persona
	 */
	public String insertarEventoLocalGloba() {

		System.out.println("ID2: "+EventoController.idLocalce);
		evento.setCategoria(EventoController.ca);
		
		System.out.println("Eventoooooooooooooooo "+evento.getCategoria().getNombre());
		recupelocal = locdao.selectLocal(EventoController.idLocalce);

		try {
		if(recupelocal!=null) {
			System.out.print("No es nuloooooo");
		}

		else {
			System.out.println("no existen categorias ingresadas");
		}
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
//		String fechaEvento  = evento.getFechaEvento();
		Date fechaEvento = new Date();
		String fechaFormato = formatter.format(fechaEvento);
			
		
		System.out.println("si me transformaaaaaaaaaa : "+""+fechaFormato);
		
		evento.setFechaEvento(fechaFormato);
		recupelocal.getEvento().add(evento);
		
		locdao.updateLocal(recupelocal);
		

		} catch (Exception e) {
			System.out.println("falta categoria" + e.getMessage());
		}
		
		return "consulEventLocPers";
	}

	

	/**
	 * Inserta categoria admin.
	 *
	 * @return URl null renderiza la misma página en JSF
	 */
	public String insertaCategoriaAdmin() {
		c = catedao.leerCategoria(id3);
		catedao.actualizarCategoria(c);
		return null;

	}

	/**
	 * Buscar.
	 *
	 * @return the string
	 */
	/*
	public String buscar() {
		listadoFiltrado = evendao.getEventosPorNombre(filtro);
		return null;
	}
*/
	public static Categoria ca=new Categoria();
	/**ESTABLEZCO LA CATEGORIA QUE SE SELECCIONADO*/
	public void categoriaSeleccionada() {
		
		System.out.println("Entraaaaaaaaaaaaa al evt");
		for(Categoria c : categorias) {
			try {

			if(c.getNombre().equals(selectcat)) {
				evento.setCategoria(c);
				EventoController.ca=c;
				System.out.println("categoria: "+evento.getCategoria().getNombre());
			}
        
        } catch (Exception e) {
				System.out.println("no existe categorias" + e.getMessage());
			}
		}

	}

	/**Meotodo devueleve, utilizafo para obtener un lista de categorias y cargarlas en un SelectItem
	 * desde JSF.
	 * 
	 *
	 * @return URL de las lista de categorias
	 */
	public List<SelectItem> devuelveLista() {
		List<SelectItem> lcategorias = new ArrayList<SelectItem>();
		categorias = catedao.listCategoria();
		System.out.println("SIZE CATEGORIAS " + categorias.size());
		// for(Categoria c : categorias) {
		for (int i = 0; i < categorias.size(); i++) {
			lcategorias.add(new SelectItem(categorias.get(i).getNombre()));
		}
		return lcategorias;
	}
	public String retornoMenuAdmin() {
		return "mainAdmin";
	}
	
	/**
	 * On date select.
	 *Metodo que me devuelve un efento de la fecha seleccionada a tarves de ajax.
	 * @param event
	 */
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

}