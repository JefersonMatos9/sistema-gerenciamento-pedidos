/**package model;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.logging.Logger;

public class GerenciadorTroco {
    private double totalDisponivel;
    private double lucroTotal;
    private static final Logger logger = Logger.getLogger(GerenciadorTroco.class.getName());
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public GerenciadorTroco() {
        this.totalDisponivel = 0.0;
        this.lucroTotal = 0.0;
    }

    public void inicializarCaixa(Map<NotasDinheiro, Integer> notas) {
        this.totalDisponivel = 0.0;
        for (Map.Entry<NotasDinheiro, Integer> entry : notas.entrySet()) {
            NotasDinheiro nota = entry.getKey();
            Integer quantidade = entry.getValue();
            this.totalDisponivel += nota.getValor() * quantidade;
        }
        logger.info("Caixa inicializado com R$ " + df.format(totalDisponivel));
    }

    public void atualizarCaixa(double valorRecebido, double troco) {
        this.totalDisponivel = this.totalDisponivel + valorRecebido - troco;
        logger.info("Caixa atualizado - Novo total: R$ " + df.format(totalDisponivel));
    }

    public void adicionarLucro(double valor) {
        this.lucroTotal += valor;
        logger.info("Lucro adicionado: R$ " + df.format(valor) + " - Lucro Total: R$ " + df.format(lucroTotal));
    }

    public double getTotalDisponivel() {
        return totalDisponivel;
    }

    public double getLucroTotal() {
        return lucroTotal;
    }
}
*/