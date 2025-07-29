# Controle de Estoque

Projeto de gerenciamento de estoque desenvolvido em Java utilizando Spring Boot. Permite adicionar, atualizar, remover e listar produtos com segurança implementada via autenticação JWT.

---

## Funcionalidades

- Cadastro de produtos  
- Atualização de dados dos produtos  
- Remoção de produtos  
- Listagem completa dos produtos no estoque  
- Autenticação via JSON Web Token (JWT)  
- Controle de acesso e proteção de rotas com Spring Security  

---

## Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.5.4  
- Spring Security  
- Spring Data JPA  
- Bean Validation  
- Lombok  
- Banco de dados H2 (em memória para desenvolvimento)  
- Biblioteca Auth0 Java JWT  

---

## Endpoints da API

### Autenticação

| Método | URL             | Descrição                        | Corpo da Requisição (JSON)                            |
|--------|-----------------|--------------------------------|------------------------------------------------------|
| POST   | `/auth/login`   | Autentica usuário e retorna token JWT | `{ "email": "usuario@exemplo.com", "password": "senha" }` |
| POST   | `/auth/register` | Registra novo usuário e retorna token JWT | `{ "email": "novo@exemplo.com", "password": "senha", "name": "Nome do Usuário" }` |

### Produtos

| Método | URL              | Descrição                       | Corpo da Requisição (JSON)                                     |
|--------|------------------|--------------------------------|----------------------------------------------------------------|
| GET    | `/produtos`      | Lista todos os produtos         | N/A                                                            |
| POST   | `/produtos`      | Adiciona um novo produto        | `{ "nome": "Produto X", "quantidade": 20, "preco": 10.0 }` |
| PUT    | `/produtos/{id}` | Atualiza dados de um produto    | `{ "nome": "Produto Atualizado", "quantidade": 30, "preco": 12.0 }` |
| DELETE | `/produtos/{id}` | Remove um produto pelo ID       | N/A                                                            |


