package com.example.projetomedicamento.Repository;

import com.example.projetomedicamento.Model.MedicamentoModel;
import com.example.projetomedicamento.Model.FornecedorModel;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class MedicamentoRepository {
    private final String caminhoCSV = "funcionarios.csv";
    private List<MedicamentoModel> medicamentos = new ArrayList<>();
    private FornecedorRepository fornecedorRepository; // Instância do repositório de fornecedores

    public MedicamentoRepository(){
        carregarCSV();
    }

    public void adicionarMedicamento(MedicamentoModel m) {
        medicamentos.add(m);
        salvarCSV();
        carregarCSV();
    }

    public void removerMedicamento(String codigo) {
        medicamentos.removeIf( m -> m.getCodigo().equals(codigo));
        salvarCSV();
        carregarCSV();
    }

    public MedicamentoModel buscarPorCodigo(String codigo){
        return medicamentos.stream()
                .filter(m -> m.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<MedicamentoModel> filtrarPorControlado(boolean controlado) {
        carregarCSV();
        return medicamentos.stream()
                .filter(m -> m.isControlado() == controlado)
                .collect(Collectors.toList());
    }

    public List<MedicamentoModel> listarTodos() {
        carregarCSV();
        return Collections.unmodifiableList(medicamentos);
    }

    private void carregarCSV() {
        if (!Files.exists(Paths.get(caminhoCSV))) return;

        medicamentos.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");

                FornecedorModel fornecedor = fornecedorRepository.buscarPorCnpj(campos[8]);

                MedicamentoModel medicamento = new MedicamentoModel(
                        campos[0], // codigo
                        campos[1], // nome
                        campos[2], // descricao
                        campos[3], // principioAtivo
                        LocalDate.parse(campos[4]), // dataValidade
                        Integer.parseInt(campos[5]), // quantidadeEstoque
                        new BigDecimal(campos[6]), // preco
                        Boolean.parseBoolean(campos[7]), // controlado
                        fornecedor // fornecedor
                );
                medicamentos.add(medicamento);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }

    public void salvarCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCSV))) {
            for (MedicamentoModel m : medicamentos) {
                writer.write(m.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }
}
