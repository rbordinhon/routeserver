package routeservice.vo;

public class Caminho {

	public RotaVo[] rotas;
	
	public double custo;
	
	public String valorCustoDescritivo;

	public String rota;
	
	@Override
	public String toString() {
		
		return rota;
	}
}
