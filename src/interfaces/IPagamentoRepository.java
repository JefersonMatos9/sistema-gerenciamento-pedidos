package interfaces;

import model.Pagamento;

public interface IPagamentoRepository {

    void salvar(Pagamento pagamento);

    Pagamento buscarPorId(int id);

    void atualizar(Pagamento pagamento);

    void deletar(int id);
}
