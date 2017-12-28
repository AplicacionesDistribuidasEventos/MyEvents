package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;


import dao.myevents.ec.edu.ups.ExtraDAO;
import modelo.myevents.ec.edu.ups.Extra;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtraController.
 */
@ManagedBean
public class ExtraController {
	
	
	/** The extdao. */
	@Inject
	private ExtraDAO extdao;
	
	
	/** The extra. */
	/*
	 * Declaracion de variables
	 */
	private Extra extra = null;
	
	/** The extras. */
	private List<Extra> extras;
	
	/**
	 * Inits the.
	 */
	private int id;
	

	@PostConstruct
	public void init(){
		
		extra=new Extra();
		loadExtra();
		
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
		loadExtraEditar(id);
	}
	
	/**
	 * Gets the extra.
	 *
	 * @return the extra
	 */
	public Extra getExtra() {
		return extra;
	}

	
	/**
	 * Sets the extra.
	 *
	 * @param extra the new extra
	 */
	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	
	/**
	 * Gets the extras.
	 *
	 * @return the extras
	 */
	public List<Extra> getExtras() {
		return extras;
	}

	/**
	 * Sets the categorias.
	 *
	 * @param extras the new categorias
	 */
	public void setCategorias(List<Extra> extras) {
		this.extras = extras;
	}
	
/////////////Controller mantenimiento

	/**
 * Load extra.
 */
public void loadExtra(){
		
		extras = extdao.listExtra();
	}	
	
	/**
	 * Load extra editar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String loadExtraEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		extra = extdao.leerExtra(id);
		return "listadoExtraAcciones";
		
	}//fin editar
	
	/**
	 * Elimina extra.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminaExtra(int id){
		
		extdao.eliminarExtra(id);
		return "litadoExtraAcciones";
		
	}
	
	/**
	 * Guardar.
	 *
	 * @return the string
	 */
	public String guardar(){
		
		System.out.println(extra);
		
		//PracticaDAO pdao= new PracticaDAO(); no es valido
		
		extdao.guardarExtra(extra);
		loadExtra();
		
		//return null;
		return "listadoExtra";
	}
}