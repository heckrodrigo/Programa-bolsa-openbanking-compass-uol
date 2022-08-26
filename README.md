<h3 align="center">
  <img align="center" alt="Logo Compass.UOL"  height="300" src="https://github.com/heckrodrigo/Programa-bolsa-openbanking-compass-uol/blob/main/src/main/resources/templates/compass.png" /><br>
  MS-Product - Programa de Bolsa API Documentation
</h3>


### Contexto do Desafio

Desenvolver uma `API REST` de catálogo de produtos que contemple as seguintes operações nos respectivos `Endpoints`:

<p align="center">
  <img align="center" src="https://github.com/heckrodrigo/Programa-bolsa-openbanking-compass-uol/blob/main/src/main/resources/templates/endpoints.png" />
</p>


<h4>Tecnologias</h4> 
<ul>
  <li> Java 11
  <li> Spring Boot 
  <li> Maven
  <li> H2 Database
  <li> Swagger
  <li> Validation
  <li> JUnit
  <li> Jpa
</ul>

### Requisitos

Para rodar estas aplicações, você deve ter instalado em seu computador:

<ul> 
  Java 11 (LTS)
 
</ul> 

### Executando o projeto

1. Clone ou baixe o projeto do repositório para o seu `Computador`.

2. Navegue até a raíz do seu diretório onde salvou ou clonou o projeto, abra o `terminal e execute em sequencia os comandos:`
<ul> 
   <li> sudo gradle build
   <li> sudo docker-compose build
   <li> sudo docker-compose up
</ul>

3. Após a execução dos processos anteriores, estarão disponíveis para acesso em seu browser os seguintes `Endpoints` para teste:
<ul> 
  <li>
   <a href="http://localhost:9999" target="_blank">API-REST (http://localhost:9999)</a>

  <li>
   <a href="http://localhost:9999/h2-console" target="_blank">H2DB Console (http://localhost:9999/h2-console)</a>
</ul> 

### Executando os testes unitários

1. Navegue até a raíz do seu diretório onde salvou ou clonou o projeto, abra o `terminal e execute ` o comando `sudo gradle clean test`. Este comando executará todos os casos de teste.

<hr>

