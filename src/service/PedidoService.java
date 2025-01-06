package service;

import enums.CategoriaProduto;
import exception.PrecoNegativoException;
import exception.ProdutoNegativoException;
import main.Main;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PedidoService {
    Produto produto;
    private List<Produto> cardapio;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public PedidoService() {
        this.cardapio = new ArrayList<>();
    }

    public void salvar(Produto produto) {
        cardapio.add(produto);
    }

    public void excluir(String nomeProduto) {
        if (cardapio.isEmpty()) {
            logger.warning("A lista está vazia.");
            return;
        }
        //remove todos os produtos com o nome especificado
        boolean removido = cardapio.removeIf(produto -> produto.getNome().equalsIgnoreCase(nomeProduto));
        if (removido) {
            logger.info("Produto(s) " + nomeProduto + " removido(s) com sucesso.");
        } else {
            logger.warning("Produto(s) " + nomeProduto + " não encontrado(s).");
        }
    }

    public List<Produto> buscarProdutoPorCategoria(CategoriaProduto categoriaProduto) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produtoAtual : cardapio) {
            if (produtoAtual.getCategoria().equals(categoriaProduto)) {
                produtosEncontrados.add(produtoAtual);
            }
        }
        return produtosEncontrados;
    }

    public List<Produto> listarTodos() {
        if (cardapio.isEmpty()) {
            logger.warning("Cardapio não possui itens.");
            return new ArrayList<>();
        }
        logger.info("Lista com Todos os nossos Produtos:");
        for (Produto produto : cardapio) {
            logger.info(String.format("%s - %s - R$ %.2f - Qtd: %d",
                    produto.getNome(),
                    produto.getCategoria(),
                    produto.getPreco(),
                    produto.getQuantidade()));
        }
        return new ArrayList<>(cardapio);
    }

    public void atualizar(String nomeProduto, int quantidade, double novoPreco) throws ProdutoNegativoException, PrecoNegativoException {
        for (Produto produto : cardapio) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                produto.setPreco(novoPreco);
                produto.setQuantidade(quantidade);
                logger.info("Produto " + nomeProduto + " atualizado com sucesso.");
                return;
            }
        }
        logger.warning("Produto " + nomeProduto + " não encontrado.");
    }
    public void listarPorStatus(){}
}
