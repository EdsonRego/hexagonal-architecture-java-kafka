# 🧩 Projeto: Arquitetura Hexagonal com Spring Boot, Kafka, MongoDB e WireMock

Este projeto demonstra na prática a **Arquitetura Hexagonal (Ports and Adapters)** utilizando:
- **Java 17**
- **Spring Boot 3**
- **MongoDB**
- **Apache Kafka**
- **Feign Client + WireMock**
- **Docker Compose**
- **Ferramentas de apoio**: Postman, Kafkalytic e Offset Explorer.

---

## 🧠 Conceitos Fundamentais

### 🏗️ Arquitetura Hexagonal

A Arquitetura Hexagonal, também conhecida como **Ports and Adapters**, tem como objetivo **isolar a lógica de negócio** (domínio) das dependências externas, como bancos de dados, mensagerias, frameworks ou APIs externas.

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

## ⚙️ Arquitetura do Projeto

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

## 🚀 Execução do Projeto

### 🔧 1. Subir os containers Docker

Na raiz do projeto:


docker compose up -d
Isso iniciará:

Zookeeper → porta 2181

Kafka → porta 9092

Kafdrop (UI Kafka) → porta 9000

MongoDB → porta 27017

Mongo Express (UI MongoDB) → porta 8083

Verifique com:

bash
Copiar código
docker ps
🔌 2. Subir o WireMock
O WireMock simula o microserviço externo de CEP (Address API).

No diretório onde está o .jar:

bash
Copiar código
cd C:\Users\edson\Downloads
java -jar wiremock-standalone-4.0.0-beta.15.jar --port 8082
📍 Endpoint simulado:

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
🧩 3. Executar a aplicação Spring Boot
Na raiz do projeto:

bash
Copiar código
./gradlew bootRun
A aplicação sobe em:

arduino
Copiar código
http://localhost:8081
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
  "cp": "12345678901",
  "isValidCpf": true
}
O ReceiveValidatedCpfConsumer consumirá esta mensagem e atualizará o cliente no MongoDB com isValidCpf = true.

📊 Offset Explorer (antigo Kafka Tool)
Ferramenta desktop para visualizar tópicos Kafka e mensagens publicadas.

Adicione o broker: localhost:9092

Expanda o tópico tp-cpf-validated

Veja as mensagens publicadas (via API ou Kafkalytic)

Monitore o offset e o consumo

🍃 MongoDB CLI ou Mongo Express
Acessar via terminal:
bash
Copiar código
docker exec -it mongo bash
mongosh -u root -p example
use hexagonal
db.customers.find().pretty()
Ou via interface web:
👉 http://localhost:8083
Login:

user: root

password: example

Coleção: customers

🔄 Fluxo Completo do Sistema
O cliente é criado via POST /customers.

O serviço publica o CPF no tópico tp-cpf-validation (Kafka Producer).

Um microserviço externo (simulado via WireMock) valida o CPF.

Uma mensagem com isValidCpf=true é publicada no tópico tp-cpf-validated.

O consumidor (ReceiveValidatedCpfConsumer) lê a mensagem e atualiza o registro no MongoDB.

Tudo pode ser acompanhado via:

Mongo Express (dados persistidos)

Kafdrop (mensagens trafegando)

Kafkalytic (publicar manualmente)

Offset Explorer (monitorar offsets)

🧾 Stack Técnica
Componente	Função
Spring Boot 3.4.0	Framework principal
Spring Data MongoDB	Persistência no MongoDB
Spring Cloud OpenFeign	Comunicação REST com WireMock
Spring Kafka	Produção e consumo de mensagens
WireMock	Mock do microserviço de endereço
Docker Compose	Orquestração de serviços
MapStruct + Lombok	Mapeamento e geração de boilerplate
Kafkalytic / Offset Explorer	Visualização e publicação de mensagens Kafka

🧠 Autor
Edson Gomes do Rego
System Support Engineer | Java Full Stack Developer
💼 ThoughtWorks | 🎓 Eng. da Computação – Univesp
🔗 LinkedIn | GitHub

📚 Este projeto é baseado no curso “Arquitetura Hexagonal com Java e Spring Boot” do professor Danilo Arantes.

```bash
