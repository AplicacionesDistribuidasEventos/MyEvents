package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.CategoriaDAO;
import modelo.myevents.ec.edu.ups.Categoria;
import modelo.myevents.ec.edu.ups.Evento;

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
		
		categoria.addEvento(new Evento());
		
		
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
		return "listadoCategoriaAcciones";
		
	}//fin elimina categoria
	
	public String guardar(){
		
		System.out.println(categoria);	
		catedao.guardarCategoria(categoria);
		loadCategoria();
		
		//return null;
		return "listadoCategoria";
	}
	
	public String agregaCategoria(){
		
		categoria.getEventos().add(new Evento());
		return null;
	}
	
	

}//fin clase

