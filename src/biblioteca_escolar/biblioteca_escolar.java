/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca_escolar;

import java.util.Scanner;
import Dao.aluno_DAO;
import Dao.livro_DAO;
import Dao.emprestimo_DAO;
import model.Aluno;
import model.Livro;
import model.Emprestimo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Higor
 */
public class biblioteca_escolar {

  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Entrada de informações passada pelo Usuário
        Scanner tec = new Scanner(System.in);
        aluno_DAO aluno = new aluno_DAO();
        livro_DAO livro = new livro_DAO();
        emprestimo_DAO emprestimo = new emprestimo_DAO();
        
        //Inicializando Variável para continuar no Sistema
        String res = null;
        //Criando variavel para entra nas opções
        int opcao;
        //Criando variavel menu_Aluno para Gerenciar Alunos
        int menu_Aluno;
        //Criando variavel menu_Livro para Gerenciar Livros
        int menu_Livro;
        //Condição para o Menu
        do {
            System.out.println("----BIBLIOTECA ESCOLAR APRENDER MAIS------");
            System.out.println("-------------------------------------------");
            System.out.println("--------------MENU DE OPÇÃO----------------");
            System.out.println("1. Gerenciamento de Alunos.");
            System.out.println("2. Gerenciamento de Livros.");
            System.out.println("3. Realizar Emprestimo de Livro.");
            System.out.println("4. Realizar Devolução do Livro.");
            System.out.println("5. Listar de Livros com Emprestimo Realizado.");
            System.out.println("6. Listar de Devolução dos Livros.");
            System.out.println("7. Sair.");
            System.out.print("Digite a sua opção: ");
            opcao = tec.nextInt();
            System.out.println("--------------------------------------------");

            //SWITCH - CASE para entrar nas funções conforme a opção do Usuário
            switch (opcao) {
                case 1:
                    System.out.println("----ALUNOS------");
                    System.out.println("1. Cadastrar ALUNO(A).");
                    System.out.println("2. Atualizar ALUNO(A).");
                    System.out.println("3. Excluir ALUNO(A).");
                    System.out.println("4. Listar ALUNO(A).");
                    System.out.print("Digite a sua opção: ");
                    menu_Aluno = tec.nextInt();

                    System.out.println("-------------------------------------------");
                    switch (menu_Aluno) {
                        case 1:
                            System.out.print("Nome do Aluno(a): ");
                            tec.nextLine();//Para quebrar a Linha
                            String nome = tec.nextLine();
                            System.out.print("Matricula do Aluno(a): ");
                            String mat = tec.nextLine();
                            System.out.print("Data de Nascimento do Aluno(a) - dd/MM/yyyy: ");
                            String nascimento = tec.nextLine();
                            //Formato de Data
                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data_nascimento = null;

                            try {
                                data_nascimento = LocalDate.parse(nascimento, formato);
                            } catch (Exception e) {
                                System.out.println("Erro no Formato da Data: dd/MM/yyyy");
                            }

                            aluno.adicionarAluno(new Aluno(nome, mat, data_nascimento));
                            break;

                        case 2:
                            System.out.print("ID do Aluno que será ATUALIZADO: ");
                            int id = tec.nextInt();
                            tec.nextLine();
                            System.out.print("Atualizar Nome:");
                            String nome_Atualizado = tec.nextLine();
                            System.out.print("Atualizar Matricula:");
                            String mat_Atualizada = tec.nextLine();
                            System.out.print("Atualizar a Data de Nascimento - dd/MM/yyyy: ");
                            String nascimento_Atualizado = tec.nextLine();
                            //Formato de Data
                            DateTimeFormatter formato_Atualizar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data_nascimento_atualizado = null;
                            try {
                                data_nascimento_atualizado = LocalDate.parse(nascimento_Atualizado, formato_Atualizar);
                            } catch (Exception e) {
                                System.out.println("Erro na Atualização da Data de Nascimento: " + e.getMessage());
                            }
                            aluno.atualizarAlunos(new Aluno(id, nome_Atualizado, mat_Atualizada, data_nascimento_atualizado));
                            break;
                        case 3:
                            System.out.print("Id do Aluno que será excluido(a):");
                            int id_Deleta = tec.nextInt();
                            aluno.removerAluno(id_Deleta);
                            break;
                        case 4:
                            System.out.println("-----------------------------------LISTA DE ALUNOS--------------------------------------");
                            System.out.println("----------------------------------------------------------------------------------------");
                            System.out.printf("| %-8s | %-30s | %-10s |\n",
                            "ID_ALUNO", "NOME", "MATRICULA");
                            System.out.println("----------------------------------------------------------------------------------------");
                            aluno.listarAluno();
                            break;
                        default:
                            System.out.print("Opção Invalida!!!");
                            break;

                    }
                    break;
                case 2:
                    System.out.println("----LIVROS----");
                    System.out.println("1. Cadastrar LIVRO");
                    System.out.println("2. Atualizar LIVRO");
                    System.out.println("3. Excluir LIVRO");
                    System.out.println("4. Listar LIVROS");
                    System.out.print("Digite a sua opção: ");
                    menu_Livro = tec.nextInt();
                    tec.nextLine();
                    System.out.println("-------------------------------------------");
                    //SWITCH - CASE para entrar nas funções conforme a opção do Usuário
                    switch (menu_Livro) {
                        case 1:
                            System.out.print("Título do LIVRO: ");
                            String titulo = tec.nextLine();
                            System.out.print("Autor(a) do LIVRO:");
                            String autor = tec.nextLine();
                            System.out.print("Ano de Publicação do LIVRO: ");
                            int ano = tec.nextInt();
                            System.out.print("Quantidade em Estoque: ");
                            int qtd = tec.nextInt();
                            livro.adicionarLivro(new Livro(titulo, autor, ano, qtd));
                            break;

                        case 2:
                            System.out.print("ID do Livro que você quer ATUALIZAR: ");
                            int id = tec.nextInt();
                            tec.nextLine();
                            System.out.print("Atualizar Título: ");
                            String novo_Titulo = tec.nextLine();
                            System.out.print("Atualizar Autor(a): ");
                            String novo_Autor = tec.nextLine();
                            System.out.print("Atualizar ano de Publicação:");
                            int novo_Ano = tec.nextInt();
                            System.out.print("Atualizar quantidade em Estoque: ");
                            int nova_Qtd = tec.nextInt();
                            livro.atualizarLivro(new Livro(id, novo_Titulo, novo_Autor, novo_Ano, nova_Qtd));
                            break;
                        case 3:
                            System.out.print("ID do Livro que você quer EXCLUIR:");
                            int id_Exclui = tec.nextInt();
                            livro.excluirLivro(id_Exclui);
                            break;
                        case 4:
                            System.out.println("-----------------------------------LISTA DE LIVROS--------------------------------------");
                            System.out.println("----------------------------------------------------------------------------------------");
                            System.out.printf("| %-8s | %-35s | %-25s | %-10s |\n",
                            "ID_LIVRO", "TITULO", "AUTOR", "QUANTIDADE");
                            System.out.println("----------------------------------------------------------------------------------------");
                            livro.listarLivro();
                            break;
                    }
                    break;
                case 3:
                    System.out.println("----EMPRESTIMO DO LIVRO----");
                    System.out.print("ID do Aluno(a): ");
                    int id_aluno = tec.nextInt();
                    System.out.print("ID do Livro: ");
                    int id_livro = tec.nextInt();
                    //Realizar o emprestimo
                    emprestimo.realizarEmprestimo(id_aluno, id_livro);
                    break;
                case 4:
                    System.out.println("----DEVOLUÇÃO DO LIVRO----");
                    System.out.print("ID do empréstimo do LIVRO: ");
                    int id_emprestimo = tec.nextInt();
                    emprestimo.devolucaoLivro(id_emprestimo);
                    break;
                case 5:
                    System.out.println("-----------------------------------------------------------LISTAR LIVROS COM EMPRÉSTIMO EFETUADOS--------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-13s | %-11s | %-11s | %-40s | %-18s | %-25s | %-15s |\n",
                            "ID_EMPRESTIMO", "ID_DO_ALUNO", "ID_DO_LIVRO", "TITULO", "DATA_EMPRESTIMO", "NOME_ALUNO","MATRICULA_ALUNO");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    emprestimo.listarLivroComEmprestimo();
                    break;
                case 6:
                    System.out.println("---------------------------------------------------- LISTAR LIVROS COM DEVOLUÇÃO PENDENTE-------------------------------------------------");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("| %-13s | %-18s | %-30s | %-25s | %-16s |\n"
                            , "ID", "DATA DA DEVOLUÇÃO", "TÍTULO", "NOME", "MATRICULA" );
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                    emprestimo.listarLivroComDevolucao();
                    break;
                case 7:
                    System.out.println("Você  SAIU da Tela de Menu!!!");
                    break;
                default:
                    throw new AssertionError();

            }
            System.out.print("Você quer continuar no Sistema - [S]SIM | [N]NÃO: ");
            res = tec.next();
        } while (res.equalsIgnoreCase("S"));

    }

}
