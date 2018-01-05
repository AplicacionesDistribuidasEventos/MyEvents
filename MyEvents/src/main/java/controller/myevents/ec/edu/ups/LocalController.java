package controller.myevents.ec.edu.ups;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;

import dao.myevents.ec.edu.ups.LocalDAO;
import dao.myevents.ec.edu.ups.PersonaDAO;
import modelo.myevents.ec.edu.ups.Local;
import modelo.myevents.ec.edu.ups.Persona;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalController.
 */
@ManagedBean
public class LocalController {

	
	/** The locdao. */
	@Inject
	private LocalDAO locdao;
	
	/** The pdao. */
	@Inject
	private PersonaDAO pdao;
	

	/** The local. */
	private Local local; 
	
	/** The p. */
	private Persona p ;
	
	

	/** The listlocal. */
	private List<Local> listlocal;
	
	/** The aux local. */
	private Local auxLocal;
	
	/** The id. */
	
	private int id;
	
	/** The id 2. */
	private int id2;

	/** The latitud. */
	
	private String latitud="-1";
	
	/** The longitud. */
	private String longitud="-1"; 
	
	/** The descripcion. */
	private String descripcion;
	
	/** The elegimos. */
	private String elegimos;
	
	/** The latituddes. */
	private String latituddes;
	
	/** The longituddes. */
	private String longituddes;
	
	/** The foto perfil. */
	private Part fotoPerfil;	
	
	/** The directorio perfil. Almacena la url donde se alamcenara la imagen */
	//private String directorioPerfil= "C:\\Users\\sesla\\git\\MyEvents\\MyEvents\\src\\main\\webapp\\imagenes";
	private String directorioPerfil="C:\\Users\\asus\\git\\MyEvents\\MyEvents\\src\\main\\webapp\\imageness";					  

	private String nombreArchivoPerfil;
	
	/** The imagenes. */
	private List<String> imagenes;
  
	/**
	 * Inits the.
	 * Metodo para incializar los metodos de clase
	 */
	@PostConstruct
	public void init() {
		local = new Local(); 
		loadLocal();
		fotoPerfil=null;
		listlocal=locdao.listlocal();
		
	}
	

	/**
	 * Gets the imagenes.
	 *
	 * @return the imagenes
	 */
	public List<String> getImagenes() {
		return imagenes;
	}


