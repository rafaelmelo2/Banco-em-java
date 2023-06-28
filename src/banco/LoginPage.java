package banco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class LoginPage {
    private static final String arquivoLogin = "logins.txt";

    private int idArmazenado;
    private String loginArmazenado;
    private String senhaArmazenada;
    private String cpfArmazenado;
    private int idadeArmazenada;
    private double saldoArmazenado;


    public int getIdArmazenado() {
        return idArmazenado;
    }
    public String getLoginArmazenado() {
        return loginArmazenado;
    }
    public String getSenhaArmazenada() {
        return senhaArmazenada;
    }
    public String getCpfArmazenado() {
        return cpfArmazenado;
    }
    public int getIdadeArmazenada() {
        return idadeArmazenada;
    }
    public double getSaldoArmazenado() {
        return saldoArmazenado;
    }


    
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

    public void exibirPaginaLogin() {
        String login = JOptionPane.showInputDialog("Login:");
        String senha = JOptionPane.showInputDialog("Senha:");
        AuthenticationPage auth = new AuthenticationPage();

        if (auth.autenticarUsuario(login, senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLogin))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] campos = linha.split(";");
                    
                    String loginArmazenado = campos[1];
                    String senhaArmazenada = campos[2];
                    if (login.equals(loginArmazenado) && senha.equals(senhaArmazenada)) {
                        this.setIdArmazenado(Integer.parseInt(campos[0]));
                        this.setLoginArmazenado(campos[1]);
                        this.setSenhaArmazenada(campos[2]);
                        this.setCpfArmazenado(campos[3]);
                        this.setIdadeArmazenada(Integer.parseInt(campos[4]));
                        this.setSaldoArmazenado(Double.parseDouble(campos[5]));
                    }
                    
                
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de login: " + e.getMessage());
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Erro ao converter dados: " + e.getMessage());
            }
            

        } else {
            JOptionPane.showMessageDialog(null, "Login ou senha inválidos!");
        }
    }
    

    
}
