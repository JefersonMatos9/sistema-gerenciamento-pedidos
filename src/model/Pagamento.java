package model;

import exception.PagamentoNaoConfirmadoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Logger;

public class Pagamento {
    private final FormaPagamento formaPagamento;
    private double valorRecebido;
    private double valorPago;
    private final GerenciadorTroco gerenciadorTroco;
    private Pedido pedido;
    private static final Logger logger = Logger.getLogger(Pagamento.class.getName());
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");
    //ATIVAR A PROMOÇÃO OU NAO
    private static boolean promocaoAtiva = false;

    public Pagamento(FormaPagamento formaPagamento, GerenciadorTroco gerenciadorTroco) {
        this.formaPagamento = formaPagamento;
        this.gerenciadorTroco = gerenciadorTroco;
    }

    public double processarPagamento(Pedido pedido, double valorRecebido) throws PagamentoNaoConfirmadoException {
        this.pedido = pedido;
        this.valorRecebido = valorRecebido;

        if (pedido == null || pedido.getItens().isEmpty()) {
            throw new PagamentoNaoConfirmadoException("Pedido inválido ou vazio");
        }

        double valorTotal = pedido.getValorTotal();
        double valorComDesconto = aplicarDesconto(valorTotal);

        switch (formaPagamento) {
            case DINHEIRO -> processarPagamentoDinheiro(valorComDesconto);
            case PIX, CARTAO_DEBITO, CARTAO_CREDITO -> processarPagamentoEletronico(valorComDesconto);
        }

        this.valorPago = valorComDesconto;
        logger.info("Pagamento finalizado com sucesso - Valor: R$ " + df.format(valorComDesconto));
        return valorComDesconto;
    }

    private void processarPagamentoDinheiro(double valorComDesconto) throws PagamentoNaoConfirmadoException {
        if (valorRecebido < valorComDesconto) {
            throw new PagamentoNaoConfirmadoException("Valor recebido insuficiente. Necessário: R$ " +
                    df.format(valorComDesconto) + ", Recebido: R$ " + df.format(valorRecebido));
        }

        double troco = valorRecebido - valorComDesconto;

        if (troco > 0 && troco > gerenciadorTroco.getTotalDisponivel()) {
            throw new PagamentoNaoConfirmadoException("Não há troco disponível. Troco necessário: R$ " +
                    df.format(troco) + ", Disponível: R$ " + df.format(gerenciadorTroco.getTotalDisponivel()));
        }

        gerenciadorTroco.atualizarCaixa(valorRecebido, troco);
        gerenciadorTroco.adicionarLucro(valorComDesconto);
        logger.info("Pagamento em dinheiro processado - Valor: R$ " + df.format(valorRecebido) +
                ", Troco: R$ " + df.format(troco));
    }

    private void processarPagamentoEletronico(double valorComDesconto) throws PagamentoNaoConfirmadoException {
        BigDecimal valorEsperado = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorRecebidoBD = new BigDecimal(valorRecebido).setScale(2, RoundingMode.HALF_EVEN);

        if (valorRecebidoBD.compareTo(valorEsperado) != 0) {
            throw new PagamentoNaoConfirmadoException("Valor incorreto para pagamento eletrônico. Esperado: R$ " +
                    df.format(valorComDesconto) + ", Recebido: R$ " + df.format(valorRecebido));
        }
        gerenciadorTroco.adicionarLucro(valorComDesconto);
        logger.info("Pagamento eletrônico processado - Valor: R$ " + df.format(valorComDesconto));
    }

    private double aplicarDesconto(double valor) {
        if (!promocaoAtiva) {
            return valor;
        }
        double percentualDesconto = switch (formaPagamento) {
            case PIX, DINHEIRO ->  0.10;     // 10% de desconto
            case CARTAO_DEBITO ->  0.05;     // 5% de desconto
            case CARTAO_CREDITO -> 0.0;           // sem desconto
        };
        double desconto = valor * percentualDesconto;
        double valorComDesconto = valor - desconto;
        // Usando BigDecimal para cálculo preciso
        // Usando BigDecimal para precisão no cálculo
        BigDecimal valorFinal = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.HALF_EVEN);
        return valorFinal.doubleValue();
    }

    public static void ativarPromocao(boolean status) {
        promocaoAtiva = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public double getValorPago() {
        return valorPago;
    }

    public Pedido getPedido() {
        return pedido;
    }
}