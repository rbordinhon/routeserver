package routeservice.provider;

import java.util.List;

import routeservice.domain.Rota;
import routeservice.exception.BussinessException;
import routeservice.vo.Caminho;

public interface BuscaMelhorCaminho {
  public Caminho buscaMelhorCaminho(List<Rota> rotas, String origem, String destino, CalculoDoCusto calcCusto) throws BussinessException;
}
