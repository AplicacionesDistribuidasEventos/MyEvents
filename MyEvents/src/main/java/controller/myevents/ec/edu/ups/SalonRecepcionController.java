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
	
	/** The srdao. */
	@Inject
	private SalonRecepcionDAO srdao;
	
	/** The salonrecepcion. */
	private SalonRecepcion salonrecepcion;
	
	/** The lsalonrecepcion. */
	private List<SalonRecepcion> lsalonrecepcion;
	

	/** The id. */
	private int id;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		salonrecepcion = new SalonRecepcion();
		listaSalonRecepcion();
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
	 * Gets the salonrecepcion.
	 *
	 * @return the salonrecepcion
	 */

	public SalonRecepcion getSalonrecepcion() {
		return salonrecepcion;
	}
	
	/**
	 * Sets the salonrecepcion.
	 *
	 * @param salonrecepcion the new salonrecepcion
	 */
	public void setSalonrecepcion(SalonRecepcion salonrecepcion) {
		this.salonrecepcion = salonrecepcion;
	}
	
	/**
	 * Gets the lsalonrecepcion.
	 *
	 * @return the lsalonrecepcion
	 */
	public List<SalonRecepcion> getLsalonrecepcion() {
		return lsalonrecepcion;
	}
	
	/**
	 * Sets the lsalonrecepcion.
	 *
	 * @param lsalonrecepcion the new lsalonrecepcion
	 */
	public void setLsalonrecepcion(List<SalonRecepcion> lsalonrecepcion) {
		this.lsalonrecepcion = lsalonrecepcion;
	}
	
	
	/**
	 * Crear.
	 *
	 * @return the string
	 */
	public String crear() {
		srdao.guardar(salonrecepcion);
		return "listadoSalonRecepcion";
	}
	
	/**
	 * Leer.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String leer(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return null;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		srdao.removeSRecepcion(id);
		return null;
	}
	
	/**
	 * Lista salon recepcion.
	 *
	 * @return the list
	 */
	public List<SalonRecepcion> listaSalonRecepcion() {
		lsalonrecepcion =srdao.listSRecepcion();
		return lsalonrecepcion;
	}
	
	/**
	 * Cargar estado.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String cargarEstado(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return "recuperarAsistenciaEvento";
	}

}
