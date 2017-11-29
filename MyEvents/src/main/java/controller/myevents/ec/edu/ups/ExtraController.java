package controller.myevents.ec.edu.ups;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;


import dao.myevents.ec.edu.ups.ExtraDAO;
import modelo.myevents.ec.edu.ups.Extra;

@ManagedBean
public class ExtraController {
	
	
	@Inject
	private ExtraDAO extdao;
	
	private Extra extra = null;
	
	private List<Extra> extras;
	
	private int id;
	private int codigo;
	
	
	@PostConstruct
	public void init(){
		
		extra=new Extra();
		loadExtra();
		
	}

	////getter and setter
	
	public int getId() {
		return id;
	}
	//
	public int getcodigo() {
		return codigo;
	}
	//

	public void setId(int id) {
		this.id = id;
		loadExtraEditar(id);//parametros
	}
	
	public Extra getExtra() {
		return extra;
	}

	
	public void setExtra(Extra extra) {
		this.extra = extra;
	}

	
	public List<Extra> getExtras() {
		return extras;
	}

	public void setCategorias(List<Extra> extras) {
		this.extras = extras;
	}
	
/////////////Controller mantenimiento

	public void loadExtra(){
		
		extras = extdao.listExtra();
	}
	
	
	public String loadExtraEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		extra = extdao.leerExtra(id);
		return "listadoExtraAcciones";
		
	}//fin editar
	
	public String eliminaExtra(int id){
		
		extdao.eliminarExtra(id);
		return "litadoExtraAcciones";
		
	}//fin elimina categoria
	
	public String guardar(){
		
		System.out.println(extra);
		
		//PracticaDAO pdao= new PracticaDAO(); no es valido
		
		extdao.guardarExtra(extra);
		loadExtra();
		
		//return null;
		return "listadoExtra";
	}
	

}//fin clase

