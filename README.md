# Projeto ADS

Este projeto é uma aplicação web desenvolvida em Java utilizando Spring Boot, Maven e PostgreSQL. O objetivo é gerenciar entidades como Aluno e Role, permitindo operações de cadastro, consulta e autenticação.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- Maven

## Estrutura do Projeto

- `model`: Contém as entidades do sistema (ex: Aluno, Role).
- `repository`: Interfaces para acesso ao banco de dados usando Spring Data JPA.
- `Enum`: Enumerações utilizadas no sistema (ex: Periodo).
- `resources/application.properties`: Configurações do banco de dados e do Spring.

## Configuração

1. Instale o PostgreSQL e crie um banco chamado `projetoads`.
2. Altere o usuário e senha do banco em `src/main/resources/application.properties` se necessário.
3. Execute o projeto via Maven ou pela IDE.

## Funcionalidades

- Cadastro e listagem de alunos.
- Busca de alunos por CPF e e-mail.
- Controle de papéis (roles) de usuário.
- Enumeração de períodos acadêmicos.
- Interface web com Thymeleaf.

## Como Executar

1. Clone o repositório.
2. Execute `mvn spring-boot:run` ou rode a aplicação pela sua IDE.
3. Acesse `http://localhost:8080` no navegador.

## Observações

- O banco de dados é atualizado automaticamente conforme as entidades (DDL auto update).
- As queries personalizadas estão nos repositórios.

---
Para criar o banco de dados e inserir os dados iniciais, siga estes passos:

1. **Crie o banco de dados no PostgreSQL:**

No terminal do PostgreSQL, execute:
```sql
CREATE DATABASE projetoads;
```

2. **Rode o projeto para que as tabelas sejam criadas automaticamente.**

3. **Insira os dados iniciais nas tabelas `roles` e `usuarios`:**

No terminal do PostgreSQL, conectado ao banco `projetoads`, execute:
```sql
INSERT INTO public.roles(nome) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO public.usuarios (ativo, data_nascimento, username, email, password, role_id) VALUES
(true, '2012-12-10', 'admin', 'admin@gmail.com', '$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu', 1),
(true, '2012-02-10', 'test', 'test@gmail.com', '$2a$12$TYSPPDsgR1T9vpgMSavOteZoqzjGVLt7rzsqKLrGL4oQdE3rWDNru', 2);
```
