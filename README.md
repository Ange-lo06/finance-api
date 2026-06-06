# Finance API

API REST para gerenciamento financeiro pessoal desenvolvida com Java e Spring Boot.

## Objetivo

O projeto tem como objetivo permitir o controle de receitas, despesas e usuários, servindo também como estudo prático de desenvolvimento Back-end com Spring Boot, JPA, PostgreSQL e autenticação JWT.

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security
* PostgreSQL
* JWT (JSON Web Token)
* Maven
* Lombok
* Swagger / OpenAPI

## Funcionalidades Implementadas

### Usuários

* Cadastro de usuários
* Listagem de usuários
* Busca por ID
* Atualização de usuário
* Exclusão de usuário

### Receitas

* Cadastro de receitas
* Listagem de receitas
* Busca por ID
* Atualização de receitas
* Exclusão de receitas

### Despesas

* Cadastro de despesas
* Listagem de despesas
* Busca por ID
* Atualização de despesas
* Exclusão de despesas

### Dashboard Financeiro

* Soma total de receitas
* Soma total de despesas
* Cálculo de saldo financeiro

### Tratamento Global de Exceções

* Recurso não encontrado (404)
* Erros de validação (400)

### DTOs e Mappers

* Separação entre entidades e objetos de transferência
* Conversão através de classes Mapper

### Segurança

* Spring Security configurado
* Senhas criptografadas com BCrypt
* Geração de Token JWT
* Validação de Token JWT
* Extração de informações do Token JWT

## Estrutura do Projeto

src/main/java
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
├── exception
├── security
└── config

## Banco de Dados

PostgreSQL

Configuração no application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/finance-api
spring.datasource.username=postgres
spring.datasource.password=1234

## Próximas Implementações

* Filtro JWT Authentication
* Login com AuthenticationManager
* Proteção de rotas por token
* Controle de acesso por usuário
* Relatórios financeiros
* Testes unitários
* Docker
* Deploy em nuvem

## Autor

Angelo Siqueira

Projeto desenvolvido para estudos de Engenharia de Software e desenvolvimento Back-end com Java e Spring Boot.
