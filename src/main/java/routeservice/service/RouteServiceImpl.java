package routeservice.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import routeservice.constants.ErrorConsts;
import routeservice.domain.Local;
import routeservice.domain.Mapa;
import routeservice.domain.Rota;
import routeservice.domain.RotaPk;
import routeservice.exception.BussinessException;
import routeservice.provider.BuscaMelhorCaminhoProvider;
import routeservice.provider.CalculoDoCusto;
import routeservice.vo.BuscaRotaVo;
import routeservice.vo.Caminho;
import routeservice.vo.RotaVo;

@Service
@Transactional
public class RouteServiceImpl implements RouteService, ErrorConsts {


	@PersistenceContext
	public EntityManager manager;

	@Override
	public Caminho buscaMelhorCaminho(final BuscaRotaVo busca) throws BussinessException {
		if (busca.destino == null) {
			throw new BussinessException("O local de destino e obrigatorio");
		}
		if (busca.origem == null) {
			throw new BussinessException("A local de origem e obrigatorio");
		}
		if (busca.mapa == null) {
			throw new BussinessException("0 valor do mapa e obrigatorio");
		}

		javax.persistence.Query qry = manager.createNamedQuery(Mapa.BUSCA_MAPA_POR_DESCRICAO);
		qry.setParameter(1, busca.mapa);
		Mapa mapa = buscaMapa(busca.mapa);
		if (mapa == null) {
			throw new BussinessException(MAPA_NAO_ENCONTRADO);
		}
		Local origem = buscaLocal(busca.origem);
		if (origem == null) {
			throw new BussinessException(LOCAL_DE_ORIGEM_NAO_ENCONTRADO);
		}
		Local destino = buscaLocal(busca.destino);
		if (destino == null) {
			throw new BussinessException(LOCAL_DE_DESTINO_NAO_ENCONTRADO);
		}
		Caminho caminho = BuscaMelhorCaminhoProvider.getDefaultInstance().buscaMelhorCaminho(mapa.getRotas(),
				busca.origem, busca.destino, new CalculoDoCusto() {
					@Override
					public double calculaCustoRota(RotaVo rota) {
						return (busca.autonomia == 0 ? 0 : rota.distancia / busca.autonomia) * busca.custoPorLitro;
					}

					@Override
					public String valorDescritivoCusto(double valor) {
						return "R$ "+ DecimalFormat.getInstance(Locale.forLanguageTag("pt-BR")).format(valor);
					}
				});

		return caminho;

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void inserirMapas(String mapaValue, RotaVo[] rotas) throws BussinessException {
		
		if (rotas == null || rotas.length == 0) {
			throw new BussinessException(E_OBRIGATORIO_O_MAPA_TER_PELO_MENOS_UMA_ROTA);
		}
		
		Mapa mapa = buscaMapa(mapaValue);
		if (mapa != null) {
			removeMapa(mapa);
		}
		mapa = new Mapa();
		mapa.setDescricao(mapaValue);
		manager.persist(mapa);
		manager.flush();
		manager.refresh(mapa);

		for (RotaVo rotaVo : rotas) {
			if (rotaVo.origem == null) {
				throw new BussinessException(LOCAL_DE_ORIGEM_DA_ROTA_E_OBRIGATORIO);
			}
			if (rotaVo.destino == null) {
				throw new BussinessException(LOCAL_DE_DESTINO_DA_ROTA_E_OBRIGATORIO);
			}
			if (rotaVo.distancia == null) {
				throw new BussinessException(O_VALOR_DA_DISTANCIA_E_OBRIGATORIA);
			}
			if (rotaVo.distancia < 1) {
				throw new BussinessException(A_DISTANCIA_DEVE_SER_MAIOR_QUE_ZERO);
			}
			Local localOrigem = buscaLocal(rotaVo.origem);
			if (localOrigem == null) {
				localOrigem = new Local(rotaVo.origem);
				manager.persist(localOrigem);
				manager.flush();
			}
			Local localDestino = buscaLocal(rotaVo.destino);
			if (localDestino == null) {
				localDestino = new Local(rotaVo.destino);
				manager.persist(localDestino);
				manager.flush();
			}
			RotaPk rotaPk = new RotaPk(localOrigem, localDestino, mapa.getIdMapa());
			Rota rota = manager.find(Rota.class, rotaPk);
			if (rota == null) {
				rota = new Rota(rotaPk, rotaVo.distancia);
				manager.persist(rota);
				manager.flush();
			}
		}

		manager.flush();

	}

	private void removeMapa(Mapa mapa) {
		List<Rota> rotas = mapa.getRotas();
		for (Rota rota : rotas) {
			manager.remove(rota);
			manager.flush();
		}
		manager.refresh(mapa);
		manager.remove(mapa);
		manager.flush();
	}

	private Mapa buscaMapa(String mapa) {
		javax.persistence.Query qry = manager.createNamedQuery(Mapa.BUSCA_MAPA_POR_DESCRICAO);
		qry.setParameter(1, mapa);
		try {
			return (Mapa) qry.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	private Local buscaLocal(String local) {
		javax.persistence.Query qry = manager.createNamedQuery(Local.BUSCA_LOCAL_POR_NOME);
		qry.setParameter(1, local);
		try {
			return (Local) qry.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
