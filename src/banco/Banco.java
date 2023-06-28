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


public class Banco extends Pessoa{
    private int id;

    boolean validaSaque;
    private int idArmazenado;
    private String loginArmazenado;
    private String senhaArmazenada;
    private String cpfArmazenado;
    private int idadeArmazenada;
    private double saldoArmazenado;
    private static final String arquivoLogin = "logins.txt";
    AuthenticationPage auth = new AuthenticationPage();


    public void setIdArmazenado(int idArmazenado) {
        this.idArmazenado = idArmazenado;
    }

    public void setLoginArmazenado(String loginArmazenado) {
        this.loginArmazenado = loginArmazenado;
    }

    public void setSenhaArmazenada(String senhaArmazenada) {
        this.senhaArmazenada = senhaArmazenada;
    }
    public void setCpfArmazenado(String cpfArmazenado) {
        this.cpfArmazenado = cpfArmazenado;
    }

    public void setIdadeArmazenada(int idadeArmazenada) {
        this.idadeArmazenada = idadeArmazenada;
    }

    public void setSaldoArmazenado(double saldoArmazenado) {
        this.saldoArmazenado = saldoArmazenado;
    }
    
    
    public double getSaldoArmazenado() {
        return saldoArmazenado;
    }



    
    

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

    // public void sacar(double valor, String login, String senha){
    //     if (auth.autenticarUsuario(login, senha)) {
    //         if( super.getSaldo() < valor){
    //             System.out.println("Valor de saque é maior que saldo em conta!");
    //             return;
    //         }else{
    //             System.out.println("Você sacou: R$" + valor);
    //             super.setSaldo(super.getSaldo() - valor);
    //             validaSaque = true;
    //         }
            
    //         super.setSaldo(super.getSaldo() - valor);
    //         try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLogin))) {
    //             List<String> linhas = new ArrayList<>();
    //             String linha;
    //             while ((linha = reader.readLine()) != null) {
    //                 String[] campos = linha.split(";");
    //                 String loginTemp = campos[1];
    //                 String senhaTemp = campos[2];
                    
        
    //                 if (loginTemp.equals(login) && senhaTemp.equals(senha)) {
    //                     setIdArmazenado(Integer.parseInt(campos[0]));
    //                     //System.out.print(campos[0]);
    //                     setLoginArmazenado(campos[1]);
    //                     setSenhaArmazenada(campos[2]);
    //                     setCpfArmazenado(campos[3]);
    //                     setIdadeArmazenada(Integer.parseInt(campos[4]));
    //                     double saldo = Double.parseDouble(campos[5]);
    //                     setSaldoArmazenado(saldo + valor);
    //                     campos[5] = String.valueOf(getSaldoArmazenado());
    //                     linha = String.join(";", campos);
    //                 }
    //                 linhas.add(linha);
    //             }
        
    //             // Write the updated lines back to the file
    //             try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLogin))) {
    //                 for (String line : linhas) {
    //                     writer.write(line);
    //                     writer.newLine();
    //                 }
    //             }
    //         } catch (IOException e) {
    //             JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de login: " + e.getMessage());
    //         }
    //     }
        
        
    //     while(!validaSaque){
            
    //     }
        
    // }
    public boolean depositar(double valor, String login, String senha){
        
        
        if (auth.autenticarUsuario(login, senha)) {
            super.setSaldo(super.getSaldo() + valor);
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
        
                // Write the updated lines back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLogin))) {
                    for (String line : linhas) {
                        writer.write(line);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null, "Depósito executado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de login: " + e.getMessage());
            }
            return true;
        }else{
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
                        
            
                    if (loginTemp.equals(login) && senhaTemp.equals(senha)){
                        if(Double.parseDouble(campos[5]) >= valor){
                            Double tempSaldoDestinatario = Double.parseDouble(campos[5]) - valor;
                            campos[5] = String.valueOf(tempSaldoDestinatario);
                            linha = String.join(";", campos);
                            JOptionPane.showMessageDialog(null, "Transferência executada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            setSaldoArmazenado(tempSaldoDestinatario);
                        }else{
                            JOptionPane.showMessageDialog(null, "Você não tem saldo suficiente", "Alerta" , JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (idTemp == idDestinatario) {
                        //int tempDestinatario = Integer.parseInt(campos[0]);    
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
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de login: " + e.getMessage());
            }
            return true;
        }else{
            return false;
        }

    }

    public String toString(){
        return "\nNome: " + super.getNome() + 
        "\nCPF: " + super.getCpf() + 
        "\nIdade: " + super.getIdade() + 
        "\nSaldo: " + super.getSaldo() + 
        "\nID: " + this.id;
    }

    public static void escrevaNoArquivo(String filename, String data) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);

            bufferedWriter.close();

            JOptionPane.showMessageDialog(null, "Operação executada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}
