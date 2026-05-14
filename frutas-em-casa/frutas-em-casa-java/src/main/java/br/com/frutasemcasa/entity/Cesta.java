package br.com.frutasemcasa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cesta {
    private double valorTotal;
    private final LocalDate dataMontagem;
    private final List<ItemCesta> itens;

    public Cesta() {
        this.valorTotal = 0.0;
        this.dataMontagem = LocalDate.now();
        this.itens = new ArrayList<>();
    }

    public void adicionar(Produto produto, int quantidade) {
        if (quantidade <= 0) return;

        for (ItemCesta item : itens) {
            if (item.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
                item.adicionarQuantidade(quantidade);
                recalcularValorTotal();
                return;
            }
        }

        itens.add(new ItemCesta(produto, quantidade));
        recalcularValorTotal();
    }

    private void recalcularValorTotal() {
        valorTotal = itens.stream().mapToDouble(ItemCesta::getSubtotal).sum();
    }

    public int getQuantidadeTotalItens() {
        return itens.stream().mapToInt(ItemCesta::getQuantidade).sum();
    }

    public double getValorTotal() { return valorTotal; }
    public LocalDate getDataMontagem() { return dataMontagem; }
    public List<ItemCesta> getItens() { return Collections.unmodifiableList(itens); }
}
