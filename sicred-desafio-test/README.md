# sicred-desafio-test
sicred-desafio-test

- Tecnologias|Bibliotecas utilizadas

.Java 17 ou mais 
.JUnit 5;
.RestAssured;
.Maven;

Estrutura do projeto:

- Cada classe testa uma funcionalidade da API "https://sicredi-desafio-qe.readme.io/":

- Divisão das Classes:

Classes:
.status da API > GET/test
.GetBuscarUsuarioAuthTest > Busca usuários > GET/users
.PostCriarTokenAuthTest > Faz login e cria token > POST/auth/login
.GetBuscarProdutoAuthTest >	Busca produtos autenticados	> GET/auth/products
.PostCriarProdutoTest > Cria novo produto >	POST/products/add
.GetBuscarTodosProdutos > Lista todos os produtos > GET/products
.GetBuscarIdProduto	> Busca produto por ID > GET/products/{id}

- Execução dos testes|Pré-requisitos:

.Ter o Java 17 ou mais instalado;
.Ter o Maven instalado;
.Ter uma IDE como por exemplo IntelliJ (a que eu utilizei);

- Clonar o projeto no git

.git clone https://github.com/AmartinsR/sicred-desafio-test.git

- Rodar os testes via terminal:

.Pelo terminal Use o Maven:
      comando: mvn test

- Rodar na própria classe de testes:

.Vá até uma classe de teste (ex: GetBuscarStatusApiTest.java)
.Clique no "Play" Run Test

- Objetivos dos testes:

.GET > Busca dados da API (usuários, produtos...);
.POST > Envia dados (login, criar produto...);
.Valida status codes, campos obrigatórios e autenticação com token;

Autor: Amanda Martins R. de Oliveira.

GitHub: github.com/AmartinsR
Link do repositório do desafio sicred: https://github.com/AmartinsR/sicred-desafio-test


