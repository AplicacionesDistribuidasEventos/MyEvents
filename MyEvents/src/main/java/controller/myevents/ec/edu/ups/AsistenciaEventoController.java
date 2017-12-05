package controller.myevents.ec.edu.ups;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.AsistenciaEventoDAO;
import modelo.myevents.ec.edu.ups.AsistenciaEvento;

@ManagedBean
public class AsistenciaEventoController {
	
	private AsistenciaEvento asistenciaevento;
	private List<AsistenciaEvento> lasistenciaevento;
	private int id;
	
	@Inject
	private AsistenciaEventoDAO aedao;
	
	@PostConstruct
	public void init() {
		asistenciaevento = new AsistenciaEvento();
		listaAsistenciaEvento();
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		cargarEstado(id);
	}




	public AsistenciaEvento getAsistenciaevento() {
		return asistenciaevento;
	}
	public void setAsistenciaevento(AsistenciaEvento asistenciaevento) {
		this.asistenciaevento = asistenciaevento;
	}
	public List<AsistenciaEvento> getLasistenciaevento() {
		return lasistenciaevento;
	}
	public void setLasistenciaevento(List<AsistenciaEvento> lasistenciaevento) {
		this.lasistenciaevento = lasistenciaevento;
	}

	public String crear() {
		//aedao.insertAEvento(asistenciaevento);
		aedao.guardar(asistenciaevento);
		return "listadoAsistenciaEvento";
	}
	
	public String leer(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return null;
	}
	
	public String eliminar(int id) {
		aedao.removeAEvento(id);
		return null;
	}
	
	public List<AsistenciaEvento> listaAsistenciaEvento() {
		lasistenciaevento = aedao.listAEvento();
		return lasistenciaevento;
	}
	
	public String cargarEstado(int id) {
		asistenciaevento = aedao.selectAEvento(id);
		return "recuperarAsistenciaEvento";
	}

}