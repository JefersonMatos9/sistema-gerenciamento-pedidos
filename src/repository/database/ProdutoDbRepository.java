package repository.database;

import enums.CategoriaProduto;
import interfaces.IProdutoRepository;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDbRepository implements IProdutoRepository {
    @Override
    public void salvar(Produto produto) {

    }

    @Override
    public void atualizar(Produto produto) {

    }

    @Override
    public void deletar(Produto excluirProduto) {

    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public void buscarPorId(Long id) {

    }

    @Override
    public Produto buscarPorNome(String nome) {
        return null;
    }

    @Override
    public List<Produto> listarTodos() {
        return listarTodos();
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {
        return new ArrayList<>();
  }
}
