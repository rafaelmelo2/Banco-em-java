package banco;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class SessionPage extends Thread {
    private Banco p1;
    private static boolean validaWhile;
    private int idArmazenado;
    private String loginArmazenado;
    private String senhaArmazenada;
    private String cpfArmazenado;
    private int idadeArmazenada;
    private double saldoArmazenado;

    public SessionPage(int id, String nome, String senha, String cpf, int idade, double saldo) {
        idArmazenado = id;
        loginArmazenado = nome;
        senhaArmazenada = senha;
        cpfArmazenado = cpf;
        idadeArmazenada = idade;
        saldoArmazenado = saldo;
        
        p1 = new Banco(id, nome, senha, cpf, idade, saldo);
    }

    public void exibeSessionPage() {
        Scanner read = new Scanner(System.in);
        while (!validaWhile) {
            System.out.println("\n\n==============Seja bem vindo ao Banco-FAFAS==============\n" +
                    "1. Depositar\n" +
                    "2. Sacar\n" +
                    "3. Transferir\n" +
                    "4. Ver saldo\n" +
                    "5. Sair\n\n");
            int opcao = read.nextInt();
            switch (opcao) {
                case 1:
                    try {
                        String valorDeposita = JOptionPane.showInputDialog("Digite o valor a ser depositado:");
                        p1.depositar(Double.parseDouble(valorDeposita));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ERRO NA CONVERSÃO DE DADOS:\n" + e);
                    }

                    break;
                case 2:
                    break;
                case 3:
                    p1.transferir(2875, 200);
            
                    break;
                case 4:
                    System.out.println("Saldo: "+p1.getSaldo());
                    break;
                case 5:
                    validaWhile = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
        read.close();
    }

    public void run() {
        try {
            this.exibeSessionPage();
        } catch (Exception e) {
            System.out.println("ERRO");
            return;
        }
    }
}
