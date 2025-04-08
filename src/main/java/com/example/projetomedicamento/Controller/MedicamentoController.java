package com.example.projetomedicamento.Controller;

import com.example.projetomedicamento.Model.FornecedorModel;
import com.example.projetomedicamento.Model.MedicamentoModel;
import com.example.projetomedicamento.Repository.FornecedorRepository;
import com.example.projetomedicamento.Repository.MedicamentoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MedicamentoController {

    @FXML
    private CheckBox booleanControlado;

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
    }

    @FXML
    void cadastrarFornecedor(ActionEvent event) {
        // Coletar dados do fornecedor
        String razaoSocial = txtRazaoSocial.getText();
        String cnpj = txtCnpjFornecedor.getText();
        String telefone = txtTelefoneFornecedor.getText();
        String email = txtEmailFornecedor.getText();
        String cidade = txtCidadeFornecedor.getText();
        String estado = txtEstadoFornecedor.getText();

        // Criar um novo fornecedor
        FornecedorModel fornecedor = new FornecedorModel(cnpj, razaoSocial, telefone, email, cidade, estado);

        // Adicionar o fornecedor ao repositório
        fornecedorRepository.adicionarFornecedor(fornecedor);

        // Atualizar a lista de fornecedores
        atualizarListaFornecedores();

        // Limpar campos
        limparCamposFornecedor();
    }

    @FXML
    void cadastrarMedicamento(ActionEvent event) {
        // Coletar dados do medicamento
        String codigo = txtCodigoMedicamento.getText();
        String nome = txtNomeMedicamento.getText();
        String descricao = txtDescMedicamento.getText();
        String principioAtivo = txtPrincipioAtivo.getText();
        LocalDate dataValidade = txtDataValidade.getValue();
        int quantidadeEstoque = 0; // Defina um valor padrão ou adicione um campo para entrada
        BigDecimal preco = new BigDecimal(txtPreco.getText());
        boolean controlado = booleanControlado.isSelected();

        // Obter o fornecedor selecionado (se necessário)
        FornecedorModel fornecedor = fornecedorRepository.buscarPorCnpj(txtbuscaCnpjFornecedor.getText());

        // Criar um novo medicamento
        MedicamentoModel medicamento = new MedicamentoModel(codigo, nome, descricao, principioAtivo, dataValidade, quantidadeEstoque, preco, controlado, fornecedor);

        // Adicionar o medicamento ao repositório
        medicamentoRepository.adicionarMedicamento(medicamento);

        // Atualizar a lista de medicamentos
        atualizarListaMedicamentos();

        // Limpar campos
        limparCamposMedicamento();
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
    void excluirFornecedor(ActionEvent event) {
        // Obter o fornecedor selecionado na ListView
        FornecedorModel fornecedorSelecionado = listFornecedores.getSelectionModel().getSelectedItem();

        if (fornecedorSelecionado != null) {
            // Remover o fornecedor do repositório
            fornecedorRepository.removerFornecedor(fornecedorSelecionado.getCnpj());

            // Atualizar a lista de fornecedores
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
        // Obter o medicamento selecionado na ListView
        MedicamentoModel medicamentoSelecionado = listMedicamentos.getSelectionModel().getSelectedItem();

        if (medicamentoSelecionado != null) {
            // Remover o medicamento do repositório
            medicamentoRepository.removerMedicamento(medicamentoSelecionado.getCodigo());

            // Atualizar a lista de medicamentos
            atualizarListaMedicamentos();
        } else {
            // Exibir uma mensagem de erro ou aviso se nenhum medicamento estiver selecionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum medicamento selecionado para exclusão.");
            alert.showAndWait();
        }
    }

    @FXML
    void comboBoxMedicamentoListado(ActionEvent event) {
        // Implementar lógica se necessário para lidar com a seleção de medicamentos
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
        booleanControlado.setSelected(false);
        txtDataValidade.setValue(null);
        txtbuscaCnpjFornecedor.clear(); // Limpar o campo de busca de CNPJ
    }
}