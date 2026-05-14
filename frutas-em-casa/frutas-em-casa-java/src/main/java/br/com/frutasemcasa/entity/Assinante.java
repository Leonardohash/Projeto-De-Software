package br.com.frutasemcasa.entity;

public class Assinante {
    private final int id;
    private final String nome;
    private final String celular;

    public Assinante(int id, String nome, String celular) {
        this.id = id;
        this.nome = nome;
        this.celular = celular;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCelular() { return celular; }
}
