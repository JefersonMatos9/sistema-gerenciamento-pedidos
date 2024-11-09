public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private String observacao;
    private double subtotal;

    public ItemPedido(Produto produto, int quantidade, String observacao, double subtotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.observacao = observacao;
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", observacao='" + observacao + '\'' +
                ", subtotal=" + subtotal +
                '}';
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calcularSubtotal() {
        // Implementar cálculo: preço do produto * quantidade
        return 0;
    }
}
