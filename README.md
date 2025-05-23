
---

## 📦 IndividualTechChallenge
#### API em Spring Boot com PostgreSQL

Este repositório contém a API desenvolvida como parte do Tech Challenge individual da FIAP.

A aplicação foi construída com Spring Framework e utiliza banco de dados PostgreSQL. 

O ambiente é containerizado com Docker e Docker Compose para facilitar a execução em qualquer máquina.

---

### 🧱 Tecnologias utilizadas

* Java
* Maven
* Spring Framework
* Spring Boot e Spring Web
* Spring Data JDBC
* PostgreSQL
* Bean Validation
* Lombok
* Docker / Docker Compose

---

### 🚀 Como executar o projeto

#### Pré-requisitos

* [Docker](https://www.docker.com/)
* [Docker Compose](https://docs.docker.com/compose/)

#### Passos para rodar o projeto:

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/TechChallenge.git
cd TechChallenge
```

2. Suba os containers com Docker Compose:

```bash
docker-compose up
```

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

O banco de dados PostgreSQL estará rodando na porta padrão: `5432`.

---

[//]: # (### 🗃️ Banco de Dados)

[//]: # ()
[//]: # (As credenciais padrão são:)

[//]: # ()
[//]: # (* **Usuário:** `postgres`)

[//]: # (* **Senha:** `Root2025@$`)

[//]: # (* **Database:** `postgres`)

[//]: # (* **Host &#40;interno no Docker Compose&#41;:** `db`)

[//]: # ()
[//]: # (> Os dados do banco são persistidos automaticamente em um volume Docker chamado `pgdata`.)

[//]: # ()
[//]: # (---)

### 🔬 Testando a API com Postman

Você pode importar a coleção pronta no Postman para testar os endpoints da API.

### ✅ Como importar:

1. Abra o Postman.
2. Clique em "Import". 
3. Selecione o arquivo `tc-caike.postman_collection` incluído neste repositório. 
4. Execute os endpoints diretamente.

---

### 📌 Endpoints da API

### 🔐 Login
- POST `/login`
  - Autentica o usuário. 
  - Body: 
    ```bash
      {
          "login": "usuario@email.com",
          "password": "senha123"
      }
    ```

### 👤 Users
- GET `/users?page=1&size=10`
    - Lista todos os usuários de forma paginada.
    - Body:
      ```bash
        {
            "login": "usuario@email.com",
            "password": "senha123"
        }
      ```
      
- GET `/users/{id}`
  - Retorna um usuário específico.

- POST `/users`
  -  Cadastra um usuário e seu endereço.
  ```bash
        {
            "userType": "Dono de restaurante",
            "name": "John Doe",
            "email": "johndoe2026@email.com",
            "login": "johnDoe2026",
            "password": "1234",
            "address": {
                "street": "Av. ABC",
                "num": 16,
                "complement": "",
                "country": "Brasil",
                "city": "SP",
                "state": "SP",
                "zipcode": "01234-567"
            }
        }
  ```
- DELETE `/users/{id}`
  - Deleta o usuário especificado do banco de dados.


- PATCH `/users/{id}`
  - Atualiza as informações do usuário especificado (não é possível atualizar a senha do usuário nesse endpoint).
  ```bash
        {
            "name": "Novo Nome",
            "email": "novo@email.com",
            "login": "novasenha"
        }
  ```

- PATCH `/users/{id}/password`
    - Troca a senha do usuário especificado, se a senha atual for compatível.
  ```bash
        {
            "currentPassword": "12345",
            "newPassword": "1234"
        }
  ```

---

[//]: # (### 📁 Estrutura do projeto)

[//]: # ()
[//]: # (```)

[//]: # (TechChallenge/)

[//]: # (├── src/)

[//]: # (├── Dockerfile)

[//]: # (├── docker-compose.yml)

[//]: # (├── pom.xml)

[//]: # (└── README.md)

[//]: # (```)

[//]: # ()
[//]: # (---)