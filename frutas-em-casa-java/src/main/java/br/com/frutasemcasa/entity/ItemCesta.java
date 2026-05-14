package br.com.frutasemcasa.entity;

public class ItemCesta {
    private final Produto produto;
    private int quantidade;
    private final double precoUnitario;

    public ItemCesta(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public double getSubtotal() {
        return quantidade * precoUnitario;
    }
}
