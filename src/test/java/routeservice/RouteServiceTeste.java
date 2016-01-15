package routeservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import routeservice.config.ComponentsConfig;
import routeservice.config.PersistenceConfigs;
import routeservice.constants.ErrorConsts;
import routeservice.domain.Local;
import routeservice.domain.Mapa;
import routeservice.domain.Rota;
import routeservice.exception.BussinessException;
import routeservice.provider.BuscaMelhorCaminhoProvider;
import routeservice.service.RouteService;
import routeservice.vo.BuscaRotaVo;
import routeservice.vo.Caminho;
import routeservice.vo.RotaVo;

public class RouteServiceTeste {

	private static AnnotationConfigApplicationContext ctx;

	@BeforeClass
	public static void beforeClass() {
		if (ctx == null) {
			System.setProperty("ambiente", "teste");
			ctx = new AnnotationConfigApplicationContext();
			ctx.register(ComponentsConfig.class);
			ctx.register(PersistenceConfigs.class);
			ctx.refresh();
			//Testa uma busca no entity manager
			EntityManager manager = ctx.getBean(LocalContainerEntityManagerFactoryBean.class).nativeEntityManagerFactory
					.createEntityManager();
			Mapa mapa = manager.find(Mapa.class, 1);
			Assert.assertNotNull(mapa);
		}
	}

	

	@Test
	public void testeAlgoritimoBUsca() throws BussinessException {
		final List<Rota> rotas = new ArrayList<Rota>();
		rotas.add(new Rota(new Local("A"), new Local("B"), 1, 10));
		rotas.add(new Rota(new Local("B"), new Local("D"), 1, 20));
		rotas.add(new Rota(new Local("A"), new Local("C"), 1, 5));
		rotas.add(new Rota(new Local("C"), new Local("D"), 1, 50));
		rotas.add(new Rota(new Local("B"), new Local("D"), 1, 50));
		rotas.add(new Rota(new Local("D"), new Local("E"), 1, 30));
		rotas.add(new Rota(new Local("E"), new Local("F"), 1, 50));
		Assert.assertEquals(BuscaMelhorCaminhoProvider.getDefaultInstance().buscaMelhorCaminho(rotas, "A", "D", null).rota, "A-B-D");

	}

	@Test
	public void testeServicoDeBusca() throws BussinessException {
		RouteService service = ctx.getBean(RouteService.class);
		BuscaRotaVo busca = new BuscaRotaVo("TESTE", "A", "D", 10, 2.50);
		Caminho caminho = service.buscaMelhorCaminho(busca);
		Assert.assertEquals(caminho.toString(), "A-B-D");
		Assert.assertEquals("6.25", caminho.custo);

	}

	@Test
	public void testeServicoDeBuscaErroRotaNaoEncontrada() {
		RouteService service = ctx.getBean(RouteService.class);
		BuscaRotaVo busca = new BuscaRotaVo("TESTE", "A", "F", 10, 2.50);
		try {
			service.buscaMelhorCaminho(busca);
			Assert.fail("Era esperado um erro");
		} catch (BussinessException e) {
			Assert.assertEquals("Não há caminho para a rota solicitada ou a rota não foi configurada.", e.getMessage());

		}
	}

	@Test
	public void testeServicoDeBuscaErroRotaLocalDestinoInexistente() {
		RouteService service = ctx.getBean(RouteService.class);
		BuscaRotaVo busca = new BuscaRotaVo("TESTE", "A", "K", 10, 2.50);
		try {
			service.buscaMelhorCaminho(busca);
			Assert.fail("Era esperado um erro");
		} catch (BussinessException e) {
			Assert.assertEquals("Local de destino não encontrado", e.getMessage());

		}
	}
	
	@Test
	public void testeServicoDeBuscaErroRotaLocalOrigemInexistente() {
		RouteService service = ctx.getBean(RouteService.class);
		BuscaRotaVo busca = new BuscaRotaVo("TESTE", "Z", "A", 10, 2.50);
		try {
			service.buscaMelhorCaminho(busca);
			Assert.fail("Era esperado um erro");
		} catch (BussinessException e) {
			Assert.assertEquals("Local de origem não encontrado", e.getMessage());

		}
	}

	@Test
	public void testeCriacaoMalha() throws BussinessException {
		RouteService service = ctx.getBean(RouteService.class);
		List<RotaVo> malha = new ArrayList();
		malha.add(new RotaVo("A", "B", 10));
		malha.add(new RotaVo("B", "C", 15));
		malha.add(new RotaVo("B", "E", 30));
		malha.add(new RotaVo("A", "C", 50));
		malha.add(new RotaVo("C", "F", 60));

		service.inserirMapas("NOVO_TESTE", malha.toArray(new RotaVo[malha.size()]));
	}
	
	@Test
	public void testeSobreescreveMalhaExistente() throws BussinessException {
		RouteService service = ctx.getBean(RouteService.class);
		List<RotaVo> malha = new ArrayList();
		malha.add(new RotaVo("A", "B", 10));
		malha.add(new RotaVo("B", "C", 15));
		malha.add(new RotaVo("B", "E", 30));
		malha.add(new RotaVo("A", "C", 50));
		malha.add(new RotaVo("C", "F", 60));

		service.inserirMapas("TESTE_MALHA_EXISTENTE", malha.toArray(new RotaVo[malha.size()]));
	}

}
