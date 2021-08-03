/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctecnology.site.Dao;



import com.jctecnology.site.ConnectionBD.ConnectionMySql;
import com.jctecnology.site.Model.Empresa;
import com.jctecnology.site.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmpresaDao {
    
    Empresa emp;
    Usuario user;
    ConnectionMySql connectionMySql = new ConnectionMySql();

    public List<Empresa> findAll() throws SQLException {
        String sql = "SELECT * FROM Empresa";
        List<Empresa> resul = new ArrayList<>();

        try (Connection conn = connectionMySql.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                emp.setId(rs.getInt("emp_id"));
                emp.setNome(rs.getString("emp_nome"));
                emp.setCnpj(rs.getString("emp_cnpj"));
                emp.setRazao(rs.getString("emp_razao"));
                emp.setStatus(rs.getString("emp_status"));
                emp.setEmail(rs.getString("emp_email"));
                emp.setTelefone(rs.getString("emp_telefone"));
                emp.setCargo(rs.getString("emp_cargo"));
                user.setId(rs.getInt("emp_usu_id"));
                resul.add(emp);
            }
        }
        return resul;
    }
     
    public Empresa procurarEmpresa(Empresa emp, String login) throws SQLException {
        String sql = "SELECT emp_id, emp_nome, emp_cnpj, emp_razao, emp_status, emp_email, emp_telefone, emp_cargo FROM Empresa, Usuario WHERE usu_login=? and usu_id=emp_usu_id; ";
        Connection conn = null;
        
        try {
            conn = connectionMySql.obterConexao();
            conn.setAutoCommit(false);      
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);            
            ResultSet rs = stmt.executeQuery();
            
                if (rs.next()) {
                emp.setId(rs.getInt("emp_id"));
                emp.setNome(rs.getString("emp_nome"));
                emp.setCnpj(rs.getString("emp_cnpj"));
                emp.setRazao(rs.getString("emp_razao"));
                emp.setStatus(rs.getString("emp_status"));
                emp.setEmail(rs.getString("emp_email"));
                emp.setTelefone(rs.getString("emp_telefone"));
                emp.setCargo(rs.getString("emp_cargo"));
                }
            }catch (SQLException e) {
                conn.rollback();
            }
        return emp;
    }
   
    public Empresa inserirEmpresa(Empresa emp, Usuario user) throws SQLException {
        String sql = "INSERT INTO Empresa ( emp_nome, emp_cnpj, emp_razao, emp_status, emp_email, emp_telefone, emp_cargo, emp_usu_id) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try  {
            conn = connectionMySql.obterConexao();
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            PreparedStatement stmt =   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, emp.getNome());
            stmt.setString(2, emp.getCnpj());
            stmt.setString(3, emp.getRazao());
            stmt.setString(4, emp.getStatus());
            stmt.setString(5, emp.getEmail());
            stmt.setString(6, emp.getTelefone());
            stmt.setString(7, emp.getCargo());
            stmt.setInt(8, user.getId());
            boolean resul = stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys(); // RECUPERA O ID GERADO PARA O INFO NOVO
            while (rs.next()) {
                Integer idGerado = rs.getInt(1);
                emp.setId(idGerado);
            }

            conn.commit();
            return emp;
        } catch (SQLException e) {
            conn.rollback();
            return null;
        }
    }
    
    public void atualizarEmpresa(Empresa emp) throws SQLException {        
        String sql = "UPDATE Empresa set emp_nome=?, emp_cnpj=?, emp_razao=?, emp_status=?, emp_email=?, emp_telefone=?, emp_cargo=? where emp_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, emp.getNome());
                stmt.setString(2, emp.getCnpj());
                stmt.setString(3, emp.getRazao());
                stmt.setString(4, emp.getStatus());
                stmt.setString(5, emp.getEmail());
                stmt.setString(6, emp.getTelefone());
                stmt.setString(7, emp.getCargo());
                stmt.setString(8, String.valueOf(emp.getId()));
                
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
     public void deletarEmpresa(Empresa emp) throws SQLException {
        String sql = "DELETE * FROM Empresa WHERE emp_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(emp.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
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
    
}
