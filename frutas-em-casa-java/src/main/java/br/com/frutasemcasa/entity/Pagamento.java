package br.com.frutasemcasa.entity;

import java.time.LocalDate;

public class Pagamento {
    private static int contador = 1;

    private final int id;
    private final LocalDate dataPagamento;
    private final double valor;
    private String status;

    public Pagamento(double valor) {
        this.id = contador++;
        this.dataPagamento = LocalDate.now();
        this.valor = valor;
        this.status = "PENDENTE";
    }

    public boolean pagar(String numeroCartao, String titular, String validade, String cvv) {
        boolean dadosPreenchidos = !numeroCartao.isBlank() && !titular.isBlank() && !validade.isBlank() && !cvv.isBlank();
        this.status = dadosPreenchidos ? "APROVADO" : "RECUSADO";
        return dadosPreenchidos;
    }

    public int getId() { return id; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public double getValor() { return valor; }
    public String getStatus() { return status; }
}
