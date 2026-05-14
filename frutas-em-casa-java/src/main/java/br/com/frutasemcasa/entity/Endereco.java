package br.com.frutasemcasa.entity;

public class Endereco {
    private final String cep;
    private final String rua;
    private final int numero;
    private final String complemento;

    public Endereco(String cep, String rua, int numero, String complemento) {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCep() { return cep; }
    public String getRua() { return rua; }
    public int getNumero() { return numero; }
    public String getComplemento() { return complemento; }

    @Override
    public String toString() {
        return rua + ", " + numero + " - CEP " + cep + (complemento.isBlank() ? "" : " - " + complemento);
    }
}
