package controller.myevents.ec.edu.ups;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.SalonRecepcionDAO;
import modelo.myevents.ec.edu.ups.SalonRecepcion;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcionController.
 */
@ManagedBean
public class SalonRecepcionController {
	
	/** Inyeccion de dependencias */
	@Inject
	private SalonRecepcionDAO srdao;
	
	/** Variables definidas para el manejo del SalonRecepcion*/
	private SalonRecepcion salonrecepcion;
	private List<SalonRecepcion> lsalonrecepcion;
	private int id;
	
	/**
	 * init(). Incializamos todos los métodos y variables.
	 */
	@PostConstruct
	public void init() {
		salonrecepcion = new SalonRecepcion();
		listaSalonRecepcion();
	}
	

	/**
	 * Geters y Seters de las variables definidas y ha ser utilizadas.
	 */
  
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}

	public SalonRecepcion getSalonrecepcion() {
		return salonrecepcion;
	}
	
	public void setSalonrecepcion(SalonRecepcion salonrecepcion) {
		this.salonrecepcion = salonrecepcion;
	}
	
	public List<SalonRecepcion> getLsalonrecepcion() {
		return lsalonrecepcion;
	}
	
	public void setLsalonrecepcion(List<SalonRecepcion> lsalonrecepcion) {
		this.lsalonrecepcion = lsalonrecepcion;
	}
	
	public String crear() {
		srdao.guardar(salonrecepcion);
		return "listadoSalonRecepcion";
	}
	
	public String leer(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return null;
	}
	
	/**
	 * Eliminar. Méoto para eliminar el salon de recepciones.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		srdao.removeSRecepcion(id);
		return null;
	}
	
	/**
	 * Lista salon recepcion. Método lista todos los las recepciones.
	 *
	 * @return una lista de tipo lsalonrecepcion
	 */
	public List<SalonRecepcion> listaSalonRecepcion() {
		lsalonrecepcion =srdao.listSRecepcion();
		return lsalonrecepcion;
	}
	
	/**
	 * Cargar estado. Es el estado del salon de recepciones.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String cargarEstado(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return "recuperarAsistenciaEvento";
	}

}
