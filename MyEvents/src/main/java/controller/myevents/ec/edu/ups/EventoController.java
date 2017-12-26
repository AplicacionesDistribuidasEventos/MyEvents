package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

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
	//Busqueda de locales
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

	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		evento = new Evento(); 
		loadEvento();
		
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
	 * @param listadoFiltrado the new listado filtrado
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
	 * @param filtro the new filtro
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


	/**
	 * Sets the id 3.
	 *
	 * @param id3 the new id 3
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
	 * @param recupelocal the new recupelocal
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
	 * @param locdao the new locdao
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
	 * @param evendao the new evendao
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
	 * @param id2 the new id 2
	 */
	public void setId2(int id2) {
		this.id2 = id2;
	}
	
		
	/**
	 * Sets the id.
	 *
	 * @param id the new id
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
	 * @param evento the new evento
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
	 * @param levento the new levento
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
	 * @param leventocercano the new leventocercano
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
	 * @param leventofecha the new leventofecha
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
	 * @param id the id
	 */
	public void loadID(int id) {
		id2 = id;
	}
	
	/**
	 * Load C id.
	 *
	 * @param id the id
	 */
	public void loadCId(int id) {
		id3 = id;
	} 
	
	
	
	/**
	 * Load evento editar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String loadEventoEditar(int id) {
		
		evento = evendao.leerEvento(id);
		
		return "CrearEvento";
	}
	
	/**
	 * Insertar.
	 *
	 * @return the string
	 */
	public String insertar() {
		evendao.guardarEvento(evento);
		loadEvento();
		return "listarEventos";
	}
	
	/**
	 * Actualizar.
	 *
	 * @return the string
	 */
	public String actualizar() {
		evendao.updateEvento(evento);
		return null;
	}
	
	/**
	 * Leer.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String leer(int id) {
		evento = evendao.leerEvento(id);
		return null;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		evendao.deleteEvento(id);
		return "eliminarEvento";
	}
	  
	
	/**
	 * Lista eventos.
	 *
	 * @return the list
	 */
	public List<Evento> listaEventos(){
		
		levento = evendao.listEvento();
		return levento;
	}
	
	
	
		/**
	 * Insertar evento local globa.
	 *
	 * @return the string
	 */
	public String insertarEventoLocalGloba(){ 


			recupelocal=locdao.leerLocal(id2); 
			recupelocal.getEvento().add(evento);
			locdao.updateLocal(recupelocal); 
			
			return null;  
		}
		
		/**
		 * Inserta categoria admin.
		 *
		 * @return the string
		 */
		public String insertaCategoriaAdmin(){
			c = catedao.leerCategoria(id3);
			//c.getEventos().add(evento);
			catedao.actualizarCategoria(c);
			return null;
					
		}
	
		
			/**
			 * Buscar.
			 *
			 * @return the string
			 */
			public String buscar(){
			
				System.out.println("INGRESO AL METODO ==================");
			listadoFiltrado = evendao.getEventosPorNombre(filtro);
					
			return null;
		}
	
}
