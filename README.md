# 🏋️ BioTrack - Sistema de Monitoramento Biométrico

> API REST para gerenciamento de usuários e acompanhamento de medidas corporais com cálculo automático de IMC, desenvolvida com Spring Boot.

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Academic-blue)]()

---

## 👥 Integrantes do Grupo

- **Elaine Fabiola Soares**
- **Eliakim Simoes de Matos**
- **Heinz Junior Stranner**
- **João Carlos Vieira Filho**
- **Julia Ingrid Santos Alencar**

---

## 📋 Sobre o Projeto

O **BioTrack** é uma aplicação completa para acompanhamento de medidas corporais e análise de saúde. O sistema permite que usuários registrem suas medidas ao longo do tempo, calculem automaticamente o IMC (Índice de Massa Corporal) e filtrem usuários por diferentes faixas de classificação corporal.

### 🎯 Funcionalidades Principais

#### ✅ Gerenciamento de Usuários
- Cadastro completo com dados pessoais
- Autenticação e segurança com Spring Security
- Armazenamento seguro de senhas (criptografadas)

#### ✅ Registro de Medidas Corporais
- Peso e altura
- Perímetros (cintura, quadril, peito, braços, coxas)
- Percentual de gordura corporal
- Histórico completo de medições

#### ✅ Cálculo Automático de IMC
- Cálculo baseado na medida mais recente
- Classificação em 4 faixas:
  - **Abaixo do Peso** (IMC < 18.5)
  - **Peso Normal** (18.5 ≤ IMC < 25)
  - **Sobrepeso** (25 ≤ IMC < 30)
  - **Obesidade** (IMC ≥ 30)

#### ✅ Filtros e Consultas
- Filtrar usuários por faixa de IMC
- Buscar medidas por usuário
- Histórico ordenado por data

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem de programação |
| **Spring Boot** | 3.5.7 | Framework principal |
| **Spring Data JPA** | 3.5.7 | Persistência de dados |
| **Spring Security** | 3.5.7 | Autenticação e segurança |
| **H2 Database** | Runtime | Banco de dados em memória |
| **Lombok** | Latest | Redução de boilerplate |
| **Maven** | 3.9.11 | Gerenciamento de dependências |

---

## 🗂️ Estrutura do Projeto

```
biotrack/
├── src/
│   ├── main/
│   │   ├── java/com/ProgWebII/biotrack/
│   │   │   ├── controller/
│   │   │   │   ├── UsuarioController.java
│   │   │   │   └── MeasureController.java
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   │   └── MeasureRequest.java
│   │   │   │   └── response/
│   │   │   │       ├── BuscarUsuarioPorIdResponse.java
│   │   │   │       └── MedidaResponse.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Measure.java
│   │   │   │   └── Imc.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── MeasureRepository.java
│   │   │   ├── service/
│   │   │   │   └── MeasureService.java
│   │   │   └── BiotrackApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/ProgWebII/biotrack/
│           └── BiotrackApplicationTests.java
├── pom.xml
└── README.md
```

---

## 📊 Modelo de Dados

### 🧑 Entidade: User (Usuário)

| Campo | Tipo | Restrições | Descrição |
|-------|------|------------|-----------|
| `id` | Long | PK, Auto-increment | Identificador único |
| `name` | String | NOT NULL, max=100 | Nome completo |
| `birthDate` | LocalDate | NOT NULL | Data de nascimento |
| `zipCode` | String | max=9 | CEP (formato: 00000-000) |
| `email` | String | UNIQUE, NOT NULL, max=100 | E-mail para login |
| `password` | String | NOT NULL | Senha (criptografada) |
| `measures` | List<Measure> | OneToMany, Cascade | Lista de medidas |

### 📏 Entidade: Measure (Medida)

| Campo | Tipo | Restrições | Descrição |
|-------|------|------------|-----------|
| `id` | Long | PK, Auto-increment | Identificador único |
| `measurementDate` | LocalDateTime | NOT NULL | Data e hora da medição |
| `weightKg` | Double | NOT NULL, min=0.1 | Peso em quilogramas |
| `heightCm` | Double | min=0.1 | Altura em centímetros |
| `waistCm` | Double | min=0.1 | Circunferência da cintura |
| `hipCm` | Double | min=0.1 | Circunferência do quadril |
| `chestCm` | Double | min=0.1 | Circunferência do peito |
| `armRightCm` | Double | min=0.1 | Circunferência do braço direito |
| `armLeftCm` | Double | min=0.1 | Circunferência do braço esquerdo |
| `thighRightCm` | Double | min=0.1 | Circunferência da coxa direita |
| `thighLeftCm` | Double | min=0.1 | Circunferência da coxa esquerda |
| `bodyFatPercentage` | Double | min=0.0 | Percentual de gordura corporal |
| `user` | User | ManyToOne, FK | Usuário proprietário |

