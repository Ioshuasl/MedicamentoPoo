package com.example.projetomedicamento.Controller;

import com.example.projetomedicamento.Model.FornecedorModel;
import com.example.projetomedicamento.Model.MedicamentoModel;
import com.example.projetomedicamento.Repository.FornecedorRepository;
import com.example.projetomedicamento.Repository.MedicamentoRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MedicamentoController {

    @FXML
    private CheckBox booleanControlado;

    @FXML
    private ComboBox<String> comboBoxMedicamento;

    @FXML
    private ListView<FornecedorModel> listFornecedores;

    @FXML
    private ListView<MedicamentoModel> listMedicamentos;

    @FXML
    private TextField txtCidadeFornecedor;

    @FXML
    private TextField txtCnpjFornecedor;

    @FXML
    private TextField txtCodigoMedicamento;

    @FXML
    private DatePicker txtDataValidade;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtDescMedicamento;

    @FXML
    private TextField txtEmailFornecedor;

    @FXML
    private TextField txtEstadoFornecedor;

    @FXML
    private TextField txtNomeMedicamento;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtPrincipioAtivo;

    @FXML
    private TextField txtRazaoSocial;

    @FXML
    private TextField txtTelefoneFornecedor;

    @FXML
    private TextField txtbuscaCnpjFornecedor;

    private FornecedorRepository fornecedorRepository = new FornecedorRepository();
    private MedicamentoRepository medicamentoRepository = new MedicamentoRepository();

    @FXML
    private void initialize(){
        atualizarListaFornecedores();
        atualizarListaMedicamentos();
        comboBoxMedicamento.getItems().addAll("Todos", "Controlados", "Não Controlados");
    }

    @FXML
    void cadastrarFornecedor(ActionEvent event) {
        String razaoSocial = txtRazaoSocial.getText();
        String cnpj = txtCnpjFornecedor.getText();
        String telefone = txtTelefoneFornecedor.getText();
        String email = txtEmailFornecedor.getText();
        String cidade = txtCidadeFornecedor.getText();
        String estado = txtEstadoFornecedor.getText();

        if (razaoSocial.isEmpty() || cnpj.isEmpty() || telefone.isEmpty() || email.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Todos os campos são obrigatórios!", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null); // Sem cabeçalho
            alert.showAndWait();
            return;
        }

        if (cnpj.length() != 14) {
            Alert alert = new Alert(AlertType.WARNING, "CNPJ Inválido. O CNPJ deve ter 14 dígitos.", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        FornecedorModel fornecedor = new FornecedorModel(cnpj, razaoSocial, telefone, email, cidade, estado);

        fornecedorRepository.adicionarFornecedor(fornecedor);

        atualizarListaFornecedores();

        limparCamposFornecedor();
        System.out.println("Fornecedor cadastrado com sucesso");
    }

    @FXML
    void cadastrarMedicamento(ActionEvent event) {
        String codigo = txtCodigoMedicamento.getText();
        String nome = txtNomeMedicamento.getText();
        String descricao = txtDescMedicamento.getText();
        String principioAtivo = txtPrincipioAtivo.getText();
        LocalDate dataValidade = txtDataValidade.getValue();
        String quantidadeEstoque = txtQuantidade.getText().trim();
        BigDecimal preco = new BigDecimal(txtPreco.getText());
        boolean controlado = booleanControlado.isSelected();
        String fornecedor = txtbuscaCnpjFornecedor.getText();

        if (codigo.isEmpty() || nome.isEmpty() || descricao.isEmpty() || principioAtivo.isEmpty() || dataValidade == null || preco.toString().isEmpty() || quantidadeEstoque.toString().isEmpty() || fornecedor.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Todos os campos são obrigatórios!", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        if (!codigo.matches("^[a-zA-Z0-9]{7}$")) {
            Alert alert = new Alert(AlertType.WARNING, "O código deve ter exatamente 7 caracteres alfanuméricos.", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        int quantidade = Integer.parseInt(quantidadeEstoque);

        try {
            preco = new BigDecimal(preco.toString().trim());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING, "Preço inválido. Por favor, insira um valor numérico.", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        FornecedorModel verificarfornecedor = fornecedorRepository.buscarPorCnpj(fornecedor);

        if (verificarfornecedor == null){
            Alert alert = new Alert(AlertType.WARNING, "Fornecedor não encontrado", ButtonType.OK);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        } else {
            System.out.println(verificarfornecedor.getRazaoSocial());
        }

        // Criar um novo medicamento
        MedicamentoModel medicamento = new MedicamentoModel(codigo, nome, descricao, principioAtivo, dataValidade, quantidade, preco, controlado, fornecedor);

        // Adicionar o medicamento ao repositório
        medicamentoRepository.adicionarMedicamento(medicamento);

        // Atualizar a lista de medicamentos
        atualizarListaMedicamentos();

        // Limpar campos
        limparCamposMedicamento();
        System.out.println("Medicamento cadastrado com sucesso");
    }

    @FXML
    void cancelarCadastroFornecedor(ActionEvent event) {
        limparCamposFornecedor();
    }

    @FXML
    void cancelarCadastroMedicamento(ActionEvent event) {
        limparCamposMedicamento();
    }

    @FXML
    public void filtrarEstoqueBaixo(ActionEvent event) {
        List<MedicamentoModel> medicamentosFiltrados;
        medicamentosFiltrados= medicamentoRepository.filtrarPorEstoqueBaixo();

        listMedicamentos.getItems().clear();
        listMedicamentos.getItems().addAll(medicamentosFiltrados);
        System.out.println("Filtrando medicamentos que estão com estoque abaixo a 5");
    }

    @FXML
    void excluirFornecedor(ActionEvent event) {
        FornecedorModel fornecedorSelecionado = listFornecedores.getSelectionModel().getSelectedItem();

        if (fornecedorSelecionado != null) {
            fornecedorRepository.removerFornecedor(fornecedorSelecionado.getCnpj());
            System.out.println("Fornecedor excluído com sucesso");
            atualizarListaFornecedores();
        } else {
            // Exibir uma mensagem de erro ou aviso se nenhum fornecedor estiver selecionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum fornecedor selecionado para exclusão.");
            alert.showAndWait();
        }
    }

    @FXML
    void excluirMedicamento(ActionEvent event) {
        MedicamentoModel medicamentoSelecionado = listMedicamentos.getSelectionModel().getSelectedItem();

        if (medicamentoSelecionado != null) {
            medicamentoRepository.removerMedicamento(medicamentoSelecionado.getCodigo());
            System.out.println("Medicamento excluído com sucesso");
            atualizarListaMedicamentos();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum medicamento selecionado para exclusão.");
            alert.showAndWait();
        }
    }

    @FXML
    void comboBoxMedicamentoListado(ActionEvent event) {
        String filtroSelecionado = comboBoxMedicamento.getValue();
        List<MedicamentoModel> medicamentosFiltrados;

        // Filtrar medicamentos com base na seleção
        switch (filtroSelecionado) {
            case "Controlados":
                medicamentosFiltrados = medicamentoRepository.filtrarPorControlado(true);
                System.out.println("Filtrando todos os medicamentos controlados");
                break;
            case "Não Controlados":
                medicamentosFiltrados = medicamentoRepository.filtrarPorControlado(false);
                System.out.println("Filtrando todos os medicamentos não controlados");
                break;
            case "Todos":
            default:
                medicamentosFiltrados = medicamentoRepository.listarTodos();
                System.out.println("Todos os medicamentos");
                break;
        }

        // Atualiza a ListView com os medicamentos filtrados
        listMedicamentos.getItems().clear();
        listMedicamentos.getItems().addAll(medicamentosFiltrados);
    }

    @FXML
    void filtrarVencimento(ActionEvent event) {
            List<MedicamentoModel> medicamentosFiltrados;
            medicamentosFiltrados= medicamentoRepository.filtrarPorVencimentoProximo();

            listMedicamentos.getItems().clear();
            listMedicamentos.getItems().addAll(medicamentosFiltrados);
            System.out.println("Filtrando medicamentos que estão com vencimento próximo a 30 dias");
    }

    private void atualizarListaFornecedores() {
        List<FornecedorModel> fornecedores = fornecedorRepository.listarTodos();
        listFornecedores.getItems().clear();
        listFornecedores.getItems().addAll(fornecedores);
    }

    private void atualizarListaMedicamentos() {
        List<MedicamentoModel> medicamentos = medicamentoRepository.listarTodos();
        listMedicamentos.getItems().clear();
        listMedicamentos.getItems().addAll(medicamentos);
    }

    private void limparCamposFornecedor() {
        txtRazaoSocial.clear();
        txtCnpjFornecedor.clear();
        txtTelefoneFornecedor.clear();
        txtEmailFornecedor.clear();
        txtCidadeFornecedor.clear();
        txtEstadoFornecedor.clear();
    }

    private void limparCamposMedicamento() {
        txtCodigoMedicamento.clear();
        txtNomeMedicamento.clear();
        txtDescMedicamento.clear();
        txtPrincipioAtivo.clear();
        txtPreco.clear();
        txtQuantidade.clear();
        booleanControlado.setSelected(false);
        txtDataValidade.setValue(null);
        txtbuscaCnpjFornecedor.clear(); // Limpar o campo de busca de CNPJ
    }
}