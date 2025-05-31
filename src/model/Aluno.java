/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Higor
 */
public class Aluno {

    private int id;
    private String nome;
    private String matricula;
    private LocalDate data_nascimento;

    //Método sem ID
    public Aluno(String nome, String matricula, LocalDate data_nascimento) {
        this.nome = nome;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }
    //Método com ID

    public Aluno(int id, String nome, String matricula, LocalDate data_nascimento) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
    }

    public Aluno(int id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    //Buscar pelo Nome do Aluno
    public String getNome() {
        return nome;
    }
    //Buscar pela Matricula do Aluno

    public String getMatricula() {
        return matricula;
    }
    //Buscar pelO Id do Aluno

    public int getId() {
        return id;
    }
    //Buscar pela Data de Nascimento do Aluno

    public LocalDate getNas() {
        return data_nascimento;
    }

    //Colocar dados pelo Nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    //Colocar dados pela Matricula

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    //Colocar dados pela ID

    public void setId(int id) {
        this.id = id;
    }
    //Colocar dados pela Data de Nascimento

    public void setNas(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
}
