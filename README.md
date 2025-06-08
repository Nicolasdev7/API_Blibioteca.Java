# 📚 API Biblioteca

Microserviço completo para gerenciamento de biblioteca, desenvolvido em Java 21 com Spring Boot, arquitetura RESTful, autenticação, persistência em banco relacional e deploy pronto para produção (Render/Docker). O projeto segue boas práticas de POO, testes automatizados e documentação via Swagger.

---

## 🚀 Tecnologias Utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot 3.4.5**
- 🗃️ **Spring Data JPA**
- 🔐 **Spring Security**
- 💾 **Banco de Dados H2** (desenvolvimento/teste)
- 🐘 **PostgreSQL** (produção)
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
- PostgreSQL (para produção/local, se não usar H2)

### ▶️ Rodando localmente (H2 ou PostgreSQL)

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/Nicolasdev7/API_Blibioteca.Java.git
   cd API_Blibioteca.Java
   ```

2. **Configure o banco de dados:**
   - Por padrão, usa H2 em memória para desenvolvimento.
   - Para usar PostgreSQL local, edite `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     ```

3. **Inicie a aplicação:**
   ```sh
   ./mvnw spring-boot:run
   ```
   Acesse: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### 🌐 Deploy em produção (Render/Docker)

- **Render:**  
  Configure as variáveis de ambiente no painel do Render:
  - `DATABASE_URL`
  - `DATABASE_USERNAME`
  - `DATABASE_PASSWORD`
  > O projeto já está preparado para ler essas variáveis automaticamente.

- **Docker:**
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
- (Endpoints para usuários e empréstimos também disponíveis)

---

## 📘 Documentação da API

Acesse a documentação interativa em:  
[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

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
  - Configuração para variáveis de ambiente no Render
  - Ajustes finais para produção e permissões do banco

- **Carlos-Eduardo18**
  - Adição da classe Usuario
  - Melhoria do README, exemplos de uso e divisão de tarefas
  - Documentação do projeto

- **Kadu212450**
  - Atualização do LivroController e demais controllers
  - PowerPoint do Trabalho

- **BRikSO**
  - Melhorias nas classes Empréstimo, Livro e Usuário
  - Integração com PostgreSQL

- **Cruz**
  - Testes unitários com JUnit 5 + Mockito
  - Cobertura mínima de 90% com JaCoCo

---

## 📦 Histórico de Commits

- Configuração do banco e permissões para produção
- Uso de variáveis de ambiente do Render
- Finalização do microserviço e resolução de conflitos
- Melhoria da modelagem, controllers e testes
- Documentação e exemplos de uso

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

---

## 📝 Observações

- Para desenvolvimento e testes, o projeto utiliza o banco H2 em memória por padrão.
- Para produção, configure um banco relacional (PostgreSQL) via variáveis de ambiente.
- Usuário padrão para autenticação:
  - **Usuário:** admin
  - **Senha:** admin123

---

## 📄 Licença

Apache 2.0