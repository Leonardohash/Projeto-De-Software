package br.com.frutasemcasa.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Protocolo {
    private final String numeroProtocolo;
    private final LocalDate dataEmissao;

    public Protocolo() {
        this.dataEmissao = LocalDate.now();
        this.numeroProtocolo = gerarProtocolo();
    }

    private String gerarProtocolo() {
        String data = dataEmissao.format(DateTimeFormatter.BASIC_ISO_DATE);
        int aleatorio = (int) (Math.random() * 9000) + 1000;
        return data + aleatorio;
    }

    public String getNumeroProtocolo() { return numeroProtocolo; }
    public LocalDate getDataEmissao() { return dataEmissao; }
}
