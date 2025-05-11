# Vendas-App

## Visão Geral
O **Vendas-App** é uma aplicação web para gerenciar vendas, voltada para pequenos negócios ou profissionais autônomos. Utiliza Spring Boot, Spring Security com autenticação JWT, e MySQL como banco de dados. Permite criar, listar, calcular o total e deletar vendas, com acesso protegido por autenticação.

### Objetivo
- Gerenciar vendas (criar, listar, calcular total e deletar).
- Garantir segurança com autenticação baseada em JWT.
- Fornecer uma base escalável para futuras funcionalidades.

### Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Security
- MySQL
- JWT (JSON Web Token)
- Lombok
- Maven

## Estrutura do Projeto
- **Config/**: Configurações de segurança (`SecurityConfig.java`).
- **Controller/**: Endpoints REST.
  - **DTO/**: `VendaDTO.java` para transferência de dados de vendas.
  - `AuthController.java`: Gerencia autenticação.
  - `VendaController.java`: Gerencia vendas.
- **Entity/**: Entidades JPA (`Pessoa.java`, `Venda.java`).
- **Repository/**: Repositórios para operações CRUD.
- **Security/**: Lógica de autenticação com JWT.
- **Service/**: Serviços de negócio (`PessoaUserDetailsService.java`, `VendaService.java`).
- **Specification/**: Especificações para filtrar vendas (`VendaSpecifications.java`).
- **resources/application.properties**: Configurações do banco e JPA.

## Funcionalidades Atuais
### Autenticação
- **Endpoint de Login**: `POST /api/auth/login`
  - Exemplo:
    ```bash
    curl -X POST http://localhost:8080/api/auth/login \
      -H "Content-Type: application/json" \
      -d '{"email": "caio@example.com", "password": "senha123"}'
    ```
  - Resposta: Token JWT.

### Gerenciamento de Vendas
- **Criar Venda**: `POST /vendas`
  - Exemplo:
    ```bash
    curl -X POST http://localhost:8080/vendas \
      -H "Content-Type: application/json" \
      -H "Authorization: Bearer <seu-token>" \
      -d '{"dataFormatada": "01/01/2025", "valor": 150.00}'
    ```
- **Listar Vendas**: `GET /vendas?dia=1&mes=1&ano=2025`
  - Resposta: Lista de vendas.
- **Calcular Total**: `GET /vendas/total`
  - Resposta: Total das vendas.
- **Deletar Venda**: `DELETE /vendas/{id}`

### Banco de Dados
- Tabelas: `pessoas` (usuários) e `vendas` (vendas).

## Configuração do Ambiente
### Pré-requisitos
- Java 21
- MySQL (porta 3306)
- Maven

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/vendas-app.git
   ```
2. Configure o banco no `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/vendas_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=sua_senha
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   ```
3. Crie o banco:
   ```sql
   CREATE DATABASE vendas_db;
   ```
4. Rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```

## Testando a Aplicação
1. **Login**:
   - Insira um usuário no banco com senha criptografada (use `BCryptPasswordEncoder`).
   - Teste o login:
     ```bash
     curl -X POST http://localhost:8080/api/auth/login \
       -H "Content-Type: application/json" \
       -d '{"email": "caio@example.com", "password": "senha123"}'
     ```
2. **Gerenciar Vendas**:
   - Criar: `POST /vendas`
   - Listar: `GET /vendas?dia=1&mes=1&ano=2025`
   - Calcular Total: `GET /vendas/total`
   - Deletar: `DELETE /vendas/{id}`

## Próximos Passos
- Adicionar um frontend (ex.: Angular ou React).
- Implementar relatórios de vendas.
- Adicionar roles para controle de acesso (ex.: ADMIN, USER).