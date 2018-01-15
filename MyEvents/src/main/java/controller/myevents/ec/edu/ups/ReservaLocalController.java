package controller.myevents.ec.edu.ups;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.ReservaLocalDAO;
import modelo.myevents.ec.edu.ups.ReservaLocal;

// TODO: Auto-generated Javadoc
/**
 * The Class SalonRecepcionController.
 */
@ManagedBean
public class ReservaLocalController {
	
	/** Inyeccion de dependencias */
	@Inject
	private ReservaLocalDAO srdao;
	
	/** Variables definidas para el manejo del SalonRecepcion*/
	private ReservaLocal salonrecepcion;
	private List<ReservaLocal> lsalonrecepcion;
	private int id;
	
	/**
	 * init(). Incializamos todos los métodos y variables.
	 */
	@PostConstruct
	public void init() {
		salonrecepcion = new ReservaLocal();
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

	public ReservaLocal getSalonrecepcion() {
		return salonrecepcion;
	}
	
	public void setSalonrecepcion(ReservaLocal salonrecepcion) {
		this.salonrecepcion = salonrecepcion;
	}
	
	public List<ReservaLocal> getLsalonrecepcion() {
		return lsalonrecepcion;
	}
	
	public void setLsalonrecepcion(List<ReservaLocal> lsalonrecepcion) {
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
	public List<ReservaLocal> listaSalonRecepcion() {
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