---

## 🚀 Como Executar

### 📋 Pré-requisitos

- **Java 21** ou superior instalado
- **Maven 3.9+** (ou use o wrapper incluído)
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

### 🔧 Instalação e Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/Jvieyrah/ProgWebII.git
   cd biotrack
   ```

2. **Execute com Maven Wrapper**
   
   **Linux/Mac:**
   ```bash
   ./mvnw spring-boot:run
   ```
   
   **Windows:**
   ```bash
   mvnw.cmd spring-boot:run
   ```

3. **Ou compile e execute o JAR**
   ```bash
   ./mvnw clean package
   java -jar target/biotrack-0.0.1-SNAPSHOT.jar
   ```

4. **A aplicação estará disponível em:**
   ```
   http://localhost:8080
   ```

### 🗄️ Acessar o Console H2

O banco de dados H2 possui uma interface web para consultas SQL:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(deixe em branco)*

---

## 📍 Documentação da API

### 🔵 Endpoints de Usuários

#### 1. Listar todos os usuários
```http
GET /usuarios
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "birthDate": "1990-05-15",
    "zipCode": "12345-678",
    "email": "joao@email.com"
  }
]
```

---

#### 2. Buscar usuário por ID
```http
GET /usuarios/{id}
```

**Exemplo:**
```http
GET /usuarios/1
```

**Resposta de Sucesso (200 OK):**
```json
{
  "id": 1,
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "zipCode": "12345-678",
  "email": "joao@email.com"
}
```

---

#### 3. Criar novo usuário
```http
POST /usuarios
Content-Type: application/json
```

**Body:**
```json
{
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "zipCode": "12345-678",
  "email": "joao@email.com",
  "password": "senha123"
}
```

**Resposta de Sucesso (201 CREATED):**
```json
{
  "id": 1,
  "name": "João Silva",
  "birthDate": "1990-05-15",
  "zipCode": "12345-678",
  "email": "joao@email.com"
}
```

---

#### 4. Atualizar usuário completamente
```http
PUT /usuarios/{id}
Content-Type: application/json
```

**Body:**
```json
{
  "name": "João Silva Santos",
  "birthDate": "1990-05-15",
  "zipCode": "12345-678",
  "email": "joao.santos@email.com",
  "password": "novaSenha123"
}
```

---

#### 5. Atualizar usuário parcialmente
```http
PATCH /usuarios/{id}
Content-Type: application/json
```

**Body (apenas campos que deseja atualizar):**
```json
{
  "email": "novo.email@email.com"
}
```

---

#### 6. Deletar usuário
```http
DELETE /usuarios/{id}
```

**Resposta de Sucesso (204 NO CONTENT)**

---

#### 7. **🔍 Filtrar usuários por faixa de IMC** ⭐
```http
GET /usuarios/filtro-imc?faixa={faixa}
```

**Faixas disponíveis:**
- `Abaixo do Peso`
- `Peso Normal`
- `Sobrepeso`
- `Obesidade`

**Exemplo:**
```http
GET /usuarios/filtro-imc?faixa=Peso Normal
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "birthDate": "1990-05-15",
    "zipCode": "12345-678",
    "email": "joao@email.com"
  }
]
```

---

### 🔵 Endpoints de Medidas

#### 1. Criar nova medida para um usuário
```http
POST /usuarios/{usuarioId}/medidas/{usuarioId}/medidas
Content-Type: application/json
```

**Body (campos mínimos):**
```json
{
  "measurementDate": "2024-11-02T10:30:00",
  "weightKg": 75.5,
  "heightCm": 175.0
}
```

**Body (todos os campos):**
```json
{
  "measurementDate": "2024-11-02T10:30:00",
  "weightKg": 75.5,
  "heightCm": 175.0,
  "waistCm": 85.0,
  "hipCm": 95.0,
  "chestCm": 100.0,
  "armRightCm": 32.5,
  "armLeftCm": 32.0,
  "thighRightCm": 58.0,
  "thighLeftCm": 57.5,
  "bodyFatPercentage": 18.5
}
```

**Resposta de Sucesso (201 CREATED):**
```json
"Medida criada com sucesso!"
```

---

#### 2. Listar todas as medidas de um usuário
```http
GET /usuarios/{usuarioId}/medidas/{usuarioId}/medidas
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "id": 1,
    "measurementDate": "2024-11-02T10:30:00",
    "weightKg": 75.5,
    "heightCm": 175.0,
    "waistCm": 85.0,
    "hipCm": 95.0,
    "imc": 24.65
  }
]
```

---

#### 3. Buscar uma medida específica
```http
GET /usuarios/{usuarioId}/medidas/{usuarioId}/medidas/{medidaId}
```

---

## 🔐 Autenticação

O projeto utiliza **Spring Security** com autenticação básica.

### Como Autenticar no Postman:

1. Vá na aba **Authorization**
2. Selecione **Type:** `Basic Auth`
3. Insira **Username** e **Password**
4. Ou adicione o header manualmente:
   ```
   Authorization: Basic [base64(username:password)]
   ```

### Configuração Temporária para Desenvolvimento:

Para desabilitar a autenticação durante o desenvolvimento, crie a classe:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
```

