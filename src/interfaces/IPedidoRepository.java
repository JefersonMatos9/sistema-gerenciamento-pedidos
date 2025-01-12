package interfaces;

import enums.StatusPedido;
import model.Pedido;

import java.util.List;

public interface IPedidoRepository {
    void salvar(Pedido pedido);

    void atualizar(Pedido pedido);

    Pedido buscarPorNumero(int numero);

    List<Pedido> listarTodos();

    List<Pedido> listarPorStatus(StatusPedido status);
}
