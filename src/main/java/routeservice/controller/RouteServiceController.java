package routeservice.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import routeservice.constants.ErrorConsts;
import routeservice.exception.BussinessException;
import routeservice.service.RouteService;
import routeservice.vo.BuscaRotaVo;
import routeservice.vo.Caminho;
import routeservice.vo.ErrorVo;
import routeservice.vo.RequisicaoMapa;

@Controller
public class RouteServiceController implements ErrorConsts {

	protected static final Logger logger = LoggerFactory.getLogger("RouteService");

	@Autowired
	private RouteService serviceCtrl;

	@RequestMapping(value = "/buscaMelhorCaminho", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Caminho getCaminho(@RequestBody BuscaRotaVo busca) throws BussinessException {
		
		
		return serviceCtrl.buscaMelhorCaminho(busca);
	}

	@RequestMapping(value = "/inserirMapa", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String inserirMapa(@RequestBody RequisicaoMapa busca) throws BussinessException {
		serviceCtrl.inserirMapas(busca.mapa, busca.rotas);
		return "";
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorVo> handleError(HttpServletRequest request, Exception exception) {
		ErrorVo error = new ErrorVo();
		if (exception instanceof BussinessException)
			error.errorMessage = exception.getMessage();
		else {
			logger.error(MessageFormat.format(O_REQUEST_0_PROPAGOU_A_EXCECAO_1, request.getRequestURL().toString(),exception.getMessage()), exception);
			error.errorMessage = ERRO_NO_SERVIDOR;
		}
		return new ResponseEntity<ErrorVo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
