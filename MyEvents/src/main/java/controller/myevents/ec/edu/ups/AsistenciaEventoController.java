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
	
	/** The asistenciaevento. */
	private AsistenciaEvento asistenciaevento;
	
	/** The lasistenciaevento. */
	private List<AsistenciaEvento> lasistenciaevento;
	
	/** The id. */
	private int id;
	
	/** The aedao. */
	@Inject
	private AsistenciaEventoDAO aedao;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		asistenciaevento = new AsistenciaEvento();
		listaAsistenciaEvento();
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
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}




	/**
	 * Gets the asistenciaevento.
	 *
	 * @return the asistenciaevento
	 */
	public AsistenciaEvento getAsistenciaevento() {
		return asistenciaevento;
	}
	
	/**
	 * Sets the asistenciaevento.
	 *
	 * @param asistenciaevento the new asistenciaevento
	 */
	public void setAsistenciaevento(AsistenciaEvento asistenciaevento) {
		this.asistenciaevento = asistenciaevento;
	}
	
	/**
	 * Gets the lasistenciaevento.
	 *
	 * @return the lasistenciaevento
	 */
	public List<AsistenciaEvento> getLasistenciaevento() {
		return lasistenciaevento;
	}
	
	/**
	 * Sets the lasistenciaevento.
	 *
	 * @param lasistenciaevento the new lasistenciaevento
	 */
	public void setLasistenciaevento(List<AsistenciaEvento> lasistenciaevento) {
		this.lasistenciaevento = lasistenciaevento;
	}

	/**
	 * Crear.
	 *
	 * @return the string
	 */
	public String crear() {
		//aedao.insertAEvento(asistenciaevento);
		aedao.guardar(asistenciaevento);
		return "listadoAsistenciaEvento";
	}
	
	/**
	 * Leer.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String leer(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return null;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		aedao.removeAEvento(id);
		return null;
	}
	
	/**
	 * Lista asistencia evento.
	 *
	 * @return the list
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