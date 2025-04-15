package com.example.projetomedicamento.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class MedicamentoModel {
    private String codigo; // Identificador único do medicamento
    private String nome; // Nome do medicamento
    private String descricao; // Descrição do medicamento
    private String principioAtivo; // Princípio ativo do medicamento
    private LocalDate dataValidade; // Data de validade do medicamento
    private int quantidade; // Quantidade em estoque
    private BigDecimal preco; // Preço do medicamento
    private boolean controlado; // Indica se o medicamento é controlado
    private String fornecedor; // Fornecedor do medicamento

    // Construtor
    public MedicamentoModel(String codigo, String nome, String descricao, String principioAtivo,
                            LocalDate dataValidade, Integer quantidade, BigDecimal preco,
                            boolean controlado, String fornecedor) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.principioAtivo = principioAtivo;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.preco = preco;
        this.controlado = controlado;
        this.fornecedor = fornecedor;
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getQuantidadeEstoque() {
        return quantidade;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidade = quantidadeEstoque;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isControlado() {
        return controlado;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataValidade=" + dataValidade +
                ", quantidadeEstoque=" + quantidade +
                ", preco=" + preco +
                ", controlado=" + controlado +
                ", Fornecedor = "+ fornecedor +
                '}';
    }

    public String toCSV() {
        return String.join(";",
                codigo,
                nome,
                descricao,
                principioAtivo,
                dataValidade.toString(),
                String.valueOf(quantidade),
                preco.toString(),
                String.valueOf(controlado),
                fornecedor != null ? fornecedor.toString() : ""); // Ajuste conforme necessário
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicamentoModel)) return false;
        MedicamentoModel that = (MedicamentoModel) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
