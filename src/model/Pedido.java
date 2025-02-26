package model;

import enums.StatusPedido;
import exception.EstoqueInsuficienteException;
import exception.PagamentoNaoConfirmadoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Pedido {

    private Long id;
    private static int contadorPedidos = 0;
    private int numeroPedido;
    private List<ItemPedido> itens;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private double valorTotal;

    public Pedido() {
        this.numeroPedido = ++contadorPedidos;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.RECEBIDO;
        this.valorTotal = 0;
    }

    public void adicionarItem(ItemPedido item) throws EstoqueInsuficienteException {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        if (item.getQuantidade() <= 0) {
            throw new EstoqueInsuficienteException("Deve ser adicionado 1 ou mais itens ao estoque.");
        }
        Produto produto = item.getProduto();
        if (produto.getQuantidade() < item.getQuantidade()) {
            throw new EstoqueInsuficienteException("Não temos estoque suficiente do produto: " + item);
        }

        itens.add(item);
        calcularTotal();
    }

    public void removerItem(ItemPedido itemExcluir) {
        if (itens.removeIf(item -> item.equals(itemExcluir))) {
            itemExcluir.getProduto().aumentarEstoque(itemExcluir.getQuantidade());
            calcularTotal();
        } else {
            throw new IllegalArgumentException("Itém não encontrado no pedido");
        }
    }

    public void calcularTotal() {
        valorTotal = 0;
        for (ItemPedido item : itens) {
            valorTotal += item.getSubtotal();
        }
    }

    public void alterarStatus(StatusPedido novoStatus) {
        GerenciadorStatus.validarMudancaStatus(this.status, novoStatus);
        this.status = novoStatus;
    }

    public double finalizarPedido() throws PagamentoNaoConfirmadoException, EstoqueInsuficienteException {
        validarPedido();
        atualizarEstoque();
        this.status = StatusPedido.ENTREGUE;
        return valorTotal;
    }

    private void validarPedido() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Pedido sem itens.");
        }
    }

    private void atualizarEstoque() throws EstoqueInsuficienteException {
        for (ItemPedido item : itens) {
            Produto produto = item.getProduto();
            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto " + produto.getNome());
            }
        }
        for (ItemPedido item : itens) {
            item.getProduto().diminuirEstoque(item.getQuantidade());
        }
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.calcularSubtotal();  // Calcula o subtotal de cada item
        }
        return subtotal;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public static int getContadorPedidos() {
        return contadorPedidos;
    }

    public static void setContadorPedidos(int contadorPedidos) {
        Pedido.contadorPedidos = contadorPedidos;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        StringBuilder itensString = new StringBuilder();
        for (ItemPedido item : itens) {
            itensString.append("\n\t").append(item);
        }
        return "Pedido {\n" + "\tNúmero do Pedido: " + numeroPedido +
                ",\n" + "\tItens: " + itensString.toString() + ",\n" +
                "\tStatus: " + status + ",\n" + "\tValor Total: R$ " +
                String.format("%.2f", valorTotal) + "\n" + '}';
    }
}
