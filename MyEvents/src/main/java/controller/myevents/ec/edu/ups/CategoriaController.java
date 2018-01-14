package controller.myevents.ec.edu.ups;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import dao.myevents.ec.edu.ups.EventoDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.AsistenciaEvento;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.CategoriaEventos;
import modelo.myevents.ec.edu.ups.Evento;
import modelo.myevents.ec.edu.ups.Persona;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriaController.
 */
@ManagedBean
@SessionScoped
public class CategoriaController {
	
	/** Inyeccion de dependecias a los DAO que se van a utilizar */
	@Inject
	private CategoriaDAO catedao;

	/** Variables definidas para el manejo de los Locales */
	private int id;
	private String cabecera_cat;


	/** Variables de lista y de objetos utilizados */
	private Categoria categoria = null;
	private List<Categoria> categorias;
	private List<CategoriaEventos> catevelist;
	
	/**Recuperar los objetos eventos segun la categoria*/
	private List<Categoria> listCatID;
	
	
	/**
	 * init().
	 * Metodo para incializar los metodos de clase
	 */
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		loadCategoria();
		catevelist = new ArrayList<CategoriaEventos>();
		listCatID = new ArrayList<Categoria>();
//		categorias = new ArrayList<Categoria>();

	}
	
	/**
	 * Geters y Seters de las variables definidas.
	 */
	public String getCabecera_cat() {
		return cabecera_cat;
	}


	public void setCabecera_cat(String cabecera_cat) {
		this.cabecera_cat = cabecera_cat;
	}


	public List<CategoriaEventos> getCatevelist() {
		return catevelist;
	}

	public void setCatevelist(List<CategoriaEventos> catevelist) {
		this.catevelist = catevelist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadCategoriaEditar(id);//parametros
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void loadCategoria(){
		
		categorias = catedao.listCategoria();
	}
	

	/**
	 * Datos categoria.
	 *
	 * @param codigo the codigo
	 * @return the string
	 */
	/**DATOS CATEGORIA PARA EL USUARIO
	 */
	public String datosCategoria(int codig){
		catevelist.clear();
		CategoriaEventos ce = new CategoriaEventos();
		listCatID = catedao.listCategoriaID(codig);
		for(Categoria c : listCatID) {
			System.out.println("NEXT CE NOM "+c.getNombre());
			if(c.getEventos().isEmpty()) {
				ce = new CategoriaEventos();
//				ce.setDescripcion_eve(c.getDescipcion());
/*				ce.setCategoria(c.getNombre());
				ce.setDescripcion_eve("No contiene registros");
				ce.setFecha("");
				ce.setCosto("");
				catevelist.add(ce);
*/				
			}else {
				for(Evento e : c.getEventos()) {
					ce = new CategoriaEventos();
					ce.setCategoria(c.getNombre());
					cabecera_cat = c.getNombre();
					ce.setCodigo_eve(e.getCodigo());
					ce.setDescripcion_eve(e.getDescripcion());
					ce.setFecha(e.getFechaEvento().toString());
					ce.setCosto(String.valueOf(e.getCosto()));
					catevelist.add(ce);
				}
			}
		}
		return "eventosCategoria.xhtml";
	}
	
	
	
	/**
	 * Load categoria editar.
	 *
	 * @param id the id
	 * @return URL para cargar las categorias listadoCategoriaAcciones.xhtml
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
	 * @return URL para eliminar la categoria en JSF segun el parámetro enviado id.
	 */
	public String eliminaCategoria(int id){
		
		catedao.eliminarCategoria(id);
		return "listadoCategoriaAcciones";
		
	}
	
	/**
	 * Guardar. Método para insertar categorias.
	 *
	 * @return URl para ver el listado de las categorias insertadas definidas en listadoCategoria.xhtml
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

