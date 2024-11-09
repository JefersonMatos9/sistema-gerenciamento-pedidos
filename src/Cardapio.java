import java.util.List;

public class Cardapio {
    private List<Produto> produtos;

    public void adicionarProduto(Produto produto){;
        produtos.add(produto);
    }

    public void removerProduto(Produto produto){
produtos.remove(produto);
    }

    public Produto buscarProduto(String nome){
return;
    }

    public List<Produto> buscarProdutoPorCategoria(String categoria){
return;
    }

    public List<Produto> listarProdutos(){
return produtos;
    }

    public void atualizarProduto(String produtoAtualizado){

    }
}
