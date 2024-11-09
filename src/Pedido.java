import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private List<ItemPedido> itens;
    private String cliente;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private FormaPagamento formaPagamento;
    private double valorTotal;

    public void adicionarItem(){

    }

    public void removerItem(){

    }

    public double calcularTotal(){
        return 0;
    }

    public boolean alterarStatusPedido(){
        return true;
    }

    public double aplicarDesconto() {
return 0;
    }
}
