package service;

import interfaces.IPagamentoRepository;
import model.GerenciadorTroco;
import model.Pagamento;
import model.Pedido;
import model.Promocoes;
import exception.PagamentoNaoConfirmadoException;
import repository.memory.PagamentoMemoryRepository;

import java.util.logging.Logger;

public class PagamentoService {
    private IPagamentoRepository pagamentoRepository;
    private static final Logger LOGGER = Logger.getLogger(PagamentoService.class.getName());
    private service.ProcessadorDePagamento processadorDePagamento;

    public PagamentoService(IPagamentoRepository pagamentoRepository, service.ProcessadorDePagamento processadorDePagamento) {
        this.pagamentoRepository = pagamentoRepository;
        this.processadorDePagamento = processadorDePagamento;
    }

    public double processarPagamento(Pagamento pagamento, Pedido pedido, double valorRecebido, Promocoes promocoes, GerenciadorTroco caixa) throws PagamentoNaoConfirmadoException {
        double valorPago = processadorDePagamento.processarPagamento(pagamento, pedido, valorRecebido, caixa, promocoes);
        pagamentoRepository.salvar(pagamento);
        LOGGER.info("Pagamento processado e salvo com sucesso");
        return valorPago;
    }
}
