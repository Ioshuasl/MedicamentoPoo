package com.example.projetomedicamento.Model;

import java.util.Objects;

public class FornecedorModel {
    private String cnpj; // CNPJ do fornecedor
    private String razaoSocial; // Razão social do fornecedor
    private String telefone; // Telefone do fornecedor
    private String email; // Email do fornecedor
    private String cidade; // Cidade do fornecedor
    private String estado; // Estado do fornecedor

    // Construtor
    public FornecedorModel(String cnpj, String razaoSocial, String telefone, String email, String cidade, String estado) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toString() {
        return "Fornecedor{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FornecedorModel)) return false;
        FornecedorModel fornecedor = (FornecedorModel) o;
        return Objects.equals(cnpj, fornecedor.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
