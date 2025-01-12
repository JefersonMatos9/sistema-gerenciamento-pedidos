package repository.memory;

import enums.StatusPedido;
import interfaces.IPedidoRepository;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PedidoMemoryRepository implements IPedidoRepository {

    private static final Logger LOGGER = Logger.getLogger(IPedidoRepository.class.getName());
    private List<Pedido> listaDePedidos;

    public PedidoMemoryRepository() {
        this.listaDePedidos = new ArrayList<>();
    }

    @Override
    public void salvar(Pedido pedido) {
        listaDePedidos.add(pedido);
        LOGGER.info("Pedido salvo com sucesso: " + pedido.getNumeroPedido());
    }

    @Override
    public void atualizar(Pedido pedido) {
        for (int i = 0; i < listaDePedidos.size(); i++) {
            Pedido p = listaDePedidos.get(i);
            if (p.getNumeroPedido() == pedido.getNumeroPedido()) {
                listaDePedidos.set(i, pedido);
                LOGGER.info("Pedido realizado com sucesso " + pedido.getNumeroPedido());
                return;
            }
        }
        LOGGER.warning("Pedido com número " + pedido.getNumeroPedido() + " não encontrado");
    }

    @Override
    public Pedido buscarPorNumero(int numero) {
        if (listaDePedidos.isEmpty()) {
            throw new IllegalArgumentException("Lista de pedidos vazia");
        }
        for (Pedido pedido : listaDePedidos) {
            if (pedido.getNumeroPedido() == numero) {
                LOGGER.info("Pedido encontrado: " + numero);
                return pedido;
            }
        }
        throw new IllegalArgumentException("Pedido com número " + numero + " não encontrado");
    }

    @Override
    public List<Pedido> listarTodos() {
        if (listaDePedidos.isEmpty()) {
            throw new IllegalArgumentException("Lista de Pedidos vazia");
        }
        return listaDePedidos;
    }

    @Override
    public List<Pedido> listarPorStatus(StatusPedido status) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido pedido : listaDePedidos) {
            if (pedido.getStatus().equals(status)) {
                filtrados.add(pedido);
            }
        }
        if (filtrados.isEmpty()) {
            throw new IllegalArgumentException("Nenhum pedido encontrado com status: " + status);
        }
        return filtrados;
    }
}
