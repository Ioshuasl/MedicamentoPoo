package com.example.projetomedicamento.Repository;

import com.example.projetomedicamento.Model.FornecedorModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FornecedorRepository {
    private final String caminhoCSV = "fornecedores.csv"; // Caminho do arquivo CSV
    private List<FornecedorModel> fornecedores = new ArrayList<>();

    public FornecedorRepository() {
        carregarCSV();
    }

    public FornecedorModel buscarPorCnpj(String cnpj) {
        return fornecedores.stream()
                .filter(f -> f.getCnpj().equals(cnpj))
                .findFirst()
                .orElse(null);
    }

    public void adicionarFornecedor(FornecedorModel fornecedor) {
        fornecedores.add(fornecedor);
        salvarCSV();
    }

    public void removerFornecedor(String cnpj) {
        fornecedores.removeIf(f -> f.getCnpj().equals(cnpj));
        salvarCSV();
    }

    public List<FornecedorModel> listarTodos() {
        return Collections.unmodifiableList(fornecedores);
    }

    private void carregarCSV() {
        if (!Files.exists(Paths.get(caminhoCSV))) return;

        fornecedores.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");

                FornecedorModel fornecedor = new FornecedorModel(
                        campos[0], // cnpj
                        campos[1], // razaoSocial
                        campos[2], // telefone
                        campos[3], // email
                        campos[4], // cidade
                        campos[5]  // estado
                );
                fornecedores.add(fornecedor);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }

    public void salvarCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCSV))) {
            for (FornecedorModel f : fornecedores) {
                writer.write(toCSV(f));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }

    private String toCSV(FornecedorModel fornecedor) {
        return String.join(";",
                fornecedor.getCnpj(),
                fornecedor.getRazaoSocial(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getCidade(),
                fornecedor.getEstado()
        );
    }
}