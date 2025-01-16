package service;

import enums.FormaPagamento;
import exception.PagamentoNaoConfirmadoException;
import model.GerenciadorTroco;
import model.Pagamento;
import model.Pedido;
import model.Promocoes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

public class ProcessadorDePagamento {
    private static final Logger logger = Logger.getLogger(ProcessadorDePagamento.class.getName());

    public double processarPagamento(Pagamento pagamento, Pedido pedido, double valorRecebido, GerenciadorTroco caixa, Promocoes promocoes) throws PagamentoNaoConfirmadoException {
        if (pedido == null || pedido.getItens().isEmpty()) {
            throw new PagamentoNaoConfirmadoException("Pedido inválido ou vazio");
        }

        double valorTotal = pedido.getValorTotal();
        double precoFinal = promocoes.calcularDesconto(pagamento.getFormaPagamento(), valorTotal, pagamento.isPromocaoAtiva());

        if (pagamento.getFormaPagamento() == FormaPagamento.DINHEIRO) {
            processarPagamentoDinheiro(precoFinal, valorRecebido, caixa);
        } else {
            processarPagamentoEletronico(precoFinal, valorRecebido, caixa);
        }

        logger.info("Pagamento finalizado com sucesso - Valor: R$ " + precoFinal);
        pagamento.setValorPago(precoFinal);
        return precoFinal;
    }

    private void processarPagamentoDinheiro(double valorComDesconto, double valorRecebido, GerenciadorTroco caixa) throws PagamentoNaoConfirmadoException {
        if (valorRecebido < valorComDesconto) {
            throw new PagamentoNaoConfirmadoException("Valor recebido insuficiente. Necessário: R$ " +
                    valorComDesconto + ", Recebido: R$ " + valorRecebido);
        }

        double troco = valorRecebido - valorComDesconto;

        if (troco > 0 && troco > caixa.getTotalDisponivel()) {
            throw new PagamentoNaoConfirmadoException("Não há troco disponível. Troco necessário: R$ " +
                    troco + ", Disponível: R$ " + caixa.getTotalDisponivel());
        }

        caixa.atualizarCaixa(valorRecebido, troco);
        caixa.adicionarLucro(valorComDesconto);
        logger.info("Pagamento em dinheiro processado - Valor: R$ " + valorRecebido + ", Troco: R$ " + troco);
    }

    private void processarPagamentoEletronico(double valorComDesconto, double valorRecebido, GerenciadorTroco caixa) throws PagamentoNaoConfirmadoException {
        BigDecimal valorEsperado = new BigDecimal(valorComDesconto).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorRecebidoBD = new BigDecimal(valorRecebido).setScale(2, RoundingMode.HALF_EVEN);

        if (valorRecebidoBD.compareTo(valorEsperado) != 0) {
            throw new PagamentoNaoConfirmadoException("Valor incorreto para pagamento eletrônico. Esperado: R$ " +
                    valorComDesconto + ", Recebido: R$ " + valorRecebido);
        }

        caixa.adicionarLucro(valorComDesconto);
        logger.info("Pagamento eletrônico processado - Valor: R$ " + valorComDesconto);
    }
}
