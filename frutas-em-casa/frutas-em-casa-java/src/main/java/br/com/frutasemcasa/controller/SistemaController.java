package br.com.frutasemcasa.controller;

import br.com.frutasemcasa.entity.*;
import br.com.frutasemcasa.persistence.AssinaturaRepositoryTxt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaController {
    private static final String CODIGO_SMS_TESTE = "6661";

    private final List<Plano> planos = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();
    private final AssinaturaRepositoryTxt repository = new AssinaturaRepositoryTxt();

    private Assinante assinanteAtual;
    private Assinatura assinaturaAtual;

    public SistemaController() {
        carregarPlanos();
        carregarProdutos();
    }

    public String enviarCodigoSms(String celular) {
        assinanteAtual = new Assinante(1, "Assinante Teste", celular);
        return CODIGO_SMS_TESTE;
    }

    public boolean validarCodigo(String codigoDigitado) {
        return CODIGO_SMS_TESTE.equals(codigoDigitado);
    }

    public List<Plano> buscarPlanos() {
        return planos;
    }

    public Assinatura criarAssinatura(int indicePlano) {
        Plano plano = planos.get(indicePlano);
        assinaturaAtual = new Assinatura(assinanteAtual, plano);
        return assinaturaAtual;
    }

    public List<Produto> buscarProdutosPorCategoria(String categoria) {
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public boolean adicionarProduto(Produto produto, int quantidade) {
        int totalAtual = assinaturaAtual.getCesta().getQuantidadeTotalItens();
        int limitePlano = assinaturaAtual.getPlano().getMaxItens();

        if (totalAtual + quantidade > limitePlano) {
            return false;
        }

        assinaturaAtual.getCesta().adicionar(produto, quantidade);
        return true;
    }

    public double getValorTotalCesta() {
        return assinaturaAtual.getCesta().getValorTotal();
    }

    public void criarEndereco(String cep, String rua, int numero, String complemento) {
        Endereco endereco = new Endereco(cep, rua, numero, complemento);
        assinaturaAtual.definirEndereco(endereco);
    }

    public boolean processarPagamento(String numeroCartao, String titular, String validade, String cvv) {
        Pagamento pagamento = new Pagamento(assinaturaAtual.getPlano().getPreco());
        boolean aprovado = pagamento.pagar(numeroCartao, titular, validade, cvv);
        assinaturaAtual.definirPagamento(pagamento);

        if (aprovado) {
            Protocolo protocolo = new Protocolo();
            assinaturaAtual.confirmar(protocolo);
            repository.salvar(assinaturaAtual);
        }

        return aprovado;
    }

    public Assinatura getAssinaturaAtual() {
        return assinaturaAtual;
    }

    private void carregarPlanos() {
        planos.add(new Plano("Plano Comum", 5, 15.99));
        planos.add(new Plano("Plano Exclusivo", 10, 29.99));
        planos.add(new Plano("Plano Premium", 20, 49.99));
    }

    private void carregarProdutos() {
        produtos.add(new Produto("Maçã", 2.00, "unidade", "Frutas"));
        produtos.add(new Produto("Banana", 1.50, "unidade", "Frutas"));
        produtos.add(new Produto("Morango", 8.00, "bandeja", "Frutas"));
        produtos.add(new Produto("Manga", 3.00, "unidade", "Frutas"));
        produtos.add(new Produto("Pera", 2.50, "unidade", "Frutas"));

        produtos.add(new Produto("Abóbora", 6.00, "unidade", "Legumes"));
        produtos.add(new Produto("Cenoura", 1.80, "unidade", "Legumes"));
        produtos.add(new Produto("Berinjela", 4.00, "unidade", "Legumes"));
        produtos.add(new Produto("Alcachofra", 7.50, "unidade", "Legumes"));
        produtos.add(new Produto("Ervilha", 5.00, "pacote", "Legumes"));

        produtos.add(new Produto("Alface", 3.50, "unidade", "Verduras"));
        produtos.add(new Produto("Repolho", 4.50, "unidade", "Verduras"));
        produtos.add(new Produto("Acelga", 5.00, "unidade", "Verduras"));
        produtos.add(new Produto("Rúcula", 3.00, "maço", "Verduras"));
        produtos.add(new Produto("Couve", 3.20, "maço", "Verduras"));
    }
}
