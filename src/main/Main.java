package main;

import enums.CategoriaProduto;
import enums.StatusPedido;
import exception.*;
import model.*;
import repository.memory.PedidoMemoryRepository;
import repository.memory.ProdutoMemoryRepository;
import service.PedidoService;
import service.ProdutoService;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicialização do caixa
            //   GerenciadorTroco gerenciadorTroco = new GerenciadorTroco();
            //   Map<NotasDinheiro, Integer> notasDisponiveis = new HashMap<>();

            //INICIALIZAÇÃO DOS REPOSITORIOS E SERVIÇOS
            ProdutoMemoryRepository produtoRepository = new ProdutoMemoryRepository();
            ProdutoService produtoService = new ProdutoService(produtoRepository);

            PedidoMemoryRepository pedidoRepository = new PedidoMemoryRepository();
            PedidoService pedidoService = new PedidoService(pedidoRepository);

            //CRIAÇÃO DOS PRODUTOS
            Produto coxinha = new Produto("Coxinha", "Coxinha de Frango", 5.00, CategoriaProduto.SALGADOS, 3);
            Produto pastelCarne = new Produto("Pastel Carne", "Pastel de Carne", 7.99, CategoriaProduto.SALGADOS, 8);
            //Produto cocaCola = new Produto("Coca-Cola Lata", "Coca Cola Lata", 3.99, CategoriaProduto.BEBIDAS, 20);
            //Produto pudim = new Produto("Pudim", "pudim", 7.50, CategoriaProduto.SOBREMESAS, 10);
            //Produto xTudo = new Produto("x-tudo", "x-tudo comum", 26.99, CategoriaProduto.LANCHES, 20);
            //Produto xBacon = new Produto("x-bacon", "x-bacon", 28.99, CategoriaProduto.LANCHES, 20);

            //SALVANDO OS PRODUTOS NA LISTA
            produtoService.cadastrarProduto(coxinha);
            produtoService.cadastrarProduto(pastelCarne);
            //produtoService.cadastrarProduto(cocaCola);
            //produtoService.cadastrarProduto(pudim);
            //produtoService.cadastrarProduto(xTudo);
            //produtoService.cadastrarProduto(xBacon);

            //ATUALIZANDO PRODUTOS
            //service.atualizarProduto("Coxinha", 10);

            //BUSCANDO PRODUTOS POR NOME
            //service.buscarProdutoPorNome("Pastel Carne");

            //DELETANDO PRODUTOS
            //service.deletarProduto(coxinha);

            //BUSCANDO PRODUTOS POR CATEGORIA
            //System.out.println(service.buscarProdutoPorCategoria(CategoriaProduto.SALGADOS));

            //LISTANDO TODOS OS PRODUTOS
            //produtoService.listarTodosOsProdutos();

            //CRIANDO ITENS DE PEDIDO
            ItemPedido item1 = new ItemPedido(coxinha, 2, "Maionese e ketchup");
            ItemPedido item2 = new ItemPedido(pastelCarne,2,"");

            //CRIANDO E CADASTRANDO UM PEDIDO
            Pedido pedido = new Pedido();
            pedido.adicionarItem(item1);
            pedido.adicionarItem(item2);
            pedidoService.cadastrarPedido(pedido);

            Pedido pedido1 = new Pedido();
            pedido1.adicionarItem(item2);
            pedidoService.cadastrarPedido(pedido1);

            //ATUALIZANDO PEDIDO - BUSCANDO E MODIFICANDO O STATUS
            Pedido pedidoExistente = pedidoService.buscarPedidoPorNumero(pedido.getNumeroPedido());
            pedidoExistente.setStatus(StatusPedido.RECEBIDO);
            pedidoService.atualizarPedido(pedidoExistente);

            //LISTANDO TODOS OS PEDIDOS
            System.out.println("Listando todos os pedidos: " + pedidoService.listarTodosOsPedidos());

produtoService.buscarProdutoPorNome("Coxinha");


            //CRIAÇÃO DO CARDAPIO
          //  Cardapio cardapio = new Cardapio();

            //  cardapio.adicionarProduto(coxinha);
            //  cardapio.adicionarProduto(pastelCarne);
            //  cardapio.adicionarProduto(cocaCola);
            // cardapio.adicionarProduto(pudim);
            //   cardapio.adicionarProduto(xBacon);
            //   cardapio.adicionarProduto(xTudo);

            //   System.out.println(cardapio.buscarProdutoPorCategoria(CategoriaProduto.LANCHES));

            //  System.out.println(cardapio.listarTodosProdutos());

            //   cardapio.atualizarProduto("Coxinha", 5, 5.99);


            // Criação dos itens do pedido
            //   ItemPedido item1 = new ItemPedido(coxinha, 2, "Por favor traga muita maionese");
            //   ItemPedido item2 = new ItemPedido(pastelCarne, 2, "Trazer Vinagrete");
            //  ItemPedido item3 = new ItemPedido(cocaCola, 2, "Gelada");

            // Criação dos pedidos
            //  Pedido pedido1 = new Pedido();
            //   pedido1.adicionarItem(item1);
            //   pedido1.adicionarItem(item2);
            //   pedido1.adicionarItem(item3);

            //  Pedido pedido2 = new Pedido();
            //pedido2.adicionarItem(new ItemPedido(cocaCola, 2, "Bem gelada"));


            // Processamento dos pagamentos
            // Pagamento pagamentoDinheiro = new Pagamento(FormaPagamento.DINHEIRO, gerenciadorTroco);
            //double valorFinalPedido1 = pagamentoDinheiro.processarPagamento(pedido1, 200.00);
            // System.out.println("Valor final do pedido 1: R$ " + String.format("%.2f", valorFinalPedido1));

            //  Pagamento pagamentoPix = new Pagamento(FormaPagamento.PIX, gerenciadorTroco);
            //  double valorFinalPedido2 = pagamentoPix.processarPagamento(pedido2, 7.18);
            //  System.out.println("Valor final do pedido 2: R$ " + String.format("%.2f", valorFinalPedido2));

            //System.out.println("Lucro Total: R$ " + String.format("%.2f", gerenciadorTroco.getLucroTotal()));
            // System.out.println("Valor em caixa: R$ " + String.format("%.2f", gerenciadorTroco.getTotalDisponivel()));

        } catch (PrecoNegativoException | ProdutoNegativoException e) {
            System.err.println("ERRO: " + e.getMessage());
        } catch (EstoqueInsuficienteException e) {
            throw new RuntimeException(e);
        }
    }
}