import java.util.List;

public class GerenciadorPedidos {
    private List<Pedido> pedidos;
    private int ultimoNumeroPedido;

    public void novoPedido(){
      //  Esse método precisará incrementar o ultimoNumeroPedido e adicionar um novo objeto Pedido à lista.
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido){

    }

    public List<Pedido> listarPedidosPorStatus(StatusPedido statusPedido){

    }

    public boolean atualizarStatusPedido(int numeroPedido, StatusPedido statusPedido){

    }

    public double calcularFaturamentoDoDia(double faturamentoDoDia){
        return faturamentoDoDia;
    }

    public List<Pedido>pedidosDoDia(){

    }


    // TODO: Implementar métodos para:
    // - Criar novo pedido
    // - Buscar pedido por número
    // - Listar pedidos por status
    // - Atualizar status do pedido
    // - Calcular faturamento do dia
    // - Listar pedidos do dia
}
