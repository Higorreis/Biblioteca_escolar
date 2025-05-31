/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Higor
 */
public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private int quantidade;
    
    //Metodo com ID
    public Livro(int id, String titulo, String autor,int ano, int quantidade){
        this.id= id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidade = quantidade;
    }
    //Metodo sem ID
     public Livro( String titulo, String autor,int ano, int quantidade){
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidade = quantidade;
    }
    
    //Criando métodos Gets(Buscar) e Sets(Colocar)
    //Buscar pelo Id do Livro
    public int getId(){
        return id;
    }
    //Buscar pelo Titulo do Livro
    public String getTitulo(){
        return titulo;
    }
    //Buscar pelo Autor do Livro
    public String getAutor(){
        return autor;
    }
    //Buscar pelo Ano do Livro
    public int getAno(){
        return ano;
    }
    //Buscar pela QTD do livro
    public int getQuantidade(){
        return quantidade;
    }
    
    //Colocar dados pelo ID
    public void setId(int id){
        this.id = id;
    }
    //Colocar dados pelo Titulo
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    //Colocar dados pelo Autor
    public void setAutor( String autor){
        this.autor = autor;
    }
    //Colocar dados pelo Ano
    public void setAno( int ano){
        this.ano = ano;
    }
    //Colocar dados pela QTD
    public void setQtd( int quantidade){
        this.quantidade = quantidade;
    }
    
    
}
