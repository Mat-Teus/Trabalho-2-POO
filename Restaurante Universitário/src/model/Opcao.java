package model;

public class Opcao {
    //Atributos
    private String nome;
    private double preco;
    
    //Construtores
    public Opcao(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Opcao() {
    }
    
    //Métodos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
