package repository.memory;

import enums.CategoriaProduto;
import interfaces.IProdutoRepository;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProdutoMemoryRepository implements IProdutoRepository {

    private static final Logger LOGGER = Logger.getLogger(IProdutoRepository.class.getName());
    private List<Produto> listaDeProdutos;

    public ProdutoMemoryRepository() {
        this.listaDeProdutos = new ArrayList<>();
    }


    @Override
    public void salvar(Produto produto) {
        listaDeProdutos.add(produto);
        LOGGER.info("Produto adicionado a lista com sucesso " + produto.getNome());
    }

    @Override
    public void deletar(Produto produto) {
        listaDeProdutos.remove(produto);
        LOGGER.info("Produto excluido com sucesso " + produto.getNome());
    }

    @Override
    public void atualizar(Produto produto) {
        for (int i =0; i < listaDeProdutos.size(); i++){
            Produto p = listaDeProdutos.get(i);
            if (p.getNome().equals(produto.getNome())){
                listaDeProdutos.set(i,produto);
                LOGGER.info("Produto atualizado com sucesso " + produto.getNome());
                return;
            }
        }
        LOGGER.info("Produto com nome " + produto.getNome() + " não encontrado");
    }

    @Override
    public Produto buscarPorNome(String nome) {
        for (Produto produto : listaDeProdutos) {
            if (produto.getNome().equals(nome)) {
                LOGGER.info("Produto encontrado " + produto.getNome() + " Quantidade disponivel " + produto.getQuantidade());
                return produto;
            }
        }
        LOGGER.warning("Produto com o nome " + nome + " não encontrado na lista");
        return null;
    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public void buscarPorId(Long id) {

    }


    @Override
    public List<Produto> listarTodos() {
        if (listaDeProdutos.isEmpty()) {
            LOGGER.warning("Lista de Produtos vazia.");
            return new ArrayList<>();
        }
        return listaDeProdutos;
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {
        if (listaDeProdutos.isEmpty()) {
            LOGGER.warning("Lista de Produtos vazia.");
            return new ArrayList<>();
        }
        List<Produto> filtrados = new ArrayList<>(); //adiciona os produtos da lista e filtra.
        for (Produto produto : listaDeProdutos) {
            if (produto.getCategoria().equals(categoria)) {
                filtrados.add(produto);
            }
        }
        if (filtrados.isEmpty()) {
            LOGGER.warning("Nenhum produto encontrado para a categoria " + categoria);
        }
        return filtrados;
    }
}