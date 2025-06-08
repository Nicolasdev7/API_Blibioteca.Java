# 📚 API Biblioteca

Microserviço desenvolvido em Java com Spring Boot para gerenciamento de uma biblioteca, aplicando conceitos de POO, arquitetura RESTful, persistência com banco relacional e boas práticas de desenvolvimento.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot 3.4.5**
- 🗃️ **Spring Data JPA**
- 🔐 **Spring Security**
- 💾 **Banco de Dados H2** (desenvolvimento/teste)
- 🐘 **PostgreSQL** (produção, opcional)
- 📖 **Swagger/OpenAPI** (documentação)
- 🧪 **JUnit 5 & Mockito** (testes)
- 🐳 **Docker** (deploy)
- 🗂️ **Git/GitHub** (controle de versão)

---

## 🛠️ Como Rodar o Projeto

### ✅ Pré-requisitos

- Java 21+
- Maven 3.9+
- Docker (opcional, para rodar em container)

### ▶️ Rodando localmente

```sh
./mvnw spring-boot:run
```

Acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 🐳 Rodando com Docker

```sh
docker build -t biblioteca .
docker run -p 8080:8080 biblioteca
```

---

## 🔗 Endpoints Principais

- `POST /livros` - Cadastrar livro
- `GET /livros` - Listar livros
- `GET /livros/{id}` - Buscar livro por ID
- `PUT /livros/{id}` - Atualizar livro
- `DELETE /livros/{id}` - Remover livro
- `GET /livros?autor=...` - Buscar livros por autor
- (Outros endpoints para usuários e empréstimos)

---

## 📘 Documentação da API

Acesse a documentação interativa em:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Testes

- Testes unitários e de integração com JUnit 5 e Mockito.
- Para rodar os testes:
  ```sh
  ./mvnw test
  ```

---

## 👥 Responsabilidades do grupo

- **Nicolasdev7**
  - Criação do projeto Spring Boot e classe Livro
  - Estrutura básica da API, controladores de erros e modelo de banco de dados
  - Otimização das pastas do projeto
  - Melhoria do pom.xml, adição de SecurityConfig e .dockerignore
  - Adição do README
  - Melhorou a modelagem do projeto

- **Carlos-Eduardo18**
  - Adição da classe Usuario
  - Melhorou o README, adicionando um exemplo de uso da API e divisão de tarefas entre os membros
  - Documentação do projeto

- **Kadu212450**
  - Atualização do LivroController e demais controllers
  - PowerPoint do Trabalho
- **BRikSO**
  - Melhorou as 3 Clases: Empréstimo, Livro e Usuário
  - Alocou o banco de dados com PostgreSQL

- **Cruz**
  - Fez os testes unitários com JUnit 5 + Mockito
  - Fez uma cobertura mínima de 90% com JaCoCo 
---

## Exemplos de uso da API

### 📘 Criar um livro

```json
POST /livros
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "categoria": "Romance",
  "disponivel": true
}
```

### 📚 Buscar todos os livros

```
GET /livros
```
### ✏️ Atualizar um livro
```json
PUT /livros/1
Content-Type: application/json

{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "categoria": "Romance",
  "disponivel": false
}
```
### ❌ Deletar um livro
```
DELETE /livros/1
```

### 📝 Explicação:
Demonstramos como utilizar a API na prática por meio de requisições HTTP simuladas. Esses exemplos mostram:
- 📘 Como enviar dados para criar um livro (POST)
- 📚 Como recuperar todos os livros existentes (GET)
- ✏️ Como atualizar os dados de um livro específico (PUT)
- ❌ Como remover um livro do sistema (DELETE)

Esses exemplos ajudam desenvolvedores a entender rapidamente como interagir com a API, facilitando testes e integração com outras aplicações. Todos os exemplos seguem o formato REST e utilizam JSON como padrão de troca de dados.

---

## 🧾 Observações

- Para desenvolvimento e testes, o projeto utiliza o banco H2 em memória.
- Para produção, basta configurar um banco relacional (MySQL/PostgreSQL) no `application.properties` se necessário.
- Usuário padrão para autenticação:
  - **Usuário:** admin
  - **Senha:** admin123

---

## 📄 Licença

Apache 2.0