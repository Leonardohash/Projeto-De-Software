package br.com.frutasemcasa.entity;

public class Produto {
    private final String nome;
    private final double preco;
    private final String unidadeMedida;
    private final String categoria;

    public Produto(String nome, double preco, String unidadeMedida, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
        this.categoria = categoria;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return String.format("%s (%s) - R$ %.2f", nome, unidadeMedida, preco);
    }
}
