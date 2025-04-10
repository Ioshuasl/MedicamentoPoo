Sistema de Cadastro de Medicamento
Trabalho aplicado da matéria de Programação Orientada a Objetos III onde tinhamos que desenvolver uma tela no JavaFX que incluia:
- Cadastro de medicamento
- Cadastro de Fornecedor do medicamento
- Listas de medicamentos e fornecedores cadastros na aplicação
- Botões para filtrar determinados medicamentos de acordo com o filtro

Estrutura do Projeto

Em relação a estrutura das pastas tentei manter o mais organizado para facilitar a localização e o entendimento de quem for querer testar a aplicação

src/
├── main/
│   ├── java/
│   │   └── com.exmaple.projetomedicamento/
│   │       ├── Controller/
│   │       │   └── MedicamentoCOntroller.java
│   │       ├── Model/
│   │       │   ├── EnderecoModel.java
│   │       │   └── FuncionarioModel.java
│   │       ├── Repository/
│   │       │   ├── MedicamentoRepository
│   │       │   └── FuncionarioRepository.java 
│   │       └── HelloAplication.java
│   └── resources/
│       └── elieldm.funcionarios.View/
│           └── FuncionarioView.fxml
├── fornecedores.csv
├──medicamentos.csv

Quais as funcionalidades?
Cadastro de Medicamentos e dos fornecedores de medicamentos
Interface feita em JavaFX
Dados persistidos em arquivo .csv
Validações de Código, CNPJ, email, telefone.
Filtros na lista:
- Filtrar Medicamento com data de validade próximo a 30 dias
- Filtrar Medicamento com estoque baixo (abaixo de 5)
- Listas gerados com Stream API


▶️ Como Executar
Clone este repositório:
git clone https://github.com/seu-usuario/medicamentopoo.git
cd sistema-funcionarios-javafx
Abra com sua IDE (IntelliJ no meu caso)
Execute a classe HelloAplication.java
