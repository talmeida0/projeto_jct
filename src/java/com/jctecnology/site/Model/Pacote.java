/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Pacote {
    String Data, Status;
    Float Preco;
    Integer Id;
    Servico Servico;
    Empresa Empresa;

    public Pacote(String Data, String Status, Float Preco, Integer Id, Servico Servico, Empresa Empresa) {
        this.Data = Data;
        this.Status = Status;
        this.Preco = Preco;
        this.Id = Id;
        this.Servico = Servico;
        this.Empresa = Empresa;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Float getPreco() {
        return Preco;
    }

    public void setPreco(Float Preco) {
        this.Preco = Preco;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Servico getServico() {
        return Servico;
    }

    public void setServico(Servico Servico) {
        this.Servico = Servico;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }
    
    
}
