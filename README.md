# 🍽️ Tech Challenge: API de Gestão de Usuários

## 📜 Descrição do Projeto

Este projeto consiste no desenvolvimento de um Backend completo e robusto utilizando Spring Boot para um sistema de gestão de restaurantes. Esta é a Fase 1 do sistema, focada no módulo de gestão de usuários.

O sistema permite o gerenciamento completo de usuários, incluindo clientes e donos de restaurantes, garantindo a unicidade de e-mail e funcionalidades separadas para troca de senha e atualização de dados.

O ambiente de desenvolvimento e execução é totalmente dockerizado, utilizando docker-compose para orquestrar a aplicação Spring Boot e um banco de dados relacional (PostgreSQL).

### Objetivos da Fase 1
<ul>
<li> Desenvolvimento de um Backend com Spring Boot e princípios de SOLID e Orientação a Objetos. </li>

<li> Implementação de dois tipos de usuários obrigatórios: Dono de restaurante e Cliente. </li>

<li> Endpoints para Cadastro, Atualização e Exclusão de usuários. </li>

<li> Endpoint distinto para Troca de Senha. </li>

<li> Endpoint distinto para Atualização dos demais dados do usuário.</li>

<li> Serviço obrigatório de Validação de Login utilizando JWT para utilização dos demais recursos. </li>

<li> Garantia de que o E-mail é único. </li>

<li> Implementação de versionamento de API. </li>

<li> Uso do padrão ProblemDetail (RFC 7807) para padronização de erros. </li>
</ul>

## 💻 Tecnologias Utilizadas

<ol>
<dl>
  
<li> <dt>Java</dt> </li>

<li> <dt>Spring Boot (Backend)</dt> </li>

<li> <dt>Maven (Gerenciador de Dependências)</dt> </li>

<li> <dt>Dockerfile / Docker Compose (Containerização e Orquestração)</dt> </li>

<li> <dt> PostgreSQL (Banco de Dados Relacional)</dt> </li>

<li> <dt>Swagger / OpenAPI (Documentação da API)</dt> </li>

<li> <dt>Postman (Coleção de Testes)</dt> </li>

</dl>
</ol>

## 🚀 Como Executar o Projeto

Para executar a aplicação e o banco de dados localmente, é necessário ter o Docker e o Docker Compose instalados.

Pré-requisitos

Docker

Docker Compose

Java / Maven (Opcional, para rodar sem Docker ou gerar o JAR)

## Passos para Execução com Docker Compose

#### 1. Passos salva arquivo .env na mesma pasta do arquivo compose.yml:  

#### 2. Passos para Execução com Docker Compose:

    docker compose up --build

#### 3. Verifique os Contêineres:

    docker ps