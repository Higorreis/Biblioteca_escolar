/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Higor
 */
public class Emprestimo {

    private int aluno_Id;
    private int livro_Id;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public Emprestimo(int aluno_Id, int livro_Id, LocalDate data_devolucao) {
        this.aluno_Id = aluno_Id;
        this.livro_Id = livro_Id;
        this.data_devolucao = data_devolucao;
    }

    //Criando métodos Gets(Buscar) e Sets(Colocar)
    public int getalunoId() {
        return aluno_Id;
    }

    public int getlivroId() {
        return livro_Id;
    }

    public LocalDate getdataEmprestimo() {
        return data_emprestimo;
    }

    public LocalDate getdataDevolucao() {
        return data_devolucao;
    }

    public void setdataDevolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
