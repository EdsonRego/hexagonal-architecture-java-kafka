# 🧩 Projeto: Arquitetura Hexagonal com Spring Boot, Kafka, MongoDB e WireMock

Este projeto demonstra na prática a **Arquitetura Hexagonal (Ports and Adapters)** utilizando:

- **Java 17**
- **Spring Boot 3**
- **MongoDB**
- **Apache Kafka**
- **Feign Client + WireMock**
- **Docker Compose**
- **Ferramentas de apoio**: Postman, Kafkalytic e Offset Explorer

---

## 🧠 Conceitos Fundamentais

### 🏗️ Arquitetura Hexagonal

A **Arquitetura Hexagonal**, também conhecida como **Ports and Adapters**, tem como objetivo **isolar a lógica de negócio** (domínio) das dependências externas, como bancos de dados, mensagerias, frameworks ou APIs externas.

Ela organiza o sistema em três camadas principais:

1. **Core (Domínio + Casos de Uso)**  
   Contém as regras de negócio puras da aplicação.  
   → Não depende de frameworks ou tecnologias externas.

2. **Ports (Interfaces)**  
   Definem *contratos* de entrada e saída da aplicação.  
   → “Portas” que permitem comunicação entre o domínio e o mundo externo.

3. **Adapters (Implementações)**  
   Implementam os *ports*, conectando o domínio a:
    - Banco de dados (MongoDB)
    - Mensageria (Kafka)
    - APIs externas (Feign Client / WireMock)
    - Controllers REST (Spring MVC)

Essa abordagem garante **baixo acoplamento** e **alta testabilidade**, permitindo substituir tecnologias facilmente (ex.: trocar Mongo por Postgres sem afetar o domínio).

---

## 📦 Estrutura de Pastas

hexagonal
├── adapters
│ ├── in
│ │ ├── controller # Exposição via REST (API)
│ │ └── consumer # Consumo de mensagens Kafka
│ └── out
│ ├── repository # Acesso ao MongoDB
│ ├── client # Comunicação com serviço externo (Feign)
│ └── mapper # MapStruct mappers
│
├── application
│ ├── core
│ │ ├── domain # Entidades do domínio
│ │ └── usecase # Casos de uso (regras de negócio)
│ └── ports
│ ├── in # Portas de entrada (chamadas externas)
│ └── out # Portas de saída (infraestrutura)
│
├── config # Beans de configuração Spring
└── HexagonalApplication.java # Classe principal

yaml
Copiar código

---

## 🧰 Pré-requisitos

Certifique-se de ter instalado:

- **Java 17**
- **Gradle 8+**
- **Docker Desktop**
- **WireMock 4.0.0-beta.15 (JAR)**
- **Postman**
- **VS Code com plugin Kafkalytic (opcional)**
- **Offset Explorer (opcional)**

---

## 🚀 Passo a Passo de Execução

### 1️⃣ Subir os containers Docker

Na raiz do projeto, execute:


docker compose up -d
Isso iniciará:

Serviço	Porta	Função
Zookeeper	2181	Coordenação do Kafka
Kafka	9092	Broker de mensagens
Kafdrop	9000	UI Web para Kafka
MongoDB	27017	Banco de dados
Mongo Express	8083	Interface web do MongoDB

Verifique se todos estão ativos:

bash
Copiar código
docker ps
2️⃣ Subir o WireMock
O WireMock simula o microserviço externo de endereços (Address API).

No diretório onde está o .jar, execute:

bash
Copiar código
java -jar wiremock-standalone-4.0.0-beta.15.jar --port 8082
Endpoint simulado:

bash
Copiar código
http://localhost:8082/addresses/{zipCode}
Exemplo de resposta mockada:

json
Copiar código
{
  "street": "Rua Hexagonal",
  "city": "Uberlândia",
  "state": "Minas Gerais"
}
3️⃣ Executar a aplicação Spring Boot
Na raiz do projeto:

bash
Copiar código
./gradlew bootRun
A aplicação estará disponível em:

