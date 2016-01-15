package routeservice.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class Rota implements Cloneable {

	/**
	 *@author Rodrigo Brdinhon 
	 */

	public Rota() {
		rotaPk = new RotaPk();
	}

	public Rota( Local origem, Local destino,Integer idMapa, Integer distancia) {
		rotaPk = new RotaPk(origem, destino,idMapa);
		this.distancia = distancia;
	}
	public Rota( RotaPk rotaPk, Integer distancia) {
		this.rotaPk = rotaPk;
		this.distancia = distancia;
	}
	
	
	@EmbeddedId
	public final RotaPk rotaPk;
	
	@Column
	private Integer distancia;

	

	public int getIdMapa() {
		return rotaPk.getIdMapa();
	}

	public void setIdMapa(int idMapa) {
		rotaPk.setIdMapa(idMapa);
	}

	public RotaPk getRotaPk() {
		return rotaPk;
	}

	

	public Local getOrigem() {
		return rotaPk.getOrigem();
	}

	

	public Local getDestino() {
		return rotaPk.getDestino();
	}

	
	
	

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
    
	
	/**
	 * Funcao auxiliar para configurar o peso no algoritimo
	 * de busca.
	 * @return
	 */
	public double getPeso(){
		return distancia;
		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
