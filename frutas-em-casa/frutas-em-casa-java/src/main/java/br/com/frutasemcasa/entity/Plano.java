package br.com.frutasemcasa.entity;

public class Plano {
    private final String tipo;
    private final int maxItens;
    private final double preco;

    public Plano(String tipo, int maxItens, double preco) {
        this.tipo = tipo;
        this.maxItens = maxItens;
        this.preco = preco;
    }

    public String getTipo() { return tipo; }
    public int getMaxItens() { return maxItens; }
    public double getPreco() { return preco; }

    public String exibirPlano() {
        return String.format("%s - Até %d itens - R$ %.2f", tipo, maxItens, preco);
    }
}
