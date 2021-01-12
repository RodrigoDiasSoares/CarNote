package br.com.CarNote.activity.model;

public class Gastos {
    private String titulo;
    private double preco;
    private String detalhes;

    public Gastos(String titulo, double preco, String detalhes) {
        this.titulo = titulo;
        this.preco = preco;
        this.detalhes = detalhes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
