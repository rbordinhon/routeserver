insert into Local(NOME) 
values('A');
insert into Local(NOME) 
values('B');
insert into Local(NOME) 
values('C');
insert into Local(NOME) 
values('D');
insert into Local(NOME) 
values('E');
insert into Local(NOME) 
values('F');
insert into Local(NOME) 
values('G');

insert into MAPA(descricao) 
values('TESTE');

insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(1,2,10,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(2,4,15,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(1,3,20,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(3,4,30,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(2,5,50,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(4,5,30,1);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(6,7,30,1);


insert into MAPA(descricao) 
values('TESTE_MALHA_EXISTENTE');

insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(1,2,10,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(2,4,15,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(1,3,20,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(3,4,30,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(2,5,50,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(4,5,30,2);
insert into ROTA(idOrigem,idDestino,distancia,idMapa) 
values(6,7,30,2);



--A B 10 B D 15 A C 20 C D 30 B E 50 D E 30