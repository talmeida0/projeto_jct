package com.jctecnology.site.Dto;

public class EmpresaDTO {
    private String nome, razao, cnpj, status, email, telefone, cargo, senha,numFuncionarios, plano,observacoes;

    public EmpresaDTO() {
    }

    public EmpresaDTO(String nome, String razao, String cnpj, String status, String email, String telefone, String cargo, String senha, String numFuncionarios, String plano, String observacoes) {
        this.nome = nome;
        this.razao = razao;
        this.cnpj = cnpj;
        this.status = status;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.senha = senha;
        this.numFuncionarios = numFuncionarios;
        this.plano = plano;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(String numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
