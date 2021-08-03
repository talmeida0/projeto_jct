/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Usuario {
    String Login, Senha;
    Integer Id;

    public Usuario() {
    }

    public Usuario(String Login, String Senha) {
        this.Login = Login;
        this.Senha = Senha;
    }

    public Usuario(Integer Id, String Login, String Senha) {
        this.Id = Id;
        this.Login = Login;
        this.Senha = Senha;
    }
    
    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    
}
