# 📂 Sistema de Gerenciamento de Usuários – POO + MVC (Java)

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=for-the-badge&logo=java)
![MVC](https://img.shields.io/badge/Pattern-MVC-orange?style=for-the-badge)
![DAO](https://img.shields.io/badge/Pattern-DAO-blueviolet?style=for-the-badge)
![JDBC](https://img.shields.io/badge/Database-JDBC-red?style=for-the-badge)
![MySQL](https://img.shields.io/badge/SGBD-MySQL-blue?style=for-the-badge&logo=mysql)

## 1. Introdução

[cite_start]Este projeto consiste em um sistema de gerenciamento de usuários, desenvolvido em Java aplicando Programação Orientada a Objetos (POO) e o padrão arquitetônico Model-View-Controller (MVC). [cite: 3] [cite_start]O objetivo principal é demonstrar a separação de responsabilidades entre as camadas (Modelo, Visão e Controle) [cite: 4][cite_start], aplicando boas práticas de arquitetura de software e garantindo uma estrutura de código modular e escalável. [cite: 9]

## 2. Escopo do Projeto

[cite_start]O sistema tem como foco as funcionalidades essenciais de gerenciamento de usuários, com armazenamento de dados em um banco de dados relacional. [cite: 16]

**Funcionalidades incluídas:**
* [cite_start]Cadastro de novos usuários. [cite: 12]
* [cite_start]Edição e exclusão de usuários existentes. [cite: 13]
* [cite_start]Listagem de todos os usuários cadastrados. [cite: 14]
* [cite_start]Autenticação de usuários (funcionalidade a ser implementada). [cite: 15]

**Funcionalidades não incluídas:**
* [cite_start]Integrações com redes sociais. [cite: 18]
* [cite_start]Recuperação de senha por e-mail. [cite: 19]
* [cite_start]Aplicativos móveis. [cite: 20]

## 3. Requisitos do Sistema

### Requisitos Funcionais (RF)
* [cite_start]**RF01:** O sistema deve permitir o cadastro de novos usuários. [cite: 22]
* [cite_start]**RF02:** O sistema deve permitir a edição dos dados de um usuário. [cite: 23]
* [cite_start]**RF03:** O sistema deve permitir a exclusão de usuários. [cite: 24]
* [cite_start]**RF04:** O sistema deve listar todos os usuários cadastrados. [cite: 25]
* [cite_start]**RF05:** O sistema deve autenticar usuários com login e senha (escopo futuro). [cite: 26]
* [cite_start]**RF06:** O sistema deve armazenar e recuperar dados de um banco de dados via JDBC. [cite: 27]

### Requisitos Não Funcionais (RNF)
* [cite_start]**RNF01:** O sistema deve ser desenvolvido em Java, utilizando POO e os padrões MVC e DAO. [cite: 29]
* [cite_start]**RNF02:** O sistema deve garantir segurança no armazenamento de dados. [cite: 31]
* [cite_start]**RNF03:** O sistema deve ser modular e de fácil manutenção. [cite: 32]
* [cite_start]**RNF04:** O sistema deve responder às ações do usuário em tempo hábil (até 2 segundos). [cite: 33]

## 4. Arquitetura e Estrutura do Projeto

A estrutura de arquivos segue a separação de responsabilidades dos padrões MVC e DAO.

/src
├── Main.java               # Classe Main – ponto de entrada do sistema
├── model/
│   └── Usuario.java        # Camada Model – representa a entidade de dados
├── view/
│   └── UsuarioView.java    # Camada View – interação com o usuário (console)
├── controller/
│   └── UsuarioController.java # Camada Controller – lógica de negócio
└── persistence/
├── ConexaoBD.java      # Classe utilitária para conexão com o banco
└── UsuarioDAO.java     # Camada de Acesso a Dados (operações CRUD)
/
├── Diagrama_de_Classes.png
├── Diagrama_Entidade_Re.jpg
└── README.md

## 5. Modelagem do Sistema

### Diagrama de Classes (UML)
O diagrama ilustra a relação entre as classes, destacando a separação de responsabilidades.
*(Sugestão: Atualizar o diagrama para incluir a classe `UsuarioDAO` e as novas relações)*

![Diagrama de Classes](./Diagrama_de_Classes.png)

### Diagrama Entidade-Relacionamento (DER)
O diagrama define a estrutura da tabela `usuario` no banco de dados.

![Diagrama Entidade-Relacionamento](./Diagrama_Entidade_Re.png)


## 6. Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Banco de Dados:** MySQL
* **API de Persistência:** JDBC (Java Database Connectivity) [cite: 38]
* **Padrões de Arquitetura:** MVC (Model-View-Controller) e DAO (Data Access Object) [cite: 36]
* **Modelagem:** UML e DER

## 7. Como Executar

1.  **Pré-requisitos:**
    * Java JDK 17+ instalado e configurado.
    * Um servidor de banco de dados MySQL em execução.

2.  **Configuração do Banco de Dados:**
    * Crie um banco de dados (ex: `CREATE DATABASE gestao_usuarios;`).
    * Execute o script SQL para criar a tabela `usuario`.
    * Ajuste as credenciais (`URL`, `USUARIO`, `SENHA`) na classe `ConexaoBD.java`.

3.  **Execução:**
    * Compile todos os arquivos `.java` a partir da pasta `/src`: `javac -encoding UTF-8 *.java model/*.java view/*.java controller/*.java persistence/*.java`
    * Execute a classe principal: `java Main`

## 8. Evolução do Projeto (Próximos Passos)

* [x] **Estrutura MVC:** Estrutura inicial clara e organizada.
* [x] **Padrão DAO:** Refatoração da persistência para usar o padrão Data Access Object.
* [x] **Integração JDBC:** Implementação das chamadas SQL (INSERT, SELECT, UPDATE, DELETE) na camada DAO.
* [ ] **Validação de Dados:** Adicionar validações para os dados de entrada (ex: formato de e-mail, campos vazios).
* [ ] **Tratamento de Exceções:** Implementar um tratamento de exceções mais robusto na camada de persistência.
* [ ] **Interface Gráfica:** Substituir a interface de console por uma GUI com JavaFX ou Swing.
* [ ] **Testes Unitários:** Desenvolver testes com JUnit para garantir a qualidade do código.

---

## 👥 Equipe

* **Jefferson** – Líder técnico e desenvolvedor principal
* **Maria Liliane Vasconcelos Barros** – Auxiliar de desenvolvimento e documentação

---

## 📜 Licença

Este projeto é destinado a fins educacionais. Sinta-se à vontade para utilizá-lo como referência, dando os devidos créditos à equipe.

O código-fonte completo deste projeto está disponível no GitHub. Sinta-se à vontade para explorar, clonar e utilizar como referência para seus estudos.

[➡️ Acessar o Repositório no GitHub](https://github.com/jeffersonjaily/A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS)