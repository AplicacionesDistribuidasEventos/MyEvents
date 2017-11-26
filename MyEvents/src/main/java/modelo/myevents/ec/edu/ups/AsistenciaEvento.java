package modelo.myevents.ec.edu.ups;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ASISTENCIAEVENTO")
public class AsistenciaEvento {

	@Id
	@Column(name="aev_codigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@Column(name="aev_estado")
	private String estado;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "AsistenciaEvento [codigo=" + codigo + ", estado=" + estado + "]";
	}
	
	
}