	/**
	 * Sets the imagenes.
	 *
	 * @param imagenes the new imagenes
	 */
	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}


	/**
	 * Gets the foto perfil.
	 *
	 * @return the foto perfil
	 */
	public Part getFotoPerfil() {
		return fotoPerfil;
	}


	/**
	 * Sets the foto perfil.
	 *
	 * @param fotoPerfil the new foto perfil
	 */
	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}



	/**
	 * Gets the nombre archivo perfil.
	 *
	 * @return the nombre archivo perfil
	 */
	public String getNombreArchivoPerfil() {
		return nombreArchivoPerfil;
	}



	/**
	 * Sets the nombre archivo perfil.
	 *
	 * @param nombreArchivoPerfil the new nombre archivo perfil
	 */
	public void setNombreArchivoPerfil(String nombreArchivoPerfil) {
		this.nombreArchivoPerfil = nombreArchivoPerfil;
	}



	/**
	 * Gets the directorio perfil.
	 *
	 * @return the directorio perfil
	 */
	public String getDirectorioPerfil() {
		return directorioPerfil;
	}



	/**
	 * Sets the directorio perfil.
	 *
	 * @param directorioPerfil the new directorio perfil
	 */
	public void setDirectorioPerfil(String directorioPerfil) {
		this.directorioPerfil = directorioPerfil;
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
		loadLocalEditar(id);
		
		loadId(id);
		insertarLocalAdmin();
	}
	
	/**
	 * Gets the local.
	 *
	 * @return the local
	 */
	public Local getLocal() {
		return local;
	}
	
	/**
	 * Sets the local.
	 *
	 * @param local the new local
	 */
	public void setLocal(Local local) {
		this.local = local;
	}
	
	/**
	 * Gets the locdao.
	 *
	 * @return the locdao
	 */
	public LocalDAO getLocdao() {
		return locdao;
	}
	
	/**
	 * Sets the locdao.
	 *
	 * @param locdao the new locdao
	 */
	public void setLocdao(LocalDAO locdao) {
		this.locdao = locdao;
	}
	
	/**
	 * Gets the listlocal.
	 *
	 * @return the listlocal
	 */
	public List<Local> getListlocal() {
		return listlocal;
	}
	
	/**
	 * Sets the listlocal.
	 *
	 * @param listlocal the new listlocal
	 */
	public void setListlocal(List<Local> listlocal) {
		this.listlocal = listlocal;
	}
	
	/**
	 * Gets the aux local.
	 *
	 * @return the aux local
	 */
	public Local getAuxLocal() {
		return auxLocal;
	}
	
	/**
	 * Sets the aux local.
	 *
	 * @param auxLocal the new aux local
	 */
	public void setAuxLocal(Local auxLocal) {
		this.auxLocal = auxLocal;
	}
	
	/**
	 * Gets the latitud.
	 *
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}
	
	/**
	 * Sets the latitud.
	 *
	 * @param latitud the new latitud
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	
	/**
	 * Gets the longitud.
	 *
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}
	
	/**
	 * Sets the longitud.
	 *
	 * @param longitud the new longitud
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the elegimos.
	 *
	 * @return the elegimos
	 */
	public String getElegimos() {
		return elegimos;
	}
	
	/**
	 * Sets the elegimos.
	 *
	 * @param elegimos the new elegimos
	 */
	public void setElegimos(String elegimos) {
		this.elegimos = elegimos;
	}
	
	/**
	 * Gets the latituddes.
	 *
	 * @return the latituddes
	 */
	public String getLatituddes() {
		return latituddes;
	}
	
	/**
	 * Sets the latituddes.
	 *
	 * @param latituddes the new latituddes
	 */
	public void setLatituddes(String latituddes) {
		this.latituddes = latituddes;
	}
	
	/**
	 * Gets the longituddes.
	 *
	 * @return the longituddes
	 */
	public String getLongituddes() {
		return longituddes;
	}
	
	/**
	 * Sets the longituddes.
	 *
	 * @param longituddes the new longituddes
	 */
	public void setLongituddes(String longituddes) {
		this.longituddes = longituddes;
	}


	/**
	 * Gets the id 2.
	 *
	 * @return the id 2
	 */
	public int getId2() {
		return id2;
	}
  

	/**
	 * Sets the id 2.
	 *
	 * @param id2 the new id 2
	 */
	public void setId2(int id2) {
		this.id2 = id2;
		
	} 


	/**
	 * Load local.
	 */
	public void loadLocal() {
		listlocal=locdao.listlocal();
	}
	
	/**
	 * Load local editar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String loadLocalEditar(int id) {
		local = locdao.leerLocal(id);
		return "CrearLocal";
	}
	
	/**
	 * Load id.
	 *
	 * @param id the id
	 */
	public void loadId(int id) {
		id2 = id;
	} 

	
	/**
	 * Guardar local admin.
	 *
	 * @return the string
	 */
	public String insertarLocalAdmin() {
		
		if(fotoPerfil!=null){
			saveFotoLocal();
			local.setFotoPerfil("imagenes/"+nombreArchivoPerfil);
		}else{	
			
					local.setFotoPerfil("imagenes/local1.jpg");
				
		}
		
		p = pdao.selectPersona(id2);
		p.getLocales().add(local);
		pdao.updatePersona(p); 
		return "AccionesLocal";
		
	}

	
	/**
	 * Guardar local.
	 *
	 * @return the string
	 */
	public String insertar() {
	
		if(fotoPerfil!=null){
			saveFotoLocal();
			local.setFotoPerfil("imagenes/"+nombreArchivoPerfil);
		}else{	
			
					local.setFotoPerfil("imagenes/local1.jpg");
				
			
		}
		
		locdao.guardarLocal(local);
		loadLocal();
		init();
		return null;
	}

	/**
	 * Actualizar.
	 *
	 * @return the string
	 */
	public String actualizar() {
		locdao.updateLocal(local);
		
		return null;
	}
	
	/**
	 * Leer.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String leer(int id) {
		
		local = locdao.leerLocal(id);
		return null;
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String eliminar(int id) {
		locdao.deleteLocal(id);
		return "eliminarLocal";
	}
	
	/**
	 * Lista locales.
	 *
	 * @return the list
	 */
	public List<Local> listaLocales(){
		listlocal = locdao.listlocal();
		return listlocal;
	}
	
	/**
	 * Save foto local.
	 *
	 * @return the string
	 */
	
	public String saveFotoLocal () {
	
		try (InputStream input = fotoPerfil.getInputStream()) {
			
			nombreArchivoPerfil = fotoPerfil.getSubmittedFileName();
			
			 Files.copy(input, new File(directorioPerfil, nombreArchivoPerfil).toPath()); 
			 System.out.println(
			 "Uploaded file successfully saved in " + fotoPerfil);
			 

		} catch (IOException e) {
			System.out.println("Error inesperado al subir foto" + e.getMessage());
		}
		return "";
	}
	
	/**
	 * Sets the ea id.
	 *
	 * @param idL the new ea id
	 */
	public void seteaId(int idL) {
		System.out.println("Este es el id qeu se esta seteando" + idL);
		EventoController.idLocal=idL;
		System.out.println("Imprimeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" + EventoController.idLocal);
	}
	
	/**
	 * Cargar mapa.
	 *
	 * @param l the l
	 * @return the string
	 */
	public String cargarMapa(Local l ){
		auxLocal = l;
		return("mapa_localidades");
	}
	
	/**
	 * Cargar archivo.
	 *
	 * @param event the event
	 */
	public void cargarArchivo(FileUploadEvent event) {

	local.setImagen(event.getFile().getContents());
	
	FacesMessage message= new FacesMessage("Exito", event.getFile().getFileName()+ "Cargadoo..");
	FacesContext.getCurrentInstance().addMessage(null, message);
	
	}

	/**
	 * Registar img local.
	 *
	 * @param l the l
	 * @return the string
	 */
	public String registarImgLocal(Local l){
		
	
		
		try {
			
			locdao.insertarLocal(l);
			
			
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!",
                               "Local cadastrado com sucesso!"));
		} catch (Exception e) {
			// TODO: handle exception
			
			
			System.out.println("entramoooooooooooo..........");
		}
			
		

    return null;
}
		
}


