package routeservice.provider;

import routeservice.vo.RotaVo;

public interface CalculoDoCusto {
  public double calculaCustoRota(RotaVo rota);
  
  public String valorDescritivoCusto(double valor);
}
