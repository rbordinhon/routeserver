package routeservice.vo;

public class RotaVo {
	public String origem;
	public String destino;
	public int distancia;

	public RotaVo(String origem, String destino, int distancia) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}

	public RotaVo() {

	}

}
