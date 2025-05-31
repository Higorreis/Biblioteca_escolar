/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Higor
 */
public class Conexao_DAO {

    //Criando Conexão com o Banco de Dados
    public static Connection conexao_BD() {
        Connection con = null;
        
        try {
            // Variavel para guardar URL
            String URL = "jdbc:mysql://localhost:3306/biblioteca_db";
            //Variavel para o Usuário
            String usuario = "root";
            //Variavel para a Senha
            String senha = "Grupo_29";

            //Acessando Drive para estabelecer a conexão
            con = DriverManager.getConnection(URL, usuario, senha);

        } catch (SQLException e) {
            //Erro de conexão
            System.out.println("Erro na conexão com o Banco de Dados: " + e.getMessage());
        }
        return con;
    }
}
