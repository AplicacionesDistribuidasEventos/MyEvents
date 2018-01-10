package modelo.myevents.ec.edu.ups;

public class CategoriaEventos {
	private int codigo_eve;
//	private String descripcion_cat;
	private String descripcion_eve;
	private String fecha;
	private String costo;

	/*Nombre de la categoria*/
	private String categoria;
	
	public int getCodigo_eve() {
		return codigo_eve;
	}

	public void setCodigo_eve(int codigo_eve) {
		this.codigo_eve = codigo_eve;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion_eve() {
		return descripcion_eve;
	}

	public void setDescripcion_eve(String descripcion_eve) {
		this.descripcion_eve = descripcion_eve;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "CategoriaEventos [codigo_eve=" + codigo_eve + ", descripcion_eve=" + descripcion_eve + ", fecha="
				+ fecha + ", costo=" + costo + ", categoria=" + categoria + "]";
	}

}