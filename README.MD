# DS-CLIENT

Este projeto é um desafio do curso DevSuperior para a criação de uma API REST utilizando as tecnologias:

- Java 11
- Spring boot
- JPA
- Spring web
- Lombook
- H2-database

---

Modelo UML da classe

![img](./img/uml.png)

---

Busca paginada de clientes:

GET

    /client

    /clients?page=0&linesPerPage=6&direction=ASC&orderBy=name

Busca de cliente por id:

GET

    /clients/1

Inserção de novo cliente:

POST

    /clients

    Body:

    {
        "name": "Maria Silva",
        "cpf": "12345678901",
        "income": 6500.0,
        "birthDate": "1994-07-20T10:30:00Z",
        "children": 2
    }

Atualização de cliente:

PUT

    /clients/1

    Body:

    {
        "name": "Maria Silvaaa",
        "cpf": "12345678901",
        "income": 6500.0,
        "birthDate": "1994-07-20T10:30:00Z",
        "children": 2
    }
