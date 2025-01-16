package repository.dao;

import config.DatabaseConfig;
import enums.CategoriaProduto;
import interfaces.IProdutoRepository;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoRepository implements IProdutoRepository {
    @Override
    public void salvar(Produto produto) {

        String sql = "INSERT INTO produtos(nome, descricao, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement prepare = null;

        try {
            prepare = DatabaseConfig.getConexao().prepareStatement(sql);
            prepare.setString(1, produto.getNome());
            prepare.setString(2, produto.getDescricao());
            prepare.setDouble(3, produto.getPreco());
            prepare.setInt(4, produto.getQuantidade());
            prepare.setString(5, produto.getCategoria().name());

            prepare.executeUpdate();
            prepare.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
