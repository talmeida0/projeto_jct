/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Chamado {
    String Motivo, Descricao, Data, Status;
    Integer Id;
    Administrador Admin;
    Colaborador Colaborador;

    public Chamado(String Motivo, String Descricao, String Data, String Status, Integer Id, Administrador Admin, Colaborador Colaborador) {
        this.Motivo = Motivo;
        this.Descricao = Descricao;
        this.Data = Data;
        this.Status = Status;
        this.Id = Id;
        this.Admin = Admin;
        this.Colaborador = Colaborador;
    }
    
    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Administrador getAdmin() {
        return Admin;
    }

    public void setAdmin(Administrador Admin) {
        this.Admin = Admin;
    }

    public Colaborador getColaborador() {
        return Colaborador;
    }

    public void setColaborador(Colaborador Colaborador) {
        this.Colaborador = Colaborador;
    }
    
    
}
