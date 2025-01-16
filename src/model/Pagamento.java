package model;

import enums.FormaPagamento;
import java.util.logging.Logger;

public class Pagamento {
    private final FormaPagamento formaPagamento;
    private double valorRecebido;
    private double valorPago;
    private int id;
    private static final Logger logger = Logger.getLogger(Pagamento.class.getName());
    private boolean promocaoAtiva;

    public Pagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isPromocaoAtiva() {
        return promocaoAtiva;
    }

    public void setPromocao(boolean promocaoAtiva) {
        this.promocaoAtiva = promocaoAtiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
