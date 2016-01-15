package routeservice.constants;

public interface ErrorConsts {
	// Erros na camada Service
	public static final String NAO_HA_CAMINHO_PARA_A_ROTA_SOLICITADA_OU_A_ROTA_NÃO_FOI_CONFIGURADA = "Não há caminho para a rota solicitada ou a rota não foi configurada.";
	public static final String LOCAL_DE_DESTINO_NAO_ENCONTRADO = "Local de destino não encontrado";
	public static final String LOCAL_DE_ORIGEM_NAO_ENCONTRADO = "Local de origem não encontrado";
	public static final String MAPA_NAO_ENCONTRADO = "Mapa não encontrado";
	public static final String O_MAPA_JA_FOI_INSERIDO_ANTERIORMENTE = "O mapa já foi inserido anteriormente";
	
	// Erros na camada COntroller
	public static final String ERRO_NO_SERVIDOR = "Erro no servidor";
	public static final String O_REQUEST_0_PROPAGOU_A_EXCECAO_1 = "O request {0} propagou a exceção:{1}";

}
