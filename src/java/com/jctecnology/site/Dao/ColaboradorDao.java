/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Dao;

import com.jctecnology.site.ConnectionBD.ConnectionMySql;
import com.jctecnology.site.Model.Colaborador;
import com.jctecnology.site.Model.Empresa;
import com.jctecnology.site.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ColaboradorDao {
    Colaborador colab;
    Empresa emp;
    Usuario user;
    
    public List<Colaborador> findAll() throws SQLException {
        String sql = "SELECT * FROM Colaborador";
        List<Colaborador> resul = new ArrayList<>();

        try (Connection conn = ConnectionMySql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                colab.setId(rs.getInt("colab_id"));
                colab.setNome(rs.getString("colab_nome"));
                colab.setCpf(rs.getString("colab_cpf"));
                colab.setEmail(rs.getString("colab_email"));
                colab.setTelefone(rs.getString("colab_telefone"));
                resul.add(colab);
            }
        }
        return resul;
    }
     
    public Colaborador procurarColaborador(Colaborador colab, String login) throws SQLException {
        String sql = "SELECT colab_id, colab_nome, colab_cpf, colab_email, colab_telefone FROM Colaborador, Empresa, Usuario WHERE usu_login=? and emp_id=colab_emp_id and usu_id=colab_usu_id; ";
        Connection conn = null;
        
        try {
            conn = ConnectionMySql.obterConexao();
            conn.setAutoCommit(false);      
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);            
            ResultSet rs = stmt.executeQuery();
            
                if (rs.next()) {
                colab.setId(rs.getInt("colab_id"));
                colab.setNome(rs.getString("colab_nome"));
                colab.setCpf(rs.getString("colab_cpf"));
                colab.setEmail(rs.getString("colab_email"));
                colab.setTelefone(rs.getString("colab_telefone"));
                }
            }catch (SQLException e) {
                conn.rollback();
            }
        return colab;
    }
   
    public void inserirEmpresa(Colaborador colab,Empresa emp, Usuario user) throws SQLException {
        String sql = "INSERT INTO Colaborador ( colab_nome, colab_cpf, colab_email, colab_telefone, colab_emp_id, colab_usu_id) VALUES (?,?,?,?,?,?)";
        Connection conn = null;
        try  {
            conn = ConnectionMySql.obterConexao();
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            PreparedStatement stmt =   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, colab.getNome());
            stmt.setString(2, colab.getCpf());
            stmt.setString(3, colab.getEmail());
            stmt.setString(4, colab.getTelefone());
            stmt.setInt(5, emp.getId());
            stmt.setInt(6, user.getId());
            boolean resul = stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys(); // RECUPERA O ID GERADO PARA O INFO NOVO
            while (rs.next()) {
                Integer idGerado = rs.getInt(1);
                colab.setId(idGerado);
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            
        }
    }
    
    public void atualizarColaborador(Colaborador colab) throws SQLException {        
        String sql = "UPDATE Colaborador set colab_nome=?, colab_cpf=?, colab_email=?, colab_telefone=? where colab_id=?";
        try (Connection conn = ConnectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, colab.getNome());
                stmt.setString(2, colab.getCpf());
                stmt.setString(3, colab.getEmail());
                stmt.setString(4, colab.getTelefone());
                stmt.setString(5, String.valueOf(colab.getId()));
                
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(3);
                        emp.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
     public void deletarColaborador(Colaborador colab) throws SQLException {
        String sql = "DELETE * FROM Colaborador WHERE colab_id=?";
        try (Connection conn = ConnectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(colab.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        colab.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }    
}
