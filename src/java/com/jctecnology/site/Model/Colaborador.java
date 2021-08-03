/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Colaborador {
    String Nome, Cpf, Email, Telefone;
    Integer Id;
    Empresa Empresa;
    Usuario User;

    public Colaborador(String Nome, String Cpf, String Email, String Telefone, Integer Id, Empresa Empresa, Usuario User) {
        this.Nome = Nome;
        this.Cpf = Cpf;
        this.Email = Email;
        this.Telefone = Telefone;
        this.Id = Id;
        this.Empresa = Empresa;
        this.User = User;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }
    
    
}
