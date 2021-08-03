package com.jctecnology.site.Dao;

import com.jctecnology.site.ConnectionBD.ConnectionMySql;
import com.jctecnology.site.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao {
    Usuario user;

    ConnectionMySql connectionMySql = new ConnectionMySql();

    public List<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> resul = new ArrayList<>();

        try (Connection conn = connectionMySql.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                user.setId(rs.getInt("usu_id"));
                user.setLogin(rs.getString("usu_usuario"));
                user.setSenha(rs.getString("usu_senha"));
                resul.add(user);
            }
        }
        return resul;
    }
    
    public Usuario procurarUsuario(String login) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE usu_login=?";
        Connection conn = null;
        Usuario user = null;
        try {
            conn = connectionMySql.obterConexao();
            conn.setAutoCommit(false);      
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);            
            ResultSet rs = stmt.executeQuery();


                if (rs.next()) {
                    user = new Usuario(rs.getInt("usu_id"),
                            rs.getString("usu_login"),
                            rs.getString("usu_senha"));
                }
            }catch (SQLException e) {
                conn.rollback();
            }
        return user;
    }
    
    public void inserirUsuario(Usuario user) throws SQLException {
        String sql = "INSERT INTO Usuario (usu_login, usu_senha) VALUES (?,?)";
        Connection conn = null;
        try  {
            conn = connectionMySql.obterConexao();
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);

            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            PreparedStatement stmt =   conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            boolean resul = stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys(); // RECUPERA O ID GERADO PARA O INFO NOVO
            while (rs.next()) {
                Integer idGerado = rs.getInt(1);
                user.setId(idGerado);
            }

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            
        }
    }
    
    public void atualizarUsuario(Usuario user) throws SQLException {
        String sql = "UPDATE Usuario set usu_login=?, usu_senha=? WHERE usu_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, String.valueOf(user.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(3);
                        user.setId(idGerado);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
     public void deletarUsuario(Usuario user) throws SQLException {
        String sql = "DELETE * FROM Usuario WHERE usu_id=?";
        try (Connection conn = connectionMySql.obterConexao()) {
            // DESLIGAR AUTO-COMMIT -> POSSIBILITAR DESFAZER OPERACOES EM CASOS DE ERROS
            conn.setAutoCommit(false);
            // ADICIONAR O Statement.RETURN_GENERATED_KEYS PARA RECUPERAR O ID GERADO NO BD
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, String.valueOf(user.getId()));
                int resul = stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    // RECUPERA O ID GERADO PARA O INFO NOVO
                    while (rs.next()) {
                        Integer idGerado = rs.getInt(1);
                        user.setId(idGerado);
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
