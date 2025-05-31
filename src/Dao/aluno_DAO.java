/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import model.Aluno;
import Util.Conexao_DAO;
import java.sql.*;

/**
 *
 * @author Higor
 */
public class aluno_DAO {

    //Criando Function para Adicionar Aluno
    public void adicionarAluno(Aluno aluno) {
        //Com PreparedStatadicionarAlunoement não a necessidade de concatenação 
        String adicionar_Alunos = "INSERT INTO  alunos ( nome, matricula,data_nascimento) VALUES (?,?,?)";

        //Conexão com o BD 
        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(adicionar_Alunos)) {

            dec.setString(1, aluno.getNome());
            dec.setString(2, aluno.getMatricula());
            dec.setDate(3, java.sql.Date.valueOf(aluno.getNas()));
            dec.executeUpdate();

            System.out.println("Aluno " + aluno.getNome() + " inserido com Sucesso!!");

        } catch (SQLException e) {
            System.out.println("Erro no cadastro do Aluno: " + e.getMessage());
        }
    }

    //Listar Alunos
    public void listarAluno() {
        //Variavel para buscar os Alunos cadastrados
        String listar_Alunos = "SELECT a.id as ID_ALUNO, a.nome as NOME , a.matricula as  MATRICULA FROM alunos a";

        //Conexão com o BD
        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(listar_Alunos)) {

            ResultSet res = dec.executeQuery();
            while (res.next()) {
                int id_aluno = res.getInt("ID_ALUNO");
                String nome_Aluno = res.getString("NOME");
                String matricula_Aluno = res.getString("MATRICULA");

                //Mostrando Alunos Cadastrados
                System.out.printf("| %-8s | %-30s | %-10s |\n",
                        id_aluno, nome_Aluno, matricula_Aluno);
                System.out.println("----------------------------------------------------------------------------------------");
            }
            //Caso ocorra um erro entra no Catch
        } catch (SQLException e) {
            System.out.println("Erro na listagem dos Alunos: " + e.getMessage());
        }
    }

    //Atualizar Alunos
    public void atualizarAlunos(Aluno aluno) {
        String atualizar_Alunos = "UPDATE alunos SET nome = ?, matricula = ?, data_nascimento = ? WHERE id = ?";
        try ( Connection con = Conexao_DAO.conexao_BD();  PreparedStatement dec = con.prepareStatement(atualizar_Alunos)) {

            dec.setString(1, aluno.getNome());
            dec.setString(2, aluno.getMatricula());
            dec.setDate(3, java.sql.Date.valueOf(aluno.getNas()));
            dec.setInt(4, aluno.getId());

            int res = dec.executeUpdate();
            if (res > 0) {
                System.out.println("Aluno " + aluno.getNome() + " atualizado com sucesso.");
            } else {
                System.out.println("Aluno não encontrado!!");
            }
        } catch (SQLException e) {
            System.out.println("Erro no processo de atualização do Aluno: " + e.getMessage());
        }
    }

    public void removerAluno(int id) {
        String removerAluno = "DELETE FROM alunos WHERE id = ?";
        String verificarEmprestimo = "SELECT * FROM emprestimos WHERE aluno_id = ?";

        try ( Connection con = Conexao_DAO.conexao_BD()) {
            //Verificar a situação de empréstimo
            try ( PreparedStatement dec = con.prepareStatement(verificarEmprestimo)) {

                dec.setInt(1, id);
                ResultSet res = dec.executeQuery();

                //Condição para analisar se tem livro emprestado
                if (res.next()) {
                    System.out.println("O Aluno(a) não poderá ser removido, pois tem Livro em Situação de empréstimo.");
                    return;
                }
            }
            //Realizar a Remoção
            try ( PreparedStatement dec = con.prepareStatement(removerAluno)) {

                dec.setInt(1, id);
                int res = dec.executeUpdate();

                //Condição para exclui Aluno(a)
                if (res > 0) {
                    System.out.println("Aluno(a) removido com sucesso!!!");
                } else {
                    System.out.println("Aluno(a) não encontrado");
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro no processo de remoção do aluno(a): " + e.getMessage());
        }
    }
}
