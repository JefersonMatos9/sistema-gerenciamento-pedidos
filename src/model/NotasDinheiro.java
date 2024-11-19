package model;

public enum NotasDinheiro {
    NOTA_100(100),
    NOTA_50(50),
    NOTA_20(20),
    NOTA_10(10),
    NOTA_5(5),
    NOTA_2(2),
    MOEDA_1(1),
    MOEDA_050(0.50),
    MOEDA_025(0.25),
    MOEDA_010(0.10),
    MOEDA_005(0.05);

    private final double valor;

    NotasDinheiro(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
