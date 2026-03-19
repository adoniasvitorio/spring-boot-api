# Cora API

REST API para gerenciamento de contas bancárias, desenvolvida com Spring Boot.

## Tecnologias

- **Java 17**
- **Spring Boot 3.5.11**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Maven**
- **JUnit 5 + Mockito**

## Pré-requisitos

- Java 17+
- Maven (ou use o wrapper `./mvnw`)

## Como executar

```bash
./mvnw spring-boot:run
```

A aplicação sobe na porta padrão `8080`.

## H2 Console

O banco de dados em memória pode ser acessado via browser em:

```
http://localhost:8080/h2-console
```

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | *(vazio)* |

## Endpoints

### Criar conta

```
POST /accounts
```

**Body:**
```json
{
  "name": "Adonias Vitorio",
  "cpf": "12345678901"
}
```

**Respostas:**

| Status | Descrição |
|---|---|
| `201 Created` | Conta criada com sucesso |
| `400 Bad Request` | CPF inválido |
| `409 Conflict` | CPF já cadastrado |

**Exemplo de resposta (`201`):**
```json
{
  "id": 1,
  "name": "Adonias Vitorio",
  "cpf": "12345678901"
}
```

**Exemplo de resposta (`400`):**
```json
{
  "message": "Invalid CPF"
}
```

**Exemplo de resposta (`409`):**
```json
{
  "message": "CPF already exists: 12345678901"
}
```

---

### Listar contas

```
GET /accounts
```

**Respostas:**

| Status | Descrição |
|---|---|
| `200 OK` | Lista de contas retornada |

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "name": "Adonias Vitorio",
    "cpf": "12345678901"
  }
]
```

## Validação de CPF

O CPF é validado segundo as seguintes regras:

- Deve conter exatamente **11 dígitos numéricos**
- Deve conter apenas numeros.
- Não pode estar duplicado no banco de dados

## Estrutura do projeto

```
src/
├── main/
│   ├── java/com/cora/api/
│   │   ├── ApiApplication.java
│   │   ├── controller/
│   │   │   └── AccountController.java
│   │   ├── exception/
│   │   │   └── DuplicateAccountException.java
│   │   ├── model/
│   │   │   └── Account.java
│   │   ├── repository/
│   │   │   └── AccountRepository.java
│   │   ├── service/
│   │   │   └── AccountService.java
│   │   └── util/
│   │       └── CPFValidator.java
│   └── resources/
│       └── application.yaml
└── test/
    └── java/com/cora/api/
        └── controller/
            └── AccountControllerTest.java
```

## Testes

```bash
./mvnw test
```

Testes cobrem:

- Listagem de contas (`200`)
- Criação de conta com sucesso (`201`)
- Criação com CPF inválido (`400`)
- Criação com CPF de tamanho incorreto (`400`)
- Criação com CPF duplicado (`409`)

## Build

Para gerar o JAR executável:

```bash
./mvnw package
java -jar target/api-0.0.1-SNAPSHOT.jar
```
