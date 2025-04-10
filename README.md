# Sistema de Cadastro de Medicamentos

Este é um projeto desenvolvido para a disciplina de **Programação Orientada a Objetos III**, utilizando **JavaFX**. A proposta consistia em criar uma aplicação gráfica com as seguintes funcionalidades:

## Funcionalidades

- Cadastro de medicamentos
- Cadastro de fornecedores dos medicamentos
- Listagem de medicamentos e fornecedores cadastrados
- Filtros para medicamentos:
  - Medicamentos com data de validade próxima (30 dias)
  - Medicamentos com estoque baixo (menos de 5 unidades)
- Validações:
  - Código do medicamento
  - CNPJ
  - E-mail
  - Telefone
- Persistência de dados em arquivos `.csv`
- Interface construída com JavaFX
- Uso da **Stream API** para geração das listas filtradas

---

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.example.projetomedicamento/
│   │       ├── Controller/
│   │       │   └── MedicamentoController.java
│   │       ├── Model/
│   │       │   ├── EnderecoModel.java
│   │       │   └── FuncionarioModel.java
│   │       ├── Repository/
│   │       │   ├── MedicamentoRepository.java
│   │       │   └── FuncionarioRepository.java 
│   │       └── HelloApplication.java
│   └── resources/
│       └── elieldm.funcionarios.View/
│           └── FuncionarioView.fxml
├── fornecedores.csv
└── medicamentos.csv

```

## Como Executar
- Clone este repositório:
```plaintext
git clone https://github.com/seu-usuario/medicamentopoo.git
```
```plaintext
cd sistema-funcionarios-javafx
```
- Abra com sua IDE (IntelliJ no meu caso)
- Execute a classe HelloAplication.java
