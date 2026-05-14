package br.com.frutasemcasa.boundary;

import br.com.frutasemcasa.controller.SistemaController;
import br.com.frutasemcasa.entity.Assinatura;
import br.com.frutasemcasa.entity.ItemCesta;
import br.com.frutasemcasa.entity.Plano;
import br.com.frutasemcasa.entity.Produto;

import java.util.List;
import java.util.Scanner;

public class SistemaBoundary {
    private final Scanner scanner = new Scanner(System.in);
    private final SistemaController controller = new SistemaController();

    public void iniciar() {
        System.out.println("====================================");
        System.out.println("        FRUTAS EM CASA");
        System.out.println("====================================");

        if (!telaValidacaoSms()) return;
        telaPlanos();
        telaMontagemCesta("Frutas");
        telaMontagemCesta("Legumes");
        telaMontagemCesta("Verduras");
        telaResumoEndereco();
        telaPagamento();
    }

    private boolean telaValidacaoSms() {
        System.out.print("Informe seu celular: ");
        String celular = scanner.nextLine();

        String codigo = controller.enviarCodigoSms(celular);
        System.out.println("SMS enviado. Código de teste: " + codigo);

        System.out.print("Digite o código recebido: ");
        String codigoDigitado = scanner.nextLine();

        if (!controller.validarCodigo(codigoDigitado)) {
            System.out.println("Código inválido. Fluxo encerrado.");
            return false;
        }

        System.out.println("Código validado com sucesso!\n");
        return true;
    }

    private void telaPlanos() {
        System.out.println("Qual plano deseja?");
        List<Plano> planos = controller.buscarPlanos();

        for (int i = 0; i < planos.size(); i++) {
            System.out.println((i + 1) + " - " + planos.get(i).exibirPlano());
        }

        int opcao = lerInteiro("Escolha o plano: ", 1, planos.size());
        controller.criarAssinatura(opcao - 1);
        System.out.println("Plano assinado para montagem da cesta.\n");
    }

    private void telaMontagemCesta(String categoria) {
        System.out.println("Cesta de " + categoria);
        System.out.println("Limite do plano: " + controller.getAssinaturaAtual().getPlano().getMaxItens() + " itens");
        System.out.println("Itens selecionados até agora: " + controller.getAssinaturaAtual().getCesta().getQuantidadeTotalItens());

        List<Produto> produtos = controller.buscarProdutosPorCategoria(categoria);

        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + " - " + produtos.get(i));
        }

        for (Produto produto : produtos) {
            int quantidade = lerInteiro("Quantidade de " + produto.getNome() + ": ", 0, 99);
            boolean adicionado = controller.adicionarProduto(produto, quantidade);

            if (!adicionado) {
                System.out.println("Quantidade ultrapassa o limite do plano. Produto não adicionado.");
            }
        }

        System.out.println("Categoria confirmada.\n");
    }

    private void telaResumoEndereco() {
        System.out.println("Resumo do Pedido");
        for (ItemCesta item : controller.getAssinaturaAtual().getCesta().getItens()) {
            System.out.println("- " + item.getProduto().getNome() + " x" + item.getQuantidade());
        }

        System.out.printf("Valor estimado da cesta: R$ %.2f%n", controller.getValorTotalCesta());
        System.out.println("Valor da assinatura: R$ " + String.format("%.2f", controller.getAssinaturaAtual().getPlano().getPreco()));

        System.out.println("\nInsira seu endereço");
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        int numero = lerInteiro("Número: ", 1, 99999);
        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();

        controller.criarEndereco(cep, rua, numero, complemento);
        System.out.println("Endereço cadastrado.\n");
    }

    private void telaPagamento() {
        Assinatura assinatura = controller.getAssinaturaAtual();

        System.out.println("Pagamento");
        System.out.printf("Valor da assinatura: R$ %.2f%n", assinatura.getPlano().getPreco());
        System.out.println("Use dados fictícios para teste.");

        System.out.print("Número do cartão: ");
        String numeroCartao = scanner.nextLine();
        System.out.print("Nome do titular: ");
        String titular = scanner.nextLine();
        System.out.print("Data de validade: ");
        String validade = scanner.nextLine();
        System.out.print("CVV: ");
        String cvv = scanner.nextLine();

        boolean aprovado = controller.processarPagamento(numeroCartao, titular, validade, cvv);

        if (!aprovado) {
            System.out.println("Pagamento recusado. Verifique os dados informados.");
            return;
        }

        telaConfirmacao();
    }

    private void telaConfirmacao() {
        Assinatura assinatura = controller.getAssinaturaAtual();

        System.out.println("\n====================================");
        System.out.println("Bem-vindo ao Frutas em Casa!!!");
        System.out.println("Dados de entrega: " + assinatura.getEndereco());
        System.out.println("Protocolo do pedido: " + assinatura.getProtocolo().getNumeroProtocolo());
        System.out.println("Status da assinatura: " + assinatura.getStatus());
        System.out.println("====================================");
        System.out.println("Assinatura salva em dados/assinaturas.txt");
    }

    private int lerInteiro(String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Digite um número entre " + minimo + " e " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite apenas números.");
            }
        }
    }
}
