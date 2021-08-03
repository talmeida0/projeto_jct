/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Dao;



import com.jctecnology.site.ConnectionBD.ConnectionMySql;
import com.jctecnology.site.Model.Empresa;
import com.jctecnology.site.Model.Orcamento;
import com.jctecnology.site.Model.Usuario;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrcamentoDao {
    
    Orcamento orcamento;
    Empresa emp;
    ConnectionMySql connectionMySql = new ConnectionMySql();

    public List<Orcamento> findAll() throws SQLException {
        String sql = "SELECT * FROM Orcamento";
        List<Orcamento> resul = new ArrayList<>();

        try (Connection conn = connectionMySql.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orcamento.setId(rs.getInt("orc_id"));
                orcamento.setNumFuncionarios(rs.getInt("orc_numFuncionario"));
                orcamento.setObservacoes(rs.getString("orc_observacoes"));
                orcamento.setPlano(rs.getString("orc_plano"));
                emp.setId(rs.getInt("orc_emp_id"));
                orcamento.setEmpresa(emp);
                resul.add(orcamento);
            }
        }
        return resul;
    }
     
    public Orcamento procurarOrcamento(Orcamento orcamento) throws SQLException {
        String sql = "SELECT * FROM Orcamento WHERE orc_emp_id=?;";
        Connection conn = null;
        
        try {
            conn = connectionMySql.obterConexao();
            conn.setAutoCommit(false);      
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orcamento.getEmpresa().getId());
            ResultSet rs = stmt.executeQuery();
            
                if (rs.next()) {
                    orcamento.setId(rs.getInt("orc_id"));
                    orcamento.setNumFuncionarios(rs.getInt("orc_numFuncionarios"));
                    orcamento.setObservacoes(rs.getString("orc_observacoes"));
                    orcamento.setPlano(rs.getString("orc_plano"));
                    orcamento.getEmpresa().setId(rs.getInt("orc_emp_id"));
                }
            }catch (SQLException e) {
                conn.rollback();
            }
        return orcamento;
    }
   
    public void inserirOrcamento(Orcamento orcamento) throws SQLException {
        String sql = "INSERT INTO Orcamento ( orc_numFuncionarios, orc_observacoes, orc_plano, orc_emp_id) VALUES (?,?,?,?)";
        Connection conn = null;
        try  {
            conn = connectionMySql.obterConexao();
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            PreparedStatement stmt =   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, orcamento.getNumFuncionarios());
            stmt.setString(2, orcamento.getObservacoes());
            stmt.setString(3, orcamento.getPlano());
            stmt.setInt(4, orcamento.getEmpresa().getId());
            boolean resul = stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys(); // RECUPERA O ID GERADO PARA O INFO NOVO
            while (rs.next()) {
                Integer idGerado = rs.getInt(1);
                orcamento.setId(idGerado);
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            
        }
    }
    
    public void atualizarOrcamento(Orcamento orcamento) throws SQLException {
        String sql = "UPDATE Empresa set orc_numFuncionarios=?, orc_observacoes=?, orc_plano=?, orc_emp_id=?, where orc_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, orcamento.getNumFuncionarios());
                stmt.setString(2, orcamento.getObservacoes());
                stmt.setString(3, orcamento.getPlano());
                stmt.setInt(4, orcamento.getEmpresa().getId());
                stmt.setInt(5, orcamento.getId());
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(3);
                        orcamento.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
     public void deletarOrcamento(Orcamento orcamento) throws SQLException {
        String sql = "DELETE * FROM Orcamento WHERE orc_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(orcamento.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        orcamento.setId(idGerado);
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
