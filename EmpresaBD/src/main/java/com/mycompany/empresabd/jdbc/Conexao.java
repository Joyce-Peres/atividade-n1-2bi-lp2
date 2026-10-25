/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.empresabd.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author 081240037
 */
public class Conexao {
    private static final String URL = "jdbc:mariadb://localhost:3306/empresa"; 
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static Conexao con;
    private Connection connection;
    
    private Conexao() throws SQLException {
        connection = DriverManager.getConnection(URL,USUARIO,SENHA);
    }
    public static Conexao getConexao() throws SQLException{
        if(con ==null){
            con = new Conexao();
        }
        return con;
    }
    public Connection getConnection(){
        return connection;
    }
    public void fechar() throws SQLException{
        if(connection!=null && !connection.isClosed()){
            connection.close();
        }
        con = null;
    }
    
}
