# 🌟 ChatGPT Oracle Integration

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-green?logo=apache-maven)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)
[![Oracle](https://img.shields.io/badge/Oracle-Database-red?logo=oracle)](https://www.oracle.com/database/)
[![OpenAI](https://img.shields.io/badge/OpenAI-GPT-purple?logo=openai)](https://platform.openai.com/docs/api-reference)

Integração entre **Spring Boot**, **Oracle Database** e **OpenAI GPT**, permitindo consultas dinâmicas no banco de dados via GPT com *function calling*.

---

## 🔹 Funcionalidades

* Consulta tabelas do Oracle via prompts de linguagem natural.
* Filtros simples para busca de registros.
* Retorno de dados em formato JSON.
* Suporte a **Spring WebFlux** e **Reactor Netty**.
* Estrutura modular para futuras extensões.

---

## 🛠 Pré-requisitos

* **Java 17+**
* **Maven 3.8+**
* **Oracle Database**
* **OpenAI API Key**

---

## ⚡️ Tecnologias Utilizadas

* Spring Boot 3.1
* Spring WebFlux
* Hibernate / JPA
* Reactor Netty
* Oracle JDBC Driver (ojdbc8 ou superior)
* OpenAI API

---

## 📦 Instalação

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/chatgpt-oracle-function-calling.git
cd chatgpt-oracle-function-calling
```

2. Configure as variáveis de ambiente:

> ⚠️ **Windows (PowerShell)**:

```powershell
# Java 17
$Env:JAVA_HOME="C:\Program Files\Java\jdk-17"

# Adiciona Java ao PATH
$Env:PATH="$Env:JAVA_HOME\bin;$Env:PATH"

# Chave da OpenAI
$Env:OPENAI_API_KEY="SUA_CHAVE_OPENAI"

# Porta do servidor (opcional, padrão 8080)
$Env:SERVER_PORT=8080
```

> ⚠️ **Linux/macOS (bash/zsh)**:

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
export OPENAI_API_KEY=SUA_CHAVE_OPENAI
export SERVER_PORT=8080
```

3. Compile o projeto:

```bash
mvn clean package
```

---

## 🚀 Execução

```bash
java -jar target/chatgpt-oracle-integration-1.0-SNAPSHOT.jar --server.port=$Env:SERVER_PORT
```

O serviço estará disponível em:

```
http://localhost:8080
```

---

## 💻 Uso

### Endpoint principal:

```http
POST /query
Content-Type: application/json
```

#### Exemplo de payload:

```json
{
  "pergunta": "Quais são as faturas do cliente Marítimo?"
}
```

#### Exemplo de resposta:

```json
[
  {
    "ID_FATURA": 123,
    "CLIENTE": "Marítimo",
    "VALOR": 1500.00,
    "DATA": "2025-08-01"
  },
  ...
]
```

---

## ⚙️ Configuração do Banco Oracle

* Certifique-se que o usuário do Oracle tenha acesso às views/tabelas necessárias.
* Configuração do `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/ORCLPDB1
spring.datasource.username=usuario
spring.datasource.password=senha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

---

## 📌 Observações

* Necessário **Java 17** para rodar a aplicação. Usar versão inferior causará `UnsupportedClassVersionError`.
* Configure corretamente **JAVA\_HOME** e **PATH**.
* Configure **OPENAI\_API\_KEY** antes de rodar a aplicação.
* A porta do servidor pode ser alterada via `SERVER_PORT` ou `--server.port`.

---

## 💡 Melhorias Futuras

* Paginação de resultados.
* Validação de queries SQL para evitar SQL Injection.
* Suporte a múltiplos bancos de dados.
* Logs detalhados e monitoramento via Spring Actuator.

---

## 📝 Licença

MIT License – sinta-se à vontade para usar e contribuir!
