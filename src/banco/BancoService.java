package banco;

import java.util.ArrayList;
import java.util.List;

public class BancoService {
    private static List<Banco> contas = new ArrayList<>();

    public static void adicionarConta(Banco conta) {
        contas.add(conta);
    }

    public static Banco obterContaPorId(int id) {
        for (Banco conta : contas) {
            if (conta.getId() == id) {
                return conta;
            }
        }
        return null; // Retornar null se a conta não for encontrada
    }
    public static Banco mostrarBancos(){
        for (Banco conta : contas) {
            System.out.println(conta.toString());
        }
        return null;
    }

    // Outros métodos para realizar operações com as contas bancárias, se necessário
}
