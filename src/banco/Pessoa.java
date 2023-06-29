package banco;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class Pessoa{
    private String nome;
    private String senha;
    private int idade;
    private String cpf;
    private double saldo = 0;
    

    
    //valida-parametros
    boolean validaIdade;
    boolean validaNome;
    boolean validaCpf;
    boolean validaSenha;

    
    
    private static final String arquivoLogin = "Banco/src/logins/logins.txt";
    
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

    

    public void cadastro(Banco b){

        while(!validaNome){
            try{
                String name = msg.pergunta("Digite seu NOME: ");//JOptionPane.showInputDialog("Digite seu NOME: ");            
                if (name != null && !name.isEmpty()) {
                    this.nome = name;
                    validaNome = true;
                } else {
                    msg.erro("Nome inválido. Tente novamente.");
                    //System.out.println("Nome inválido. Tente novamente.");
                }
            }catch(Exception e){
                msg.erro("ERRO NO VALIDA NOME: ", e.getMessage());
                //System.out.println("ERRO NO VALIDA NOME: " + e.getMessage());
            }   
        }
        while(!validaCpf){
            try{
                String cpf = msg.pergunta("Digite seu CPF:");//JOptionPane.showInputDialog("Digite seu CPF:");
                if (cpf != null && !cpf.isEmpty() && cpf.length() == 11) {
                    this.cpf = cpf;
                    validaCpf = true;
                } else {
                    msg.erro("CPF inválido. Tente novamente.");
                    //System.out.println("CPF inválido. Tente novamente.");
                }

            }catch(Exception e){
                msg.erro("ERRO NO VALIDA CPF: ", e.getMessage());
                //System.out.println("ERRO NO VALIDA CPF: " + e.getMessage());
            }catch(Error e){
                msg.erro("ERRO NO VALIDA CPF: ", e.getMessage());
            }  
        }
        while(!validaIdade){
            try{
                String idadeStr = msg.pergunta("Digite sua idade:");//JOptionPane.showInputDialog("Digite sua idade:");
                if (idadeStr != null && !idadeStr.isEmpty()) {
                    int idade = Integer.parseInt(idadeStr);
                    if (idade >= 0) {
                        this.idade = idade;
                        validaIdade = true;
                    } else {
                        msg.erro("Idade inválida. Tente novamente.");
                        //System.out.println("Idade inválida. Tente novamente.");
                    }
                } else {
                    msg.erro("Não é permitido campo vazio.");
                    //System.out.println("Idade inválida. Tente novamente.");
                }

            }catch(NumberFormatException e){
                msg.erro("ERRO NO VALIDA IDADE: ",e.getMessage());
                //System.out.println("ERRO NO VALIDA IDADE: "+ e.getMessage());
            }   
        }
        while(!validaSenha){
            try{
                String senha = msg.pergunta("Digite sua SENHA:");//JOptionPane.showInputDialog("Digite sua SENHA:");
                if (senha != null && senha.length() >= 8) {
                    this.senha = senha;
                    validaSenha = true;
                } else {
                    msg.erro("Senha inválida. A senha deve ter pelo menos 8 caracteres.");
                    //System.out.println("Senha inválida. A senha deve ter pelo menos 8 caracteres.");
                }

            }catch(Exception e){
                msg.erro("ERRO NO VALIDA SENHA: ", e.getMessage());
                //System.out.println("ERRO NO VALIDA SENHA: "+ e.getMessage());
            }   
        }
        escrevaNoArquivo(arquivoLogin, b.getId()+";"+this.nome+";"+this.senha+";"+this.cpf+";"+this.idade+";"+this.saldo+";"+"\n");
        // BancoService bancoService = new BancoService();
        // bancoService.adicionarConta(b);
        
        
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
