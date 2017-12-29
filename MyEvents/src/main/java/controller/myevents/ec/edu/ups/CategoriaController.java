package controller.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.CategoriaEventos;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Persona;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaController.
 */
@ManagedBean
public class CategoriaController {
	
	
	/** The catedao. */
	@Inject
	private CategoriaDAO catedao;
	
	/** The edao. */
	@Inject
	private EventoDAO edao;
	
	/** The categoria. */
	private Categoria categoria = null;
	
	/** The categorias. */
	private List<Categoria> categorias;
	
	/** The id. */
	private int id;

	/*Recuperar los objetos eventos segun la categoria*/
	private List<Categoria> listCatID;
	private List<CategoriaEventos> catevelist;
	
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		loadCategoria();
		catevelist = new ArrayList<CategoriaEventos>();
		listCatID = new ArrayList<Categoria>();
//		categorias = new ArrayList<Categoria>();
	}


	public String datosCategoria(int codigo){
//		catevelist.clear();
		listCatID = catedao.listCategoriaID(codigo);
		for(Categoria c : listCatID) {
			CategoriaEventos ce = new CategoriaEventos();
			ce.setDescripcion_cat(c.getDescipcion());
			System.out.println("NEXT CE DESC "+c.getDescipcion());
			ce.setCategoria(c.getNombre());
			System.out.println("NEXT CE NOM "+c.getNombre());
			if(c.getEventos().isEmpty()) {
				System.out.println("SIN EVENTOS");
				ce.setDescripcion_eve("");
				ce.setFecha("");
				ce.setCosto("");
			}else {
				for(Evento e : c.getEventos()) {
					ce.setDescripcion_eve(e.getDescripcion());
					System.out.println("NEXT CE EDESC "+e.getDescripcion());
					ce.setFecha(e.getFechaEvento().toString());
					System.out.println("NEXT CE EFECH "+e.getFechaEvento().toString());
					ce.setCosto(String.valueOf(e.getCosto()));
					System.out.println("NEXT CE COST "+String.valueOf(e.getCosto()));
				}
			}

			catevelist.add(ce);
		}
		return "eventosCategoria";
	}
	
	public List<CategoriaEventos> getCatevelist() {
		return catevelist;
	}

	public void setCatevelist(List<CategoriaEventos> catevelist) {
		this.catevelist = catevelist;
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
		loadCategoriaEditar(id);//parametros
	}
	
	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	
	/**
	 * Sets the categoria.
	 *
	 * @param categoria the new categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	 * Load categoria.
	 */
	public void loadCategoria(){
		
		categorias = catedao.listCategoria();
	}
	
	
	/**
	 * Load categoria editar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String loadCategoriaEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		categoria = catedao.leerCategoria(id);
		return "listadoCategoriaAcciones";
		
	}	
	
	/**
	 * Elimina categoria.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminaCategoria(int id){
		
		catedao.eliminarCategoria(id);
		return "listadoCategoriaAcciones";
		
	}
	
	/**
	 * Guardar.
	 *
	 * @return the string
	 */
	public String guardar(){
		
		System.out.println(categoria);	
		catedao.guardarCategoria(categoria);
		loadCategoria();
		
		//return null;
		return "listadoCategoria";
	}
	

	/**
	 * Agrega categoria.
	 *
	 * @return the string
	 */

	public String agregaCategoria(){
		
		categoria.getEventos().add(new Evento());
		return null;
	}
	
	

}

