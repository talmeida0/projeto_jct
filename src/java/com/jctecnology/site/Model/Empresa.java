/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Empresa {
    String Nome, Razao, Cnpj, Status, Email, Telefone, Cargo;
    Integer Id;
    Usuario User;

    public Empresa(String Nome, String Razao, String Cnpj, String Status, String Email, String Telefone, String Cargo) {
        this.Nome = Nome;
        this.Razao = Razao;
        this.Cnpj = Cnpj;
        this.Status = Status;
        this.Email = Email;
        this.Telefone = Telefone;
        this.Cargo = Cargo;
    }

    public Empresa(String Nome, String Razao, String Cnpj, String Status, String Email, String Telefone, String Cargo, Integer Id, Usuario User) {
        this.Nome = Nome;
        this.Razao = Razao;
        this.Cnpj = Cnpj;
        this.Status = Status;        
        this.Email = Email;
        this.Telefone = Telefone;
        this.Cargo = Cargo;
        this.Id = Id;
        this.User = User;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getRazao() {
        return Razao;
    }

    public void setRazao(String Razao) {
        this.Razao = Razao;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
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
