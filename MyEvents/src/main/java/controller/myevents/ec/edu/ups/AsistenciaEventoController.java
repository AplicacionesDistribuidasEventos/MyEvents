package controller.myevents.ec.edu.ups;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.AsistenciaEventoDAO;
import modelo.myevents.ec.edu.ups.AsistenciaEvento;


// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaEventoController.
 */

@ManagedBean
public class AsistenciaEventoController {
	
	/** Inyeccion de dependecias a los DAO que se van a utilizar */
	@Inject
	private AsistenciaEventoDAO aedao;
	
	/** Variables definidas para el manejo de los Locales */
	private int id;
	
	/** Variables de lista y de objetos utilizados */
	private AsistenciaEvento asistenciaevento;
	private List<AsistenciaEvento> lasistenciaevento;
	
	
	/**
	 * init().
	 * Metodo para incializar los metodos de clase
	 */
	@PostConstruct
	public void init() {
		asistenciaevento = new AsistenciaEvento();
		listaAsistenciaEvento();
	}
	
	/**
	 * Geters y Seters de las variables definidas.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}


	public AsistenciaEvento getAsistenciaevento() {
		return asistenciaevento;
	}
	
	public void setAsistenciaevento(AsistenciaEvento asistenciaevento) {
		this.asistenciaevento = asistenciaevento;
	}
	
	public List<AsistenciaEvento> getLasistenciaevento() {
		return lasistenciaevento;
	}
	
	public void setLasistenciaevento(List<AsistenciaEvento> lasistenciaevento) {
		this.lasistenciaevento = lasistenciaevento;
	}

	/**
	 * Crear. Inserta las AsistenciaEventos del local
	 *
	 * @return URL de navegacion
	 */
	public String crear() {
		//aedao.insertAEvento(asistenciaevento);
		aedao.guardar(asistenciaevento);
		return "listadoAsistenciaEvento";
	}
	
	/**
	 * Leer. Método para leer el codigo de la entidad AsistenciaEvento
	 *
	 * @param id
	 * @return null
	 */
	public String leer(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return null;
	}
	
	/**
	 * Eliminar. Aistencia evento
	 *
	 * @param id the id
	 * @return null
	 */
	public String eliminar(int id) {
		aedao.removeAEvento(id);
		return null;
	}
	
	/**
	 * Lista asistencia evento.
	 *
	 * @return the list de todas las asitencias
	 */
	public List<AsistenciaEvento> listaAsistenciaEvento() {
		lasistenciaevento = aedao.listAEvento();
		return lasistenciaevento;
	}
	
	/**
	 * Cargar estado.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String cargarEstado(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return "recuperarAsistenciaEvento";
	}

}