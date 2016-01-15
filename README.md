RouteService

Solução:
   1 - Para solucionar o problema do menor caminho 
   utilizei o framework JGRAPHT, que utiliza grafos
   que juntamente com o algoritmo de Dijkstra do framework. 
   2 - A framework utilizada para fazer a persistência e o webservice
   é o Spring.
   3 - Para testes eu estou utilizando o banco de dados H2-DB 
   e na execução na internet estou utilizando mysql. 
   4 - O webservice utiliza o tipo de dados JSON nas requisições e respostas.    
   Obs: O desenvolvimento foi focado em uma solução minimalista.   
   
   
Regras de negocio:
  1 - Se for inserido o mesmo mapa, ele sera apagado do banco e inserido novamente.
  2 - A busca nao e sensivel ao tamanho das letras, elas podem ser maiusculas ou minusculas.
Formato das mensagens do webservice:
       1 - Todas as mensagens devem ser configuradas como método POST e  com o header Content-Type = "application/json"';      
       2 - Em caso de sucesso o status Http é 200 em caso de erro é diferente de 200.
       3 - Mensagens:
	       http://{HOST}/buscaMelhorCaminho
		   POST: 
		   {"mapa":"TESTE","origem":"A","destino":"D","autonomia":10,"custoPorLitro":2.50}
		   RESPOSTA COM SUCESSO:
		  {"rotas":[{"origem":"A","destino":"B","distancia":10},
		  {"origem":"B","destino":"D","distancia":15}],
		  "custo":6.25,"valorCustoDescritivo":"R$ 6,25","rota":"A-B-D"}
		   
		   http://{HOST}/inserirMapa
		   POST: 
		   {"mapa":"MINAS_GERAIS","rotas":[
			 {"origem":"A","destino":"B","distancia":10}
			,{"origem":"B","destino":"D","distancia":20}
			,{"origem":"D","destino":"E","distancia":30}
			,{"origem":"E","destino":"F","distancia":40}
			,{"origem":"F","destino":"H","distancia":10}
			]
			}
			RESPOSTA COM SUCESSO: texto vazio.
			
			No caso de erro no servidor a resposta e:
			{"errorMessage":"Descrição do erro"}

  
Execução:
   Estou disponibilizando a execução na internet através do host:   	
	   
   
         	  
