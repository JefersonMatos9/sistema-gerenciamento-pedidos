package main;

import exception.*;
import model.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicialização do caixa
            GerenciadorTroco gerenciadorTroco = new GerenciadorTroco();
            Map<NotasDinheiro, Integer> notasDisponiveis = new HashMap<>();
            notasDisponiveis.put(NotasDinheiro.MOEDA_050, 10);
            notasDisponiveis.put(NotasDinheiro.NOTA_50, 10);
            notasDisponiveis.put(NotasDinheiro.NOTA_20, 10);
            notasDisponiveis.put(NotasDinheiro.NOTA_5, 10);
            gerenciadorTroco.inicializarCaixa(notasDisponiveis);

            // Criação dos produtos
            Produto coxinha = new Produto("Coxinha", "Coxinha de Frango", 5.00, CategoriaProduto.SALGADOS, 3);
            Produto pastelCarne = new Produto("Pastel Carne", "Pastel de Carne", 7.99, CategoriaProduto.SALGADOS, 8);
            Produto cocaCola = new Produto("Coca-Cola Lata", "Coca Cola Lata", 3.99, CategoriaProduto.BEBIDAS, 20);

            // Criação dos itens do pedido
            ItemPedido item1 = new ItemPedido(coxinha, 2, "Por favor traga muita maionese");
            ItemPedido item2 = new ItemPedido(pastelCarne, 2, "Trazer Vinagrete");
            ItemPedido item3 = new ItemPedido(cocaCola, 2, "Gelada");

            // Criação dos pedidos
            Pedido pedido1 = new Pedido();
            pedido1.adicionarItem(item1);
            pedido1.adicionarItem(item2);
            pedido1.adicionarItem(item3);

            Pedido pedido2 = new Pedido();
            pedido2.adicionarItem(new ItemPedido(cocaCola, 2, "Bem gelada"));


            // Processamento dos pagamentos
            Pagamento pagamentoDinheiro = new Pagamento(FormaPagamento.DINHEIRO, gerenciadorTroco);
            double valorFinalPedido1 = pagamentoDinheiro.processarPagamento(pedido1, 200.00);
            System.out.println("Valor final do pedido 1: R$ " + String.format("%.2f", valorFinalPedido1));

            Pagamento pagamentoPix = new Pagamento(FormaPagamento.PIX, gerenciadorTroco);
            double valorFinalPedido2 = pagamentoPix.processarPagamento(pedido2, 7.18);
            System.out.println("Valor final do pedido 2: R$ " + String.format("%.2f", valorFinalPedido2));

            System.out.println("Lucro Total: R$ " + String.format("%.2f", gerenciadorTroco.getLucroTotal()));
            System.out.println("Valor em caixa: R$ " + String.format("%.2f", gerenciadorTroco.getTotalDisponivel()));

        } catch (PrecoNegativoException | ProdutoNegativoException |
                 EstoqueInsuficienteException | PagamentoNaoConfirmadoException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}