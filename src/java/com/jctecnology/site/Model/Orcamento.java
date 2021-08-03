/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;


public class Orcamento {
        String plano, observacoes;
        Integer id, numFuncionarios;
        Empresa empresa;

    public Orcamento() {
    }

    public Orcamento(String plano, String observacoes, Integer numFuncionarios, Empresa empresa) {
        this.plano = plano;
        this.observacoes = observacoes;
        this.numFuncionarios = numFuncionarios;
        this.empresa = empresa;
    }

    public Orcamento(Integer id, String plano, String observacoes, Integer numFuncionarios, Empresa empresa) {
        this.id = id;
        this.plano = plano;
        this.observacoes = observacoes;
        this.numFuncionarios = numFuncionarios;
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(Integer numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
