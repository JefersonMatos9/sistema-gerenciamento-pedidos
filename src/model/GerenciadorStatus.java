package model;

public class GerenciadorStatus {
    public static void validarMudancaStatus(StatusPedido statusAtual,StatusPedido novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("Novo status não pode ser nulo.");
        }
        if (!isTransicaoValida(statusAtual, novoStatus)) {
            throw new IllegalArgumentException( String.format("Transição inválida: %s para %s", statusAtual, novoStatus));
        }
    }
    private static boolean isTransicaoValida(StatusPedido atual , StatusPedido novo) {
        return switch (atual) {
            case RECEBIDO -> novo == StatusPedido.EM_PREPARACAO;
            case EM_PREPARACAO -> novo == StatusPedido.PRONTO || novo == StatusPedido.CANCELADO;
            case PRONTO -> novo == StatusPedido.ENTREGUE || novo == StatusPedido.CANCELADO;
            case ENTREGUE, CANCELADO -> false;
        };
    }
}

