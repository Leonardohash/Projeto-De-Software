package br.com.frutasemcasa.entity;

import java.time.LocalDate;

public class Assinatura {
    private final LocalDate dataInicio;
    private final LocalDate dataCobranca;
    private String status;
    private final Assinante assinante;
    private final Plano plano;
    private final Cesta cesta;
    private Endereco endereco;
    private Pagamento pagamento;
    private Protocolo protocolo;

    public Assinatura(Assinante assinante, Plano plano) {
        this.dataInicio = LocalDate.now();
        this.dataCobranca = LocalDate.now().plusMonths(1);
        this.status = "EM_MONTAGEM";
        this.assinante = assinante;
        this.plano = plano;
        this.cesta = new Cesta();
    }

    public void definirEndereco(Endereco endereco) { this.endereco = endereco; }
    public void definirPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
    public void confirmar(Protocolo protocolo) {
        this.protocolo = protocolo;
        this.status = "ATIVA";
    }

    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataCobranca() { return dataCobranca; }
    public String getStatus() { return status; }
    public Assinante getAssinante() { return assinante; }
    public Plano getPlano() { return plano; }
    public Cesta getCesta() { return cesta; }
    public Endereco getEndereco() { return endereco; }
    public Pagamento getPagamento() { return pagamento; }
    public Protocolo getProtocolo() { return protocolo; }
}
