/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Model;

public class Servico {
    String Nome, Descricao, Status;
    Float  Preco;
    Integer Id;

    public Servico(String Nome, String Descricao, Float Preco, String Status, Integer Id) {
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.Preco = Preco;
        this.Status = Status;
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Float getPreco() {
        return Preco;
    }

    public void setPreco(Float Preco) {
        this.Preco = Preco;
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
    
    
    
}
