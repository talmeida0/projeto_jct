/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;


public class Administrador {        
        String Nome, Cpf, Telefone, Status;
        Integer Id;
        Usuario User;

    public Administrador(String Nome, String Cpf, String Telefone, String Status, Integer Id, Usuario User) {
        this.Nome = Nome;
        this.Cpf = Cpf;
        this.Telefone = Telefone;
        this.Status = Status;
        this.Id = Id;
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

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
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

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }
        
        
        
}
