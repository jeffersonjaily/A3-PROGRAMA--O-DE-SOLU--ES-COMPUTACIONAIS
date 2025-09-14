# 📂 Sistema de Gerenciamento de Usuários – POO + MVC (Java)

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=for-the-badge&logo=java)
![MVC](https://img.shields.io/badge/Pattern-MVC-orange?style=for-the-badge)
![DAO](https://img.shields.io/badge/Pattern-DAO-blueviolet?style=for-the-badge)
![JDBC](https://img.shields.io/badge/Database-JDBC-red?style=for-the-badge)
![MySQL](https://img.shields.io/badge/SGBD-MySQL-blue?style=for-the-badge&logo=mysql)

# Sistema de Gerenciamento de Usuários

## 1. Introdução

Este projeto consiste em um sistema de gerenciamento de usuários, desenvolvido em **Java** aplicando **POO** e o padrão **MVC**.  
O objetivo é demonstrar a separação de responsabilidades entre as camadas (Modelo, Visão e Controle) e aplicar boas práticas de arquitetura de software.

---

## 2. Escopo do Projeto

### Funcionalidades incluídas
- Cadastro, edição e exclusão de usuários  
- Listagem de todos os usuários cadastrados  
- Autenticação de usuários (**a ser implementada**)  

### Funcionalidades não incluídas
- Integrações com redes sociais  
- Recuperação de senha por e-mail  
- Aplicativos móveis  

---

## 3. Estrutura de Arquivos do Projeto

```plaintext
\A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS\
├── .vscode
├── assets\
│   ├── favicon.ico
│   ├── ícone para um aplica-Photoroom.png
│   └── ícone para um aplica.png
├── Gerenciamento_de_Usuarios\
│   ├── .vscode
│   ├── bin\
│   │   ├── view\
│   │   │   ├── ConfigDbView.fxml
│   │   │   └── UsuarioView.fxml
│   │   ├── ConexaoBD.class
│   │   ├── ConfigDbController.class
│   │   ├── Gerenciamento_de_Usuarios.exe
│   │   ├── Gerenciamento_de_Usuarios.jar
│   │   ├── MainApp.class
│   │   ├── Usuario.class
│   │   ├── UsuarioController.class
│   │   ├── UsuarioDAO.class
│   │   ├── UsuarioView.class
│   │   ├── UsuarioView.fxml
│   │   └── ValidaEmail.class
│   ├── lib\
│   ├── ConexaoBD.java
│   ├── ConfigDbController.java
│   ├── ConfigDbView.fxml
│   ├── MainApp.java
│   ├── mysql-connector-j-9.4.0.jar
│   ├── Usuario.java
│   ├── UsuarioController.java
│   ├── UsuarioDAO.java
│   ├── UsuarioView.java
│   └── ValidaEmail.java
├── instalador\
│   └── Gerenciamento_de_Usuarios_Installer.exe
├── javafx-sdk-24.0.2\
│   ├── bin
│   ├── legal
│   ├── lib
│   ├── src
│   └── src.zip
├── .gitattributes
├── .gitignore
├── Diagrama_de_Classes.png
├── Diagrama_Entidade_Re.png
├── Especificação de Requisitos do Sistema de Gerenciamento de Usuários.docx
├── gerenciador.xlm.xml
└── README.md
```

---

## 4. Como Executar

### 4.1 Usando o Instalador (recomendado)

1. Navegue até o instalador:  
   \A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS\instalador\Gerenciamento_de_Usuarios_Installer.exe
2. Execute o instalador e siga os passos na tela.  
3. Um atalho será criado no **Menu Iniciar**. Clique para abrir o sistema.

---

### 4.2 Manualmente (para desenvolvedores)

1. Pré-requisitos:  
   - Java JDK 17+  
   - MySQL ativo  

2. Configure o banco de dados (`gestao_usuarios`) e ajuste as credenciais em `ConexaoBD.java`.  

3. Compile os arquivos:  
   javac -encoding UTF-8 *.java model/*.java view/*.java controller/*.java persistence/*.java

4. Execute a aplicação:  
   java Main

---

## 5. Tecnologias Utilizadas

- **Java 17+**  
- **MySQL**  
- **JDBC**  
- **MVC e DAO**  
- **Modelagem UML e DER**  
- **UX/UI Design** – Foco na experiência e interface do usuário

---

## 6. Evolução do Projeto

- Estrutura MVC  
- Padrão DAO  
- Integração JDBC  
- Validação de Dados  
- Tratamento de Exceções  
- Interface Gráfica (JavaFX ou Swing)  
- Testes Unitários (JUnit)
---

## 7. Equipe

  * **Jefferson Jaily Gonçalves Félix** – Líder Técnico e Desenvolvedor Principal
  * **Maria Liliane Vasconcelos Barros** – Desenvolvedora e Documentação
  ***João Vitor Ruggi Seara** – Especialista Backend e Integração 
  * **ISABELLE Benegas MARIANO** – Designer de UI/UX 
  * **Flávio Costa da Silva** – Analista de Requisitos 

---

## 8. Licença

Este projeto é educacional. Dê os devidos créditos à equipe ao utilizá-lo.

[➡️ Acessar o Repositório no GitHub](https://github.com/jeffersonjaily/A3-PROGRAMA--O-DE-SOLU--ES-COMPUTACIONAIS)


