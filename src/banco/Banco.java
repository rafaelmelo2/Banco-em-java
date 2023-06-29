package banco;

import java.util.Random;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Banco extends Pessoa {
    private int id;

    boolean validaSaque;
    private int idArmazenado;
    private String loginArmazenado;
    private String senhaArmazenada;
    private String cpfArmazenado;
    private int idadeArmazenada;
    private double saldoArmazenado;
    // private static final String arquivoLogin = "Banco/src/logins/logins.txt";
    private static final String arquivoLogin = "logins.txt";
    AuthenticationPage auth = new AuthenticationPage();
    ErrorPage msg = new ErrorPage();

    @Override
    public void cadastro() {
        super.cadastro();
    }

    Banco() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(9999);
        this.id = numeroAleatorio;
    }

    Banco(int id, String nome, String senha, String cpf, int idade, double saldo) {
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

    public boolean depositar(double valor, String login, String senha) {

        if (auth.autenticarUsuario(login, senha)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLogin))) {
                List<String> linhas = new ArrayList<>();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] campos = linha.split(";");
                    String loginTemp = campos[1];
                    String senhaTemp = campos[2];

                    if (loginTemp.equals(login) && senhaTemp.equals(senha)) {
                        Double tempSaldo = Double.parseDouble(campos[5]) + valor;
                        campos[5] = String.valueOf(tempSaldo);
                        linha = String.join(";", campos);

                    }
                    linhas.add(linha);
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLogin))) {
                    for (String line : linhas) {
                        writer.write(line);
                        writer.newLine();
                    }
                    msg.informar("Depósito executado com sucesso");
                }
            } catch (IOException e) {
                msg.erro("Erro ao ler o arquivo de login: ", e.getMessage());
            }
            return true;
        } else {
            return false;
        }

    }

    public boolean transferir(int idDestinatario, double valor, String login, String senha) {
        if (auth.autenticarUsuario(login, senha)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLogin))) {
                List<String> linhas = new ArrayList<>();
                String linha;

                while ((linha = reader.readLine()) != null) {
                    String[] campos = linha.split(";");
                    int idTemp = Integer.parseInt(campos[0]);

                    String loginTemp = campos[1];
                    String senhaTemp = campos[2];

                    if (loginTemp.equals(login) && senhaTemp.equals(senha)) {
                        if (Double.parseDouble(campos[5]) >= valor) {
                            Double tempSaldoDestinatario = Double.parseDouble(campos[5]) - valor;
                            campos[5] = String.valueOf(tempSaldoDestinatario);
                            linha = String.join(";", campos);
                            msg.informar("Transferência executada com sucesso");

                        } else {
                            msg.informar("Você não tem saldo suficiente");
                        }
                    }

                    if (idTemp == idDestinatario) {
                        Double tempSaldoDestinatario = Double.parseDouble(campos[5]) + valor;
                        campos[5] = String.valueOf(tempSaldoDestinatario);
                        linha = String.join(";", campos);
                    }
                    linhas.add(linha);
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLogin))) {
                    for (String line : linhas) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                msg.erro("Erro ao ler o arquivo de login: ", e.getMessage());
            }
            return true;
        } else {
            return false;
        }

    }

    // public String toString(){
    // return "\nNome: " + super.getNome() +
    // "\nCPF: " + super.getCpf() +
    // "\nIdade: " + super.getIdade() +
    // "\nSaldo: " + super.getSaldo() +
    // "\nID: " + this.id;
    // }
}
