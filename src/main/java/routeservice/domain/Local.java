package routeservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table
@Entity
@NamedQueries({ @NamedQuery(name = Local.BUSCA_LOCAL_POR_NOME, query = "select o from Local o where upper(o.nome)=upper(?)")


}

)
public class Local implements Serializable {
	/**
	 * @author Rodrigo Bordinhon
	 */
	private static final long serialVersionUID = 2466517143525853518L;
	public static final String BUSCA_LOCAL_POR_NOME = "Local.buscaPorNome";



	public Local() {
		// TODO Auto-generated constructor stub
	}

	public Local(String nome) {
		super();
		this.nome = nome;
	}

	@Id
	@Column
	@GeneratedValue
	private Long idLocal;

	@Column(unique = true)
	private String nome;

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idLocal ^ (idLocal >>> 32));
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
		Local other = (Local) obj;
		if (idLocal != other.idLocal)
			return false;
		return true;
	}

}
