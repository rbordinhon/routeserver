package routeservice.vo;

public class BuscaRotaVo {
	public String mapa;
	public String origem;
	public String destino;
	public Double autonomia;
	public Double custoPorLitro;

	public BuscaRotaVo() {
	}
	
	public BuscaRotaVo(String mapa, String origem, String destino, double autonomia, double custoPorLitro) {
		this.mapa = mapa;
		this.origem = origem;
		this.destino = destino;
		this.autonomia = autonomia;
		this.custoPorLitro = custoPorLitro;
	}
	
	public static void main(String[] args) {
		
	}
}