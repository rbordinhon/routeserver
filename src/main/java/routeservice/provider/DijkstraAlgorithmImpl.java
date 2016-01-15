package routeservice.provider;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import routeservice.constants.ErrorConsts;
import routeservice.domain.Rota;
import routeservice.exception.BussinessException;
import routeservice.vo.Caminho;
import routeservice.vo.RotaVo;

// Algoritmo de busca  
class DijkstraAlgorithmImpl implements BuscaMelhorCaminho, ErrorConsts {

	public Caminho buscaMelhorCaminho(List<Rota> rotas, String origem, String destino, CalculoDoCusto custo)
			throws BussinessException {
		DirectedGraph<String, RotaEdge> grap = new DefaultDirectedGraph<String, RotaEdge>(RotaEdge.class);
		for (Rota rota : rotas) {
			grap.addVertex(rota.getOrigem().getNome());
			grap.addVertex(rota.getDestino().getNome());
			grap.addEdge(rota.getOrigem().getNome(), rota.getDestino().getNome(), new RotaEdge(rota, custo));

		}
		List<RotaEdge> rotasEdge = DijkstraShortestPath.findPathBetween(grap, origem, destino);
		if (rotasEdge == null) {
			throw new BussinessException(NAO_HA_CAMINHO_PARA_A_ROTA_SOLICITADA_OU_A_ROTA_NÃO_FOI_CONFIGURADA);
		}
		Caminho caminho = new Caminho();
		caminho.rotas = new RotaVo[rotasEdge.size()];
		caminho.custo = 0;
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < rotasEdge.size(); i++) {
			caminho.rotas[i] = rotasEdge.get(i).getRota();
			caminho.custo += rotasEdge.get(i).getWeight();
			if (i > 0) {
				build.append("-");
				build.append(caminho.rotas[i].destino);
			} else {
				build.append(caminho.rotas[i].origem);
				build.append("-");
				build.append(caminho.rotas[i].destino);
			}
		}
		caminho.valorCustoDescritivo = custo == null ? (caminho.custo + " Km"): custo.valorDescritivoCusto(caminho.custo);
		caminho.rota = build.toString();
		return caminho;
	}

}

class RotaEdge extends DefaultWeightedEdge {
	/**
	 * @author Rodrigo
	 */
	private static final long serialVersionUID = 6303934308128570578L;
	private final RotaVo source = new RotaVo();
	private CalculoDoCusto custo;

	public RotaEdge(Rota rota, CalculoDoCusto custo) {
		this.source.origem = rota.getOrigem().getNome();
		this.source.destino = rota.getDestino().getNome();
		this.source.distancia = rota.getDistancia();
		this.custo = custo;
	}

	@Override
	protected Object getSource() {
		return this.source.origem;
	}

	@Override
	protected Object getTarget() {
		return this.source.destino;
	}

	@Override
	public double getWeight() {
		return custo == null ? source.distancia : custo.calculaCustoRota(source);
	}

	


	public RotaVo getRota() {
		return source;
	}

}
