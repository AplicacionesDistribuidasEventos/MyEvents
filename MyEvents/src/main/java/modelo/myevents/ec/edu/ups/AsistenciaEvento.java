package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaEvento.
 */
@Entity
@Table(name="ASISTENCIAEVENTO")
public class AsistenciaEvento {

	/** The codigo. */
	@Id
	@Column(name="aev_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	/** The estado. */
	@Column(name="aev_estado")
	private String estado;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AsistenciaEvento [codigo=" + codigo + ", estado=" + estado + "]";
	}
	
	
}
