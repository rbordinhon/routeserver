package routeservice.constants;

public interface ErrorConsts {
	// Erros na camada Service
	public static final String NAO_HA_CAMINHO_PARA_A_ROTA_SOLICITADA_OU_A_ROTA_NÃO_FOI_CONFIGURADA = "Não há caminho para a rota solicitada ou a rota não foi configurada.";
	public static final String LOCAL_DE_DESTINO_NAO_ENCONTRADO = "Local de destino não encontrado";
	public static final String LOCAL_DE_ORIGEM_NAO_ENCONTRADO = "Local de origem não encontrado";
	public static final String MAPA_NAO_ENCONTRADO = "Mapa não encontrado";
	public static final String O_MAPA_JA_FOI_INSERIDO_ANTERIORMENTE = "O mapa já foi inserido anteriormente";
	public static final String A_DISTANCIA_DEVE_SER_MAIOR_QUE_ZERO = "A distância deve ser maior que zero";
	public static final String O_VALOR_DA_DISTANCIA_E_OBRIGATORIA = "O valor da distância é obrigatória";
	public static final String LOCAL_DE_DESTINO_DA_ROTA_E_OBRIGATORIO = "Local de destino da rota é obrigatório";
	public static final String LOCAL_DE_ORIGEM_DA_ROTA_E_OBRIGATORIO = "Local de origem da rota é obrigatório";
	public static final String E_OBRIGATORIO_O_MAPA_TER_PELO_MENOS_UMA_ROTA = "É obrigatório o mapa ter pelo menos uma rota";

	
	// Erros na camada Controller
	public static final String ERRO_NO_SERVIDOR = "Erro no servidor";
	public static final String O_REQUEST_0_PROPAGOU_A_EXCECAO_1 = "O request {0} propagou a exceção:{1}";

}