arduino
Copiar código
http://localhost:8081
🌐 Endpoints da API
Método	Endpoint	Descrição
POST	/api/v1/customers	Cria um novo cliente
GET	/api/v1/customers/{id}	Busca cliente por ID
PUT	/api/v1/customers/{id}	Atualiza dados do cliente
DELETE	/api/v1/customers/{id}	Remove cliente existente

🧪 Testes e Validações
🧰 Postman
➕ Criar cliente (POST)

bash
Copiar código
POST http://localhost:8081/api/v1/customers
Body (JSON):

json
Copiar código
{
  "name": "Edson",
  "cpf": "12345678901",
  "zipCode": "38400001"
}
🔍 Buscar cliente (GET)

bash
Copiar código
GET http://localhost:8081/api/v1/customers/{id}
✏️ Atualizar cliente (PUT)

bash
Copiar código
PUT http://localhost:8081/api/v1/customers/{id}
Body (JSON):

json
Copiar código
{
  "name": "Edson Rego",
  "cpf": "12345678901",
  "zipCode": "38400001"
}
❌ Deletar cliente (DELETE)

bash
Copiar código
DELETE http://localhost:8081/api/v1/customers/{id}
💬 Kafkalytic (VS Code Plugin)
Utilize para publicar mensagens manualmente no tópico tp-cpf-validated.

Mensagem de exemplo:

json
Copiar código
{
  "id": "691244db8dff586dc37107e9",
  "name": "Edson Rego",
  "zipCode": "38400001",
  "cpf": "12345678901",
  "isValidCpf": true
}
O ReceiveValidatedCpfConsumer consumirá essa mensagem e atualizará o cliente no MongoDB com isValidCpf = true.

📊 Offset Explorer (Kafka Tool)
Ferramenta desktop para visualizar tópicos e mensagens Kafka.

Adicione o broker: localhost:9092

Expanda o tópico tp-cpf-validated

Veja as mensagens publicadas (via API ou Kafkalytic)

Monitore o offset e o consumo

🍃 MongoDB CLI ou Mongo Express
Via terminal:

bash
Copiar código
docker exec -it mongo bash
mongosh -u root -p example
use hexagonal
db.customers.find().pretty()
Via interface web:
👉 http://localhost:8083

Login:

user: root

password: example

database: hexagonal

collection: customers

🔄 Fluxo Completo do Sistema
O cliente é criado via POST /customers.

O serviço publica o CPF no tópico tp-cpf-validation (Kafka Producer).

Um microserviço externo (simulado via WireMock) valida o CPF.

Uma mensagem com isValidCpf=true é publicada no tópico tp-cpf-validated.

O consumidor (ReceiveValidatedCpfConsumer) lê a mensagem e atualiza o registro no MongoDB.

🔍 Pode ser acompanhado via:

Mongo Express → dados persistidos

Kafdrop → mensagens trafegando

Kafkalytic → publicação manual

Offset Explorer → monitoramento de offsets

🧱 Testes de Arquitetura (ArchUnit)
O projeto utiliza ArchUnit para garantir conformidade com a Arquitetura Hexagonal.

Executar os testes:

bash
Copiar código
./gradlew test
As regras verificam convenções como:

Classes Controller em adapters.in.controller

Classes Repository em adapters.out.repository

Sufixos e camadas respeitando Ports & Adapters

🧾 Stack Técnica
Componente	Função
Spring Boot 3.4.0	Framework principal
Spring Data MongoDB	Persistência
Spring Cloud OpenFeign	Comunicação REST (mockada via WireMock)
Spring Kafka	Produção e consumo de mensagens
WireMock	Mock do microserviço de endereço
Docker Compose	Orquestração de serviços
MapStruct + Lombok	Mapeamento e redução de boilerplate
Kafkalytic / Offset Explorer	Observabilidade de mensagens Kafka

👨‍💻 Autor
Edson Gomes do Rego
System Support Engineer | Java Full Stack Developer
💼 ThoughtWorks | 🎓 Eng. da Computação – Univesp
🔗 LinkedIn | GitHub

📚 Projeto baseado no curso
“Arquitetura Hexagonal com Java e Spring Boot” — Prof. Danilo Arantes

```bash
