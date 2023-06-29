package banco;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Locale.Category;

import javax.swing.JOptionPane;

public class SessionPage extends Thread {
    private Banco p1;
    private boolean validaWhile;
    private int idArmazenado;
    private String loginArmazenado;
    private String senhaArmazenada;
    private String cpfArmazenado;
    private int idadeArmazenada;
    private double saldoArmazenado;

    ErrorPage msg = new ErrorPage();

    SessionPage(int id, String nome, String senha, String cpf, int idade, double saldo) {
        this.idArmazenado = id;
        this.loginArmazenado = nome;
        this.senhaArmazenada = senha;
        this.cpfArmazenado = cpf;
        this.idadeArmazenada = idade;
        this.saldoArmazenado = saldo;

        p1 = new Banco(id, nome, senha, cpf, idade, saldo);
    }

    public void exibeSessionPage() {
        Scanner read = new Scanner(System.in);

        while (!validaWhile) {
            String opcao = msg.pergunta("\n\n==============Seja bem vindo(a) " + p1.getNome().toUpperCase()
                    + " ao Banco-FAFAS==============\n1. Depositar\n2. Transferir\n3. Sair\n\nSaldo: "
                    + this.saldoArmazenado + "\nMeu Id: " + p1.getId() + "\n\n");

            // String opcao = JOptionPane.showInputDialog("\n\n==============Seja bem
            // vindo(a) "+p1.getNome() +" ao Banco-FAFAS==============\n" +
            // "1. Depositar\n" +
            // "2. Transferir\n" +
            // "3. Sair\n"+
            // "Saldo: " + this.saldoArmazenado+
            // "\n\n");
            switch (Integer.parseInt(opcao)) {
                case 1:
                    try {
                        String valorDeposita = msg.pergunta("Digite o valor a ser depositado:");// JOptionPane.showInputDialog("Digite
                                                                                                // o valor a ser
                                                                                                // depositado:");
                        if (p1.depositar(Double.parseDouble(valorDeposita), this.loginArmazenado,
                                this.senhaArmazenada)) {
                            this.saldoArmazenado = this.saldoArmazenado + Double.parseDouble(valorDeposita);
                        }
                    } catch (NumberFormatException e) {
                        msg.erro("Alerta erro: ", e.getMessage());
                        // JOptionPane.showMessageDialog(null, "Alerta erro: "
                        // +e.getMessage(),"Alerta",JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2:
                    try {
                        String valorTransfere = msg.pergunta("Digite o valor a ser transferido:");// JOptionPane.showInputDialog("Digite
                                                                                                  // o valor a ser
                                                                                                  // transferido:");
                        String idTransfere = msg.pergunta("Digite o ID do destinatário:"); // JOptionPane.showInputDialog("Digite
                                                                                           // o ID do destinatário:");
                        if (p1.transferir(Integer.parseInt(idTransfere), Double.parseDouble(valorTransfere),
                                this.loginArmazenado, this.senhaArmazenada)) {
                            this.saldoArmazenado = this.saldoArmazenado - Double.parseDouble(valorTransfere);
                        }
                    } catch (NumberFormatException e) {
                        msg.erro("Erro: " + e.getMessage());
                        // JOptionPane.showMessageDialog(null, "Alerta: "+e.getMessage(), "Alerta" ,
                        // JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:
                    validaWhile = true;

                    msg.informar("Fazendo logoff....");
                    // JOptionPane.showMessageDialog(null, "Fazendo logoff!", "Sucesso",
                    // JOptionPane.INFORMATION_MESSAGE);
                    return;
                default:
                    msg.erro("Opção inválida, digite novamente!");
                    // JOptionPane.showMessageDialog(null, "Opção inválida" );
            }
            // if (opcao == null) {
            // // JOptionPane foi fechado pelo botão "X" ou "Cancelar"
            // interrupt();
            // System.out.println("JOptionPane foi fechado pelo botão \"X\" ou
            // \"Cancelar\"");
            // } else {
            // // JOptionPane foi fechado pelo botão "OK" ou digitou algo
            // interrupt();
            // System.out.println("Opção selecionada: " + opcao);
            // }
        }
        read.close();
    }

    public void run() {
        try {
            this.exibeSessionPage();
        } catch (Exception e) {
            msg.erro("ERRO exception run: ", e.getMessage());
            // System.out.println("ERRO exception run: " +e.getMessage());
            return;
        }
    }
}