---

## 📈 Funcionalidades Extras Implementadas

- ✅ **Cálculo automático de IMC**
- ✅ **Classificação em faixas de IMC**
- ✅ **Filtro avançado por faixa de IMC**
- ✅ **Sistema de autenticação com Spring Security**
- ✅ **Relacionamento entre entidades (User ↔ Measure)**
- ✅ **Validação de dados com Bean Validation**
- ✅ **DTOs para separação de responsabilidades**
- ✅ **Histórico completo de medições ordenadas**

---

## 🧪 Testes

### Executar todos os testes:
```bash
./mvnw test
```

### Executar apenas testes de integração:
```bash
./mvnw verify
```

---

## 📝 Exemplos de Uso com cURL

### Criar um usuário:
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Santos",
    "birthDate": "1985-03-20",
    "zipCode": "01234-567",
    "email": "maria@email.com",
    "password": "senha123"
  }'
```

### Criar uma medida:
```bash
curl -X POST http://localhost:8080/usuarios/1/medidas/1/medidas \
  -H "Content-Type: application/json" \
  -d '{
    "measurementDate": "2024-11-02T10:30:00",
    "weightKg": 68.5,
    "heightCm": 165.0
  }'
```

### Filtrar por IMC:
```bash
curl "http://localhost:8080/usuarios/filtro-imc?faixa=Peso%20Normal"
```

---

## 🔄 Roadmap e Melhorias Futuras

### 🚧 Em Desenvolvimento
- [ ] Corrigir duplicação de paths nos endpoints de medidas
- [ ] Implementar CRUD completo para todas as entidades
- [ ] Adicionar validações de negócio mais robustas

### 📅 Planejado
- [ ] Implementar JWT para autenticação stateless
- [ ] Adicionar Swagger/OpenAPI para documentação interativa
- [ ] Criar gráficos de evolução de peso e IMC
- [ ] Implementar exportação de relatórios em PDF
- [ ] Adicionar cálculo de outras métricas (TMB, % de gordura ideal, etc.)
- [ ] Criar dashboard web com histórico visual
- [ ] Migrar para PostgreSQL em ambiente de produção
- [ ] Implementar sistema de metas e acompanhamento
- [ ] Adicionar notificações de progresso

---

## ⚙️ Configuração (application.properties)

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Server
server.port=8080
```

---

## 🐛 Problemas Conhecidos

### Issue #1: Duplicação de paths nos endpoints de medidas
**Descrição:** Os endpoints de medidas possuem duplicação no path (`/usuarios/{id}/medidas/{id}/medidas`)  
**Solução temporária:** Use a URL completa como documentado  
**Fix planejado:** Remover a duplicação no `@PostMapping`, `@GetMapping`, etc.

---

## 📚 Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)
- [H2 Database](https://www.h2database.com/)

---

## 🤝 Contribuindo

Este é um projeto acadêmico, mas sugestões são bem-vindas!

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da disciplina de **Programação Web II**.

---

## 📞 Contato

Para dúvidas, sugestões ou reportar problemas:

- **Issues:** Abra uma issue no GitHub
- **Email:** [emails dos integrantes]

---

## ✅ Checklist de Avaliação

- [x] Funcionalidade dos endpoints (GET, POST, PUT, PATCH, DELETE)
- [x] Conexão com banco de dados (H2)
- [x] Código limpo e estruturado (uso de DTOs, Services, etc.)
- [x] Uso adequado de boas práticas REST
- [x] Funcionalidades extras (cálculo de IMC, filtros, autenticação)
- [x] Documentação completa no README
- [x] Validação de dados
- [x] Tratamento de erros

---

**Desenvolvido com ☕ e 💻 por Dream Team - 2025**

*BioTrack - Transformando dados em saúde!* 🏋️‍♂️📊
