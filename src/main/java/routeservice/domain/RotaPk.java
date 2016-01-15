package routeservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class RotaPk implements Serializable{
	
	/**
	 *@author Rodrigo Bordinhon 
	 */
	private static final long serialVersionUID = 3712141563057927779L;

	public RotaPk() {
	}
	
	public RotaPk(Local origem, Local destino, int idMapa) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.idMapa=idMapa;
	}

	@Column
	public int idMapa;
	
	@ManyToOne()
	@JoinColumn(name = "idOrigem",foreignKey = @ForeignKey(name="idLocal"))
	private Local origem;

	@ManyToOne
	@JoinColumn(name = "idDestino",foreignKey = @ForeignKey(name="idLocal"))
	private Local destino;

	public Local getOrigem() {
		return origem;
	}

	public void setOrigem(Local origem) {
		this.origem = origem;
	}

	public Local getDestino() {
		return destino;
	}

	public void setDestino(Local destino) {
		this.destino = destino;
	}

	public int getIdMapa() {
		return idMapa;
	}

	public void setIdMapa(int idMapa) {
		this.idMapa = idMapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + idMapa;
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RotaPk other = (RotaPk) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (idMapa != other.idMapa)
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		return true;
	}

	
	
	

}
