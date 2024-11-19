package model;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private String observacao;
    private double subtotal;

    public ItemPedido(Produto produto, int quantidade, String observacao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.subtotal = calcularSubtotal();
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto='" + produto.getNome() + '\'' +  // Exibe o nome do produto
                ", quantidade=" + quantidade +
                ", subtotal=" + calcularSubtotal() + // Calcula o subtotal ao invés de exibir o valor já armazenado
                '}';
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.subtotal = calcularSubtotal();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = calcularSubtotal();
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getSubtotal() {
        return calcularSubtotal();
    }

    public double calcularSubtotal() {
     return quantidade * produto.getPreco();
    }
}
