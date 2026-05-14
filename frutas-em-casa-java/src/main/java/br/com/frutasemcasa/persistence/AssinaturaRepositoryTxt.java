package br.com.frutasemcasa.persistence;

import br.com.frutasemcasa.entity.Assinatura;
import br.com.frutasemcasa.entity.ItemCesta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class AssinaturaRepositoryTxt {
    private final Path arquivo = Path.of("dados", "assinaturas.txt");

    public void salvar(Assinatura assinatura) {
        try {
            Files.createDirectories(arquivo.getParent());
            if (!Files.exists(arquivo)) {
                Files.writeString(arquivo, "protocolo;assinante;celular;plano;valorPlano;itens;endereco;statusPagamento;statusAssinatura\n");
            }
            Files.writeString(arquivo, montarLinha(assinatura), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar assinatura em arquivo txt.", e);
        }
    }

    private String montarLinha(Assinatura assinatura) {
        String itens = assinatura.getCesta().getItens().stream()
                .map(this::formatarItem)
                .collect(Collectors.joining(", "));

        return String.format("%s;%s;%s;%s;%.2f;%s;%s;%s;%s\n",
                assinatura.getProtocolo().getNumeroProtocolo(),
                assinatura.getAssinante().getNome(),
                assinatura.getAssinante().getCelular(),
                assinatura.getPlano().getTipo(),
                assinatura.getPlano().getPreco(),
                itens,
                assinatura.getEndereco(),
                assinatura.getPagamento().getStatus(),
                assinatura.getStatus());
    }

    private String formatarItem(ItemCesta item) {
        return item.getProduto().getNome() + " x" + item.getQuantidade();
    }
}
