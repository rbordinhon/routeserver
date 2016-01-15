package routeservice.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
@NamedQueries({
	@NamedQuery(name=Mapa.BUSCA_MAPA_POR_DESCRICAO,query="select o from Mapa o where upper(o.descricao)=upper(?)")
	
}
		
		)
public class Mapa implements Serializable{

	public static final  String BUSCA_MAPA_POR_DESCRICAO="mapa.buscaPordescricao";
	
	/**
	 *@author Rodrigo Bordinhon 
	 */
	private static final long serialVersionUID = 8808859747860683054L;

	@Id
	@Column
	@GeneratedValue
	private int idMapa;
	
	@Column(unique = true)
	private String descricao;
	
	@OneToMany(mappedBy="rotaPk.idMapa",fetch = FetchType.EAGER)
	private List<Rota> rotas;

	public int getIdMapa() {
		return idMapa;
	}

	public void setIdMapa(int idMapa) {
		this.idMapa = idMapa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMapa ^ (idMapa >>> 32));
		result = prime * result + ((rotas == null) ? 0 : rotas.hashCode());
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
		Mapa other = (Mapa) obj;
		if (idMapa != other.idMapa)
			return false;
		if (rotas == null) {
			if (other.rotas != null)
				return false;
		} else if (!rotas.equals(other.rotas))
			return false;
		return true;
	}
	
	
	
	
}
