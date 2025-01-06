/** MUDANÇAS NO PROJETO:

 1. NO PACOTE SERVICE - Classes mantidas, apenas reorganizadas:
 ✓ ProdutoService
 ✓ PedidoService
 ✓ RelatorioService (mantida com seus métodos originais)

 2. NO PACOTE MODEL - Classes mantidas:
 ✓ Produto
 ✓ ItemPedido
 ✓ Pedido
 ✓ Cliente

 3. PACOTE REPOSITORY - Reorganização:
 Original:
 - Cardapio (era uma classe separada)
 - GerenciadorPedidos (era uma classe separada)

 Novo formato:
 - Funcionalidades do Cardapio foram incorporadas ao:
 * IProdutoRepository
 * ProdutoService

 - Funcionalidades do GerenciadorPedidos foram incorporadas ao:
 * IPedidoRepository
 * PedidoService

 4. CLASSES QUE PRECISAM SER REIMPLEMENTADAS:

 - RelatorioService (precisa implementar):
 Métodos:
 - gerarRelatorioDiario()
 - calcularProdutosMaisVendidos()
 - analisarHorarioPico()
 - calcularTicketMedio()
 - analisarCancelamentos()

 - ClienteService (precisa ser criada):
 Métodos:
 - cadastrarCliente()
 - atualizarCliente()
 - buscarPorId()
 - listarTodos()
 - buscarPorTelefone()

 - IClienteRepository (precisa ser criada):
 Métodos:
 - salvar(Cliente cliente)
 - atualizar(Cliente cliente)
 - deletar(Long id)
 - buscarPorId(Long id)
 - listarTodos()
 - buscarPorTelefone(String telefone)

 5. DTOs ADICIONAIS NECESSÁRIOS:

 - ClienteDTO
 Atributos:
 - nome
 - telefone
 - endereco

 - ItemPedidoDTO

 Atributos:
 - produtoId
 - quantidade
 - observacao

 - ResumoPedidoDTO
 Atributos:
 - numero
 - cliente
 - status
 - valorTotal

 SUGESTÃO DE IMPLEMENTAÇÃO ADICIONAL:

 1. ClienteService:
 public class ClienteService {
 private final IClienteRepository repository;

 public ClienteService(IClienteRepository repository) {
 this.repository = repository;
 }

 public Cliente cadastrarCliente(ClienteDTO dto) {
 // implementação
 }
 // demais métodos
 }

 2. IClienteRepository:
 public interface IClienteRepository {
 Cliente salvar(Cliente cliente);
 Cliente atualizar(Cliente cliente);
 void deletar(Long id);
 Optional<Cliente> buscarPorId(Long id);
 List<Cliente> listarTodos();
 Optional<Cliente> buscarPorTelefone(String telefone);
 }

 3. Implementações do ClienteRepository:
 public class ClienteMemoryRepository implements IClienteRepository {
 private List<Cliente> clientes = new ArrayList<>();
 private static Long proximoId = 1L;
 // implementações
 }

 public class ClienteDBRepository implements IClienteRepository {
 private final EntityManager entityManager;
 // implementações
 }
 */