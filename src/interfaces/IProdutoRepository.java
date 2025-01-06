package interfaces;

import enums.CategoriaProduto;
import model.Produto;

import java.util.List;

public interface IProdutoRepository {



    void salvar(Produto produto);

    void atualizar(Produto produto);

    void deletar(Produto excluirProduto);

    void deletar(Long id);

    void buscarPorId(Long id);

    Produto buscarPorNome(String nome);

    List<Produto> listarTodos();

    List<Produto> buscarPorCategoria(CategoriaProduto categoria);
}
