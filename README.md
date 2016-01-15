#Route Service#

###Solução
   1. Para solucionar o problema do menor caminho 
   utilizei o framework JGRAPHT, que utiliza grafos
   que juntamente com o algoritmo de Dijkstra do framework. 
   2. A framework utilizada para fazer a persistência e o webservice é o Spring.
   3. Para testes eu estou utilizando o banco de dados H2-DB e na internet estou utilizando MySQL.
   4. O webservice utiliza o tipo de dados JSON nas requisições e respostas.    

###Banco de Dados
     Acesso através do link https://routeservice-rbprojects.rhcloud.com/phpmyadmin/
     - Usuário: routeserverapp 
     - Senha: wbPSphppAcQURhAe

###Regras de negócio
  1. Se for inserido o mesmo mapa, ele será apagado do banco e inserido novamente.
  2. A busca não é sensível ao tamanho das letras, elas podem ser maiúsculas ou minúsculas.

### Execução  
  1. A execução do código pode ser feita através da internet  pelo servidor routeservice-rbprojects.rhcloud.com.
  2. Há também a opção de execução local através da classe de testes routeservice.web.teste.JsonMensagensTeste   
  Obs: A opção de execução local utiliza banco de dados de teste. 

### Formato das mensagens do webservice:
  1. Todas as mensagens devem ser configuradas com o método POST e o header Content-Type igual a "application/json"'.     

### Mensagem de Resposta
  2. Em caso de sucesso o status Http é 200.
  3. Em caso de erro o status Http é diferente de 200.

### Mensagens
      http://routeservice-rbprojects.rhcloud.com/buscaMelhorCaminho
		- POST: 
	            {"mapa":"TESTE","origem":"A","destino":"D","autonomia":10,"custoPorLitro":2.50}
		- RESPOSTA COM SUCESSO:
	   	    {"rotas":[{"origem":"A","destino":"B","distancia":10},
		    {"origem":"B","destino":"D","distancia":15}],
		    "custo":6.25,"valorCustoDescritivo":"R$ 6,25","rota":"A-B-D"}
		   
      http://routeservice-rbprojects.rhcloud.com/inserirMapa
		- POST: 
	    	    {"mapa":"TESTE","rotas":[
					 {"origem":"A","destino":"B","distancia":10}
					,{"origem":"B","destino":"D","distancia":15}
					,{"origem":"A","destino":"C","distancia":20}
					,{"origem":"C","destino":"D","distancia":30}
					,{"origem":"B","destino":"E","distancia":50}
					,{"origem":"D","destino":"E","distancia":30}
					]
					}
		- RESPOSTA COM SUCESSO: Texto vazio
       No caso de erro no servidor a resposta é:
	          {"errorMessage":"Descrição do erro"}

  
