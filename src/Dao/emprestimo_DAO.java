/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Util.Conexao_DAO;
import model.Emprestimo;
import java.sql.*;
import java.time.LocalDate;
import model.Livro;

/**
 *
 * @author Higor
 */
public class emprestimo_DAO {

    public void realizarEmprestimo(int id_aluno, int id_livro) {
        //Conectando com o Banco de Dados
        try ( Connection con = Conexao_DAO.conexao_BD()) {

            //Buscar o Aluno
            String buscar_Aluno = "SELECT * FROM alunos WHERE id =?";
            try ( PreparedStatement dec = con.prepareStatement(buscar_Aluno)) {
                dec.setInt(1, id_aluno);
                ResultSet res = dec.executeQuery();
                if (!res.next()) {
                    System.out.println("Aluno não cadastrado!!!");
                    return;
                }
            }

            //Buscar Livro
            String buscar_Livro = "SELECT * FROM livros WHERE id = ?";
            try ( PreparedStatement dec = con.prepareStatement(buscar_Livro)) {

                dec.setInt(1, id_livro);
                ResultSet res = dec.executeQuery();
                if (res.next()) {
                    Livro livro = new Livro(
                            res.getInt("id"),
                            res.getString("titulo"),
                            res.getString("autor"),
                            res.getInt("quantidade"),
                            res.getInt("ano")
                    );
                } else {
                    System.out.println("Livro não encontrado!!!");
                    return;
                }
            }

            //Verificar se tem Estoque
            String verificar_Estoque = "SELECT quantidade FROM livros WHERE id = ?";
            int quantidade = 0;
            try ( PreparedStatement dec = con.prepareStatement(verificar_Estoque)) {

                dec.setInt(1, id_livro);
                ResultSet res = dec.executeQuery();
                if (res.next()) {
                    quantidade = res.getInt("quantidade");
                    System.out.println("Quantidade disponível: " + quantidade);
                }
            }
            if (quantidade <= 0) {
                System.out.println("Estoque indisponível!!!");
                return;
            }

            //Atualizar estoque
            String atualizar_Estoque = "UPDATE livros SET quantidade = quantidade - 1 WHERE id = ? ";
            try ( PreparedStatement dec = con.prepareStatement(atualizar_Estoque)) {

                dec.setInt(1, id_livro);
                dec.executeUpdate();
            }

            //Realizar emprestimo
            String emprestimo_Livro = "INSERT INTO emprestimos (aluno_id,livro_id, data_devolucao) VALUES ( ?,?,?)";
            try ( PreparedStatement dec = con.prepareStatement(emprestimo_Livro)) {
                dec.setInt(1, id_aluno);
                dec.setInt(2, id_livro);
                dec.setDate(3, java.sql.Date.valueOf(LocalDate.now().plusDays(7)));
                dec.executeUpdate();

                System.out.println("Empréstimo realizado com sucesso");
            }
        } catch (SQLException e) {
            System.out.println("Erro o processo de empréstimo: " + e.getMessage());
        }

    }

    public void listarLivroComEmprestimo() {
        //Listar os Livros que estão em situação de empréstimo
        String listar = "SELECT e.id AS ID_EMPRESTIMO, e.aluno_id AS ID_DO_ALUNO, e.livro_id AS ID_DO_LIVRO, "
                + "l.titulo AS TITULO, e.data_emprestimos AS DATA_EMPRESTIMO,\n"
                + "e.data_devolucao AS DATA_DEVOLUCAO, a.nome AS NOME_ALUNO, a.matricula AS MATRICULA_ALUNO\n"
                + " FROM emprestimos e JOIN livros l ON e.livro_id = l.id JOIN alunos a ON e.aluno_id = a.id";

        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(listar)) {

            ResultSet res = dec.executeQuery();
            while (res.next()) {
                int emprestimoId = res.getInt("ID_EMPRESTIMO");
                int alunoId = res.getInt("ID_DO_ALUNO");
                int livroId = res.getInt("ID_DO_LIVRO");
                String nomeLivro = res.getString("TITULO");
                Date dataEmprestimo = res.getDate("DATA_EMPRESTIMO");
                String nomeAluno = res.getString("NOME_ALUNO");
                int matriculaAluno = res.getInt("MATRICULA_ALUNO");

                //Printar na tela as Informações
                System.out.printf("| %-13s | %-11s | %-11s | %-40s | %-18s | %-25s | %-15s |\n",
                        emprestimoId, alunoId, livroId, nomeLivro, dataEmprestimo, nomeAluno, matriculaAluno);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro no Processo da Listagem de empréstimo de Livros: " + e.getMessage());
        }
    }

    //Devolução de Livro Emprestado
    public void devolucaoLivro(int id) {
        try ( Connection con = Conexao_DAO.conexao_BD()) {
            //Variável para realizar a devolução
            String devolucao_Livro = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
            try ( PreparedStatement dec = con.prepareStatement(devolucao_Livro)) {

                dec.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                dec.setInt(2, id);
                dec.executeUpdate();
                System.out.println("Devolução do Livro com ID: " + id + " registrada com Sucesso!!");
                
            }
            //Variável para atualizar estoque
            String atualizar_Estoque = "UPDATE livros SET quantidade = quantidade + 1 WHERE id = ? ";
            try(PreparedStatement dec = con.prepareStatement(atualizar_Estoque)){
                
                dec.setInt(1, id);
                dec.executeUpdate();
                System.out.println("Estoque Atualizado!!!");
            }
            
            
        } catch (Exception e) {
            System.out.println("Erro no processo de Devolução: " + e.getMessage());
        }
    }
    //Listar livro com Devolução
    public void listarLivroComDevolucao(){
        //Variável para Listar Livros com Devolução
        String livro_Devolucao = "SELECT e.id AS ID_EMPRESTIMO, e.data_emprestimos AS DATA_EMPRESTIMO, e.data_devolucao AS DATA_DEVOLUCAO,"
                + " l.titulo AS TITULO, a.nome AS NOME, a.matricula AS MATRICULA  FROM emprestimos e JOIN alunos a "
                + "ON e.aluno_id = a.id JOIN livros l ON e.livro_id = l.id  WHERE e.data_devolucao IS NOT NULL;";
        
        try(Connection con = Conexao_DAO.conexao_BD();
            PreparedStatement dec = con.prepareStatement(livro_Devolucao)) {
            ResultSet res = dec.executeQuery();
            while(res.next()){
                int idEmprestimo = res.getInt("ID_EMPRESTIMO");
                Date dataDevolucao = res.getDate("DATA_DEVOLUCAO");
                String titulo = res.getString("TITULO");
                String nome = res.getString("NOME");
                String matricula = res.getString("MATRICULA");
                
                System.out.printf("| %-13s | %-18s | %-30s | %-25s | %-16s |\n",
                                idEmprestimo, dataDevolucao, titulo, nome, matricula);
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------|");
            }
         
        } catch (Exception e) {
            System.out.println("Erro na Listagem de Devolução: " + e.getMessage());
        }
    }
}
