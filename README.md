# API Biblioteca

Microserviço desenvolvido em Java com Spring Boot para gerenciamento de uma biblioteca, aplicando conceitos de POO, arquitetura RESTful, persistência com banco relacional e boas práticas de desenvolvimento.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Data JPA**
- **Spring Security**
- **Banco de Dados H2** (desenvolvimento/teste)
- **Swagger/OpenAPI** (documentação)
- **JUnit 5 & Mockito** (testes)
- **Docker** (deploy)
- **Git/GitHub** (controle de versão)

---

## Como Rodar o Projeto

### Pré-requisitos

- Java 21+
- Maven 3.9+
- Docker (opcional, para rodar em container)

### Rodando localmente

```sh
./mvnw spring-boot:run
```

Acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Rodando com Docker

```sh
docker build -t biblioteca .
docker run -p 8080:8080 biblioteca
```

---

## Endpoints Principais

- `POST /livros` - Cadastrar livro
- `GET /livros` - Listar livros
- `GET /livros/{id}` - Buscar livro por ID
- `PUT /livros/{id}` - Atualizar livro
- `DELETE /livros/{id}` - Remover livro
- `GET /livros?autor=...` - Buscar livros por autor
- (Outros endpoints para usuários e empréstimos)

---

## Documentação da API

Acesse a documentação interativa em:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Testes

- Testes unitários e de integração com JUnit 5 e Mockito.
- Para rodar os testes:
  ```sh
  ./mvnw test
  ```

---

## Responsabilidades do grupo

- **Nicolasdev7**
  - Criação do projeto Spring Boot e classe Livro
  - Estrutura básica da API, controladores de erros e modelo de banco de dados
  - Otimização das pastas do projeto
  - Melhoria do pom.xml, adição de SecurityConfig e .dockerignore

- **Carlos-Eduardo18**
  - Adição da classe Usuario

- **Kadu212450**
  - Atualização do LivroController e demais controllers

---

## Exemplos de uso da API

### Criar um livro

```json
POST /livros
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "categoria": "Romance",
  "disponivel": true
}
```

### Buscar todos os livros

```
GET /livros
```

---

## Observações

- Para desenvolvimento e testes, o projeto utiliza o banco H2 em memória.
- Para produção, basta configurar um banco relacional (MySQL/PostgreSQL) no `application.properties` se necessário.
- Usuário padrão para autenticação:
  - **Usuário:** admin
  - **Senha:** admin123

---

## Licença

Apache 2.0