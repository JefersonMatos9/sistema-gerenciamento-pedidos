package main;

import enums.CategoriaProduto;
import exception.PrecoNegativoException;
import exception.ProdutoNegativoException;
import model.Produto;
import repository.dao.ProdutoDaoRepository;

public class MainDao {
    public static void main(String[] args) throws PrecoNegativoException, ProdutoNegativoException {
        Produto produto = new Produto();
        ProdutoDaoRepository pd = new ProdutoDaoRepository();

        produto.setNome("Pastel Frango");
        produto.setDescricao("Pastel de Frango");
        produto.setPreco(6.99);
        produto.setQuantidade(10);
        produto.setCategoria(CategoriaProduto.SALGADOS);

        pd.salvar(produto);

    }
}
