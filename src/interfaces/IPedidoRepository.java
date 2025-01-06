/**package interfaces;

import enums.StatusPedido;
import model.Pedido;

public interface IPedidoRepository {
    void salvar(Pedido pedido);

    void atualizar(Pedido pedido);

    void buscarPorNumero(String numero);

    void listarTodos();

    void listarPorStatus(StatusPedido status);
}
*/