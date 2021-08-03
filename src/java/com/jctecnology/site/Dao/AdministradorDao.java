/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Dao;

import com.jctecnology.site.ConnectionBD.ConnectionMySql;
import com.jctecnology.site.Model.Administrador;
import com.jctecnology.site.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdministradorDao {
    
    Administrador admin;
    
    public List<Administrador> findAll() throws SQLException {
        String sql = "SELECT * FROM Administrador";
        List<Administrador> resul = new ArrayList<>();

        try (Connection conn = ConnectionMySql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                admin.setId(rs.getInt("admin_id"));
                admin.setNome(rs.getString("admin_nome"));
                admin.setNome(rs.getString("admin_cpf"));
                admin.setNome(rs.getString("admin_telefone"));
                admin.setNome(rs.getString("admin_status"));
                admin.setNome(rs.getString("admin_usu_id"));
                resul.add(admin);
            }
        }
        return resul;
    }
    
    public Administrador procurarAdmin(Administrador admin, String login) throws SQLException {
        String sql = "SELECT admin_id, admin_nome, admin_cpf,admin_tel,admin_status FROM Administrador, Usuario WHERE usu_login=? and usu_id=admin_usu_id; ";
        Connection conn = null;
        
        try {
            conn = ConnectionMySql.obterConexao();
            conn.setAutoCommit(false);      
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);            
            ResultSet rs = stmt.executeQuery();
            
                if (rs.next()) {
                admin.setId(rs.getInt("admin_id"));
                admin.setNome(rs.getString("admin_nome"));
                admin.setNome(rs.getString("admin_cpf"));
                admin.setNome(rs.getString("admin_telefone"));
                admin.setNome(rs.getString("admin_status"));
                }
            }catch (SQLException e) {
                conn.rollback();
            }
        return admin;
    }
    
    public void inserirAdmin(Administrador admin, Usuario user) throws SQLException {
        String sql = "INSERT INTO Administrador (admin_nome, admin_cpf,admin_tel,admin_status,admin_usu_id) VALUES (?,?,?,?,?)";
        Connection conn = null;
        try  {
            conn = ConnectionMySql.obterConexao();
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            PreparedStatement stmt =   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getCpf());
            stmt.setString(3, admin.getTelefone());
            stmt.setString(4, admin.getStatus());
            stmt.setInt(5, user.getId());
            boolean resul = stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys(); // RECUPERA O ID GERADO PARA O INFO NOVO
            while (rs.next()) {
                Integer idGerado = rs.getInt(1);
                admin.setId(idGerado);
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            
        }
    }
    
    public void atualizarAdmin(Administrador admin) throws SQLException {        
        String sql = "UPDATE Administrador set admin_nome=?, admin_cpf=?,admin_tel=?,admin_status=? where admin_id=?";
        try (Connection conn = ConnectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, admin.getNome());
                stmt.setString(2, admin.getCpf());
                stmt.setString(3, admin.getTelefone());
                stmt.setString(4, admin.getStatus());
                stmt.setString(5, String.valueOf(admin.getId()));
                
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(3);
                        admin.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
     public void deletarAdmin(Administrador admin) throws SQLException {
        String sql = "DELETE * FROM Administrador WHERE admin_id=?";
        try (Connection conn = ConnectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(admin.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        admin.setId(idGerado);
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
