package controller.myevents.ec.edu.ups;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	/** The evendao. */
	@Inject
	private EventoDAO evendao;

	/** The locdao. */
	@Inject
	private LocalDAO locdao;

	/** The catedao. */
	@Inject
	private CategoriaDAO catedao;

	/** The evento. */

	/*
	 * Declaracion de variables
	 */

	private Evento evento;

	/** The recupelocal. */
	private Local recupelocal;

	/** The c. */
	private Categoria c;

	/** The levento. */
	private List<Evento> levento;

	/** The leventocercano. */
	private List<Evento> leventocercano;

	/** The leventofecha. */
	private List<Evento> leventofecha;

	/** The listado filtrado. */
	// Busqueda de locales
	private List<Evento> listadoFiltrado;

	/** The filtro. */
	private String filtro;

	/** The id. */
	// Variables para controlar ID de la navegacion

	private int id;

	/** The id 2. */
	private int id2;

	/** The id 3. */
	private int id3;

	/** The categorias. */
	private List<Categoria> categorias;
	
	/** The selectcat. */
	private String selectcat;
	
	private String grabado;
	
	//variable statica
	public static int idLocal=0;

	/**
	 * Inits the.
	 */

	@PostConstruct
	public void init() {
		evento = new Evento();
		loadEvento();
		categorias = new ArrayList<Categoria>();
		// devuelveLista();
	}

	public String getGrabado() {
		return grabado;
	}


	public void setGrabado(String grabado) {
		this.grabado = grabado;
	}


	public String getSelectcat() {
		return selectcat;
	}


	/**
	 * Sets the selectcat.
	 *
	 * @param selectcat the new selectcat
	 */
	public void setSelectcat(String selectcat) {
		this.selectcat = selectcat;
	}


	/**
	 * Gets the categorias.
	 *
	 * @return the categorias
	 */
	public List<Categoria> getCategorias() {
		return categorias;
	}


	/**
	 * Sets the categorias.
	 *
	 * @param categorias the new categorias
	 */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * Gets the id 3.
	 *
	 * @return the id 3
	 */
	public int getId3() {
		return id3;
	}

	/**
	 * Gets the listado filtrado.
	 *
	 * @return the listado filtrado
	 */

	public List<Evento> getListadoFiltrado() {
		return listadoFiltrado;
	}

	/**
	 * Sets the listado filtrado.
	 *
	 * @param listadoFiltrado
	 *            the new listado filtrado
	 */
	public void setListadoFiltrado(List<Evento> listadoFiltrado) {
		this.listadoFiltrado = listadoFiltrado;
	}

	/**
	 * Gets the filtro.
	 *
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}

	/**
	 * Sets the filtro.
	 *
	 * @param filtro
	 *            the new filtro
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	/**
	 * Sets the id 3.
	 *
	 * @param id3
	 *            the new id 3
	 */
	public void setId3(int id3) {
		this.id3 = id3;

	}

	/**
	 * Gets the recupelocal.
	 *
	 * @return the recupelocal
	 */
	public Local getRecupelocal() {
		return recupelocal;
	}

	/**
	 * Sets the recupelocal.
	 *
	 * @param recupelocal
	 *            the new recupelocal
	 */
	public void setRecupelocal(Local recupelocal) {
		this.recupelocal = recupelocal;
	}

	/**
	 * Gets the locdao.
	 *
	 * @return the locdao
	 */
	public LocalDAO getLocdao() {
		return locdao;
	}

	/**
	 * Sets the locdao.
	 *
	 * @param locdao
	 *            the new locdao
	 */
	public void setLocdao(LocalDAO locdao) {
		this.locdao = locdao;
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
	 * Gets the evendao.
	 *
	 * @return the evendao
	 */
	public EventoDAO getEvendao() {
		return evendao;
	}

	/**
	 * Sets the evendao.
	 *
	 * @param evendao
	 *            the new evendao
	 */
	public void setEvendao(EventoDAO evendao) {
		this.evendao = evendao;
	}

	/**
	 * Gets the id 2.
	 *
	 * @return the id 2
	 */
	public int getId2() {
		return id2;
	}

	/**
	 * Sets the id 2.
	 *
	 * @param id2
	 *            the new id 2
	 */
	public void setId2(int id2) {
		this.id2 = id2;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */

	public void setId(int id) {
		this.id = id;
		loadEventoEditar(id);
		loadID(id);

		insertarEventoLocalGloba();

		loadCId(id);
		insertaCategoriaAdmin();

	}

	/**
	 * Gets the evento.
	 *
	 * @return the evento
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * Sets the evento.
	 *
	 * @param evento
	 *            the new evento
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	/**
	 * Gets the levento.
	 *
	 * @return the levento
	 */
	public List<Evento> getLevento() {
		return levento;
	}

	/**
	 * Sets the levento.
	 *
	 * @param levento
	 *            the new levento
	 */
	public void setLevento(List<Evento> levento) {
		this.levento = levento;
	}

	/**
	 * Gets the leventocercano.
	 *
	 * @return the leventocercano
	 */
	public List<Evento> getLeventocercano() {
		return leventocercano;
	}

	/**
	 * Sets the leventocercano.
	 *
	 * @param leventocercano
	 *            the new leventocercano
	 */
	public void setLeventocercano(List<Evento> leventocercano) {
		this.leventocercano = leventocercano;
	}

	/**
	 * Gets the leventofecha.
	 *
	 * @return the leventofecha
	 */
	public List<Evento> getLeventofecha() {
		return leventofecha;
	}

	/**
	 * Sets the leventofecha.
	 *
	 * @param leventofecha
	 *            the new leventofecha
	 */
	public void setLeventofecha(List<Evento> leventofecha) {
		this.leventofecha = leventofecha;
	}

	/**
	 * Load evento.
	 */
	public void loadEvento() {

		levento = evendao.listEvento();
	}

	/**
	 * Load ID.
	 *
	 * @param id
	 *            the id
	 */
	public void loadID(int id) {
		id2 = id;
	}

	/**
	 * Load C id.
	 *
	 * @param id
	 *            the id
	 */
	public void loadCId(int id) {
		id3 = id;
	}

	/**
	 * Load evento editar.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String loadEventoEditar(int id) {

		evento = evendao.leerEvento(id);

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
	 * @param envio de parametro id para
	 * eliminarlo a traves del id
	 * @return null no existe navegabilidad a trves de JSF 
	 * @param id
	 *            the id
	 * @return the string
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
	 *agregar la cateogoria a ese evento
	 * @return URl de navegacion con la cosulta de eventos y locales por persona
	 */
public String insertarEventoLocalGloba() {

System.out.println("ID2: "+EventoController.idLocal);
		evento.setCategoria(EventoController.ca);
		
System.out.println("Eventoooooooooooooooo "+evento.getCategoria().getNombre());
//		recupelocal = locdao.leerLocal(id2);
		recupelocal = locdao.selectLocal(EventoController.idLocal);

		try {
		if(recupelocal!=null) {
			System.out.print("No es nuloooooo");
		}

		else {
			System.out.println("no existen categorias ingresadas");
		}
		recupelocal.getEvento().add(evento);
		locdao.updateLocal(recupelocal);
		

		} catch (Exception e) {
			System.out.println("falta categoria" + e.getMessage());
		}
		//return null;
    return "consulEventLocPers";
	}

		/*if(recupelocal!=null) {
			System.out.print("No es nuloooooo");
		}
		recupelocal.getEvento().add(evento);
		locdao.updateLocal(recupelocal);

		return null;
	}*/


	/**
	 * Inserta categoria admin.
	 *
	 * @return the string
	 */
	public String insertaCategoriaAdmin() {
		c = catedao.leerCategoria(id3);
		// c.getEventos().add(evento);
		catedao.actualizarCategoria(c);
		return null;

	}

	/**
	 * Buscar.
	 *
	 * @return the string
	 */
	public String buscar() {
		listadoFiltrado = evendao.getEventosPorNombre(filtro);

		return null;
	}

	public static Categoria ca=new Categoria();
	/**ESTABLEZCO LA CATEGORIA QUE SE SELECCIONADO*/
	public void categoriaSeleccionada() {
		
		System.out.println("Entraaaaaaaaaaaaa al evt");
		for(Categoria c : categorias) {
			try {

		/*System.out.println("Entraaaaaaaaaaaaa al evt");
		for(Categoria c : categorias) {*/
			if(c.getNombre().equals(selectcat)) {
				evento.setCategoria(c);
				EventoController.ca=c;
				System.out.println("categoria: "+evento.getCategoria().getNombre());
			}
        
        } catch (Exception e) {
				System.out.println("no existe categorias" + e.getMessage());
			}
		}
		
		//}

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
	 *Metodo para selecionar un mapa atravez de primefaces
	 * @param event
	 */
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

}