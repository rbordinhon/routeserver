package routeservice.provider;

import java.util.HashMap;
import java.util.Map;

public class BuscaMelhorCaminhoProvider{

	public static final Map<String, BuscaMelhorCaminho> map = new HashMap<String, BuscaMelhorCaminho>();

	static {
		// Algoritimo padrao para a busca do menor caminho
		DijkstraAlgorithmImpl dijkstra = new DijkstraAlgorithmImpl();
		map.put(null,dijkstra);
		map.put("",dijkstra);

		map.put(DijkstraAlgorithmImpl.class.getSimpleName(), dijkstra);
	}

	public static BuscaMelhorCaminho getInstance(String algoritimo) {
		//Para evitar null pointer
		return map.get(algoritimo == null ? "" : algoritimo.trim());
	}
	
	public static BuscaMelhorCaminho getDefaultInstance() {
		return map.get(null);
	}


}
