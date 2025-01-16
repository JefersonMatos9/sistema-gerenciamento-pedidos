package main;

import enums.CategoriaProduto;
import enums.FormaPagamento;
import enums.StatusPedido;
import exception.*;
import model.*;
import repository.memory.PagamentoMemoryRepository;
import repository.memory.PedidoMemoryRepository;
import repository.memory.ProdutoMemoryRepository;
import service.PagamentoService;
import service.PedidoService;
import service.ProcessadorDePagamento;
import service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        try {

            //INICIALIZAÇÃO DOS REPOSITORIOS E SERVIÇOS
            ProdutoMemoryRepository produtoRepository = new ProdutoMemoryRepository();
            ProdutoService produtoService = new ProdutoService(produtoRepository);

            PedidoMemoryRepository pedidoRepository = new PedidoMemoryRepository();
            PedidoService pedidoService = new PedidoService(pedidoRepository);

            PagamentoMemoryRepository pagamentoRepository = new PagamentoMemoryRepository();
            ProcessadorDePagamento processadorDePagamento = new ProcessadorDePagamento();
            PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, processadorDePagamento);

            //INICIALIZAÇÃO DO GERENCIADOR DE TROCO E PROMOÇÕES
            GerenciadorTroco gerenciadorTroco = new GerenciadorTroco();
            Promocoes promocoes = new Promocoes();

            //CRIAÇÃO DOS PRODUTOS
            Produto produto = new Produto();
            produto.setId(1);
            produto.setNome("Coxinha");
            produto.setDescricao("Coxinha de Frango");
            produto.setPreco(6.99);
            produto.setQuantidade(10);
            produto.setCategoria(CategoriaProduto.SALGADOS);
            produto.setDisponivel();

            //SALVANDO OS PRODUTOS NA LISTA
            produtoService.cadastrarProduto(produto);
            //produtoService.cadastrarProduto(pastelCarne);
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
            ItemPedido item1 = new ItemPedido(produto, 2, "Maionese e ketchup");



            //CRIANDO E CADASTRANDO UM PEDIDO
            Pedido pedido = new Pedido();
            pedido.adicionarItem(item1);
            pedidoService.cadastrarPedido(pedido);

            //LISTANDO PEDIDO
            System.out.println(pedidoService.buscarPedidoPorNumero(1));

            //REMOVENDO ITEM DO PEDIDO
            pedido.removerItem(item1);

            //LISTANDO O PEDIDO APOS REMOÇÃO DO ITEM
            System.out.println(pedidoService.buscarPedidoPorNumero(1));

            Pedido pedido1 = new Pedido();
            pedido1.adicionarItem(item1);
            //pedidoService.cadastrarPedido(pedido1);

            Pedido pedido2 = new Pedido();
            pedido2.adicionarItem(item1);
            pedidoService.cadastrarPedido(pedido2);

            //ATUALIZANDO PEDIDO - BUSCANDO E MODIFICANDO O STATUS
            Pedido pedidoExistente = pedidoService.buscarPedidoPorNumero(pedido.getNumeroPedido());
            pedidoExistente.setStatus(StatusPedido.RECEBIDO);
            pedidoService.atualizarPedido(pedidoExistente);


            //LISTANDO TODOS OS PEDIDOS
            // System.out.println("Listando todos os pedidos: " + pedidoService.listarTodosOsPedidos());

            //FINALIZANDO O PEDIDO
              pedido.finalizarPedido();
              pedido2.finalizarPedido();

            //INICIANDO O CAIXA COM VALOR INICIAL ]
            gerenciadorTroco.atualizarCaixa(200.00, 0.00);

            //PROCESSAMENTO DOS PAGAMENTOS
            Pagamento pagamentoDinheiro = new Pagamento(FormaPagamento.DINHEIRO);
            pagamentoDinheiro.setPromocao(false); //desativando promoção
            double valorFinalPedido1 = pagamentoService.processarPagamento(pagamentoDinheiro, pedido1, 200.00, promocoes, gerenciadorTroco);
            System.out.println("Valor final do pedido 1: R$ " + String.format("%.2f", valorFinalPedido1));

            Pagamento pagamentoPix = new Pagamento(FormaPagamento.PIX);
            pagamentoPix.setPromocao(true); //ativando promocao
            double valorFinalPedido2 = pagamentoService.processarPagamento(pagamentoPix, pedido2, 13.5, promocoes, gerenciadorTroco);
            System.out.println("Valor final do pedido 2: R$ " + String.format("%.2f", valorFinalPedido2));

            System.out.println("Lucro Total: R$ " + String.format("%.2f", gerenciadorTroco.getLucroTotal()));
            System.out.println("Valor em caixa: R$ " + String.format("%.2f", gerenciadorTroco.getTotalDisponivel()));

            System.out.println("Quantidade de coxinha atualizada " + produto.getQuantidade());


        } catch (PrecoNegativoException | ProdutoNegativoException e) {
            System.err.println("ERRO: " + e.getMessage());
        } catch (EstoqueInsuficienteException | PagamentoNaoConfirmadoException e) {
            throw new RuntimeException(e);
        }
    }
}