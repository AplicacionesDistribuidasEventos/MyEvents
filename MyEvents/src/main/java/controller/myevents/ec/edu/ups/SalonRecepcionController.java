package controller.myevents.ec.edu.ups;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import dao.myevents.ec.edu.ups.SalonRecepcionDAO;
import modelo.myevents.ec.edu.ups.SalonRecepcion;

@ManagedBean
public class SalonRecepcionController {
	
	private SalonRecepcion salonrecepcion;
	private List<SalonRecepcion> lsalonrecepcion;
	private int id;
	
	@Inject
	private SalonRecepcionDAO srdao;
	
	@PostConstruct
	public void init() {
		salonrecepcion = new SalonRecepcion();
		listaSalonRecepcion();
	}
	
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
		//aedao.insertAEvento(asistenciaevento);
		srdao.guardar(salonrecepcion);
		return "listadoSalonRecepcion";
	}
	
	public String leer(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return null;
	}
	
	public String eliminar(int id) {
		srdao.removeSRecepcion(id);
		return null;
	}
	
	public List<SalonRecepcion> listaSalonRecepcion() {
		lsalonrecepcion =srdao.listSRecepcion();
		return lsalonrecepcion;
	}
	
	public String cargarEstado(int id) {
		salonrecepcion = srdao.selectSRecepcion(id);
		return "recuperarAsistenciaEvento";
	}

}
