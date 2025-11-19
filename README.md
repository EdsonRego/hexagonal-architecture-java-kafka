# 🧩 Arquitetura Hexagonal com Spring Boot, Kafka, MongoDB e WireMock

Este projeto implementa, na prática, a **Arquitetura Hexagonal (Ports & Adapters)** aplicada a um microsserviço moderno com:

- **Java 17**
- **Spring Boot 3**
- **MongoDB**
- **Apache Kafka**
- **Feign Client + WireMock**
- **Docker Compose**

O objetivo é demonstrar como construir serviços **altamente desacoplados**, **testáveis**, **escaláveis** e **prontos para produção**.

---

## 🚀 Visão Geral da Arquitetura

A Arquitetura Hexagonal separa o sistema em três camadas principais:

### **1️⃣ Core (Domínio + Casos de Uso)**
Contém regras de negócio puras, sem dependências de frameworks.

### **2️⃣ Ports (Interfaces)**
Contratos que definem como o domínio se comunica com o mundo externo.

### **3️⃣ Adapters (Infraestrutura)**
Implementações dos ports, conectando o domínio a:
- REST Controllers
- Kafka Producers/Consumers
- Banco de dados (MongoDB)
- APIs externas (Feign + WireMock)

💡 **Benefícios:** baixo acoplamento, facilidade de teste, substituição de tecnologias sem alterar a regra de negócio, manutenção simplificada.

---

## 📁 Estrutura do Projeto

hexagonal
├── adapters
│ ├── in
│ │ ├── controller # API REST
│ │ └── consumer # Kafka Consumer
│ └── out
│ ├── repository # Persistência MongoDB
│ ├── client # Integração externa (Feign)
│ └── mapper # MapStruct
│
├── application
│ ├── core
│ │ ├── domain # Entidades de domínio
│ │ └── usecase # Casos de uso
│ └── ports
│ ├── in # Portas de entrada
│ └── out # Portas de saída
│
├── config # Beans e configurações Spring
└── HexagonalApplication.java

yaml
Copiar código

---

## 🧰 Pré-requisitos

- Java 17
- Gradle 8+
- Docker Desktop
- WireMock Standalone
- Postman
- Kafkalytic / Offset Explorer (opcional)

---

# ⚙️ Execução Completa do Ambiente

## **1️⃣ Subir infraestrutura com Docker**

docker compose up -d
Serviços provisionados:
Serviço	Porta	Função
Zookeeper	2181	Coordenação Kafka
Kafka	9092	Broker
Kafdrop	9000	Visualização Kafka
MongoDB	27017	Banco de dados
Mongo Express	8083	UI para Mongo

2️⃣ Subir o WireMock (mock da Address API)
bash
Copiar código
java -jar wiremock-standalone-4.0.0-beta.15.jar --port 8082
Exemplo de resposta mockada:

json
Copiar código
{
  "street": "Rua Hexagonal",
  "city": "Uberlândia",
  "state": "Minas Gerais"
}
Endpoint:

bash
Copiar código
http://localhost:8082/addresses/{zipCode}
3️⃣ Executar a aplicação
bash
Copiar código
./gradlew bootRun
A API estará disponível em:

arduino
Copiar código
http://localhost:8081
🌐 Endpoints Principais
Método	Endpoint	Descrição
POST	/api/v1/customers	Cria cliente
GET	/api/v1/customers/{id}	Consulta cliente
PUT	/api/v1/customers/{id}	Atualiza
DELETE	/api/v1/customers/{id}	Remove

Exemplo para criação:

json
Copiar código
{
  "name": "Edson",
  "cpf": "12345678901",
  "zipCode": "38400001"
}
🔄 Fluxo Completo do Sistema
Cliente é criado (POST /customers).

O sistema envia o CPF ao tópico tp-cpf-validation.

WireMock simula validação externa de endereço.

Mensagem com isValidCpf=true é enviada ao tópico tp-cpf-validated.

O consumer atualiza o cliente no MongoDB.

Monitoramento via:

Kafdrop → tópicos Kafka

Mongo Express → dados persistidos

Offset Explorer → offsets

Kafkalytic → publicação manual

🧪 Testes
O projeto contém:

✔️ Testes de Arquitetura (ArchUnit)
Garantindo que:

Controllers estão em adapters.in.controller

Repositórios em adapters.out.repository

Regras de Ports & Adapters são respeitadas

Executar:

bash
Copiar código
./gradlew test
🧱 Stack Técnica
Tecnologia	Uso
Spring Boot 3.4	Framework principal
Spring Data MongoDB	Persistência
Spring Cloud OpenFeign	Integração externa
Spring Kafka	Produtor/Consumidor
WireMock	Mock externo
MapStruct	Mapeamento
Docker Compose	Orquestração
Mongo + Kafdrop	Infra completa local

👨‍💻 Autor
Edson Gomes do Rego
System Support Engineer | Backend Java Developer
📍 São Paulo – Brasil

🔗 LinkedIn
🔗 GitHub

📌 Observação
Este projeto foi desenvolvido como uma prova de conceito avançada para demonstrar:

Arquitetura Hexagonal na prática

Integração com mensageria Kafka

Comunicação externa com Feign + mock

Persistência em banco NoSQL

Orquestração completa via Docker

```bash
