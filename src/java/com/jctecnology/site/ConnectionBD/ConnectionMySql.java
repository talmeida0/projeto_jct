package com.jctecnology.site.ConnectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionMySql {
    public static Connection obterConexao() throws SQLException {
        // 1) Declarar o driver JDBC de acordo com o Banco de dados usado
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        
        //String url ="jdbc:mysql://jc_tech";

        //Connection conn = DriverManager.getConnection(url, "root", "");
        
        // 2) Abrir a conexão

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/jc_tech?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC",
                "root", // Usuário de conexão no BD
                ""); // Senha

        return conn ;
    }
}
