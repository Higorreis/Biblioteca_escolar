/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import Util.Conexao_DAO;
import model.Livro;

/**
 *
 * @author User
 */
public class livro_DAO {

    //Adicionando Livro
    public void adicionarLivro(Livro livro) {
        String adicionar_Livros = "INSERT INTO livros (titulo, autor, ano, quantidade) VALUES(?,?,?,?)";

        //Conectando com o banco
        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(adicionar_Livros)) {

            dec.setString(1, livro.getTitulo());
            dec.setString(2, livro.getAutor());
            dec.setInt(3, livro.getAno());
            dec.setInt(4, livro.getQuantidade());
            dec.executeUpdate();

            System.out.println("Livro " + livro.getTitulo() + " inserido com Sucesso!!");

        } catch (SQLException e) {
            System.out.println("Erro no Cadastro do livro: " + e.getMessage());
        }
    }

    //Listar Livro
    public void listarLivro() {
        String listar_Livros = "SELECT l.id as ID_LIVRO, l.titulo as TITULO, l.autor AS AUTOR, l.quantidade AS QUANTIDADE FROM livros l";

        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareCall(listar_Livros)) {

            ResultSet res = dec.executeQuery();
            while (res.next()) {
                int id_livro = res.getInt("ID_LIVRO");
                String titulo = res.getString("TITULO");
                String autor = res.getString("AUTOR");
                int quantidade = res.getInt("QUANTIDADE");

                //Listando os Livros
                System.out.printf("| %-8s | %-35s | %-25s | %-10s |\n",
                        id_livro, titulo, autor, quantidade);
                System.out.println("----------------------------------------------------------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Erro na Listagem de Livros: " + e.getMessage());
        }
    }

    //Atualizar Livro
    public void atualizarLivro(Livro livro) {
        String atualizar_Livros = "UPDATE livros SET  titulo = ?, autor = ?, ano = ?, quantidade = ? WHERE id = ?";

        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(atualizar_Livros)) {

            dec.setString(1, livro.getTitulo());
            dec.setString(2, livro.getAutor());
            dec.setInt(3, livro.getAno());
            dec.setInt(4, livro.getQuantidade());
            dec.setInt(5, livro.getId());

            int banco = dec.executeUpdate();
            if (banco > 0) {
                System.out.println("Livro " + livro.getTitulo() + " atualizado com Sucesso!!");
            } else {
                System.out.println("Livro não encontrado ");
            }
        } catch (SQLException e) {
            System.out.println("Erro no processo de Atualizar o Livro:  " + e.getMessage());
        }
    }

    //Excluir Livro
    public void excluirLivro(int id) {
        String removerLivro = "DELETE FROM livros WHERE id = ?";
        String verificarEmprestimo = "SELECT * FROM emprestimos WHERE livro_id = ?";
        //Verificar se tem livro em situação de Empréstimo
        try ( Connection con = Conexao_DAO.conexao_BD()) {
            try ( PreparedStatement dec = con.prepareStatement(verificarEmprestimo)) {

                dec.setInt(1, id);
                ResultSet res = dec.executeQuery();
                //Condição para verificar 
                if (res.next()) {
                    System.out.println("Livro não poderá ser excluido, pois está em situação de Empréstimo.");
                    return;
                }
                //Verificar se poderá exclui
            }
            try ( PreparedStatement dec = con.prepareStatement(removerLivro)) {

                dec.setInt(1, id);
                int res = dec.executeUpdate();
                if (res > 0) {
                    System.out.println("Livro excluído com Sucesso!!!");
                } else {
                    System.out.println("Livro não encontrado!!!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na Exclusão do Livro: " + e.getMessage());
        }
    }

    //Buscar Livro
    public void buscarLivro(int id) {
        String banco_SQL = "SELECT * FROM livros WHERE id = ?";

        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(banco_SQL)) {

            dec.setInt(1, id);
            ResultSet res = dec.executeQuery();
            if (res.next()) {
                Livro buscar_Livro = new Livro(
                        res.getInt("id"),
                        res.getString("titulo"),
                        res.getString("autor"),
                        res.getInt("quantidade"),
                        res.getInt("ano")
                );
            }

        } catch (SQLException e) {
            System.out.println("Livro não encontrado: " + e.getMessage());
        }

    }

    //Verificar Estoque
    public void verificarEstoque(int id) {
        String banco_SQL = "SELECT quantidade FROM livros WHERE id = ?";
        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(banco_SQL)) {

            dec.setInt(1, id);
            ResultSet res = dec.executeQuery();
            if (res.next()) {
                int quantidade = res.getInt("quantidade");
                System.out.println("Estoque Disponível: " + quantidade);
            } else {
                System.out.println("Estoque Indisponível!!!");
            }
        } catch (SQLException e) {
            System.out.println("Erro no processo de Estoque: " + e.getMessage());
        }
    }

}
