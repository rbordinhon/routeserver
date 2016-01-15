package routeservice.service;

import java.util.List;

import routeservice.exception.BussinessException;
import routeservice.vo.BuscaRotaVo;
import routeservice.vo.Caminho;
import routeservice.vo.RotaVo;

public interface RouteService {
	public Caminho buscaMelhorCaminho(BuscaRotaVo rotaVo) throws BussinessException;

	public void inserirMapas(String mapa, RotaVo[] rotas) throws BussinessException;
}
