package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import modelo.myevents.ec.edu.ups.Categoria;

@ManagedBean
public class CategoriaController {
	
	
	@Inject
	private CategoriaDAO catedao;
	
	private Categoria categoria = null;
	
	private List<Categoria> categorias;
	
	private int id;
	
	
	@PostConstruct
	public void init(){
		
		categoria=new Categoria();
		loadCategoria();
		
	}

	////getter and setter
	
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
	
/////////////Controller mantenimiento

	public void loadCategoria(){
		
		categorias = catedao.listCategoria();
	}
	
	
	public String loadCategoriaEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		categoria = catedao.leerCategoria(id);
		return "listadoCategoriaAcciones";
		
	}//fin editar
	
	public String eliminaCategoria(int id){
		
		catedao.eliminarCategoria(id);
		return "litadoCategoriaAcciones";
		
	}//fin elimina categoria
	
	public String guardar(){
		
		System.out.println(categoria);
		
		//PracticaDAO pdao= new PracticaDAO(); no es valido
		
		catedao.guardarCategoria(categoria);
		loadCategoria();
		
		//return null;
		return "listadoCategoria";
	}
	

}//fin clase

