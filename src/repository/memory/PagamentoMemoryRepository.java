package repository.memory;

import interfaces.IPagamentoRepository;
import model.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoMemoryRepository implements IPagamentoRepository {

    private List<Pagamento> pagamentos = new ArrayList<>();

    @Override
    public void salvar(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    @Override
    public Pagamento buscarPorId(int id) {
        return pagamentos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void atualizar(Pagamento pagamento) {
        int index = -1;
        for (int i = 0; i < pagamentos.size(); i++) {
            if (pagamentos.get(i).getId() == pagamento.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            pagamentos.set(index, pagamento);
        }
    }

    @Override
    public void deletar(int id) {
        pagamentos.removeIf(p -> p.getId() == id);
    }
}
