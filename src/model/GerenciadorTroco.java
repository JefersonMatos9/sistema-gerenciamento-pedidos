package model;

import java.util.logging.Logger;

public class GerenciadorTroco {
    private double totalDisponivel;
    private double lucroTotal;
    private static final Logger logger = Logger.getLogger(GerenciadorTroco.class.getName());

    public GerenciadorTroco() {
        this.totalDisponivel = 0.0;
        this.lucroTotal = 0.0;
    }

    public void adicionarLucro(double valor) {
        this.lucroTotal += valor;
        logger.info("Lucro adicionado: R$ " + valor + " - Lucro Total: R$ " + lucroTotal);
    }

    public double getTotalDisponivel() {
        return totalDisponivel;
    }

    public double getLucroTotal() {
        return lucroTotal;
    }

    public void atualizarCaixa(double valorRecebido, double troco) {
        this.totalDisponivel = this.totalDisponivel + valorRecebido - troco;
        logger.info("Caixa atualizado - Novo total: R$ " + totalDisponivel);
    }
}
