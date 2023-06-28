package banco;
import java.util.Random;

public class Banco extends Pessoa{
    private int id;

    boolean validaSaque;
    
    @Override
    public void cadastro() {
        System.out.println("1");
        super.cadastro();
    }

    Banco(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(9999);
        this.id = numeroAleatorio;
    }
    
    Banco(int id, String nome, String senha, String cpf, int idade, double saldo){
        this.id = id;
        super.setNome(nome);
        super.setSenha(senha);
        super.setCpf(cpf);
        super.setIdade(idade);
        super.setSaldo(saldo);
    }

    
    public void setId(int id) {
        this.id = id;
    }     
    public int getId() {
        return id;
    }
    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    public void sacar(double valor){
        
        while(!validaSaque){
            if( super.getSaldo() < valor){
                System.out.println("Valor de saque é maior que saldo em conta!");
                return;
            }else{
                System.out.println("Você sacou: R$" + valor);
                super.setSaldo(super.getSaldo() - valor);
                validaSaque = true;
            }
        }
        
    }
    public void depositar(double valor){
        super.setSaldo(super.getSaldo() + valor);
    }
    public void transferir(int idDestinatario, double valor) {
        Banco destinatario = BancoService.obterContaPorId(idDestinatario); // Supondo que existe uma classe BancoService com um método para obter a conta pelo ID
        if (destinatario != null) {
            if (getSaldo() >= valor) {
                setSaldo(getSaldo() - valor);
                destinatario.setSaldo(destinatario.getSaldo() + valor);
                System.out.println("Transferência realizada com sucesso: R$" + valor);
            } else {
                System.out.println("Saldo insuficiente para realizar a transferência.");
            }
        } else {
            System.out.println("Destinatário não encontrado.");
        }
    }

    public String toString(){
        return "\nNome: " + super.getNome() + 
        "\nCPF: " + super.getCpf() + 
        "\nIdade: " + super.getIdade() + 
        "\nSaldo: " + super.getSaldo() + 
        "\nID: " + this.id;
    }


}
