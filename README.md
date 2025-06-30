# 🛣️ TripGas API

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue?style=for-the-badge&logo=apache-maven)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)

**Sistema de cálculo de consumo de combustível baseado em rotas e modelos de veículos** 🚗⛽

[Demonstração](#-uso) • [Instalação](#-instalação) • [Endpoints](#-documentação-da-api) • [Testes](#-testes) • [Tecnologias](#-tecnologias-usadas)

</div>

---

## 📌 Sobre o Projeto

A **TripGas API** é uma aplicação REST desenvolvida com Spring Boot, que estima o custo de uma viagem rodoviária com base na distância entre dois pontos, consumo do veículo e preço do combustível. Um projeto ideal para portfólios de desenvolvedores back-end com foco em integração de APIs, persistência e boas práticas de arquitetura.

---

## ✨ Funcionalidades

- 🔍 Cálculo de custo de viagem com base em coordenadas e preço do combustível
- 🚘 Registro de rotas e veículos personalizados
- 📍 Consumo de API externa para cálculo de distância
- 💾 Persistência com PostgreSQL
- 🔁 Estrutura RESTful organizada em camadas
- 📄 Documentação Swagger integrada

---

## ⚙️ Instalação

### Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL (local ou via Docker)

### Configuração

```bash
git clone https://github.com/HenriqueMartinho/tripgas-api-springboot.git
cd tripgas-api-springboot
```

Crie o banco de dados PostgreSQL:

```sql
CREATE DATABASE tripgas_db;
```

Configure o `application.yml`:

```properties
spring
  datasource
    url: jdbc:postgresql://localhost:5432/tripgas_db
    username: seu_usuario
    password: sua_senha
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

---

## 💻 Uso

### Requisição de cálculo

```json
POST /api/v1/trip/calculate

{
  "origin": "São Paulo, SP",
  "destination": "Campinas, SP",
  "vehicle": {
    "consumption": 12.0,
    "fuelType": "GASOLINE"
  },
  "fuelPrice": 5.89
}
```

### Resposta esperada

```json
{
  "tripId": "uuid",
  "distance": 120.5,
  "litersNeeded": 10.04,
  "totalCost": 59.14
}
```

---

## 🔗 Documentação da API

Disponível em: `http://localhost:8080/swagger-ui/index.html`

| Método | Endpoint                    | Descrição                         |
|--------|-----------------------------|-----------------------------------|
| POST   | `/api/v1/trip/calculate`    | Calcula custo da viagem           |
| GET    | `/api/v1/routes`            | Lista rotas registradas           |
| GET    | `/api/v1/vehicles`          | Lista veículos cadastrados        |

---

## 🧪 Testes

```bash
mvn test
```

Cobertura de testes para serviços e validações.

---

## 🧰 Tecnologias Usadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Swagger/OpenAPI

---

## 📌 Melhorias Futuras

- Autenticação JWT
- 
---

## 👤 Autor

**Henrique Martinho**  
[GitHub](https://github.com/HenriqueMartinho) • [LinkedIn](https://linkedin.com/in/henriquemartinho)

---
