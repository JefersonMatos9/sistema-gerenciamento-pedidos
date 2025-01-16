package model;

import enums.CategoriaProduto;
import exception.EstoqueInsuficienteException;
import exception.PrecoNegativoException;
import exception.ProdutoNegativoException;

import java.util.logging.Logger;

public class Produto {
    private static final Logger logger = Logger.getLogger(Produto.class.getName()); // iniciando o logger

    private long id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private CategoriaProduto categoria; // Exemplo: Lanches, Bebidas, Sobremesas
    private boolean disponivel;

    @Override
    public String toString() {
        return "Produto:\n" +
                "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Descrição: " + descricao + "\n" +
                "Preço: R$ " + String.format("%.2f", preco) + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Categoria: " + categoria + "\n" +
                "Disponível: " + (disponivel ? "Sim" : "Não") + "\n";
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) throws PrecoNegativoException {
        if (preco > 0) {
            this.preco = preco;
        } else {
            logger.warning("Tentativa de definir um preço negativo ou zero do produto: " + nome + ": " + preco);
            throw new PrecoNegativoException("O preço não pode ser negativo ou zero.");
        }
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws ProdutoNegativoException {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
            setDisponivel(); // atualiza a disponibilidade com base na nova quantidade
        } else {
            logger.warning("Tentativa de definir uma quantidade negativa de produto: " + nome + ": " + quantidade);
            throw new ProdutoNegativoException("A quantidade de produto não pode ser negativa.");
        }
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel() {
        this.disponivel = quantidade > 0;
    }

    public void diminuirEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (this.quantidade < quantidade) {
            logger.warning("Não temos quantidade suficiente para o pedido ,temos apenas: " + getQuantidade() + " ," + getNome() + " ,em estoque.");
            throw new EstoqueInsuficienteException("O item " + getNome() + " não tem estoque suficiente.");
        }
        this.quantidade -= quantidade;
    }

    public void aumentarEstoque(int quantidade){
        this.quantidade += quantidade;
    }
}

