package banco;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Pessoa {
    private String nome;
    private String senha;
    private int idade;
    private String cpf;
    private double saldo = 0;

    boolean validaIdade;
    boolean validaNome;
    boolean validaCpf;
    boolean validaSenha;

    // private static final String arquivoLogin = "Banco/src/logins/logins.txt";
    private static final String arquivoLogin = "logins.txt";

    ErrorPage msg = new ErrorPage();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void cadastro(Banco b) {

        while (!validaNome) {
            try {
                String name = msg.pergunta("Digite seu NOME: ");
                if (name != null && !name.isEmpty()) {
                    this.nome = name;
                    validaNome = true;
                } else {
                    msg.erro("Nome inválido. Tente novamente.");
                }
            } catch (Exception e) {
                msg.erro("ERRO NO VALIDA NOME: ", e.getMessage());
            }
        }
        while (!validaCpf) {
            try {
                String cpf = msg.pergunta("Digite seu CPF:");
                if (cpf != null && !cpf.isEmpty() && cpf.length() == 11) {
                    this.cpf = cpf;
                    validaCpf = true;
                } else {
                    msg.erro("CPF inválido. Tente novamente.");
                }

            } catch (Exception e) {
                msg.erro("ERRO NO VALIDA CPF: ", e.getMessage());
            } catch (Error e) {
                msg.erro("ERRO NO VALIDA CPF: ", e.getMessage());
            }
        }
        while (!validaIdade) {
            try {
                String idadeStr = msg.pergunta("Digite sua idade:");
                if (idadeStr != null && !idadeStr.isEmpty()) {
                    int idade = Integer.parseInt(idadeStr);
                    if (idade >= 0) {
                        this.idade = idade;
                        validaIdade = true;
                    } else {
                        msg.erro("Idade inválida. Tente novamente.");
                    }
                } else {
                    msg.erro("Não é permitido campo vazio.");
                }

            } catch (NumberFormatException e) {
                msg.erro("ERRO NO VALIDA IDADE: ", e.getMessage());
            }
        }
        while (!validaSenha) {
            try {
                String senha = msg.pergunta("Digite sua SENHA:");
                if (senha != null && senha.length() >= 8) {
                    this.senha = senha;
                    validaSenha = true;
                } else {
                    msg.erro("Senha inválida. A senha deve ter pelo menos 8 caracteres.");
                }

            } catch (Exception e) {
                msg.erro("ERRO NO VALIDA SENHA: ", e.getMessage());
            }
        }
        escrevaNoArquivo(arquivoLogin, b.getId() + ";" + this.nome + ";" + this.senha + ";" + this.cpf + ";"
                + this.idade + ";" + this.saldo + ";" + "\n");

    }

    public static void escrevaNoArquivo(String filename, String data) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);

            bufferedWriter.close();

            System.out.println("Dados escritos com sucesso no arquivo.");

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}
