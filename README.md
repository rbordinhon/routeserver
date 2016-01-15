Route Server
============

###Solução:
   1 - Para solucionar o problema do menor caminho 
   utilizei o framework JGRAPHT, que utiliza grafos
   que juntamente com o algoritmo de Dijkstra do framework. 
   2 - A framework utilizada para fazer a persistência e o webservice
   é o Spring.
   3 - Para testes eu estou utilizando o banco de dados H2-DB 
   e na execução na internet estou utilizando Mysql. 
   4 - O webservice utiliza o tipo de dados JSON nas requisições e respostas.    
   Obs: O desenvolvimento foi focado em uma solução minimalista.   

###Banco de Dados Acesso
     Através do link https://routeservice-rbprojects.rhcloud.com/phpmyadmin/
     com o usuário routeserverapp e senha wbPSphppAcQURhAe
###Regras de negocio:
  1. Se for inserido o mesmo mapa, ele será apagado do banco e inserido novamente.
  2. A busca não é sensível ao tamanho das letras, elas podem ser maiúsculas ou minúsculas.
###Execução:  
  - A execução do código pode ser feita através da internet  pelo servidor routeservice-rbprojects.rhcloud.com
  - Há também a opção de execução local, que utiliza o banco de dados de teste H2-DB, em um servidor Jetty embarcado no código
     através da classe de testes routeservice.web.server.JettyEmbededLocalWebServer   
  - Formato das mensagens do webservice:
       1. Todas as mensagens devem ser configuradas como método POST e  com o header Content-Type = "application/json"';      
       2. Em caso de sucesso o status Http é 200 em caso de erro é diferente de 200.
       3. Mensagens:
	       http://routeservice-rbprojects.rhcloud.com/buscaMelhorCaminho
		   POST: 
		   {"mapa":"TESTE","origem":"A","destino":"D","autonomia":10,"custoPorLitro":2.50}
		   RESPOSTA COM SUCESSO:
		  {"rotas":[{"origem":"A","destino":"B","distancia":10},
		  {"origem":"B","destino":"D","distancia":15}],
		  "custo":6.25,"valorCustoDescritivo":"R$ 6,25","rota":"A-B-D"}
		   
		   http://routeservice-rbprojects.rhcloud.com/inserirMapa
		   POST: 
		   {"mapa":"TESTE","rotas":[
			 {"origem":"A","destino":"B","distancia":10}
			,{"origem":"B","destino":"D","distancia":15}
			,{"origem":"A","destino":"C","distancia":20}
			,{"origem":"C","destino":"D","distancia":30}
			,{"origem":"B","destino":"E","distancia":50}
			,{"origem":"D","destino":"E","distancia":30}
			]
			}
			RESPOSTA COM SUCESSO: Texto vazio
	        	No caso de erro no servidor a resposta e:
			{"errorMessage":"Descrição do erro"}

  
