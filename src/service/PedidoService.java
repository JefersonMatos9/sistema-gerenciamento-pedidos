package service;

import enums.CategoriaProduto;
import enums.StatusPedido;
import exception.PrecoNegativoException;
import exception.ProdutoNegativoException;
import interfaces.IPedidoRepository;
import main.Main;
import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PedidoService {

    private final IPedidoRepository repository;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public PedidoService(IPedidoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarPedido(Pedido pedido) {
        validarPedido(pedido);
        repository.salvar(pedido);
        LOGGER.info("Pedido realizado com sucesso: " + pedido.getNumeroPedido());
    }

    public void atualizarPedido(Pedido pedido) {
        validarPedido(pedido);
        repository.atualizar(pedido);
        LOGGER.info("Pedido atualizado com sucesso: " + pedido.getNumeroPedido());
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        Pedido pedido = repository.buscarPorNumero(numero);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        return pedido;
    }

    public List<Pedido> listarTodosOsPedidos() {
        List<Pedido> pedidos = repository.listarTodos();
        if (pedidos.isEmpty()) {
            throw new IllegalArgumentException("Lista de pedidos esta vazia");
        }
        return pedidos;
    }

    public List<Pedido> listarPedidoPorStatus(StatusPedido status) {
        List<Pedido> pedidos = repository.listarPorStatus(status);
        if (pedidos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum pedido encontrado para o status: " + status);
        }
        return pedidos;
    }

    private void validarPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter no minimo um item");
        }
        if (pedido.getStatus() == null) {
            throw new IllegalArgumentException("Status do pedido é obrigatorio");
        }
        if (pedido.getValorTotal() <= 0) {
            throw new IllegalArgumentException("Valor total do pedido deve ser maior que zero");
        }
    }
}
