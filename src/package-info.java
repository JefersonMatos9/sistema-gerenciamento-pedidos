/* Classe principal que representa um Produto do cardápio
public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria; // Exemplo: Lanches, Bebidas, Sobremesas
    private boolean disponivel;

    // TODO: Implementar construtor

    // TODO: Implementar getters e setters

    // TODO: Implementar toString() para exibição formatada do produto
}

// Classe que representa um Item do pedido
public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private String observacao;
    private double subtotal;

    // TODO: Implementar construtor

    // TODO: Implementar getters e setters

    // TODO: Implementar método para calcular subtotal
    public double calcularSubtotal() {
        // Implementar cálculo: preço do produto * quantidade
    }
}

// Classe que representa um Pedido completo
public class Pedido {
    private int numeroPedido;
    private List<ItemPedido> itens;
    private String cliente;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private FormaPagamento formaPagamento;
    private double valorTotal;

    // TODO: Implementar construtor

    // TODO: Implementar métodos para:
    // - Adicionar item ao pedido
    // - Remover item do pedido
    // - Calcular valor total
    // - Alterar status do pedido
    // - Aplicar desconto
}

// Enum para status do pedido
public enum StatusPedido {
    RECEBIDO,
    EM_PREPARACAO,
    PRONTO,
    ENTREGUE,
    CANCELADO
}

// Enum para formas de pagamento
public enum FormaPagamento {
    DINHEIRO,
    CARTAO_CREDITO,
    CARTAO_DEBITO,
    PIX
}

// Classe para gerenciar o cardápio
public class Cardapio {
    private List<Produto> produtos;

    // TODO: Implementar métodos para:
    // - Adicionar produto
    // - Remover produto
    // - Buscar produto por id
    // - Buscar produtos por categoria
    // - Listar todos os produtos
    // - Atualizar produto
}

// Classe para gerenciar os pedidos
//public class GerenciadorPedidos {
  //  private List<Pedido> pedidos;
    //private int ultimoNumeroPedido;

    // TODO: Implementar métodos para:
    // - Criar novo pedido
    // - Buscar pedido por número
    // - Listar pedidos por status
    // - Atualizar status do pedido
    // - Calcular faturamento do dia
    // - Listar pedidos do dia
//}

// Classe para relatórios e estatísticas
//public class Relatorios {
  //  private GerenciadorPedidos gerenciador;

    // TODO: Implementar métodos para:
    // - Gerar relatório de vendas diário
    // - Calcular produto mais vendido
    // - Calcular horário de pico
    // - Calcular ticket médio
    // - Listar cancelamentos
}
*/