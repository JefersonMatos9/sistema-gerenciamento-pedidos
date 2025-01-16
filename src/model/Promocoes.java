package model;

import enums.FormaPagamento;

public class Promocoes {
    public double calcularDesconto(FormaPagamento formaPagamento, double valor, boolean promocaoAtiva) {
        if (!promocaoAtiva) {
            return valor;
        }
        double percentualDesconto = switch (formaPagamento) {
            case PIX, DINHEIRO -> 0.10;
            case CARTAO_DEBITO -> 0.05;
            case CARTAO_CREDITO -> 0.0;
        };
        return valor - (valor * percentualDesconto);
    }
}
