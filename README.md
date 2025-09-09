Aqui está uma versão completa e organizada do README.md para o seu projeto **Bingo Manager API**:


# Bingo Manager API

API RESTful para gerenciamento de partidas de bingo, desenvolvida com **Spring Boot**.

---

## 🚀 Sobre o Projeto

O **Bingo Manager API** fornece endpoints para criar, gerenciar e acompanhar partidas de bingo.  
A autenticação é realizada via **tokens JWT**, utilizando um **par de chaves RSA** para garantir segurança e integridade.

Principais funcionalidades:

- Cadastro e autenticação de usuários
- Criação e gerenciamento de partidas de bingo
- Geração e acompanhamento de cartelas
- Sorteio de números em tempo real
- Segurança com JWT e chaves RSA

---

## ⚙️ Configuração Local

Siga os passos abaixo para executar o projeto em seu ambiente de desenvolvimento.

### Pré-requisitos

- Java 21 ou superior
- Maven
- PostgreSQL (ou outro banco compatível)
- OpenSSL (para geração de chaves RSA)

---

### 1. Gerando as Chaves de Autenticação

A aplicação utiliza **chaves RSA** (pública e privada) para assinar e validar tokens JWT.  
Esses arquivos **não estão incluídos no repositório** por segurança.

#### Passos:

1. Navegue até a pasta de resources do projeto:

```bash
cd src/main/resources/
````

2. Gere a **chave privada** (`app.key`):

```bash
openssl genrsa > app.key 
```


3. Gere a **chave pública** (`app.pub`) a partir da chave privada:

```bash
openssl rsa -in app.key -pubout -out app.pub 
```

Ao final, você terá os arquivos `app.key` e `app.pub` dentro de `src/main/resources/`.

---

### 2. Configuração do Banco de Dados

1. Configure suas credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

2. A aplicação utiliza **Flyway** para gerenciar as migrações do banco de dados, que serão aplicadas automaticamente na primeira inicialização.

---

### 3. Executando a Aplicação

Com as chaves geradas e o banco de dados configurado, você pode iniciar a aplicação:

#### Pela IDE:

* Execute a classe principal do Spring Boot (`BingoManagerApiApplication.java`).

#### Pelo terminal com Maven:

```bash
mvn spring-boot:run
```

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 📄 Endpoints

Alguns dos principais endpoints disponíveis:

| Endpoint                 | Método | Descrição                         |
| ------------------------ | ------ | --------------------------------- |
| `/auth/login`            | POST   | Autenticação de usuário           |
| `/usuarios`              | POST   | Cadastro de novo usuário          |
| `/partidas`              | POST   | Criação de uma nova partida       |
| `/partidas/{id}`         | GET    | Consultar detalhes de uma partida |
| `/partidas/{id}/sorteio` | POST   | Sortear número em uma partida     |

> Para detalhes completos de cada endpoint, consulte a documentação da API.

---

## 🔒 Segurança

* Autenticação baseada em **JWT**
* Assinatura dos tokens com chave **privada RSA**
* Validação de tokens com chave **pública RSA**

---

## 🛠 Tecnologias

* Java 21
* Spring Boot
* PostgreSQL
* Maven
* Flyway
* OpenSSL (para chaves RSA)

