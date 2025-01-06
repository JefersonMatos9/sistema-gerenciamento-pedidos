package service;

import enums.CategoriaProduto;
import exception.ProdutoNegativoException;
import interfaces.IProdutoRepository;
import model.Produto;

import java.util.List;
import java.util.logging.Logger;

public class ProdutoService {
    private final IProdutoRepository repository;
    private static final Logger LOGGER = Logger.getLogger(IProdutoRepository.class.getName());

    public ProdutoService(IProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarProduto(Produto produto) {
        validarProduto(produto);
        repository.salvar(produto);
    }

    public void deletarProduto(Produto produto) {
        validarExistenciaProduto(produto);
        repository.deletar(produto);
    }

    public void atualizarProduto(String nome, int novaQuantidade) throws ProdutoNegativoException {
        Produto produtoParaAtualizar = validarAtualizacao(nome, novaQuantidade);
        repository.atualizar(produtoParaAtualizar);
        LOGGER.info("Produto atualizado com sucesso: " + produtoParaAtualizar);
    }

    public Produto buscarProdutoPorNome(String nome) {
        Produto produto = repository.buscarPorNome(nome);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;
    }

    public List<Produto> buscarProdutoPorCategoria(CategoriaProduto categoria) {
        List<Produto> produtos = repository.buscarPorCategoria(categoria);
        if (produtos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum produto encontrado para a categoria");
        }
        return produtos;
    }

    public void listarTodosOsProdutos() {
        List<Produto> produtos = repository.listarTodos();
        if (produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de Produtos esta vazia");
        }
        for (Produto produto : produtos){
            LOGGER.info("Produto: " + produto.getNome() + " Preço: " + produto.getPreco() + " Quantidade: " + produto.getQuantidade());
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (produto.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatoria");
        }
    }

    private void validarExistenciaProduto(Produto produto) {
        if (repository.buscarPorNome(produto.getNome()) == null) {
            throw new IllegalArgumentException("Produto não encontrado na lista");
        }
    }

    private Produto validarAtualizacao(String nome, int novaQuantidade) throws ProdutoNegativoException {
        Produto produtoParaAtualizar = repository.buscarPorNome(nome);
        if (produtoParaAtualizar != null) {
            if (novaQuantidade < 0) {
                throw new ProdutoNegativoException("Quantidade não pode ser negativa");
            }
            produtoParaAtualizar.setQuantidade(novaQuantidade);
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produtoParaAtualizar;
    }

}
